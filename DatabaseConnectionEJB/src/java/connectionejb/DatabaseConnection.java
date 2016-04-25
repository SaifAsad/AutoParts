/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionejb;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;

/**
 *
 * @author Saif Asad
 */
@Stateless
public class DatabaseConnection implements DatabaseConnectionRemote, DatabaseConnectionLocal {

    private Connection conn;
    private Properties properties;
    private PreparedStatement allProducts;
    private PreparedStatement singleProduct;
    private PreparedStatement allProductsDetails;
    private PreparedStatement allOrders;
    
    private String test = "Saif is angry";

    @Override
    public String getTest() {
        return test;
    }

    public DatabaseConnection() throws IOException, ClassNotFoundException, SQLException {

        properties = new Properties();
        properties.loadFromXML(getClass().getResourceAsStream("DatabaseConnectionConfig.xml"));
        
        String dbDriver = properties.get("dbDriver").toString();
        String dbUrl = properties.get("dbUrl").toString();

        //get attribute names from all the tables and use them in the prepared statements
        //product table
        String dbTableProduct = properties.get("dbTableProduct").toString();
        String dbProductID    = properties.get("dbProductID").toString();
        String dbProductName  = properties.get("dbProductName").toString();
        String dbProductPrice = properties.get("dbProductPrice").toString();
        String dbProductDesc  = properties.get("dbProductDesc").toString();
        String dbProductImage = properties.get("dbProductImage").toString();
/*
        //product_type table
        String dbTableProductType = properties.get("dbTableProductType").toString();
        String dbTypeID = properties.get("dbTypeID").toString();
        String dbTypeName = properties.get("dbTypeName").toString();

        //customer table
        String dbTableCustomer = properties.get("dbTableCustomer").toString();
        String dbCustomerID = properties.get("dbCustomerID").toString();
        String dbCustomerFirstName = properties.get("dbCustomerFirstName").toString();
        String dbCustomerLastName = properties.get("dbCustomerLastName").toString();
        String dbCustomerAddress = properties.get("dbCustomerAddress").toString();
        String dbCustomerPostalcode = properties.get("dbCustomerPostalcode").toString();

        //order table
        String dbTableOrder = properties.get("dbTableOrder").toString();
        String dbOrderID = properties.get("dbOrderID").toString();
        String dbOrderClientID = properties.get("dbOrderClientID").toString();
        String dbOrderDate = properties.get("dbOrderDate").toString();

        //oder_line table
        String dbTableOrderLine = properties.get("dbTableOrderLine").toString();
        String dbOrderLineOrderID = properties.get("dbOrderLineOrderID").toString();
        String dbOrderLineProductID = properties.get("dbOrderLineProductID").toString();
        String dbOrderLineAmount = properties.get("dbOrderLineAmount").toString();
*/
      String userName = properties.get("user").toString();
      String password = properties.get("password").toString();
        
        // connect to the database and create a prepared statements
        Class.forName(dbDriver);
        
        //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull", "root", "");
        conn = DriverManager.getConnection(dbUrl, userName, "");
        //allProducts = conn.prepareStatement("SELECT " + dbProductName + ", " + dbProductPrice  + " from autopartsdb." +dbTableProduct + ";");
       allProducts = conn.prepareStatement("SELECT " + dbProductID + ", " + dbProductName + ", " + dbProductPrice + ", " + dbProductImage + " from autopartsdb." +dbTableProduct + ";");
        
       //singleProduct = conn.prepareStatement("SELECT " + dbProductID + ", " + dbProductName + ", " + dbProductPrice + ", " + dbProductImage + ", " + dbProductDesc + " from autopartsdb." +dbTableProduct 
          //      + " WHERE " + dbProductID + " = " + 11);
        
       singleProduct = conn.prepareStatement("SELECT * from autopartsdb." + dbTableProduct + " where " + dbProductID + " = ?");
// singleProduct = conn.prepareStatement("SELECT * FROM autopartsdb.product where p_id = 11");
//        allProductsDetails = conn.prepareStatement("SELECT * FROM " + dbTable
//                + " WHERE " + dbFirstNameAtt + " = ? AND " + dbLastNameAtt + " = ?");
//
//        allOrders = conn.prepareStatement("SELECT * FROM " + dbTableOrder
//                + " WHERE " + dbFirstNameAtt + " = ? AND " + dbLastNameAtt + " = ?");
    }

    /**
     *
     * @return and array of products
     */
    @Override
    public ArrayList getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            synchronized (this) // synchronize access to stmt
            {
                ResultSet rs = allProducts.executeQuery();
                
                while(rs.next()) {
                    Product product = new Product();
                    product.setProductID(rs.getInt("p_id"));
                    product.setProductName(rs.getString("p_name"));
                    product.setProductPrice(rs.getBigDecimal("p_price"));
                    product.setProductImage(rs.getString("p_image"));
                    products.add(product);
                }    
            }
        } catch (SQLException e) {
            System.err.println("SQL Exception during query: " + e);
            
        }
        return products;
    }

    /**
     *
     * @param productID
     * @return
     */
    @Override
    public Product getProduct(int productID) {
        try {
            singleProduct.setInt(1, productID);
            ResultSet rs = singleProduct.executeQuery();
            rs.next();
            Product product = new Product();
            product.setProductID(rs.getInt("p_id"));
            product.setProductName(rs.getString("p_name"));
            product.setProductPrice(rs.getBigDecimal("p_price"));
            product.setProductImage(rs.getString("p_image"));
            product.setProductDescription(rs.getString("p_desc"));
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Product> getAllProductsDetails() {
        return null;

    }

    /**
     *
     * @return
     */
    @Override
    public Order getOrders() {
        //allOrders =  "SELECT order_id, order_date, c_fname, c_lname, p_name, order_line_amount FROM customer_order, customer, product, orderline WHERE order_line.order_id = customer_order.o_id AND customer_order.c_id = customer.c_id AND orderline.order_line_product_id = product.p_id";
        
        return null;
    }
    
    
}
