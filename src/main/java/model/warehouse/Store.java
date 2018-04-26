package main.java.model.warehouse;


import main.java.model.tree.BinarySearchTree;
import main.java.model.users.AbstractUser;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    private Map<String, AbstractUser> users = new HashMap<>();
    private BinarySearchTree<Product> products;
    private static Store storeInstance;


    public Map<String, AbstractUser> getUsers() {
        return users;
    }

    public void setUsers(Map<String, AbstractUser> users) {
        this.users = users;
    }

    public BinarySearchTree<Product> getProducts() {
        return products;
    }



    public static Store getStoreInstance() {
        if(storeInstance==null)
            storeInstance=new Store();
        return storeInstance;
    }

    public static void setStoreInstance(Store storeInstance) {
        Store.storeInstance = storeInstance;
    }

    /**
     * Checks product in the {@link Store}
     * @param product String. product name.
     * @return Returns {@link Product} if parameter in  {@link Store}.
     */
    public Product searchProduct(String product) {
        return  products.find(new Product(product));
    }

    /**
     * Adds new product to store
     * @param name Product's name
     * @param price Product's price
     * @param quality Product's quality
     * @return returns false if Product already is addedi otherwise true
     */
    public boolean addProduct(String name, Double price, String quality) {
        if(searchProduct(name) == null) {
            return  false;
        } else {
            products.add(new Product(name,price,quality));
            return true;
        }
    }
    public Double calculateRoute(String route) {
        Double returnValue = 0.0;
        for (int i = 0; i < route.length(); ++i) {
            returnValue += i * route.charAt(i);
        }
        return returnValue;
    }

}
