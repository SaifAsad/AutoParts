/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cartejb;

import connectionejb.Product;
import java.util.ArrayList;
import javax.ejb.Stateful;

/**
 *
 * @author Saif Asad
 */
@Stateful
public class CartEJB implements CartEJBRemote {

    private ArrayList<Product> cartItems;

    public CartEJB() {
        cartItems = new ArrayList<>();
    }
    
    @Override
    public ArrayList<Product> getCartItems() {
        return cartItems;
    }
    
    @Override
    public boolean addItem(Product product) {
        return cartItems.add(product);
    }

    @Override
    public boolean removeItem(int id) {
        for(Product p : cartItems) {
            if(p.getProductID() == id) {
                return cartItems.remove(p);
            }
        }
        return false;
    } 
    
}
