package com.servlets;

import com.backend.ProductsManager;
import com.backend.RecordsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;


@WebServlet(name = "WriteToProductsList", urlPatterns = { "/WriteToProductsList" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class WriteToProductsList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        System.out.println(1000);
//        System.out.println(0);

        String code = request.getParameter("pCode");
        String name = request.getParameter("pName");
        String prices = request.getParameter("pPrice");
        String flavor = request.getParameter("pFlavor");
        String stocks = request.getParameter("pStockQuant");
        String comments = request.getParameter("pComments");
        String category = request.getParameter("productType");
//        System.out.println(0.04);
        // 解析价格和库存数量
        double price = 0;
        if (prices != null && !prices.trim().isEmpty()) {
            price = Double.parseDouble(prices.trim());
        }

        int stock = 0;
        if (stocks != null && !stocks.trim().isEmpty()) {
            stock = Integer.parseInt(stocks.trim());
        }
//        System.out.println(1);
//
//        System.out.println(0.1);

//        System.out.println(1);


        Part filePart = request.getPart("picAddress");
//        System.out.println(2);

        String picAddress = filePart.getSubmittedFileName();
//        System.out.println(3);
//        System.out.println("picaddress is " + picAddress);
        if (picAddress == "") {
//            System.out.println("file name is not null");
            picAddress = "defaultpic.jpg";
        }
//        System.out.println("new picaddress is " + picAddress);
//        System.out.println("Uploaded File Name: " + picAddress);

        String savePath = "/Users/michelleyang/IdeaProjects/webAppPractice/src/main/webapp/resources/images/" + picAddress;
//        System.out.println(savePath);
// Save file
        filePart.write(savePath);
//        System.out.println("File saved to: " + savePath);

        picAddress = "resources/images/" + picAddress;
//        System.out.println("Updated picAddress: " + picAddress);

//        Part filePart = request.getPart("picAddress");
//        String picAddress = filePart.getSubmittedFileName();
//        System.out.println(picAddress);
//
//
////        String code = request.getParameter("pCode");
////        String name = request.getParameter("pName");
////        System.out.println("here ok");
////        double price = Double.parseDouble(request.getParameter("pPrice"));
////        System.out.println("here ok2");
////        String flavor = request.getParameter("pFlavor");
////        int stock = Integer.parseInt(request.getParameter("pStockQuant"));
////        String comments = request.getParameter("pComments");
////        String category = request.getParameter("productType");
////
////        Part filePart = request.getPart("picAddress");
////        String picAddress = filePart.getSubmittedFileName();
//        for (Part part : request.getParts()) {
//            part.write("/Users/michelleyang/IdeaProjects/webAppPractice/src/main/webapp/resources/images/" + picAddress);
//            System.out.println("added successfully");
//            picAddress = "resources/images/" + picAddress;
//            System.out.println("pic address is " + picAddress);
//        }

//        System.out.println(2);

        //System.out.println(name + comments);

        boolean exists = ProductsManager.productExists(code);
        if(exists){
            request.getRequestDispatcher("productExistsPage.jsp").forward(request, response);
            return;
        }

        ProductsManager.addProduct(category, code, name, price, flavor, stock, comments, picAddress);
//        System.out.println(3);
//        System.out.println(1);

        RecordsManager.addColumn(code);

        ArrayList<String[]> texts = ProductsManager.readCSVArrayList();
//        System.out.println(4);
        request.setAttribute("products", texts);
//        System.out.println(5);
        request.getRequestDispatcher("homepage.jsp").forward(request, response);

    }
}