package com.servlets;

import com.backend.RecordsManager;
import com.backend.StatisticsManager;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ToViewDetailedStats")
public class ToViewDetailedStats extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {

//        System.out.println("inside to view detailed stats");

        ArrayList<String[]> records = RecordsManager.readCSVArrayList();

        //---weeek
        HashMap<String, Integer> weekResults;
        HashMap<String, Integer> weekCatResults;

        try {
            weekResults = StatisticsManager.listBySalesByWeek();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            weekCatResults = StatisticsManager.categoryWeekStats();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("weekResults",weekResults);
        request.setAttribute("weekCatResults",weekCatResults);


        //---month
        HashMap<String, Integer> monthResults;
        HashMap<String, Integer> monthCatResults;

        try {
            monthResults = StatisticsManager.listBySalesByMonth();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            monthCatResults = StatisticsManager.categoryMonthStats();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("monthResults",monthResults);
        request.setAttribute("monthCatResults",monthCatResults);


        //---quintile
        HashMap<String, Integer> quintResults;
        HashMap<String, Integer> quintCatResults;

        try {
            quintResults = StatisticsManager.listBySalesByQuintile();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        try {
            quintCatResults = StatisticsManager.categoryQuintStats();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("quintResults",quintResults);
        request.setAttribute("quintCatResults",quintCatResults);

        request.setAttribute("records", records);
        request.getRequestDispatcher("detailedStats.jsp").forward(request, response);
    }
}