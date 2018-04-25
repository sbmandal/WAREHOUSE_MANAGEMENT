package warehouseManagementSystem;

import java.util.List;

public class Customer extends AbstractUser{

    public Customer(String name, String email, String password) {
        super(name, email, password);
    }

    public Customer(String email, String password) {
        super(null, email, password);
    }

    @Override
    public String checkUserInfo(AbstractUser user) {
        List<AbstractUser> users=super.getStore().getUsers();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getEmail().equals(user.getEmail())
                    && users.get(i).getPassword().equals(user.getPassword())
                    && users.get(i) instanceof Customer){
                return users.get(i).getName();
            }
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
