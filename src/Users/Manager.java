package Users;

public class Manager implements User {
    private final String Username;
    private final String Password;

    public String getUsername() { return Username; }
    public String getPassword() { return Password; }

    public Manager(String username, String password) {
        Username = username;
        Password = password;
    }
}
