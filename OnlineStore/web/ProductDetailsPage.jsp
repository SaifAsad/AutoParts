<%-- 
    Document   : ProductDetailsPage
    Created on : Apr 24, 2016, 1:20:34 PM
    Author     : Saif Asad
--%>
<%@page import="onlinestore.Product"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="Styles/StyleSheet.css">
        <title>Product Details</title>
    </head>
    <body>
        <jsp:useBean id ="product" class="onlinestore.Product" scope="session" />
        
        <h1><%= product.getProductName()%></h1>

        <img class="productImage" src="./Images/Products/<%=product.getProductImage()%>" alt="Product Image" width="42" height="42" border="0">
        <%= product.getProductID()%>
        <BR>
        <SPAN class="productName"> <%= product.getProductDescription()%></SPAN>
        <BR>
        <SPAN class="productPrice">$<%= product.getProductPrice() %></SPAN>
        
        
        <button>Add to Cart</button>
        <button>Home</button>
    </body>
</html>
