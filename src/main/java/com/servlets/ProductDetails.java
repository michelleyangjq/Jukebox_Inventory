package com.servlets;

import com.backend.Products;
import com.backend.ProductsManager;
import com.backend.RecordsManager;
import com.backend.StatisticsManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProductDetails")
public class ProductDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the product code from the request attributes
        String pCode = (String) request.getAttribute("productCode");

        // If the attribute is not set, fall back to the request parameter (for direct access)
        if (pCode == null) {
            pCode = request.getParameter("productCode");
        }

        // Fetch product information
        String[] productInfo = ProductsManager.findProduct(pCode);
        request.setAttribute("productInfo", productInfo);

        // Fetch sales statistics
        int[] weekStats = RecordsManager.getSalesByWeekFor(pCode);
        int[] monthStats = RecordsManager.getSalesByMonthFor(pCode);
        int[] quintStats = RecordsManager.getSalesByQuintileFor(pCode);

        request.setAttribute("weekStats", weekStats);
        request.setAttribute("monthStats", monthStats);
        request.setAttribute("quintStats", quintStats);

        // Fetch and process dates
        ArrayList<String[]> records = RecordsManager.readCSVArrayList();
        String[] dates = new String[records.size()];
        for (int i = 0; i < records.size(); i++) {
            dates[i] = records.get(i)[0];
        }

        String[] weekDates = new String[7];
        String[] monthDates = new String[30];
        String[] quintDates = new String[90];

        Arrays.fill(weekDates, "//");
        Arrays.fill(monthDates, "//");
        Arrays.fill(quintDates, "//");

        int dateSize = dates.length;
        for (int i = 0; i < 7 && i < dateSize; i++) {
            weekDates[i] = dates[dateSize - 1 - i];
            monthDates[i] = dates[dateSize - 1 - i];
            quintDates[i] = dates[dateSize - 1 - i];
        }
        for (int i = 7; i < 30 && i < dateSize; i++) {
            monthDates[i] = dates[dateSize - 1 - i];
            quintDates[i] = dates[dateSize - 1 - i];
        }
        for (int i = 30; i < 90 && i < dateSize; i++) {
            quintDates[i] = dates[dateSize - 1 - i];
        }

        request.setAttribute("weekDates", weekDates);
        request.setAttribute("monthDates", monthDates);
        request.setAttribute("quintDates", quintDates);

        // Calculate average sales and stock date
        Products thisProduct = new Products(productInfo);
        double averageSales = thisProduct.getAverageSales();
        LocalDate stockDay = thisProduct.findEstimateStockDate();

        request.setAttribute("averageSales", averageSales);
        request.setAttribute("stockDay", stockDay == null ? "...(no sales have been made yet 😶‍🌫️)" : stockDay);

        // Forward to the JSP page
        request.getRequestDispatcher("productsDetails.jsp").forward(request, response);
    }
}