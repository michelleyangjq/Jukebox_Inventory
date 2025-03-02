package com.servlets;

import com.backend.ProductsManager;
import com.backend.StockListManager;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pCode = request.getParameter("pCode");
//        System.out.println("delete product servlet");
//        System.out.println("code is "+pCode);
        ProductsManager.deleteProduct(pCode);

        ArrayList<String[]> texts = ProductsManager.readCSVArrayList();
        request.setAttribute("products", texts);

        ArrayList<String[]> stockList = StockListManager.readCSVArrayList();

//        for(String[] task : stockList){
//            System.out.println("a");
//            System.out.println(Arrays.toString(task));
//        }
        request.setAttribute("stockList", stockList);
//        for(String[] ele: texts){
//            System.out.println(Arrays.toString(ele));
//        }

        //System.out.println("Leaving servlet");

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}