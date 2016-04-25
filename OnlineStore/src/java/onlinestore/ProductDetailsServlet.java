/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import connectionejb.DatabaseConnectionRemote;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Saif Asad
 */
public class ProductDetailsServlet extends HttpServlet {
    @EJB
    private DatabaseConnectionRemote databaseConnection;

    

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        connectionejb.Product productDetails = null;
        
        String id = request.getParameter("id");
        String m = request.getMethod();
        StringBuffer sb= request.getRequestURL();
        String servPath = request.getServletPath();
        String qs = request.getQueryString();
        
 
        int productID = Integer.parseInt(id);
        productDetails = databaseConnection.getProduct(productID);
        
        Product product = new Product();
        product.setProductID(productDetails.getProductID());
        product.setProductImage(productDetails.getProductImage());
        product.setProductPrice(productDetails.getProductPrice());
        product.setProductName(productDetails.getProductName());
        product.setProductDescription(productDetails.getProductDescription());

        HttpSession session = request.getSession(true);
        session.setAttribute("product", product);
        
        // pass bean to appropriate page for displaying response
        RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/ProductDetailsPage.jsp");
            dispatcher.forward(request, response);
        
    }
 
    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
