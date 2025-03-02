package com.backend;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Products {

    private String name;
    private double price;
    private String flavor;
    private int remainingStock;
    private String category;
    private String code; //String because also includes letters
    private String comments;
    private String picAddress;

    public Products(String scategory, String sname, double sprice, String sflavor,
                    int sremainingStock, String scode, String scomments, String spicAddress){
        name = sname;
        price = sprice;
        flavor = sflavor;
        remainingStock = sremainingStock;
        category = scategory;
        code = scode;
        comments = scomments;
        picAddress = spicAddress;
    }

    public Products(String[] productInfo){
        name = productInfo[2];
        price = Double.parseDouble(productInfo[3]);
        flavor = productInfo[4];
        remainingStock = Integer.parseInt(productInfo[5]);
        category = productInfo[0];
        code = productInfo[1];
        comments = productInfo[6];
        picAddress = productInfo[7];
    }

    public LocalDate findEstimateStockDate() throws IOException {
        double averageSales = getAverageSales();
        int quantRemain = getRemainingStock();
        int daysLeft = 0;

        if(averageSales > 0) { //if averageSales = 0, infinite loop
            while (quantRemain > averageSales) { //if the stock is not enough to last another normal day
                daysLeft++;
                quantRemain -= averageSales;
            }

            return LocalDate.now().plusDays(daysLeft);
        }
        else return null;
    }

    public double getAverageSales() throws IOException {

        //use records from past month, most representative
        int[] salesPastMonth = RecordsManager.getSalesByWeekFor(code);

        int totalSales = 0;
        for(int sale : salesPastMonth){totalSales += sale;}

        double result = (totalSales/7.0);
        result = (int)(result*100);
        result = result/100.00;

        return (result);
        /*The average sales should round up to the nearest integer
        It is safer for the treasurer to overestimate than to actually run short on products*/
    }



    //-----------------Getters and Setters--------------------//
    public String getCategory() {return category;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getRemainingStock() {
        return remainingStock;
    }

    public void setRemainingStock(int remainingStock) {
        this.remainingStock = remainingStock;
    }

    public void sellProduct(){
        remainingStock =- 1;
    }

    public void sellProduct(int quantity){
        remainingStock =- quantity;
    }

    public void stockProduct(){
        remainingStock =+ 1;
    }

    public void stockProduct(int quantity){
        remainingStock =+ quantity;
    }

}
