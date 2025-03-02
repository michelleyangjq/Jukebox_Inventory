package com.servlets;

import com.backend.ProductsManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "EditProduct", urlPatterns = { "/EditProduct" })
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditProduct extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String code = request.getParameter("pCode");
        String name = request.getParameter("pName");
        String price = request.getParameter("pPrice");
        String flavor = request.getParameter("pFlavor");
        String stock = request.getParameter("pStockQuant");
        String category = request.getParameter("category");
        String comments = request.getParameter("pComments");
        String picAddress = request.getParameter("picAddress");

        // Handle file upload
        Part filePart = request.getPart("picAddress");
        String fileName = filePart.getSubmittedFileName();
        if (fileName != null && !fileName.isEmpty()) {
            picAddress = "resources/images/" + fileName;
            String savePath = "/Users/michelleyang/IdeaProjects/webAppPractice/src/main/webapp/" + picAddress;
            filePart.write(savePath);
        }

        // Update product information
        String[] productInfo = new String[]{category, code, name, price, flavor, stock, comments, picAddress};
        ProductsManager.deleteProductWithoutRecords(code);
        ProductsManager.addProduct(productInfo);

        // Forward the request to ProductDetails servlet with the product code
        request.setAttribute("productCode", code);
        request.getRequestDispatcher("ProductDetails").forward(request, response);
    }
}