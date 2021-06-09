package main.java.Utilities;
/* This class provides static methods to retrieve sequence information from
 * any number of protein data files
 *
 * The files containing the appropriate protein sequences should have one of
 * the following file extensions- .pdb, .fasta, .faa, .gbk
 *
 * @author     Jill Zapoticznyj
 * @author     Adam Bazinet
 * @author     Janine Garnham
 */

//import java.io.;  // input/output package

import main.java.Electro2D.Electro2D;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

public class GenomeFileParser {
	
    private final static String[] aas = {
		"alanine","ALA","A",
		"arginine","ARG","R",
		"asparagine","ASN","N",
		"aspartic acid","ASP","D",
		"asparagine or aspartic acid","ASX","B",
		"cysteine","CYS","C",
		"glutamic acid","GLU","E",
		"glutamine","GLN","Q",
		"glutamine or glutamic acid","GLX","Z",
		"glycine","GLY","G",
		"histidine","HIS","H",
		"isoleucine","ILE","I",
		"leucine","LEU","L",
		"lysine","LYS","K",
		"methionine","MET","M",
		"phenylalanine","PHE","F",
		"proline","PRO","P",
		"serine","SER","S",
		"threonine","THR","T",
		"tryptophan","TRP","W",
		"tyrosine","TYR","Y",
		"valine","VAL","V",
    };
    
    
    final static Hashtable<String, String> aminoConversions = new Hashtable<>(); //holds amino conversions

    static {
    	for (int i = 1; i < aas.length; i++)
    		aminoConversions.put(aas[i++], aas[i++]);
    }

    private final static Hashtable<String, Double> htPI = new Hashtable<>();
    /**
     * This method calculates the pI from inputted sequence
     *
     * @param pro protein sequence
     * @return returns the pI value
     */
    public static double getpI(String pro) {

    	Double d = htPI.get(pro);
    	if (d != null)
    		return d.doubleValue();
    	
        // Calculate charge at a certain pH, starting with a pH of 7
        double pH = 7;

        // pH boundaries used to determine pH where charge is 0
        double lowpH = 0, highpH = 14;

        // Length of protein sequence
        int Plength = pro.length();

        // Total charge on the protein at specified pH, initialize to 1
        double charge = 1;

        // Type of AA: a - acid, b - base, n - neutral
        char type = 'n';

        // pK value of specified AA
        double pK = 0;

        // Calculate total charge at varying pH values until the
        // charge is within 0.005 of 0
        while (Math.abs(charge) >= .005) {

            // Reset charge to 0
            charge = 0;

            // Calculate the charge for each AA until reach end of sequence
            // Add the charge for each AA to the value of total charge
            for (int a = 0; a < Plength; a++) {

                // Determine appropriate pK value for current AA
                // If AA not acid or base, set to neutral
                // and give default pK of 0

                // pK values were obtained from Bjellqvist, B., Basse, B., Olsen, E.,
                // Celis, J., Reference points for comparisons of two-dimensional
                // maps of proteins from different human cell types defined in a
                // pH scale where isoelectric points correlate with polypeptide
                // compositions, Electro1DMain 1994, 15, 529-539.
                switch (pro.charAt(a)) {
                    case 'R':
                        type = 'b';
                        pK = 12;
                        break;
                    case 'D':
                        type = 'a';
                        pK = 4.05;
                        break;
                    case 'C':
                        type = 'a';
                        pK = 9;
                        break;
                    case 'E':
                        type = 'a';
                        pK = 4.75;
                        break;//pK = 4.45; break;
                    case 'H':
                        type = 'b';
                        pK = 5.98;
                        break;
                    case 'K':
                        type = 'b';
                        pK = 10;
                        break;
                    case 'Y':
                        type = 'a';
                        pK = 10;
                        break;
                    default:
                        type = 'n';
                        pK = 0;
                        break;
                }

                // Calculate charge for acids, then add to total charge
                if (type == 'a') {
                    charge += -1 /
                            (1 + Math.pow(10, pK - pH));
                }

                // Calculate charge for bases, then add to total charge
                if (type == 'b') {
                    charge += 1 / (1 + Math.pow(10, pH - pK));
                }
            }

            // Calculate charge on C-terminus and add to total charge
            charge += -1 /
                    (1 + Math.pow(10, 3.2 - pH));

            // Calculate charge on N-terminus and add to total charge
            charge += 1 / (1 + Math.pow(10, pH -/*9.53*/8.2));

            // If total charge is greater than +0.005, then
            // set pH to a higher value and recalculate charge
            if (charge > 0.005) {

                // Set lower pH limit to value of current pH
                lowpH = pH;

                // Set new pH to a value midway between current pH
                // and upper pH limit
                pH = (lowpH + highpH) / 2;
            }

            // If total charge is less than -0.005, then
            // set pH to a lower value and recalculate charge
            if (charge < -0.005) {

                // Set upper pH limit to value of current pH
                highpH = pH;

                // Set new pH to a value midway between current
                // pH and lower pH limit
                pH = (lowpH + highpH) / 2;
            }
        }
        htPI.put(pro, Double.valueOf(pH));
        return pH; // Method returns the pH at which charge is 0 (pI)
    }


    private final static Hashtable<String, Double> htMW = new Hashtable<>();
    /**
     * This method calculates the molecular weight from inputted sequence
     *
     * @param pro protein sequence
     *
     * @return returns the molecular weight
     */
    public static double getMW(String pro) {
    	Double d = htMW.get(pro);
    	if (d != null)
    		return d.doubleValue();
        // Length of protein sequence
        int len = pro.length();

        // Molecular weight of protein
        double weight = 0;//I( String pro ) {


        /* Determine the molecular weight for each AA until reach
       end of sequence. Add the weight for each AA to the value
	   of total weight. Since a water molecule (MW 18) is lost with
	   each bond, the weight given for each AA is its molecular
	   weight minus that of water. The MW of one water molecule is
	   then added to the total molecular weight of the protein sequence.
	   If the AA character does not match one of the 20 accepted
	   abbreviations, then a weight of 0 is given for that AA. */

        for (int f = 0; f < len; f++) {
            switch (pro.charAt(f)) {
                case 'A':
                    weight += 71.0938;
                    break;
                case 'R':
                    weight += 156.2022;
                    break;
                case 'N':
                    weight += 114.1188;
                    break;
                case 'D':
                    weight += 115.1036;
                    break;
                case 'C':
                    weight += 103.1538;
                    break;
                case 'Q':
                    weight += 128.1456;
                    break;
                case 'E':
                    weight += 129.1304;
                    break;
                case 'G':
                    weight += 57.067;
                    break;
                case 'H':
                    weight += 137.156;
                    break;
                case 'I':
                    weight += 113.1742;
                    break;
                case 'L':
                    weight += 113.1742;
                    break;
                case 'K':
                    weight += 128.1888;
                    break;
                case 'M':
                    weight += 131.2074;
                    break;
                case 'F':
                    weight += 147.1914;
                    break;
                case 'P':
                    weight += 97.1316;
                    break;
                case 'S':
                    weight += 87.0932;
                    break;
                case 'T':
                    weight += 101.12;
                    break;
                case 'W':
                    weight += 186.228;
                    break;
                case 'Y':
                    weight += 163.1908;
                    break;
                case 'V':
                    weight += 99.1474;
                    break;
                default:
                    weight += 0;
            }
        }

        // Add molecular weight of water to total weight
        weight += 18;

        // Return the molecular weight of the protein
        htMW.put(pro, Double.valueOf(weight));
        return weight;
    }

    final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * This method parses a .pdb file, extracting sequence information and
     * appropriate descriptor for the sequence.
     *
     * @param theFile   file to retrieve sequence data from
     * @param electro2D reference to calling applet
     * @param data      user-inputted file data
     * @param fileNum the file number
     */
    public static int pdbParse(File f, Electro2D electro2D,
                                String data, int fileNum) {
    	String theFile = f.getName();
        //error in reading file?
        boolean anerror = false;

        //structures to hold file data
        Vector<String> fileData = new Vector<>();  //holds complete file
        Vector<String> compoundInfo = new Vector<>(); //holds COMPND tag data
        Vector<String> sequenceInfo = new Vector<>(); //holds SEQRES tag data
        Vector<String> keywordInfo = new Vector<>(); //holds KEYWDS info
        Vector<String> moleculeTitles = new Vector<>(); //holds mol. titles
        Vector<String> chainData = new Vector<>(); //holds chain designations
        Vector<String> sequences = new Vector<>(); //holds sequence data
        Vector<String> sequenceTitles = new Vector<>(); //holds sequence titles
        Vector<String> functions = new Vector<>(); //holds protein function info
        String proteinFunction = ""; //holds the protein function
        String headerLine = ""; //holds the information from the HEADER line
        double maxPi = -1;
        double minPi = -1;
        double maxMW = -1;
        double minMW = -1;

        //file reading variable
        BufferedReader in = null;

        //structures to hold calculated MW and pI
        Vector<String> molecularWeights = new Vector<>();
        Vector<String> piValues = new Vector<>();

        //temporary variables used in parsing
        String temp = "";
        String tempLabel = "";
        String proteinID = "";
        String chainValue = "";
        String totalChain = "";
        boolean hasMoleculeTag = true;
        boolean foundChain = false;
//        boolean noChainData = false;
        boolean hasECnumber = false;
        int foundIndex = 0;

        if (data == null || data.equals("")) {  //read from server
            try {
//                File f = new File("data" + File.separator + theFile);
                in = new BufferedReader
                        (new InputStreamReader(f.toURI().toURL().openStream()));
                String temp1;

                while ((temp1 = in.readLine()) != null) {

                    fileData.addElement(temp1);
                }
            } catch (Exception e) {
                MessageFrame error = new MessageFrame();
                error.setMessage("Error reading from file.  Be sure you " +
                        "typed the name correctly.");
                error.setVisible(true);
                anerror = true;
                System.err.println("Exception was: " + e);
            }
        } else { //use data from textarea
            StringTokenizer fileSplitter = new StringTokenizer(data, "\r\n");
            while (fileSplitter.hasMoreTokens()) {
                fileData.addElement(fileSplitter.nextToken());
            }
        }
        if (anerror == false) {

            //assign protein ID based on file descriptor
            proteinID = theFile.substring(0, theFile.indexOf("."));

            //separate compound info and sequence info
            for (int x = 0; x < fileData.size(); x++) {
                temp = fileData.elementAt(x);
                tempLabel = temp.substring(0, 6);

                if (tempLabel.equals("COMPND")) {
                    compoundInfo.addElement(temp);
                } else if (tempLabel.equals("SEQRES")) {
                    sequenceInfo.addElement(temp);
                }
                //if the label is KEYWDS store this in the keyword Info vector
                else if (tempLabel.equals("KEYWDS")) {
                    keywordInfo.addElement(temp);
                }
                //if the label is HEADER, store as the headerLine
                else if (tempLabel.equals("HEADER")) {
                    headerLine = temp;
                }
            }

            //determine whether or not the protein is an enzyme
            for (int i = 0; i < compoundInfo.size(); i++) {
                //if the COMPND section of the file listed an EC number,
                //the protein is an enzyme.  Store the information accordingly
                if ((compoundInfo.elementAt(i)).indexOf("EC\u003A") != -1) {
                    hasECnumber = true;
                    temp = compoundInfo.elementAt(i);
                    temp = temp.substring(temp.indexOf("EC:") + 4, temp.indexOf(
                            "\u003B") + 1);
                    proteinFunction = proteinFunction + temp;
                }
            }
            if (hasECnumber) {
                proteinFunction = "Enzyme " + proteinFunction;
                int index = proteinFunction.indexOf(",");
                if (index != -1) {
                    proteinFunction = proteinFunction.replace(',', '\u003B');
                }
            }

            //determine the number of molecules
            for (int x = 0; x < compoundInfo.size(); x++) {
                temp = compoundInfo.elementAt(x);
                if (temp.indexOf("MOLECULE:") != -1) {
                    temp = temp.substring(temp.indexOf("MOLECULE:") + 10);
                    temp = temp.trim();
                    moleculeTitles.addElement(temp);
                }
            }

            //if moleculeTitles is empty, pull main title from concatenating all
            //COMPND tags
            if (moleculeTitles.size() == 0) {

                totalChain = "";
                hasMoleculeTag = false;
                for (int x = 0; x < compoundInfo.size(); x++) {
                    temp = compoundInfo.elementAt(x);
                    totalChain += (compoundInfo.elementAt(x)).substring
                            (temp.indexOf("COMPND") + 10, temp.indexOf(proteinID));
                }

                //remove excess whitespace
                totalChain = totalChain.trim();
                for (int x = 0; x < totalChain.length(); x++) {
                    tempLabel = totalChain.substring(x, x + 1);
                    if (tempLabel.equals(" ")) {
                        tempLabel = totalChain.substring(x + 1, x + 2);
                        if (tempLabel.equals(" ")) {
                            //remove this extra space
                            totalChain = totalChain.substring(0, x + 1) +
                                    totalChain.substring(x + 2);

                            x -= 1;
                        }
                    }
                }

                //add temp to moleculeTitles
                moleculeTitles.addElement(totalChain);

            } else { //pull chain data

                int counter = 0; //sequenceTitle counter

                for (int x = 0; x < compoundInfo.size(); x++) {
                    temp = compoundInfo.elementAt(x);
                    foundIndex = temp.indexOf("CHAIN:");
                    if (foundIndex != -1) {
                        //isolate first letter or number
                        tempLabel = temp.substring(foundIndex + 7, foundIndex + 8);
                        chainData.addElement(tempLabel);

                        sequenceTitles.addElement(moleculeTitles.
                                elementAt(counter));

                        while (temp.charAt(foundIndex + 8) == ',') {
                            temp = temp.substring(0, foundIndex + 7) +
                                    temp.substring(foundIndex + 10);

                            tempLabel = temp.substring(foundIndex + 7,
                                    foundIndex + 8);
                            chainData.addElement(tempLabel);
                            sequenceTitles.addElement(moleculeTitles.
                                    elementAt(counter));
                        }
                        counter++;
                    }
                }
            }

            //extract sequences

            //handle special case where there is no chain data
            temp = sequenceInfo.elementAt(0);
            tempLabel = temp.substring(11, 12);
            if (tempLabel.equals(" ")) {
//                noChainData = true;
                temp = "";
                //must be only one chain
                for (int x = 0; x < sequenceInfo.size(); x++) {
                    //may include whitespace
                    temp += (sequenceInfo.elementAt(x)).substring(19, 70);
                    temp += " ";
                }

                //add sequence
                if (!(temp = temp.trim()).equals("")) {
                    sequences.addElement(temp);
                    sequenceTitles.addElement(moleculeTitles.elementAt(0));
                }

            } else if (hasMoleculeTag == false) {

	    /* handle special case where chains are lettered, but there
	       was no chain tag to tell which labels to look for */

                //cycle through the alphabet
                for (int whichChain = 0; whichChain < 26; whichChain++) {
                    //find the chain we're looking for
                    chainValue = alphabet.substring(whichChain, whichChain + 1);
                    totalChain = "";
                    foundChain = false;
                    for (int x = 0; x < sequenceInfo.size(); x++) {
                        temp = sequenceInfo.elementAt(x);
                        tempLabel = temp.substring(11, 12);
                        if (tempLabel.equals(chainValue)) {
                            foundChain = true;
                            totalChain += (sequenceInfo.elementAt(x)).
                                    substring(19, 70);
                            totalChain += " ";
                        }
                    }

                    //add chain data
                    if (foundChain == true) {
                        chainData.addElement(alphabet.substring
                                (whichChain, whichChain + 1));
                        //should only be one
                        sequenceTitles.addElement
                                (moleculeTitles.elementAt(0));
                    }
                    //add sequence
                    if (!(totalChain = totalChain.trim()).equals("")) {
                        sequences.addElement(totalChain);
                    }
                }

            } else { //"normal" case, has predefined chain data

                for (int whichChain = 0; whichChain < chainData.size(); whichChain++) {
                    //find which chain we're looking for
                    chainValue = chainData.elementAt(whichChain);
                    totalChain = "";
                    for (int x = 0; x < sequenceInfo.size(); x++) {
                        temp = sequenceInfo.elementAt(x);
                        tempLabel = temp.substring(11, 12);
                        if (tempLabel.equals(chainValue)) {
                            totalChain += (sequenceInfo.elementAt(x)).
                                    substring(19, 70);
                            totalChain += " ";
                        }
                    }

                    //add sequence
                    if (!(totalChain = totalChain.trim()).equals("")) {
                        sequences.addElement(totalChain);
                    }
                }
            }

//            try {
//                //read in aminoconversiontable
//            	// BH this file does not exist.
//                f = new File("./aminoconversiontable.txt");
//                in = new BufferedReader(new InputStreamReader(
//                        f.toURL().openStream()));
//
//                while ((temp = in.readLine()) != null) {
//                    aminoConversions.put(temp.substring(0, 3), temp.substring(4, 5));
//                }
//            } catch (Exception e) {
//                System.err.println("Exception was: " + e);
//            }

            //convert sequence data
            for (int i = 0; i < sequences.size(); i++) {
                totalChain = sequences.elementAt(i);
                int len = totalChain.length();
                byte[] c = new byte[(len + 1)/4];
                int pt = 0;
                for (int j = 0; j < len; j += 4) {
                	String aaa = totalChain.substring(j, j + 3).toUpperCase();
                	String s = aminoConversions.get(aaa);
                    if (s != null)
                    	c[pt++] = (byte)s.charAt(0);
                    else
                    	System.err.println("GenomeFileParser could not convert " + aaa + " to X");
                }
                sequences.setElementAt(new String(c, 0, pt), i);
            }

            //temp data storage
            double mW = 0.0;
            double pI = 0.0;
            String mWstring = "";
            String pIstring = "";

            // For each sequence in the file, determine the pI and MW
            for (int x = 0; x < sequences.size(); x++) {

                temp = sequences.elementAt(x);

                // Determine the MW from the getMW method
                mW = getMW(temp);
                // Determine the pI from the getpI method
                pI = getpI(temp);
                // Convert the MW from a double to a string data type
                mWstring = String.valueOf(mW);
                // Set format to two decimal places
                if (mWstring.length() >
                        mWstring.indexOf('.') + 3) {
                    mWstring = mWstring.substring(0, mWstring.indexOf('.') + 3);
                }

                double doubleValue = Double.parseDouble(mWstring);
                if (minMW == -1 || doubleValue <= minMW) {
                    minMW = doubleValue;
                }
                if (maxMW == -1 || doubleValue >= maxMW) {
                    maxMW = doubleValue;
                }

                molecularWeights.addElement(mWstring);

                // Convert the pI from a double to a string data type
                pIstring = String.valueOf(pI);
                // Set format to two decimal places
                if (pIstring.length() > pIstring.indexOf('.') + 3) {
                    pIstring = pIstring.substring(0, pIstring.indexOf('.') + 3);
                }

                doubleValue = Double.parseDouble(pIstring);
                if (minPi == -1 || doubleValue <= minPi) {
                    minPi = doubleValue;
                }
                if (maxPi == -1 || doubleValue >= maxPi) {
                    maxPi = doubleValue;
                }

                piValues.addElement(pIstring);

            }

            //if the protein was an enzyme, store the EC number as the function
            if (hasECnumber) {
                for (int fcn = 0; fcn < sequenceTitles.size(); fcn++) {
                    functions.addElement(proteinFunction);
                }
            }
            //otherwise if there was a KEYWDS section, store the data from the
            //section as the protien function
            else if (keywordInfo.size() > 0) {
                for (int fcn = 0; fcn < keywordInfo.size(); fcn++) {
                    if (fcn == 0) {
                        temp = (keywordInfo.elementAt(fcn)).substring(10);
                        //while( (temp.substring( temp.length() - 1 )).equals(" ")){
                        temp.trim();
                        // }
                    } else {
                        temp = temp +
                                (keywordInfo.elementAt(fcn)).substring(10);
                        //  while((temp.substring(temp.length() - 1)).equals(" ")){
                        temp.trim();
                        //   }
                    }
                }
                proteinFunction = temp;
                for (int fcn = 0; fcn < sequenceTitles.size(); fcn++) {
                    functions.addElement(proteinFunction);
                }
            }
            //otherwise, if there was a header line, store the protein function
            //as the HEADER line
            else if (!headerLine.equals("")) {
                headerLine = headerLine.substring(10, 50);
                headerLine.trim();
                for (int fcn = 0; fcn < sequenceTitles.size(); fcn++) {
                    functions.addElement(headerLine);
                }
            }
            //otherwise, store the function as a null string ("")
            else {
                for (int fcn = 0; fcn < sequenceTitles.size(); fcn++) {
                    functions.addElement(proteinFunction);
                }
            }

            //set vectors in calling applet based on results of this file read
            if (fileNum == 1) {
                electro2D.setSequences(sequences);
                electro2D.setSequenceTitles(sequenceTitles);
                electro2D.setMolecularWeights(molecularWeights);
                electro2D.setPiValues(piValues);
                electro2D.setFunctionValues(functions);
                // store the file name in Electro2D.Electro2D
                electro2D.setLastFileLoaded(theFile);
                electro2D.setMaxAndMinVals(maxMW, minMW, maxPi, minPi);
            } else if (fileNum == 2) {
                electro2D.setSequences2(sequences);
                electro2D.setSequenceTitles2(sequenceTitles);
                electro2D.setMolecularWeights2(molecularWeights);
                electro2D.setPiValues2(piValues);
                electro2D.setFunctionValues2(functions);
                // store the file name in Electro2D.Electro2D
                electro2D.setLastFileLoaded(theFile);
                electro2D.setMaxAndMinVals(maxMW, minMW, maxPi, minPi);
            }

        }
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
        }

        Preprocessor.process(electro2D);
        return sequences.size();
    }

    /**
     * This method parses a FASTA file, extracting sequence information and
     * appropriate descriptor for the sequence.
     *
     * @param fileNum the file number
     * @param theFile the file
     * @param electro2D electro2D reference
     * @param data the data
     */
    public static int fastaParse(File f, Electro2D electro2D,
                                  String data, int fileNum) {
    	String theFile = f.getName();
        //was there an error in file reading?
        boolean anerror = false;

        //structures to hold file data
        Vector<String> fileData = new Vector<>();  //holds complete file
        Vector<String> chainData = new Vector<>(); //holds chain designations
        Vector<String> sequences = new Vector<>(); //holds sequence data
        Vector<String> sequenceTitles = new Vector<>(); //holds sequence titles
        Vector<String> functions = new Vector<>(); //holds the protein functions
        double maxMW = -1;
        double minMW = -1;
        double maxPi = -1;
        double minPi = -1;
        double doubleVal;

        //file reading variable
        BufferedReader in = null;

        //structures to hold calculated MW and pI
        Vector<String> molecularWeights = new Vector<>();
        Vector<String> piValues = new Vector<>();

        //temporary variables used in parsing
        String temp = "";
        String totalChain = "";
//        String proteinID = "";
//        boolean foundChain = false;
//        boolean noChainData = false;
//        int foundIndex = 0;

        if (data == null || data.equals("")) {  //read from server

            try {
                in = new BufferedReader
                        (new InputStreamReader(f.toURI().toURL().openStream()));
                String temp1;

                while ((temp1 = in.readLine()) != null) {

                    fileData.addElement(temp1);
                }
            } catch (Exception e) {
                MessageFrame error = new MessageFrame();
                error.setMessage("Error reading from file.  Be sure you " +
                        "typed the name correctly.");
                error.setVisible(true);
                anerror = true;
                System.err.println("Exception was: " + e);
            }
        } else { //use data from text area
            StringTokenizer fileSplitter = new StringTokenizer(data, "\r\n");
            while (fileSplitter.hasMoreTokens()) {
                fileData.addElement(fileSplitter.nextToken());
            }
        }

        if (anerror == false) {

            //assign protein ID based on file descriptor
//            proteinID = theFile.substring(0, theFile.indexOf("."));

            for (int x = 0; x < fileData.size(); x++) {

                temp = fileData.elementAt(x);
                if (temp.substring(0, 1).equals(">")) { //we know it's a header line

                    //if it's not the first line in the file
                    if (x > 0) {
                        //add the previous sequence
                        sequences.addElement(totalChain);
                    }

                    //add chain data
                    if (temp.indexOf(":") != -1) {

                        if (temp.indexOf("|") != -1 && temp.indexOf("|") <
                                temp.indexOf(":")) {
                            //this is most likely not chain data, so don't add
                        } else {
                            chainData.addElement(temp.substring
                                    (temp.indexOf(":") +
                                            1, temp.indexOf(":") + 2));
                        }
                    }

                    //add sequence title

                    // Edited 6/12/03 - Need to include whole line to
                    // account for possibility of different proteins with
                    // same name but different data.

                    //just add the whole line
                    sequenceTitles.addElement(temp.substring
                            (temp.indexOf(">") + 1));
                    //the function of the protein is the same as its title,
                    //minus the identification numbers
                    functions.addElement(temp.substring(temp.lastIndexOf("|")
                            + 1));


                    totalChain = "";

                } else {
                    //must be part of a sequence
                    totalChain += temp;

                    if (x == fileData.size() - 1) {
                        //add this final sequence
                        sequences.addElement(totalChain);
                    }
                }
            }

            //temp data storage
            double mW = 0.0;
            double pI = 0.0;
            String mWstring = "";
            String pIstring = "";

            // For each sequence in the file, determine the pI and MW
            for (int x = 0; x < sequences.size(); x++) {

                temp = sequences.elementAt(x);

                // Determine the MW from the getMW method
                mW = getMW(temp);
                // Determine the pI from the getpI method
                pI = getpI(temp);
                // Convert the MW from a double to a string data type
                mWstring = String.valueOf(mW);
                // Set format to two decimal places
                if (mWstring.length() >
                        mWstring.indexOf('.') + 3) {

                    mWstring = mWstring.substring(0, mWstring.indexOf('.') + 3);
                }

                doubleVal = Double.parseDouble(mWstring);
                if (minMW == -1 || doubleVal <= minMW) {
                    minMW = doubleVal;
                }
                if (maxMW == -1 || doubleVal >= maxMW) {
                    maxMW = doubleVal;
                }

                molecularWeights.addElement(mWstring);

                // Convert the pI from a double to a string data type
                pIstring = String.valueOf(pI);
                // Set format to two decimal places
                if (pIstring.length() > pIstring.indexOf('.') + 3) {
                    pIstring = pIstring.substring(0, pIstring.indexOf('.') + 3);
                }

                doubleVal = Double.parseDouble(pIstring);
                if (minPi == -1 || doubleVal <= minPi) {
                    minPi = doubleVal;
                }
                if (maxPi == -1 || doubleVal >= maxPi) {
                    maxPi = doubleVal;
                }

                piValues.addElement(pIstring);

            }

            //set vectors in calling applet based on results of this file read
            if (fileNum == 1) {
                electro2D.setSequences(sequences);
                electro2D.setSequenceTitles(sequenceTitles);
                electro2D.setMolecularWeights(molecularWeights);
                electro2D.setPiValues(piValues);
                electro2D.setFunctionValues(functions);
                // store the file name in Electro2D.Electro2D
                electro2D.setLastFileLoaded(theFile);
                electro2D.setMaxAndMinVals(maxMW, minMW, maxPi, minPi);
            } else if (fileNum == 2) {
                electro2D.setSequences2(sequences);
                electro2D.setSequenceTitles2(sequenceTitles);
                electro2D.setMolecularWeights2(molecularWeights);
                electro2D.setPiValues2(piValues);
                electro2D.setFunctionValues2(functions);
                // store the file name in Electro2D.Electro2D
                electro2D.setLastFileLoaded(theFile);
                electro2D.setMaxAndMinVals(maxMW, minMW, maxPi, minPi);
            }
        }
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
        }

        Preprocessor.process(electro2D);
        return sequences.size();
    }

    /**
     * This method parses a .gbk file, extracting sequence information and
     * appropriate descriptor for the sequence.
     *
     * @return n on success, 0 on error
     *
     * @param data the data
     * @param fileNum the file number
     * @param electro2D electro2D reference
     * @param theFile the file
     */
    public static int gbkParse(File f, Electro2D electro2D,
                               String data, int fileNum) {

    	String theFile = f.getName();
        //was there an error in file reading?
        boolean anerror = false;

        //structures to hold file data
        //String organismID = "";
        //String organismTitle = "";
        Vector<String> fileData = new Vector<>();  //holds complete file
        Vector<String> sequences = new Vector<>(); //holds sequence data
        Vector<String> sequenceTitles = new Vector<>(); //holds sequence titles
        Vector<String> functions = new Vector<>(); //holds protein functions
        double doubleVal;
        double minMW = -1;
        double maxMW = -1;
        double maxPi = -1;
        double minPi = -1;

        //file reading variable
        BufferedReader in = null;

        //structures to hold calculated MW and pI
        Vector<String> molecularWeights = new Vector<>();
        Vector<String> piValues = new Vector<>();

        //temporary variables used in parsing
        String temp = "";
        String totalChain = "";
        boolean foundTranslation = false;
        boolean foundGene = false;
        String function = "";
//        boolean hadFunctionLine = false;
//        boolean hadEnzymeClassNumber = false;

        if (data == null || data.equals("")) {  //read from server
            try {
//                File f = new File("data" + File.separator + theFile);
                in = new BufferedReader
                        (new InputStreamReader((/*electro2D.getCodeBase()*/
						   /* "data/" + theFile*/
                                f.toURI().toURL()).openStream()));
                String temp1;

                while ((temp1 = in.readLine()) != null) {

                    fileData.addElement(temp1);
                }
            } catch (Exception e) {
                MessageFrame error = new MessageFrame();
                error.setMessage("Error reading from file.  Be sure you " +
                        "typed the name correctly.");
                error.setVisible(true);

                anerror = true;
                System.err.println("Exception was: " + e);
            }
        } else { //use data from textarea
            StringTokenizer fileSplitter = new StringTokenizer(data, "\r\n");
            while (fileSplitter.hasMoreTokens()) {
                fileData.addElement(fileSplitter.nextToken());
            }
        }

        if (anerror == false) {

            //assign organism ID based on file descriptor
            //organismID = theFile.substring(0, theFile.indexOf("."));

            for (int x = 0; x < fileData.size(); x++) {

                temp = fileData.elementAt(x);

                if (temp.length() >= 10 &&
                        temp.substring(0, 10).equals("DEFINITION")) {
                    //assign organism title
                    //organismTitle = temp.substring(12);
                }
                //look for a title for this sequence
                if (temp.length() >= 9 && temp.substring(5, 9).equals("gene")) {

                    //check to see if there was a translation for previous gene;
                    //if not, replace old gene with this gene
                    if (foundTranslation == false && sequenceTitles.size() >= 1) {
                        sequenceTitles.removeElementAt(sequenceTitles.size() - 1);
                    }
                    //add sequence title
                    while (foundGene == false) {
                        if (temp.length() >= 26 &&
                                temp.substring(21, 26).equals("/gene")) {
                            foundGene = true;
                        } else {
                            x++;
                            if (x < fileData.size()) {
                                temp = fileData.elementAt(x);
                            } else {
                                return 0; //error code
                            }
                        }
                    }
                    sequenceTitles.addElement(temp.substring
                            (28, temp.lastIndexOf("\"")));
                    foundGene = false;
                    foundTranslation = false;
                }
                //here is where the sequence data should be
                //as well as information about the protein function
                if (temp.length() >= 8 && temp.substring(5, 8).equals("CDS")) {

                    while (foundTranslation == false) {
                        if (temp.length() >= 33 &&
                                temp.substring(21, 33).equals("/translation")) {
                            foundTranslation = true;
                        }
                        //if the line contains the enzyme commission number
                        //the protein function is an Enzyme
                        else if (temp.length() >= 33 &&
                                temp.substring(21, 31).equals("/EC_number")) {
                            if (function.equals("")) {
                                temp = temp.substring(33, temp.lastIndexOf("\""));
                                function = "Enzyme " + temp + "\u003B";
                            } else {
                                temp = temp.substring(33, temp.lastIndexOf("\""));
                                function = function + " " + temp + "\u003B";
                            }
                            x++;
                            if (x < fileData.size()) {
                                temp = fileData.elementAt(x);
                            } else {
                                return 0; //error code
                            }
                        }
                        //if the line is the function line then its contents are
                        //included in the protein function
                        else if (temp.length() >= 30 &&
                                temp.substring(21, 30).equals("/function")) {
                            //hadFunctionLine = true;
                            if (temp.substring(32).lastIndexOf("\"") != -1) {
                                temp = temp.substring(32);
                                function = function + " " + temp.substring(0,
                                        temp.lastIndexOf("\"")) + ".";
                            } else {
                                temp = temp.substring(32);
                                function = function + " " + temp;
                                x = x + 1; //jump to next line
                                temp = fileData.elementAt(x);
                                while (temp.lastIndexOf("\"") == -1) {
                                    function = function + " " +
                                            temp.substring(21);
                                    x = x + 1;
                                    if (x < fileData.size()) {
                                        temp = fileData.elementAt(x);
                                    } else {
                                        return 0; //error code
                                    }
                                }
                                function = function + temp.substring(21,
                                        temp.lastIndexOf("\"")) + ".";
                            }

                            x++;
                            if (x < fileData.size()) {
                                temp = fileData.elementAt(x);
                            } else {
                                return 0; //error code
                            }
                        }
                        //if the line is the section containing notes about the
                        //protein, its contents are included in the function
                        else if (temp.length() >= 27 &&
                                temp.substring(21, 26).equals("/note")) {
                            if ((function.indexOf("unknown.") != -1) ||
                                    function.equals("")) {
                                //hadFunctionLine = false;
                                function = "";
                            }
                            if (temp.substring(28).lastIndexOf("\"") != -1) {
                                temp = temp.substring(28);
                                function = function + " " + temp.substring(0,
                                        temp.lastIndexOf("\"")) + ".";
                            } else {
                                temp = temp.substring(28);
                                function = function + " " + temp;
                                x = x + 1;
                                temp = fileData.elementAt(x);
                                while (temp.lastIndexOf("\"") == -1) {
                                    function = function + " " +
                                            temp.substring(21);
                                    x = x + 1;
                                    if (x < fileData.size()) {
                                        temp = fileData.elementAt(x);
                                    } else {
                                        return 0; //error code
                                    }
                                }
                                function = function + " " + temp.substring(21,
                                        temp.lastIndexOf("\"")) + ".";
                            }

                            x++;
                            if (x < fileData.size()) {
                                temp = fileData.elementAt(x);
                            } else {
                                return 0; //error code
                            }
                        }
                        //if the line is the product information of the protein,
                        //its contents are included in the function
                        else if (temp.length() >= 30 &&
                                temp.substring(21, 29).equals("/product")) {
                            if (temp.substring(31).lastIndexOf("\"") != -1) {
                                temp = temp.substring(31);
                                function = function + " " + temp.substring(0,
                                        temp.lastIndexOf("\"")) + ".";
                            } else {
                                temp = temp.substring(31);
                                function = function + " " + temp;
                                x = x + 1;
                                temp = fileData.elementAt(x);
                                while (temp.lastIndexOf("\"") == -1) {
                                    function = function + " " +
                                            temp.substring(21);
                                    x = x + 1;
                                    if (x < fileData.size()) {
                                        temp = fileData.elementAt(x);
                                    } else {
                                        return 0;
                                    }
                                }
                                function = function + " " + temp.substring(21,
                                        temp.lastIndexOf("\"")) + ".";
                            }

                            x++;
                            if (x < fileData.size()) {
                                temp = fileData.elementAt(x);
                            } else {
                                return 0; //error code
                            }
                        }

                        //otherwise move to next line
                        else {
                            x++;
                            if (x < fileData.size()) {
                                temp = fileData.elementAt(x);
                            } else {
                                System.err.println("Error! Protein lacking " +
                                        "sequence.");
                                return 0; //error code
                            }
                        }
                    }
                    if (temp.length() >= 35 && temp.substring(35).lastIndexOf("\"") != -1) {
                        temp = temp.substring(35);
                        //add first line
                        totalChain += temp.substring(0, temp.lastIndexOf("\""));
                    } else /*if( temp.length() >= 35 )*/ {

                        //add first line
                        totalChain += temp.substring(35);
                        x++; //jump to next line
                        temp = fileData.elementAt(x);

                        //add remaining lines
                        while (temp.lastIndexOf("\"") == -1) {

                            totalChain += temp.substring(21);
                            x++;
                            if (x < fileData.size()) {
                                temp = fileData.elementAt(x);
                            } else {
                                return 0;
                            }
                        }

                        //add last line
                        totalChain += temp.substring(21, temp.lastIndexOf("\""));
                    }

                    sequences.addElement(totalChain); //add the sequence
                    totalChain = ""; //reset for next chain
                    functions.addElement(function); //add the function
                    function = ""; //reset for next chain
//                    hadEnzymeClassNumber = false;
//                    hadFunctionLine = false;
                }
            }

            //temp data storage
            double mW = 0.0;
            double pI = 0.0;
            String mWstring = "";
            String pIstring = "";

            // For each sequence in the file, determine the pI and MW
            for (int x = 0; x < sequences.size(); x++) {

                temp = sequences.elementAt(x);

                // Determine the MW from the getMW method
                mW = getMW(temp);
                // Determine the pI from the getpI method
                pI = getpI(temp);
                // Convert the MW from a double to a string data type
                mWstring = String.valueOf(mW);
                // Set format to two decimal places
                if (mWstring.length() >
                        mWstring.indexOf('.') + 3) {

                    mWstring = mWstring.substring(0, mWstring.indexOf('.') + 3);
                }

                doubleVal = Double.parseDouble(mWstring);
                if (minMW == -1 || doubleVal <= minMW) {
                    minMW = doubleVal;
                }
                if (maxMW == -1 || doubleVal >= maxMW) {
                    maxMW = doubleVal;
                }

                molecularWeights.addElement(mWstring);

                // Convert the pI from a double to a string data type
                pIstring = String.valueOf(pI);
                // Set format to two decimal places
                if (pIstring.length() > pIstring.indexOf('.') + 3) {
                    pIstring = pIstring.substring(0, pIstring.indexOf('.') + 3);
                }

                doubleVal = Double.parseDouble(pIstring);
                if (minPi == -1 || doubleVal <= minPi) {
                    minPi = doubleVal;
                }
                if (maxPi == -1 || doubleVal >= maxPi) {
                    maxPi = doubleVal;
                }

                piValues.addElement(pIstring);

            }
            //set vectors in calling applet based on results of this file read
            if (fileNum == 1) {
                electro2D.setSequences(sequences);
                electro2D.setSequenceTitles(sequenceTitles);
                electro2D.setMolecularWeights(molecularWeights);
                electro2D.setPiValues(piValues);
                electro2D.setFunctionValues(functions);
                // store the file name in Electro2D.Electro2D
                electro2D.setLastFileLoaded(theFile);
                electro2D.setMaxAndMinVals(maxMW, minMW, maxPi, minPi);
            } else if (fileNum == 2) {
                electro2D.setSequences2(sequences);
                electro2D.setSequenceTitles2(sequenceTitles);
                electro2D.setMolecularWeights2(molecularWeights);
                electro2D.setPiValues2(piValues);
                electro2D.setFunctionValues2(functions);
                // store the file name in Electro2D.Electro2D
                electro2D.setLastFileLoaded(theFile);
                electro2D.setMaxAndMinVals(maxMW, minMW, maxPi, minPi);
            }
        }
        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException e) {
        }

        // store the file name in Electro2D.Electro2D
        electro2D.setLastFileLoaded(theFile);

        Preprocessor.process(electro2D);
        return sequences.size();
    }

    /**
     * This method parses a .e2d file, extracting sequence information and
     * appropriate descriptor for the sequence.
     *
     * @param data the data
     * @param electro2D electro2D reference
     * @param fileNum the file number
     * @param theFile the file to be parsed
     * @return n on success, 0 on error
     */
    public static int e2dParse(File f, Electro2D electro2D, String data, int fileNum) {
        //File f = new File("data" + File.separator + theFile);
        BufferedReader in = null;
        try {
            in = new BufferedReader(new InputStreamReader(f.toURI().toURL().openStream()));

        } catch (Exception e) {
            System.err.println("Error reading from file.  Double-check the file name and try again.");
        }
        return Preprocessor.readFromFile(in, electro2D, fileNum);
        //	electro2D.setLastFileLoaded( theFile );
    }

    

} 









