package com.backend;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class StockListManager {

    static String fName = "/Users/michelleyang/IdeaProjects/webAppPractice/ToStockList.csv";

    public StockListManager() throws IOException {

    }

    public static ArrayList<String[]> readCSVArrayList() throws IOException {
//        System.out.println(1);
        String thisLine;
        ArrayList<String[]> result = new ArrayList<String[]>();

        FileInputStream fis = new FileInputStream(fName);
        DataInputStream myInput = new DataInputStream(fis);

        while ((thisLine = myInput.readLine()) != null) {
            String[] line = thisLine.split(",");
            result.add(line);
        }
//        System.out.println(2);
        return result;
    }

    public static boolean taskExists(String pName, LocalDate aDay) throws IOException {
        ArrayList<String[]> result = readCSVArrayList();
        for(String[] task: result){
            if(task[0].equals(pName) && task[1].equals(String.valueOf(aDay)));
            return true;
//            System.out.println("current tasks");
//            System.out.println(Arrays.toString(task));
        }
        return false;
    }

    public static void addToStockList(String pName) throws IOException {
        LocalDate today = LocalDate.now();

        if(!taskExists(pName, today)) { //Do not add again if task already exists

            ArrayList<String[]> result = readCSVArrayList();
            for (String[] task : result) {
//            System.out.println("current tasks");
//            System.out.println(Arrays.toString(task));
            }

            String[] newTask = new String[]{pName, String.valueOf(today)};
            result.add(newTask);
//            for (String[] task : result) {
//                System.out.println("new task");
//                System.out.println(Arrays.toString(task));
//            }
            writeToFile(result);
        }
    }

    public static void removeFromStockList() throws IOException {
//        System.out.println("remove from stock list");
        ArrayList<String[]> result = readCSVArrayList();
//        System.out.println("y");
//        for(String[] task: result){
//            System.out.println("z");
//            System.out.println(Arrays.toString(task));
//        }
        if(!result.isEmpty()) {
            result.remove(0);
            writeToFile(result);
        }
    }

    public static void writeToFile(ArrayList<String[]> stockList){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fName))) {
            for (String[] updatedRow : stockList) {
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





}
