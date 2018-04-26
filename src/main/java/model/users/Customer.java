package main.java.model.users;

import java.util.Map;

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
        if(users.get(user.getEmail()).getEmail().equals(user.getEmail())
                && users.get(user.getEmail()).getPassword().equals(user.getPassword())
                && users.get(user.getEmail()) instanceof Customer){
            return users.get(user.getEmail()).getName();
        }
        return null;
    }

    /**
     * Bu method sistemdeki kullanicilari print eder .
     * Sadece resepsiyoniste ait bir method dur bunu hata firlatmaya ornek olarak yaptim.
     */
    @Override
    public void viewUsers(){
        try{
            throw new UnsupportedOperationException();
        }
        catch(UnsupportedOperationException e){
            System.out.println("This Operation is unsupported...");
        }

    }
}
