package com.servlets;

import com.backend.ProductsManager;
import com.backend.RecordsManager;
import com.backend.StockListManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ResolveToStock")
public class ResolveToStock extends HttpServlet{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
//        System.out.println("in servlet");

        StockListManager.removeFromStockList();

        ArrayList<String[]> texts = ProductsManager.readCSVArrayList();
        request.setAttribute("products", texts);

        ArrayList<String[]> stockList = StockListManager.readCSVArrayList();

//        for(String[] task : stockList){
//            System.out.println("a");
//            System.out.println(Arrays.toString(task));
//        }
        request.setAttribute("stockList", stockList);

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }
}
