<%-- 
    Document   : Cart
    Created on : Apr 25, 2016, 2:07:10 PM
    Author     : Saif Asad
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <h1>Cart Items</h1>

        <ul style="list-style: none">
            <%
                ArrayList<connectionejb.Product> productslist = (ArrayList<connectionejb.Product>) session.getAttribute("productslist");
                for (connectionejb.Product product : productslist) {
            %>

            <li>     
                <a href="/OnlineStore/ProductDetailsServlet?id=<%=product.getProductID()%>" >
                    <img class="productImage" src="./Images/Products/<%=product.getProductImage()%>" alt="Product Image" width="42" height="42" border="0">
                </a>
                <%=product.getProductID()%>
                <BR>
                <SPAN class="productName"> <%= product.getProductName()%></SPAN>
                <BR>
                <SPAN class="productPrice">$<%= product.getProductPrice()%></SPAN>
                <BR>
                <button>Delete</button>
                <hr>
            </li>
            <%
                }
            %>
        </ul>
        <input type="submit" value="Proceed to Checkout"/>
    </body>
</html>
