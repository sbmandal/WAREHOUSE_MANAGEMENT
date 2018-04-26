package warehouseManagementSystem;

import bst.BinarySearchTree;

import javax.swing.text.html.HTMLDocument;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Dashboard {
    private static StoreStaff storeStaff = new StoreStaff("admin","admin@gmail.com","password");
    private static Customer customer = new Customer("Customer","customer@gmail.com","password");
    private static DistributionStuff  dist= new DistributionStuff("Customer","customer@gmail.com","password");
    private static AbstractUser user=null;

    public static void main (String []argv){

        BinarySearchTree<Product> products = new BinarySearchTree<>();
        Map<String,String> stock=new HashMap<>();

        if(!loadUsersFromFile("users.csv"))
            storeStaff.registerNewUser(storeStaff);


        if(!loadProductsFromFile("products.csv")){

            products.add(new Product("productA",2.2,"Super"));
            products.add(new Product("productB",3.1,"Super"));
            products.add(new Product("productC",5.1,"FirstClass"));

            storeStaff.getStore().setProducts(products);

        }
        if(!loadProductsStockFromFile("stocks.csv")){
            storeStaff.getStore().getProductStock().put("productA","50");
            storeStaff.getStore().getProductStock().put("productB","60");
            storeStaff.getStore().getProductStock().put("productC","70");
        }
        mainMenu();
    }

    private static void mainMenu(){
        boolean state=true;
        try {
            while (state) {

                System.out.println(" *********************************************");
                System.out.println(" ***************  MAIN MENU  *****************");
                System.out.println(" *                                           *");
                System.out.println(" *  Are you Customer                => 1     *");
                System.out.println(" *  Are you StoreStaff              => 2     *");
                System.out.println(" *  Are you Distributor             => 3     *");
                System.out.println(" *  Exit                            => 0     *");
                System.out.println(" *********************************************");

                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Enter your choose (number): ");
                int choose = reader.nextInt();

                String name = null;
                if (choose == 1) {
                    if ((name = userAuthentication(reader, 1)) != null) {
                        user.setName(name);
                        customer.customerMenu((Customer)user);
                        saveUsersToFile("users.csv");
                        saveProductsToFile("products.csv");
                        saveProductsStockToFile("stocks.csv");
                    } else {
                        System.out.println("Invalid login, please try again");
                    }

                } else if (choose == 2) {
                    if ((name = userAuthentication(reader, 2)) != null) {
                        user.setName(name);
                        storeStaff.storeStaffMenu((StoreStaff) user);
                        saveUsersToFile("users.csv");
                        saveProductsToFile("products.csv");
                        saveProductsStockToFile("stocks.csv");
                    } else {
                        System.out.println("Invalid login, please try again");
                    }

                } else if (choose == 3) {
                    if ((name = userAuthentication(reader, 2)) != null) {
                        user.setName(name);
                        storeStaff.storeStaffMenu((StoreStaff) user);
                        saveUsersToFile("users.csv");
                        saveProductsToFile("products.csv");
                        saveProductsStockToFile("stocks.csv");

                    } else {
                        System.out.println("Invalid login, please try again");
                    }

                } else if (choose == 0) {
                    state = false;
                    System.out.println("The program is finished.");
                    saveUsersToFile("users.csv");
                    saveProductsToFile("products.csv");
                    saveProductsStockToFile("stocks.csv");

                } else {
                    System.out.println("Invalid Input,Try again ");
                }
            }
        }catch(InputMismatchException e) {
            System.out.println("Invalid Input,Try again ");
            mainMenu();
        }

    }
    /**
     *
     * @param reader reader
     * @param type user tip
     * @return sisteme giris yapacak olan kisinin ismi
     */
    private static String userAuthentication(Scanner reader,int type){
        System.out.println(" *  Enter your email                         *");
        String email = reader.next();
        System.out.println(" *  Enter your password                      *");
        String password = reader.next();
        if(type == 1){
            user=new Customer(email,password);
        }else if(type == 2){
            user=new StoreStaff(email,password);
        }else if(type == 3){
            user=new DistributionStuff(email,password);
        }
        return polimorfism(user);
    }

    /**
     * Polimorfism e ornek
     * @param a bilgileri kontrol edilecek kullanıcı
     * @return kullanicinin adi
     */
    private static String polimorfism(AbstractUser a){
        return a.checkUserInfo(a);
    }

    /*------------------------ FILE OPERATIONS ----------------------------------*/
    /**
     *
     * Bu method user bilgilerini dosyadan veri yapısına yüklemede kullanilir.
     * @param filename bilgilerin alindigi dosya adi.
     */
    private static boolean loadUsersFromFile(String filename) {
        boolean flag=false;
        String line = "";
        String cvsSplitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null) {
                flag=true;
                String[] token = line.split(cvsSplitBy);
                if(token[3].equals("customer")) {
                    storeStaff.getStore().getUsers().put(token[1],new Customer(token[0],token[1],token[2]));
                }else if(token[3].equals("store")){
                    storeStaff.getStore().getUsers().put(token[1],new StoreStaff(token[0],token[1],token[2]));
                }else if(token[3].equals("distributor")){
                    storeStaff.getStore().getUsers().put(token[1],new DistributionStuff(token[0],token[1],token[2]));
                }
                //System.out.println(token[0] + " " + token[1] + " " + token[2]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    private static void saveUsersToFile( String filename) {

        FileWriter fileWriter = null;

        try{

            fileWriter = new FileWriter(filename);
            Object[] keys = storeStaff.getStore().getUsers().keySet().toArray();
            for(int i=0;i<storeStaff.getStore().getUsers().size();++i){

                fileWriter.append(String.valueOf(storeStaff.getStore().getUsers().get(keys[i]).getName()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(storeStaff.getStore().getUsers().get(keys[i]).getEmail()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(storeStaff.getStore().getUsers().get(keys[i]).getPassword()));
                fileWriter.append(",");
                if(storeStaff.getStore().getUsers().get(keys[i]) instanceof Customer) {
                    fileWriter.append(String.valueOf("customer"));
                    fileWriter.append("\n");
                }else if(storeStaff.getStore().getUsers().get(keys[i]) instanceof StoreStaff){
                    fileWriter.append(String.valueOf("store"));
                    fileWriter.append("\n");
                }else if(storeStaff.getStore().getUsers().get(keys[i]) instanceof DistributionStuff){
                    fileWriter.append(String.valueOf("distributor"));
                    fileWriter.append("\n");
                }
            }

        }catch (NullPointerException e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            }catch (NullPointerException e) {
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            }catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }

    private static boolean loadProductsFromFile(String filename) {
        boolean flag=false;
        String line = "";
        String cvsSplitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null) {
                flag=true;
                String[] token = line.split(cvsSplitBy);
                //System.out.println(token[0] + " " + token[1] + " " + token[2]);
                storeStaff.getStore().getProducts().add(new Product(token[0],(double)Double.parseDouble(token[1]),token[2]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static void saveProductsToFile( String filename) {

        FileWriter fileWriter = null;

        try{

            fileWriter = new FileWriter(filename);
            BinarySearchTree<Product> products = storeStaff.getStore().getProducts();
            Iterator iter = products.iterator();
           while (iter.hasNext()){
                Product product = (Product) iter.next();
                fileWriter.append(String.valueOf(product.getName()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(product.getUnitPrice()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(product.getQuality()));
                fileWriter.append("\n");
            }

        }catch (NullPointerException e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            }catch (NullPointerException e) {
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            }catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }

    private static boolean loadProductsStockFromFile(String filename) {
        boolean flag=false;
        String line = "";
        String cvsSplitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            while ((line = br.readLine()) != null) {
                flag=true;
                String[] token = line.split(cvsSplitBy);

                storeStaff.getStore().getProductStock().put(token[0],token[1]);
                //storeStaff.addStock(token[0],(int)Integer.parseInt(token[1]));
                //System.out.println(token[0] + " " + token[1] + " " + token[2]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return flag;
    }


    private static void saveProductsStockToFile( String filename) {

        FileWriter fileWriter = null;

        try{

            fileWriter = new FileWriter(filename);
            Object[] keys = storeStaff.getStore().getProductStock().keySet().toArray();
            for(int i=0;i<storeStaff.getStore().getProductStock().size();++i){

                fileWriter.append(String.valueOf(keys[i].toString()));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(storeStaff.getStore().getProductStock().get(keys[i])));
                fileWriter.append("\n");

            }

        }catch (NullPointerException e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }finally {

            try {
                fileWriter.flush();
                fileWriter.close();
            }catch (NullPointerException e) {
                System.out.println("Error in CsvFileWriter !!!");
                e.printStackTrace();
            }catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }

        }

    }


}



