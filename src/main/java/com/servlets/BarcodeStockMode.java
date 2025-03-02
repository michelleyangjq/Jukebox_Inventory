package com.servlets;

import com.backend.ProductsManager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BarcodeStockMode")
public class BarcodeStockMode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

        String bcCode = request.getParameter("barcodeCode");
        //bcCode = bcCode.substring(0,bcCode.length());
//        System.out.println("\""+bcCode+"\"");

//        if(null == bcCode){
//            System.out.println("a");
//            System.out.println("code is null");
//        }
//        else if(bcCode.equals("firstTime")){
//            System.out.println("b");
//            System.out.println("first time");
//        }
        if(!(null == bcCode) && !(bcCode.equals("firstTime"))) {
            System.out.println("c");
            System.out.println("Scanned code is " + bcCode);
            ProductsManager.updateProductSQ(bcCode,1);
        }
        response.sendRedirect("barcodeStockPage.jsp");

    }
}
