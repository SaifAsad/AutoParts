/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionejb;

import java.sql.ResultSet;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Saif Asad
 */
@Remote
public interface DatabaseConnectionRemote {

    public Order getOrders();

    public ArrayList<Product> getAllProductsDetails();

    public Product getProduct(int productID);

    public ArrayList<Product> getAllProducts();
    
    //public ResultSet getAllProducts();
    
    public String getTest();
    
}
