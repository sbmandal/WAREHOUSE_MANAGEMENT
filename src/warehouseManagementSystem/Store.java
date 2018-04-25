package warehouseManagementSystem;

import bst.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Store {
   // private List<AbstractUser> users = new ArrayList<>();
    private Map<String,AbstractUser> users = new HashMap<>();
    private BinarySearchTree<Product>  products;
    private static Store storeInstance;


   /* public List<AbstractUser> getUsers() {
        return users;
    }
*/
    /*public void setUsers(List<AbstractUser> users) {
        this.users = users;
    }
*/

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
}
