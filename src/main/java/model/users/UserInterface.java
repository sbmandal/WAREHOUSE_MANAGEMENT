package main.java.model.users;

public interface UserInterface {

    public String getName();

    public void setName(String name);

    public String getEmail() ;

    public void setEmail(String email);

    public String getPassword();

    public void setPassword(String password);



    public void viewUsers();


    public String checkUserInfo(AbstractUser user);
}
