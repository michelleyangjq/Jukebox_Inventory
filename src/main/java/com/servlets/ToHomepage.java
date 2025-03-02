package com.servlets;

import com.backend.ProductsManager;
import com.backend.StockListManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/ToHomepage")
public class ToHomepage extends HttpServlet {

    private static boolean loggedIn = false;
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //response.setContentType("text/html");
        //PrintWriter out = response.getWriter();
        //out.println("HI");
        //System.out.println("Inside Servlet");
        loggedIn = true;

        ArrayList<String[]> texts = ProductsManager.readCSVArrayList();
//        System.out.println(Arrays.toString(texts.get(3)));
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

//        System.out.println("Leaving servlet");

        request.getRequestDispatcher("homepage.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        System.out.println(loggedIn);

        if(!loggedIn){
            request.getRequestDispatcher("notLoggedInPage.jsp").forward(request, response);
            return; // Add a return statement to stop the execution after forwarding
        }
        else {
            ArrayList<String[]> texts = ProductsManager.readCSVArrayList();
//        System.out.println(Arrays.toString(texts.get(3)));
            request.setAttribute("products", texts);

            ArrayList<String[]> stockList = StockListManager.readCSVArrayList();

            request.setAttribute("stockList", stockList);

            request.getRequestDispatcher("homepage.jsp").forward(request, response);
        }
    }

    public static void logOut(){
        loggedIn = false;
    }
}