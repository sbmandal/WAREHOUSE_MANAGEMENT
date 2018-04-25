package main.java.model.warehouse;

import java.util.List;
import main.java.model.tree.BinarySearchTree;
import  main.java.model.users.AbstractUser;



public class Store {

    private BinarySearchTree<Product>  products;
    private List<AbstractUser> users;
    private Store storeInstance;


    public List<AbstractUser> getUsers() {
        return users;
    }

    public Store getStoreInstance() {
        return storeInstance;
    }

}
