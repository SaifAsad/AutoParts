/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartejb;

import connectionejb.Product;
import java.util.ArrayList;
import javax.ejb.Remote;

/**
 *
 * @author Saif Asad
 */
@Remote
public interface CartEJBRemote {

    boolean addItem(Product product);

    boolean removeItem(int id);
    
    public ArrayList<Product> getCartItems();
    
}
