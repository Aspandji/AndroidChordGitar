package pandji.com.chordgitar.model;

public class users {
    private  String Name;
    private  String Password;

    public users() {
    }

    public users(String name, String password) {
        Name = name;
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
