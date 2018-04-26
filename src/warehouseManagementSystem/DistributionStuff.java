package warehouseManagementSystem;

import java.util.List;
import java.util.Map;

public class DistributionStuff extends AbstractUser{
    public DistributionStuff(String name, String email, String password) {
        super(name, email, password);
    }
    public DistributionStuff(String email, String password) {
        super(null, email, password);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String checkUserInfo(AbstractUser user) {

        Map<String,AbstractUser> users=super.getStore().getUsers();
        if(users.containsKey(user.getEmail())) {
            if (users.get(user.getEmail()).getEmail().equals(user.getEmail())
                    && users.get(user.getEmail()).getPassword().equals(user.getPassword())
                    && users.get(user.getEmail()) instanceof DistributionStuff) {
                return users.get(user.getEmail()).getName();
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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
    @Override
    public String toString() {
        return super.toString();
    }
}
