package com.backend;

import java.io.*;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static com.backend.ProductsManager.readCSVArrayList;

public class RecordsManager extends CSVFileManager{
    static String fName = "/Users/michelleyang/IdeaProjects/webAppPractice/SalesRecord.csv";
    public RecordsManager() {
//        super("/Users/michelleyang/IdeaProjects/webAppPractice/ProductsList.csv");
    }

    public static ArrayList<String[]> readCSVArrayList() throws IOException {
        String thisLine;
        ArrayList<String[]> result = new ArrayList<String[]>();

        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        while ((thisLine = myInput.readLine()) != null) {
            String[] line = thisLine.split(",");
            result.add(line);
        }
        return result;
    }

    //New column when there is a new product added to the system
    public static void addColumn(String pName) throws IOException {
        ArrayList<String[]> lines = readCSVArrayList(); //reads all the records in SalesRecords

        // Add the new column to each line
        for (int i = 0; i < lines.size(); i++) {
            String[] row = lines.get(i);
            // Create a new array with one additional column
            String[] newRow = new String[row.length + 1];
            System.arraycopy(row, 0, newRow, 0, row.length);

            // Add the new column value
            if (i == 0) {
                newRow[row.length] = pName;
            } else {
                newRow[row.length] = "0"; // Default zero sales
            }

            lines.set(i, newRow);
        }

        writeToFile(lines);

        // Write the updated content back to the CSV file
//        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fName))) {
//            for (String[] updatedRow : lines) {
//                StringBuilder sb = new StringBuilder();
//                for (int j = 0; j < updatedRow.length; j++) {
//                    if (j > 0) {
//                        sb.append(",");
//                    }
//                    sb.append(updatedRow[j]);
//                }
//                bw.write(sb.toString());
//                bw.newLine();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void removeColumn(String code) throws IOException {
        ArrayList<String[]> lines = readCSVArrayList();

        if (lines.isEmpty()) {
//            System.out.println("CSV file is empty.");
            return;
        }

        // Find the index of the column to remove
        String[] header = lines.get(0);
        int columnIndex = -1;
        for (int i = 0; i < header.length; i++) {
            if (header[i].equals(code)) {
                columnIndex = i;
                break;
            }
        }

        if (columnIndex == -1) {
//            System.out.println("Column with code '" + code + "' not found.");
            return;
        }

        // Remove the column from each line
        for (int i = 0; i < lines.size(); i++) {
            String[] row = lines.get(i);
            String[] newRow = new String[row.length - 1];

            // Copy all columns except the one to remove
//            System.arraycopy(row, 0, newRow, 0, columnIndex);
//            System.arraycopy(row, columnIndex + 1, newRow, columnIndex, row.length - columnIndex - 1);

            lines.set(i, newRow);
        }

        // Write the updated content back to the CSV file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fName))) {
            for (String[] updatedRow : lines) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < updatedRow.length; j++) {
                    if (j > 0) {
                        sb.append(",");
                    }
                    sb.append(updatedRow[j]);
                }
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static double getRevenue(LocalDate day) throws IOException{
        ArrayList<String[]> salesRecords = readCSVArrayList();
        String[] productsList = salesRecords.get(0);

        String[] record = null;
        for (String[] records : salesRecords) {
            if (String.valueOf(day).equals(records[0])) { // Check if the date matches
                record = records;
                break;
            }
        }

        if (record == null) { //no revenue that day
            return 0.0;
        }

        double totalRevenue = 0.0;

        // Iterate through the quantities (starting from index 1)
        for (int i = 1; i < record.length; i++) {
            int quantity = Integer.parseInt(record[i]); // Parse the quantity sold
//            System.out.println("Quantity is " + quantity);
            String[] p = ProductsManager.findProduct(productsList[i]);
//            System.out.println("Product is " + p[2]);
            double price = Double.parseDouble(p[3]); // Get the price of the product (index adjusted for products array)
            totalRevenue += (quantity * price); // Add to the total revenue
        }

        return totalRevenue;
    }

    public static void sellProduct(String bcCode, LocalDate date) throws IOException {

        if (ProductsManager.productExists(bcCode)) {
//        System.out.println("in records sell product method");
//        System.out.println(date);

//        System.out.println("a");
            ArrayList<String[]> records = readCSVArrayList();
//        System.out.println("b");
            int index = 0; //index of the product in the CSV

//            System.out.println(Arrays.toString(records.get(0)));

            for (String column : records.get(0)) {
//                System.out.println("current column is " + column);
                if (column.equals(bcCode)) {
//                    System.out.println("c");
                    break;
                }
                index++;
//                System.out.println("index is" + index);
            }

            String[] targetRecord = null;

            boolean found = false;
            for (String[] record : records) {
                if (record[0].equals(date.toString())) { //if a record already exists
                    targetRecord = record;
                    found = true;
//                    System.out.println("e");
                    break;
                }
            }

            if (!found) {
                targetRecord = new String[records.get(0).length]; //make new record if not existing
                targetRecord[0] = date.toString();
                for (int i = 1; i < targetRecord.length; i++) {
                    targetRecord[i] = "0";
                }
                records.add(targetRecord);
            }

//        System.out.println(Arrays.toString(targetRecord));

            int sales = Integer.parseInt(targetRecord[index]);
            sales++;
//        System.out.println("current sales is " + sales);
//        System.out.println("sold one product");
//        System.out.println(Arrays.toString(targetRecord));
            targetRecord[index] = String.valueOf(sales);

            writeToFile(records);
        }
        //don't do anything if the product doesn't exist...
    }

    public static void writeToFile(ArrayList<String[]> records){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fName))) {
            for (String[] updatedRow : records) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < updatedRow.length; j++) {
                    if (j > 0) {
                        sb.append(",");
                    }
                    sb.append(updatedRow[j]);
                }
                bw.write(sb.toString());
                bw.newLine();
            }
//            System.out.println("written");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] getSalesByWeekFor(String code) throws IOException {
//        System.out.println("get sales by week");
        int[] weekStats = new int[7]; // Seven days prior to "today"

        ArrayList<String[]> allRecords = readCSVArrayList();
        int alLength = allRecords.size();

        // Find the column of the desired product in the CSV
        String[] allProducts = allRecords.get(0);
        int indexOfProduct = 0;
        boolean productFound = false;
        for (String product : allProducts) {
            if (product.equals(code)) {
//                System.out.println("get sales by week - 1");
//                System.out.println("index is " + indexOfProduct + ", name is " + product);
                productFound = true;
                break;
            }
            indexOfProduct++;
        }
        if (!productFound) {
            return null;
        }

        // Ensure we don't exceed the number of available records
//        int startIndex = Math.max(alLength - 7, 1); // Start from the 7th last record, but skip the header (index 0)
        int weekIndex = 0;

        for (int i = alLength-1; i > 1 && weekIndex < 7; i--) {

            String[] record = allRecords.get(i);

            weekStats[weekIndex] = Integer.parseInt(record[indexOfProduct]);

            weekIndex++;
        }

        return weekStats;
    }

    public static int[] getSalesByMonthFor(String code) throws IOException {
//        System.out.println("get sales by week");
        int[] monthStats = new int[30]; // 30 days prior to "today"

        ArrayList<String[]> allRecords = readCSVArrayList();
        int alLength = allRecords.size();

        // Find the column of the desired product in the CSV
        String[] allProducts = allRecords.get(0);
        int indexOfProduct = 0;
        boolean productFound = false;
        for (String product : allProducts) {
            if (product.equals(code)) {
//                System.out.println("get sales by week - 1");
//                System.out.println("index is " + indexOfProduct + ", name is " + product);
                productFound = true;
                break;
            }
            indexOfProduct++;
        }
        if (!productFound) {
            return null;
        }

        // Ensure we don't exceed the number of available records
//        int startIndex = Math.max(alLength - 30, 1); // Start from the 7th last record, but skip the header (index 0)
        int monthIndex = 0;

        for (int i = alLength-1; i > 1 && monthIndex < 30; i--) {

            String[] record = allRecords.get(i);

            monthStats[monthIndex] = Integer.parseInt(record[indexOfProduct]);

            monthIndex++;
        }

//        System.out.println(Arrays.toString(monthStats));
        return monthStats;
    }

    public static int[] getSalesByQuintileFor(String code) throws IOException {
//        System.out.println("get sales by week");
        int[] quintStats = new int[90]; // 90 days prior to "today"

        ArrayList<String[]> allRecords = readCSVArrayList();
        int alLength = allRecords.size();

        // Find the column of the desired product in the CSV
        String[] allProducts = allRecords.get(0);
        int indexOfProduct = 0;
        boolean productFound = false;
        for (String product : allProducts) {
            if (product.equals(code)) {
//                System.out.println("get sales by week - 1");
//                System.out.println("index is " + indexOfProduct + ", name is " + product);
                productFound = true;
                break;
            }
            indexOfProduct++;
        }
        if (!productFound) {
            return null;
        }

        // Ensure we don't exceed the number of available records
//        int startIndex = Math.max(alLength - 90, 1); // Start from the 7th last record, but skip the header (index 0)
        int quintIndex = 0;

        for (int i = alLength - 1; i > 1 && quintIndex < 90; i--) {
//            System.out.println("get sales by week - 1.5");
            String[] record = allRecords.get(i);
//            System.out.println("Record: " + Arrays.toString(record));
            quintStats[quintIndex] = Integer.parseInt(record[indexOfProduct]);
//            System.out.println(i + " " + Arrays.toString(record));
//            System.out.println("Week Stats: " + Arrays.toString(weekStats));
//            System.out.println("get sales by week - 2");
            quintIndex++;
        }


//        System.out.println(Arrays.toString(quintStats));
        return quintStats;
    }

}
