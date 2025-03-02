package com.servlets;

import com.backend.ProductsManager;
import com.backend.RecordsManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BarcodeSellMode")
public class BarcodeSellMode extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
//When a product is sold
        String bcCode = request.getParameter("barcodeCode");
//        bcCode = bcCode.substring(0,bcCode.length());
//        System.out.println("\""+bcCode+"\"");

        if(null == bcCode){
//            System.out.println("a");
//            System.out.println("code is null");
        }
        else if(bcCode.equals("firstTime")){
//            System.out.println("b");
//            System.out.println("first time");
        }
        else {
//            System.out.println("c");
//            System.out.println("Scanned code is " + bcCode);
            ProductsManager.updateProductSQ(bcCode,-1); //remove one from remaining stock
            RecordsManager.sellProduct(bcCode, LocalDate.now()); //add a sales record
        }
        response.sendRedirect("barcodeSellPage.jsp");

    }
}
