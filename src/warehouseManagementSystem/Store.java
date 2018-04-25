package warehouseManagementSystem;

import bst.*;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<AbstractUser> users = new ArrayList<>();
    private BinarySearchTree<Product>  products;
    private static Store storeInstance;


    public List<AbstractUser> getUsers() {
        return users;
    }

    public void setUsers(List<AbstractUser> users) {
        this.users = users;
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
