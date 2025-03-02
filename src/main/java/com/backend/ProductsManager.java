package com.backend;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductsManager extends CSVFileManager {

    static String fName = "/Users/michelleyang/IdeaProjects/webAppPractice/ProductsList.csv";

    public ProductsManager() {

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

    public static ArrayList<String> readCSVALStrings() throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void updateProductSQ(String code, int quant) throws IOException {

        if(productExists(code)) {
//        System.out.println(1);
            ArrayList<String[]> allProducts = readCSVArrayList();

//        System.out.println(2 + "The code is " + code);

            String[] product = findProduct(code);
//            System.out.println(Arrays.toString(product));

            int stock = Integer.parseInt(product[5]);
//        System.out.println(quant);
            if (quant < 0) {
                if (stock > 0) { //The product can only be sold if there *are* remaining stock, cannot be negative
                    stock = stock + quant;
                }
            } else {
                stock = stock + quant;
            }

            product[5] = String.valueOf(stock);

            //System.out.println(product[5]);

            editProduct(product);
        }
    }

    public static String[] findProduct(String code) throws IOException {

        //System.out.println(code);

        ArrayList<String[]> products = readCSVArrayList();

        for (String[] line : products) {
            if (line!=null && line[1].equals(code)) {
                //System.out.println(line);
                return line;
            }
        }

        return new String[]{"Error","Error","Error","Error","Error","Error","Error","Error"};
    }

    public static void editProduct(String[] productInfo) throws IOException {
//        deleteProduct(productInfo[1]);
        String pCode = productInfo[1];

//        System.out.println(Arrays.toString(productInfo));

        ArrayList<String[]> lines = readCSVArrayList();

        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        // Find and remove the entry with the specified pname
        for (String[] thisLine : lines) {
            if (thisLine.length > 1 && thisLine[1].equals(pCode)) { // Check if the entry has the pname in the second column
//                System.out.println(Arrays.toString(thisLine));
                lines.remove(thisLine);
                break; // Assuming there is only one entry with the pname, so we can break after deleting it
            }
        }

        writeToFile(lines);

        addProduct(productInfo);
    }

    public static void deleteProductWithoutRecords(String pCode) throws IOException {
        ArrayList<String[]> lines = readCSVArrayList();

        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        // Find and remove the entry with the specified pname
        for (String[] thisLine : lines) {
            if (thisLine.length > 1 && thisLine[1].equals(pCode)) { // Check if the entry has the pname in the second column
//                System.out.println(Arrays.toString(thisLine));
                lines.remove(thisLine);
                break; // Assuming there is only one entry with the pname, so we can break after deleting it
            }
        }

        writeToFile(lines);
    }

    public static void deleteProduct(String pCode) throws IOException {
//        System.out.println(pCode);

        ArrayList<String[]> lines = readCSVArrayList();

        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        // Find and remove the entry with the specified pname
        for (String[] thisLine : lines) {
            if (thisLine.length > 1 && thisLine[1].equals(pCode)) { // Check if the entry has the pname in the second column
                System.out.println(Arrays.toString(thisLine));
                lines.remove(thisLine);
                break; // Assuming there is only one entry with the pname, so we can break after deleting it
            }
        }

        // Write the updated content back to the CSV file
        RecordsManager.removeColumn(pCode);

        writeToFile(lines);
//        try (FileWriter writer = new FileWriter(fName)) {
//            for (String[] line : lines) {
//                writer.write(String.join(",", line) + "\n");
//            }
//            System.out.println("Entry with product name '" + pname + "' has been deleted.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static void addProduct(String category, String code, String name, double price,
                                  String flavor, int stock, String comments, String picAddress) throws IOException {
//        System.out.println("addProduct1");
//        System.out.println("the comments are \'" + comments+"\'");

        if(productExists(code)){
            return;
        }

        if (comments==""){
//            System.out.println("comments is null");
            comments = "[no comments]";
        }
        if(picAddress==""){
            picAddress = "/Users/michelleyang/IdeaProjects/webAppPractice/Pictures/defaultpic.jpg";
        }
        try {
            int insertIndex = ProductsManager.findInsertIndex(category);
            //System.out.println(insertIndex);

            // Read existing content of the CSV file
            ArrayList<String> lines = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fName))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            //System.out.println("addProduct2");

//            System.out.println(category + "," + code + "," + name + ","
//                    + price + "," + flavor + "," + stock + "," + comments);

            // Insert the new product information before the first line with the same category
            lines.add(insertIndex, category + "," + code + "," + name + ","
                    + price + "," + flavor + "," + stock + "," + comments + "," + picAddress);



            // Write the updated content back to the CSV file
            try (FileWriter writer = new FileWriter(fName)) { // Open the FileWriter in overwrite mode
                for (String line : lines) {
                    writer.write(line);
                    writer.write("\n");
                }
                //System.out.println("Written");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addProduct(String[] productInfo) throws IOException {

        String category = productInfo[0];
        String code = productInfo[1];
        String name = productInfo[2];
        String price = productInfo[3];
        String flavor = productInfo[4];
        String stock = productInfo[5];
        String comments = productInfo[6];
        String picAddress = productInfo[7];

        addProduct(category,code,name,Double.parseDouble(price),flavor,Integer.parseInt(stock),comments,picAddress);

        if(Integer.parseInt(stock) == 0){
            StockListManager.addToStockList(name);
        }
//        try {
//            int insertIndex = ProductsManager.findInsertIndex(productInfo[0]);
//
//
//            // Read existing content of the CSV file
//            ArrayList<String> products = new ArrayList<>();
//            try (BufferedReader reader = new BufferedReader(new FileReader(fName))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    products.add(line);
//                }
//            }
//
//            // Insert the new product information before the first line with the same category
//            String pI = Arrays.toString(productInfo);
//            pI = pI.substring(1,pI.length());
//            products.add(Arrays.toString(productInfo));
//
//
//
//            // Write the updated content back to the CSV file
//            try (FileWriter writer = new FileWriter(fName)) { // Open the FileWriter in overwrite mode
//                for (String line : products) {
//                    writer.write(line);
//                    writer.write("\n");
//                }
//                //System.out.println("Written");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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

    public static void writeToFile(ArrayList<String[]> products){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fName))) {
            for (String[] updatedRow : products) {
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

    public static boolean productExists(String code) throws IOException {
        ArrayList<String[]> products = readCSVArrayList();

        for (String[] line : products) {
            if (line[1].equals(code)) {
                return true;
            }
        }
        return false;

    }

}

