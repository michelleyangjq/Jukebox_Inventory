package com.servlets;

import com.backend.RecordsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@WebServlet("/FindRevenue")
public class FindRevenue extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sDate = request.getParameter("date");
        System.out.println("Date from form: " + sDate); // Debugging line

        if (sDate == null || sDate.isEmpty()) {
            request.setAttribute("error", "Date cannot be empty");
            request.getRequestDispatcher("findRevenueByDate.jsp").forward(request, response);
            return;
        }

        try {
            LocalDate date = LocalDate.parse(sDate);
            double rev = RecordsManager.getRevenue(date);
//            System.out.println(1);
//            System.out.println(rev);

            String revenue = "The revenue on " + String.valueOf(date) + " was " + rev;
            request.setAttribute("revenue", revenue);
        } catch (DateTimeParseException e) {
            request.setAttribute("error", "Invalid date format. Please use yyyy-MM-dd.");
        }

        request.getRequestDispatcher("findRevenueByDate.jsp").forward(request, response);
    }
}
