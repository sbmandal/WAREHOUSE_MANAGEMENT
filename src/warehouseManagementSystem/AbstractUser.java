package warehouseManagementSystem;

import java.util.List;

public abstract class AbstractUser implements UserInterface{
    /**
     * kulllaını adi
      */
    private String name;
    /**
     * kullanıcı email
     */
    private String email;
    /**
     * kullanici sifre
     */
    private String password;

    /**
     * otel objesi
     */
    private Store store=Store.getStoreInstance();

    public AbstractUser(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    public Store getStore(){
        return this.store;
    }
    //@Override
    //public abstract void viewRooms();
    @Override
    public abstract void viewUsers();

    /**
     * Bu method kullanici bilgilerini veri yapimiz icinde aramaktadir.
     * @param user bilgilerinin kontrol edilmesini istedigimiz kullanici
     * @return kullanici sistemede kayitliysa ismini return eder degilse null doner.
     */
    @Override
    public String checkUserInfo(AbstractUser user){
        List<AbstractUser> users=this.getStore().getUsers();
        for(int i=0;i<users.size();i++){
            if(users.get(i).getEmail().equals(user.getEmail()) && users.get(i).getPassword().equals(user.getPassword())){
                return users.get(i).getName();
            }
        }
        return null;
    }

    /**
     * objelerin esitlik kontrol
     * @param obj karsilastirilacak obje
     * @return esitse true
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractUser)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        AbstractUser user = (AbstractUser) obj;
        return this.name.equals(user.getName())&&
                this.email.equals(user.getEmail())&&
                this.password.equals(user.getPassword());
    }

    @Override
    public String toString() {
        return "user's name: "+name+" // user's email: "+email;
    }

}