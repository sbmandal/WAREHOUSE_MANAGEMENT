package main.java.model.users;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

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
       /* for(int i=0;i<super.getStore().getUsers().size();i++)
            if(super.getStore().getUsers().get(i)!=null)
                System.out.println(i+1+" - "+super.getStore().getUsers().get(i).toString());*/
    }

    @Override
    public String checkUserInfo(AbstractUser user) {
        Map<String,AbstractUser> users=super.getStore().getUsers();
        if(users.get(user.getEmail()).getEmail().equals(user.getEmail())
                && users.get(user.getEmail()).getPassword().equals(user.getPassword())
                && users.get(user.getEmail()) instanceof StoreStaff){
            return users.get(user.getEmail()).getName();
        }
        return null;
    }

    public boolean registerNewUser(AbstractUser user){
        /*for(int i=0;i<super.getStore().getUsers().size();i++){
            if(super.getStore().getUsers().get(i).equals(user)){
                System.out.println("There is same user in records!!");
                return false;
            }
        }*/
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
        //super.getStore().getUsers().add(newUser);
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
       // return false;
        /*for(int i=0;i<super.getStore().getUsers().size();i++){
            if(super.getStore().getUsers().get(i).getEmail().equals(email)){
                super.getStore().getUsers().remove(i);
                System.out.println("The remove user operation is done");
                return true;
            }
        }*/
        System.out.println("There is no user in records!!");
        return false;

    }
    /**
     * Bu method resepsiyonist in yapabilecegi operasyonlari gostermektedir.
     * Bu menude yapilan degisiklikler log out olunduktan sonra dosyaya kaydedilir
     * @param storeStaff obje
     *
     */
    public void receptionistMenu(StoreStaff storeStaff) {
        boolean state = true;
        try {
            while (state) {
                System.out.println(" *********************************************");
                System.out.println(" ************* RECEPTIONIST MENU  ************");
                System.out.println(" *  WELCOME," + storeStaff.getName() + "   *");
                System.out.println(" *  View Users                      => 1     *");
                System.out.println(" *  Add New User                    => 2     *");
                System.out.println(" *  Remove User                     => 3     *");
                System.out.println(" *  Log Out                         => 4    *");
                System.out.println(" *********************************************");
                Scanner reader = new Scanner(System.in);  // Reading from System.in
                System.out.println("Enter your choose number: ");
                int choose = reader.nextInt();

                AbstractUser user=null;
                switch (choose) {
                  /*  case 6: {
                        storeStaff.viewRooms();
                        break;
                    }
                    case 1: {
                        System.out.println("Enter the room number to book Room : ");//buraya boolean ekle
                        int roomNumber = reader.nextInt();
                        System.out.println("Enter the guest name to book Room  ");
                        String name = reader.next();
                        HotelGuest h;
                        if(( h = findUser(name) )!=null)
                            storeStaff.bookRoom(roomNumber,h);
                        else
                            storeStaff.bookRoom(roomNumber,new HotelGuest(name));
                        break;
                    }
                    case 2: {
                        System.out.println("Enter the room number for cancel reservation: ");//buraya boolean ekle
                        int roomNumber = reader.nextInt();
                        System.out.println("Enter the guest name to check in  ");
                        String name = reader.next();
                        HotelGuest h;
                        if(( h = findUser(name) )!=null)
                            storeStaff.cancelReservation(roomNumber,h);
                        else
                            storeStaff.cancelReservation(roomNumber,new HotelGuest(name));
                        break;
                    }
                    case 3: {
                        System.out.println("Enter the room number to check in: ");//buraya boolean ekle
                        int roomNumber = reader.nextInt();
                        System.out.println("Enter the guest name to check in  ");
                        String name = reader.next();
                        HotelGuest h;
                        if(( h = findUser(name) )!=null)
                            storeStaff.checkIn(roomNumber,h);
                        else
                            storeStaff.checkIn(roomNumber,new HotelGuest(name));
                        break;
                    }
                    case 4: {
                        System.out.println("Enter the room number to check out: ");//buraya boolean ekle
                        int roomNumber = reader.nextInt();
                        System.out.println("Enter the guest name to check out  ");
                        String name = reader.next();
                        HotelGuest h;
                        if(( h = findUser(name) )!=null)
                            storeStaff.checkOut(roomNumber,h);
                        else
                            storeStaff.checkOut(roomNumber,new HotelGuest(name));
                        break;
                    }*/
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
                        /*System.out.println(" *  Enter the user name                          *");
                        String name = reader.next();*/
                        System.out.println(" *  Enter the user email address                 *");
                        String email = reader.next();
                        /*System.out.println(" *  Enter the user password                      *");
                        String password = reader.next();*/
                       // user = new HotelGuest(name, email, password);
                        if (storeStaff.removeUser(email)) {
                            System.out.println(" User removed from the system");
                        }
                        break;
                    }
                    case 1: {
                        storeStaff.viewUsers();
                        break;
                    }

                    case 4: {
                        state = false;
                        break;
                    }
                    default:{
                        System.out.println("Invalid Input,Try again ");
                        receptionistMenu(storeStaff);
                    }

                }
            }
        }catch(InputMismatchException e) {
            System.out.println("Invalid Input,Try again ");
            receptionistMenu(storeStaff);
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
