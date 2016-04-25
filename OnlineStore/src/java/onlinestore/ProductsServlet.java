/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package onlinestore;

import connectionejb.DatabaseConnectionRemote;
import java.io.IOException;
import java.util.ArrayList;
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
public class ProductsServlet extends HttpServlet {
    
    @EJB
    private DatabaseConnectionRemote databaseConnection;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<connectionejb.Product> productsList = null;
        
        //represent data coming from database through DatabaseConnection ejb
        //ArrayList<connectionejb.Product> products = null;
        
        productsList = databaseConnection.getAllProducts();
        //ResultSet rs = databaseConnection.getAllProducts();
        
//        try {
//            while(rs.next()) {
//                Product product = new Product();
//                product.setProductName(rs.getString("p_name"));
//                product.setProductPrice(rs.getBigDecimal("p_price"));
//                product.setProductImage(rs.getString("p_image"));
//                productsList.add(product);  
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(ProductsServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        for(connectionejb.Product p : products){
//            Product product = new Product();
//            product.setProductName(p.getProductName());
//            product.setProductPrice(p.getProductPrice());
//            product.setProductImage(p.getProductImage());
//            productsList.add(product);  
//        }
        
        
        

    //        try (PrintWriter out = response.getWriter()) {
    //            /* TODO output your page here. You may use following sample code. */
    //            out.println("<!DOCTYPE html>");
    //            out.println("<html>");
    //            out.println("<head>");
    //            out.println("<title>Servlet testServlet</title>");            
    //            out.println("</head>");
    //            out.println("<body>");
    //            out.println("<h1>Servlet testServlet at " + request.getContextPath() + "</h1>");
    //            out.println(productsList.get(1).getProductName());
    //            out.println("</body>");
    //            out.println("</html>");
    //        }
        //String name = databaseConnection.getTest();
        //String hello = helloBean.getHello();
        // make customer bean available for session
        HttpSession session = request.getSession(true);
        session.setAttribute("productslist", productsList);
        // pass bean to appropriate page for displaying response
        boolean customerFound = true;
        if (customerFound) {
            RequestDispatcher dispatcher = getServletContext().
                    getRequestDispatcher("/home.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
