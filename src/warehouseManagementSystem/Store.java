package warehouseManagementSystem;

import bst.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {

    private Map<String,AbstractUser> users = new HashMap<>();
    private BinarySearchTree<Product>  products =new BinarySearchTree<>();
    private Map<String,String> productStock = new HashMap<>();//stock name ,number

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

    public void setProducts(BinarySearchTree<Product> products) {
        this.products = products;
    }

    public Map<String, String> getProductStock() {
        return productStock;
    }

    public void setProductStock(Map<String, String> productStock) {
        this.productStock = productStock;
    }

    public static Store getStoreInstance() {
        if(storeInstance==null)
            storeInstance=new Store();
        return storeInstance;
    }

    public static void setStoreInstance(Store storeInstance) {
        Store.storeInstance = storeInstance;
    }
}
