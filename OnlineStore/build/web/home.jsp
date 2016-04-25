<%-- 
    Document   : home.jsp
    Created on : Apr 18, 2016, 9:46:19 AM
    Author     : Saif Asad
--%>
<%@page import="onlinestore.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="onlinestore.Customer"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
    <TITLE>Home-Products</TITLE>
    <link rel="stylesheet" type="text/css" href="Styles/StyleSheet.css">
</HEAD>
<BODY>
   <img src="./Images/BannerCar.jpg" alt="Product Image" border="0"> 
   
   <div id="navigation">  
<ul id="nav">
  <li><a href="#home">Products</a></li>
  <li><a href="#news">Cart</a></li>
  <li><a href="#contact">Management</a></li>
  <li><a href="#contact">Orders</a></li>
  <li><a href="#about">About</a></li>
</ul>
       </div>
   
   <H3>Products</H3>

<ul style="list-style: none">
   
<%
    ArrayList<connectionejb.Product> productslist = (ArrayList<connectionejb.Product>) session.getAttribute("productslist");
    for(connectionejb.Product product : productslist) {
%>
 
<li>     
        <a href="/OnlineStore/ProductDetailsServlet?id=<%=product.getProductID()%>" >
            <img class="productImage" src="./Images/Products/<%=product.getProductImage()%>" alt="Product Image" width="42" height="42" border="0">
        </a>
        <%=product.getProductID()%>
        <BR>
        <SPAN class="productName"> <%= product.getProductName() %></SPAN>
        <BR>
        <SPAN class="productPrice">$<%= product.getProductPrice() %></SPAN>
        <hr>
</li>
<%
}
%>
</ul>
</BODY>
</HTML>
