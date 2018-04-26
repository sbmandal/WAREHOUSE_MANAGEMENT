package warehouseManagementSystem;


import bst.BinarySearchTree;

import java.util.*;

public class StoreStaff extends AbstractUser{

    public StoreStaff(String name, String email, String password) {
        super(name, email, password);
    }

    public StoreStaff(String email, String password) {
        super(null, email, password);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void viewUsers() {
        Object[] keys = super.getStore().getUsers().keySet().toArray();
        for(int i=0;i<super.getStore().getUsers().size();++i){
            if(super.getStore().getUsers().get(keys[i])!=null)
                System.out.println(i+1+" - "+super.getStore().getUsers().get(keys[i]).toString());

        }

    }

    @Override
    public String checkUserInfo(AbstractUser user) {
        Map<String,AbstractUser> users=super.getStore().getUsers();
        if(users.containsKey(user.getEmail())){
            if(users.get(user.getEmail()).getEmail().equals(user.getEmail())
                    && users.get(user.getEmail()).getPassword().equals(user.getPassword())
                    && users.get(user.getEmail()) instanceof StoreStaff){
                return users.get(user.getEmail()).getName();
            }
        }
        return null;
    }

    public boolean registerNewUser(AbstractUser user){

        Map<String,AbstractUser> users=super.getStore().getUsers();
        if(users.get(user.getEmail())!=null){
            System.out.println("There is same user in records!!");
            return false;
        }
        AbstractUser newUser=null;
        if(user instanceof StoreStaff){
            newUser = new StoreStaff(user.getName(),user.getEmail(),user.getPassword());
        }else if(user instanceof DistributionStuff){
            newUser = new DistributionStuff(user.getName(),user.getEmail(),user.getPassword());
        }else if(user instanceof Customer){
            newUser = new Customer(user.getName(),user.getEmail(),user.getPassword());
        }else
            return false;

        super.getStore().getUsers().put(newUser.getEmail(),newUser);
        System.out.println("The user add operation is done");
        return true;
    }


    public boolean removeUser(String email){
        Map<String,AbstractUser> users=super.getStore().getUsers();
        if(users.get(email).getEmail().equals(email)){
            super.getStore().getUsers().remove(email);
            System.out.println("The remove user operation is done");
            return true;
        }

        System.out.println("There is no user in records!!");
        return false;

    }


    public void viewProductsStock(){

        Map<String,String> stock=super.getStore().getProductStock();
        Object []keys=stock.keySet().toArray();

        for(int i=0;i<stock.size();i++){

            System.out.println("product name: "+keys[i].toString()+" -- stock state: "+stock.get(keys[i]));

        }

    }
    public void viewProducts(){
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

    }

    public boolean addStock(String name,int quantity){
        Map<String,String> stock = super.getStore().getProductStock();
        BinarySearchTree<Product> products = super.getStore().getProducts();

        Iterator iter = products.iterator();

        while(iter.hasNext())
        {
            Product product=((Product)iter.next());
            if(product.getName().equals(name)) {
                String quantity1=stock.get(name);
                int sum =(int)Integer.parseInt(quantity1);
                sum+=quantity;
                stock.put(name,String.valueOf(sum));
                return true;
            }

        }
        return false;
    }


    public void storeStaffMenu(StoreStaff storeStaff) {
        boolean state = true;
        try {
            while (state) {
                System.out.println(" *********************************************");
                System.out.println(" ************* STORESTAFF MENU  **************");
                System.out.println(" *  WELCOME," + storeStaff.getName() + "     *");
                System.out.println(" *  View Users                      => 1     *");
                System.out.println(" *  Add New User                    => 2     *");
                System.out.println(" *  Remove User                     => 3     *");
                System.out.println(" *  Increment Stock                 => 4     *");
                System.out.println(" *  View Products                   => 5     *");
                System.out.println(" *  View Products Stock             => 6     *");
                System.out.println(" *  Log Out                         => 0     *");
                System.out.println(" *********************************************");
                Scanner reader = new Scanner(System.in);
                System.out.println("Enter your choose number: ");
                int choose = reader.nextInt();

                AbstractUser user=null;
                switch (choose) {

                    case 1: {
                        storeStaff.viewUsers();
                        break;
                    }
                    case 2: {
                        boolean state2=true;
                        System.out.println(" *  Enter the user name                          *");
                        String name = reader.next();
                        System.out.println(" *  Enter the user email address                 *");
                        String email = reader.next();
                        System.out.println(" *  Enter the user password                      *");
                        String password = reader.next();
                        try {
                            while (state2) {

                                System.out.println(" *********************************************");
                                System.out.println(" ************  Choose User's Type ************");
                                System.out.println(" *                                           *");
                                System.out.println(" *  Customer                => 1             *");
                                System.out.println(" *  StoreStaff              => 2             *");
                                System.out.println(" *  Distributor             => 3             *");
                                System.out.println(" *********************************************");

                                //Scanner reader = new Scanner(System.in);  // Reading from System.in
                                System.out.println("Enter your choose (number): ");
                                int ch = reader.nextInt();

                                if (ch == 1) {
                                    user = new Customer(name, email, password);
                                    state2=false;
                                } else if (ch == 2) {
                                    user = new StoreStaff(name, email, password);
                                    state2=false;
                                } else if (ch == 3) {
                                    user = new DistributionStuff(name, email, password);
                                    state2=false;
                                } else {
                                    System.out.println("Invalid Input,Try again ");
                                }
                            }
                        }catch (Exception e){

                        }


                        if (storeStaff.registerNewUser(user)) {
                            System.out.println(" User added the system");
                        }
                        break;
                    }
                    case 3: {
                        System.out.println(" *  Enter the user email address                 *");
                        String email = reader.next();
                        if (storeStaff.removeUser(email)) {
                            System.out.println(" User removed from the system");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println(" *  Enter the product name                          *");
                        String name = reader.next();
                        System.out.println(" *  Enter the product quantity                   *");
                        int quantity = reader.nextInt();
                        if (storeStaff.addStock(name,quantity)) {
                            System.out.println("This Operation is done");
                        }
                        break;
                    }
                    case 5: {

                        storeStaff.viewProducts();
                        break;
                    }
                    case 6: {

                        storeStaff.viewProductsStock();
                        break;
                    }
                    case 0: {
                        state = false;
                        break;
                    }
                    default:{
                        System.out.println("Invalid Input,Try again ");
                        storeStaffMenu(storeStaff);
                    }

                }
            }
        }catch(InputMismatchException e) {
            System.out.println("Invalid Input,Try again ");
            storeStaffMenu(storeStaff);
        }

    }


    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
