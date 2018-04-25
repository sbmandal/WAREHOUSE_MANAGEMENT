package warehouseManagementSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Dashboard {
    private static StoreStaff storeStaff = new StoreStaff("admin","admin@gmail.com","password");
   // private static HotelGuest hotelGuest = new HotelGuest("guest","guest","guest");
    private static AbstractUser user=null;

    public static void main (String []argv){
      //  List<Room> rooms = new ArrayList<Room>();
        boolean state=true;
        if(!loadUsersFromFile("users.csv"))
            storeStaff.registerNewUser(storeStaff);

        //dosyada odalar kayıtlı degilse default olarak otel odalarını olusturuyorum.
        /*if(!loadRoomsFromFile("rooms.csv")){

            for(int i=1;i<=3;i++){
                rooms.add(new Room(i,false,false,"for 2 people",10.2));
            }
            receptionist.getHotel().setRooms(rooms);
        }*/
        /*if(!loadRecordsFromFile("records.csv")){
            for(int i=0;i<rooms.size();i++){
                receptionist.getHotel().getHotelRecords()[i][0]=String.valueOf(receptionist.getHotel().getRooms().get(i).getRoomNumber());
                receptionist.getHotel().getHotelRecords()[i][1]=null;
                receptionist.getHotel().getHotelRecords()[i][2]=null;
            }
        }*/
        try {
            while (state) {

                System.out.println(" *********************************************");
                System.out.println(" ***************  MAIN MENU  *****************");
                System.out.println(" *                                           *");
                System.out.println(" *  Are you Customer                => 1     *");
                System.out.println(" *  Are you StoreStaff              => 2     *");
                System.out.println(" *  Are you Distributor             => 3     *");
                System.out.println(" *  Exit                            => 4     *");
                System.out.println(" *********************************************");

                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Enter your choose (number): ");
                int choose = reader.nextInt();

                String name = null;
                if (choose == 1) {
                    if ((name = userAuthentication(reader, 1)) != null) {
                        user.setName(name);
                        //hotelGuest.guestMenu((HotelGuest) user);
                        saveUsersToFile("users.csv");
                       // saveRoomsToFile("rooms.csv");
                        //saveRecordsToFile("records.csv");
                    } else {
                        System.out.println("Invalid login, please try again");
                    }

                } else if (choose == 2) {
                    if ((name = userAuthentication(reader, 2)) != null) {
                        user.setName(name);
                        storeStaff.receptionistMenu((StoreStaff) user);
                        saveUsersToFile("users.csv");
                       // saveRoomsToFile("rooms.csv");
                      //  saveRecordsToFile("records.csv");
                    } else {
                        System.out.println("Invalid login, please try again");
                    }

                } else if (choose == 3) {
                    state = false;
                    System.out.println("The program is finished.");
                    saveUsersToFile("users.csv");
                    //saveRoomsToFile("rooms.csv");
                    //saveRecordsToFile("records.csv");
                }else{
                    System.out.println("Invalid Input,Try again ");
                }
            }
        }catch(InputMismatchException e) {
            System.out.println("Invalid Input,Try again ");
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
            user=new DistributionStuff(email,password);
        }else if(type == 2){
            user=new StoreStaff(email,password);
        }else if(type == 3){
            user=new Customer(email,password);
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

    /**
     *Bu method kullanicilari dosyaya kaydeder.
     *
     * Dosyadan okurken kimin ne oldugu anlasilmsı icin
     * @param filename kayit yapilan dosya adi.
     */
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


}



