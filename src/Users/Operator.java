package Users;

public class Operator implements User{
    private final String Username;
    private final String Password;
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }

    public Operator(String username, String password) {
        Username = username;
        Password = password;
    }
}
