package warehouseManagementSystem;

import bst.BinarySearchTree;

import java.util.*;

public class Customer extends AbstractUser{

    public Customer(String name, String email, String password) {
        super(name, email, password);
    }

    public Customer(String email, String password) {
        super(null, email, password);
    }

    @Override
    public String checkUserInfo(AbstractUser user) {
        Map<String,AbstractUser> users=super.getStore().getUsers();
        if(users.containsKey(user.getEmail())) {
            if (users.get(user.getEmail()).getEmail().equals(user.getEmail())
                    && users.get(user.getEmail()).getPassword().equals(user.getPassword())
                    && users.get(user.getEmail()) instanceof Customer) {

                return users.get(user.getEmail()).getName();
            }
        }
        return null;
    }


    @Override
    public void viewUsers(){
        try{
            throw new UnsupportedOperationException();
        }
        catch(UnsupportedOperationException e){
            System.out.println("This Operation is unsupported...");
        }

    }
    public boolean viewProducts(){
        BinarySearchTree<Product> products = super.getStore().getProducts();
        Map<String,String> stock = super.getStore().getProductStock();
        Iterator iter=products.iterator();
        while(iter.hasNext())
        {
            Product product=((Product)iter.next());
            String result=product.toString()+" -- stock state: "+stock.get(product.getName())+" pieces";
            System.out.println(result);
            //return true;
        }
        return false;
    }

    public void customerMenu(Customer customer) {
        boolean state = true;
        try {
            while (state) {
                System.out.println(" *********************************************");
                System.out.println(" ************* CUSTOMER MENU  **************");
                System.out.println(" *  WELCOME," + customer.getName() + "     *");
                System.out.println(" *  View Products                   => 1     *");
                System.out.println(" *  Log Out                         => 0     *");
                System.out.println(" *********************************************");
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Enter your choose number: ");
                int choose = reader.nextInt();

                AbstractUser user=null;
                switch (choose) {

                    case 1: {
                        customer.viewProducts();
                        break;
                    }

                    case 0: {
                        state = false;
                        break;
                    }
                    default:{
                        System.out.println("Invalid Input,Try again ");
                        customerMenu(customer);
                    }

                }
            }
        }catch(InputMismatchException e) {
            System.out.println("Invalid Input,Try again ");
            customerMenu(customer);
        }

    }

}
