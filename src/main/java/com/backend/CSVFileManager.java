package com.backend;

import java.io.*;
import java.util.ArrayList;

public class CSVFileManager {

    static String fName;

    public CSVFileManager() {
    }

    public static ArrayList<String[]> readCSVArrayList(String fileName) throws IOException {
        String thisLine;
        ArrayList<String[]> result = new ArrayList<String[]>();

        FileInputStream fis = new FileInputStream(fileName);
        DataInputStream myInput = new DataInputStream(fis);

        while ((thisLine = myInput.readLine()) != null) {
            String[] line = thisLine.split(",");
            result.add(line);
        }
        return result;
    }


    public static String readCSV() throws IOException {
        String result = "";
        String thisLine;
        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);
        int i = 0;
        while ((thisLine = myInput.readLine()) != null) {
            String splitLine[] = thisLine.split(",");
            for (int j = 0; j < splitLine.length; j++) {
                if (i != 0) {
                    result += (" " + splitLine[j] + " ");
                } else {
                    result += (" <b>" + splitLine[j] + "</b> ");
                }
            }
            result += ("<br>");
            i++;
        }
        return result;
    }



    public static int findInsertIndex(String category) throws IOException {
        int index = 0;
        String thisLine;

        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        while ((thisLine = myInput.readLine()) != null) {
            String strar[] = thisLine.split(",");
            if (strar[0].equals(category)) {
                return index;
            }
            index++;
        }
        return index;
    }

}