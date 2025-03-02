package com.servlets;

import com.backend.HowToUsePage;
import com.backend.Instruction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DemoShowInstructions")
public class DemoShowInstructions extends HttpServlet {
    private HowToUsePage page = new HowToUsePage();
    private Instruction currentInstruction = page.getFirst(); // Start with the first instruction

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the action parameter from the request (either "previous" or "next")
        String action = request.getParameter("action");

        // Update the current instruction based on the action
        if ("previous".equals(action) && currentInstruction.hasPrevious()) {
            currentInstruction = currentInstruction.getPrevious(); // Move to the previous instruction
        } else if ("next".equals(action) && currentInstruction.hasNext()) {
            currentInstruction = currentInstruction.getNext(); // Move to the next instruction
        }

        // Set the current instruction as a request attribute
        request.setAttribute("currentInstruction", currentInstruction);

        // Forward the request back to the JSP page
        request.getRequestDispatcher("UserInstructionsPage.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set the current instruction as a request attribute
    request.setAttribute("currentInstruction", currentInstruction);
        request.setAttribute("hasNext", currentInstruction.hasNext());

        // Forward the request to the JSP page
    request.getRequestDispatcher("UserInstructionsPage.jsp").forward(request, response);
    }
}