package main.java.model.users;

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
        //List<AbstractUser> users=super.getStore().getUsers();
        Map<String,AbstractUser> users=super.getStore().getUsers();
        if(users.get(user.getEmail()).getEmail().equals(user.getEmail())
                && users.get(user.getEmail()).getPassword().equals(user.getPassword())
                && users.get(user.getEmail()) instanceof DistributionStuff){
            return users.get(user.getEmail()).getName();
        }
        /*for(int i=0;i<users.size();i++){
            if(users.get(i).getEmail().equals(user.getEmail())
                    && users.get(i).getPassword().equals(user.getPassword())
                    && users.get(i) instanceof DistributionStuff){
                return users.get(i).getName();
            }
        }*/
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
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
    @Override
    public String toString() {
        return super.toString();
    }
}
