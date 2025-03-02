package com.backend;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.*;

public class StatisticsManager {

    public StatisticsManager(){

    }

    public static HashMap<String, Integer> listBySalesByWeek() throws IOException, ParseException {

//        System.out.println("method called");

        ArrayList<String[]> records = RecordsManager.readCSVArrayList();
        String[] allProducts = records.get(0); //First row of CSV, CSV[0][0] is "date", remember to remove

        int[] productsSales = new int[allProducts.length]; //stores the quant of sales of product on the same index

        LocalDate currentDate = LocalDate.now(); //get Today's date

//        System.out.println("a");

        int last = records.size()-1; //index of the most recent record
        int times = 0;
        String[] todaysRecords = {};

            while(times < last && times < 7) { //iterate 7 times, while there still is a record, to get the records
                todaysRecords = records.get(last - times);
    //            System.out.println(1);

                for (int i = 1; i < todaysRecords.length; i++) //first column is date
                    productsSales[i] += Integer.parseInt(todaysRecords[i]); //add the sales to productsSales

                times++;
            }

//            System.out.println(2);
//            System.out.println(Arrays.toString(productsSales));

            //----bubble sort for sorting the products

        mergeSort(productsSales,allProducts,0, productsSales.length-1);
//
//            boolean sorted = false;
//            //int infiniteLoopTest = 0;
//            while(!sorted){
//                sorted = true;
////                System.out.println(Arrays.toString(productsSales));
//                for(int i = 1; i < todaysRecords.length - 1; i++){
//                    if (productsSales[i] < productsSales[i+1]){//out of order?
//                        int iTemp = productsSales[i]; //swap the productSales records
//                        String sTemp = allProducts[i]; //swap the corresponding product in the list
//                        productsSales[i] = productsSales[i+1];
//                        allProducts[i] = allProducts[i+1];
//                        productsSales[i+1] = iTemp;
//                        allProducts[i+1] = sTemp;
//
//                        sorted = false;
//                    }
//                }
//            }
////            System.out.println(3);
//
////        System.out.println("b");

        String[] productsNames = codeToNames(allProducts);
        String[] result = new String[productsNames.length-1];

//        System.out.println("c");

        for(int i = 1; i< allProducts.length; i++)
            result[i-1] = allProducts[i];

//        System.out.println(Arrays.toString(result));


//        System.out.println(Arrays.toString(productsNames));
//        System.out.println(Arrays.toString(productsSales));
        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();

        for (int i = 1; i < allProducts.length; i++) {
//            System.out.println("new key " + i + " " + productsNames[i] + " " + productsSales[i]);
            resultMap.put(productsNames[i], productsSales[i]);
        }

//        System.out.println(resultMap.toString());
        return resultMap;
    }

    public static HashMap<String, Integer> listBySalesByMonth() throws IOException, ParseException {
        ArrayList<String[]> records = RecordsManager.readCSVArrayList();
        String[] allProducts = records.get(0); //First row of CSV, CSV[0][0] is "date", remember to remove

        int[] productsSales = new int[allProducts.length]; //stores the quant of sales of product on the same index

        LocalDate currentDate = LocalDate.now(); //get Today's date

        int last = records.size()-1; //index of the most recent record
        int times = 0;
        String[] todaysRecords = {};

        while(times < last && times < 30) { //iterate 7 times, while there still is a record, to get the records
            todaysRecords = records.get(last - times);

            for (int i = 1; i < todaysRecords.length; i++) //first column is date
                productsSales[i] += Integer.parseInt(todaysRecords[i]); //add the sales to productsSales

            times++;
        }

        //----bubble sort for sorting the products

        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(int i = 1; i < todaysRecords.length - 1; i++){
                if (productsSales[i] < productsSales[i+1]){//out of order?
                    int iTemp = productsSales[i]; //swap the productSales records
                    String sTemp = allProducts[i]; //swap the corresponding product in the list
                    productsSales[i] = productsSales[i+1];
                    allProducts[i] = allProducts[i+1];
                    productsSales[i+1] = iTemp;
                    allProducts[i+1] = sTemp;

                    sorted = false;
                }
            }
        }

        String[] productsNames = codeToNames(allProducts);
        String[] result = new String[productsNames.length-1];

//        System.out.println("c");

        for(int i = 1; i< allProducts.length; i++)
            result[i-1] = allProducts[i];

//        System.out.println(Arrays.toString(productsNames));
//        System.out.println(Arrays.toString(productsSales));

        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();

        for (int i = 1; i < allProducts.length; i++) {
            resultMap.put(productsNames[i], productsSales[i]);
        }

        return resultMap;
    }

    public static HashMap<String, Integer> listBySalesByQuintile() throws IOException, ParseException {
        ArrayList<String[]> records = RecordsManager.readCSVArrayList();
        String[] allProducts = records.get(0); //First row of CSV, CSV[0][0] is "date", remember to remove

        int[] productsSales = new int[allProducts.length]; //stores the quant of sales of product on the same index

        LocalDate currentDate = LocalDate.now(); //get Today's date

        int last = records.size()-1; //index of the most recent record
        int times = 0;
        String[] todaysRecords = {};

        while(times < last && times < 90) { //iterate 7 times, while there still is a record, to get the records
            todaysRecords = records.get(last - times);

            for (int i = 1; i < todaysRecords.length; i++) //first column is date
                productsSales[i] += Integer.parseInt(todaysRecords[i]); //add the sales to productsSales

            times++;
        }

        //----bubble sort for sorting the products

        boolean sorted = false;
        while(!sorted){
            sorted = true;
            for(int i = 1; i < todaysRecords.length - 1; i++){
                if (productsSales[i] < productsSales[i+1]){//out of order?
                    int iTemp = productsSales[i]; //swap the productSales records
                    String sTemp = allProducts[i]; //swap the corresponding product in the list
                    productsSales[i] = productsSales[i+1];
                    allProducts[i] = allProducts[i+1];
                    productsSales[i+1] = iTemp;
                    allProducts[i+1] = sTemp;

                    sorted = false;
                }
            }
        }

        String[] productsNames = codeToNames(allProducts);
        String[] result = new String[productsNames.length-1];

        for(int i = 1; i< allProducts.length; i++)
            result[i-1] = allProducts[i];

        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();

        for (int i = 1; i < allProducts.length; i++) {
            resultMap.put(productsNames[i], productsSales[i]);
        }

        return resultMap;
    }

    public static HashMap<String, Integer> categoryWeekStats() throws IOException, ParseException{
        String[] categories = {"Drinks", "Snacks", "Cup Noodles", "Ice Cream"}; //hardcoded since only four types
        int[] productsSales = new int[4]; //stores the quant of sales of product on the same index

        ArrayList<String[]> records = RecordsManager.readCSVArrayList();

        String[] allProducts = records.get(0);

        LocalDate currentDate = LocalDate.now(); //get Today's date

        int last = records.size()-1; //index of the most recent record
        int times = 0;
        String[] todaysRecords = {};

        while(times < last && times < 7) { //iterate 7 times, while there still is a record, to get the records
            todaysRecords = records.get(last - times); //get records starting from back
            for (int i = 1; i < todaysRecords.length; i++) { //first column is date
                String category = codeToCategory(allProducts[i]);
                System.out.println("code: " + allProducts[i] + " " + i+" " + category);
                switch(category) {
                    case "Drinks":
                        productsSales[0] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Snacks":
                        productsSales[1] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Cup Noodles":
                        productsSales[2] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Ice Cream":
                        productsSales[3] += Integer.parseInt(todaysRecords[i]);
                        break;
                }
            }
            times++;
        }


        mergeSort(productsSales, categories, 0, productsSales.length - 1);


        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();

        for (int i = 0; i < categories.length; i++) {
            resultMap.put(categories[i], productsSales[i]);
            System.out.println(categories[i]+ productsSales[i]);
        }

        return resultMap;
    }

    public static HashMap<String, Integer> categoryMonthStats() throws IOException, ParseException{
        String[] categories = {"Drinks", "Snacks", "Cup Noodles", "Ice Cream"}; //hardcoded since only four types
        int[] productsSales = new int[4]; //stores the quant of sales of product on the same index

        ArrayList<String[]> records = RecordsManager.readCSVArrayList();

        String[] allProducts = records.get(0);

        LocalDate currentDate = LocalDate.now(); //get Today's date

        int last = records.size()-1; //index of the most recent record
        int times = 0;
        String[] todaysRecords = {};

        while(times < last && times < 30) { //iterate 30 times, while there still is a record, to get the records
            todaysRecords = records.get(last - times); //get records starting from back
            for (int i = 1; i < todaysRecords.length; i++) { //first column is date
                String category = codeToCategory(allProducts[i]);
                System.out.println("code: " + allProducts[i] + " " + i+" " + category);
                switch(category) {
                    case "Drinks":
                        productsSales[0] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Snacks":
                        productsSales[1] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Cup Noodles":
                        productsSales[2] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Ice Cream":
                        productsSales[3] += Integer.parseInt(todaysRecords[i]);
                        break;
                }
            }
            times++;
        }


        mergeSort(productsSales, categories, 0, productsSales.length - 1);

        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();

        for (int i = 1; i < categories.length; i++) {
            resultMap.put(categories[i], productsSales[i]);
        }

        return resultMap;
    }

    public static HashMap<String, Integer> categoryQuintStats() throws IOException, ParseException{
        String[] categories = {"Drinks", "Snacks", "Cup Noodles", "Ice Cream"}; //hardcoded since only four types
        int[] productsSales = new int[4]; //stores the quant of sales of product on the same index

        ArrayList<String[]> records = RecordsManager.readCSVArrayList();

        String[] allProducts = records.get(0);

        LocalDate currentDate = LocalDate.now(); //get Today's date

        int last = records.size()-1; //index of the most recent record
        int times = 0;
        String[] todaysRecords = {};

        while(times < last && times < 90) { //iterate 90 times, while there still is a record, to get the records
            todaysRecords = records.get(last - times); //get records starting from back
            for (int i = 1; i < todaysRecords.length; i++) { //first column is date
                String category = codeToCategory(allProducts[i]);
                System.out.println("code: " + allProducts[i] + " " + i+" " + category);
                switch(category) {
                    case "Drinks":
                        productsSales[0] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Snacks":
                        productsSales[1] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Cup Noodles":
                        productsSales[2] += Integer.parseInt(todaysRecords[i]);
                        break;
                    case "Ice Cream":
                        productsSales[3] += Integer.parseInt(todaysRecords[i]);
                        break;
                }
            }
            times++;
        }


        mergeSort(productsSales, categories, 0, productsSales.length - 1);

        LinkedHashMap<String, Integer> resultMap = new LinkedHashMap<>();

        for (int i = 1; i < categories.length; i++) {
            resultMap.put(categories[i], productsSales[i]);
        }

        return resultMap;
    }

    public static void mergeSort(int[] productsSales, String[] categories, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergeSort(productsSales, categories, left, mid);
            mergeSort(productsSales, categories, mid + 1, right);

            merge(productsSales, categories, left, mid, right);
        }
    }

    public static void merge(int[] productsSales, String[] categories, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];
        String[] LCategories = new String[n1];
        String[] RCategories = new String[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = productsSales[left + i];
            LCategories[i] = categories[left + i];
        }
        for (int j = 0; j < n2; j++) {
            R[j] = productsSales[mid + 1 + j];
            RCategories[j] = categories[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] >= R[j]) {
                productsSales[k] = L[i];
                categories[k] = LCategories[i];
                i++;
            } else {
                productsSales[k] = R[j];
                categories[k] = RCategories[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            productsSales[k] = L[i];
            categories[k] = LCategories[i];
            i++;
            k++;
        }

        while (j < n2) {
            productsSales[k] = R[j];
            categories[k] = RCategories[j];
            j++;
            k++;
        }
    }

    public static String[] codeToNames(String[] code) throws IOException {
//        System.out.println("code is " + Arrays.toString(code));

        String[] names = new String[code.length];

//        System.out.println("code length is " + code.length);

        ArrayList<String[]> products = ProductsManager.readCSVArrayList();

        int count = 0;

        for(int i = 0; i<code.length; i++){
            for(int j = 0; j<products.size(); j++){
                String[] thisProduct = products.get(j);
//                System.out.println(Arrays.toString(thisProduct));
                if(thisProduct[1].equals(code[i])){//if the product with the same code is found...
                    count++;
//                    System.out.println(true);
//                    System.out.println("Name is " + thisProduct[2]);
                    names[i] = thisProduct[2];
                    break;
                }
            }
        }

//        System.out.println("count is " + count);
//        System.out.println("names are " + Arrays.toString(names));

        return names;
    }

    public static String codeToCategory(String code) throws IOException {
        ArrayList<String[]> products = ProductsManager.readCSVArrayList();

        for(int i = 0; i < products.size(); i++){
            if(products.get(i)[1].equals(code)){
                return (products.get(i)[0]);
            }
        }
        return null;
    }

}
