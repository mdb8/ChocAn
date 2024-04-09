package Users;

public class Provider implements User {
    public String getUsername() { return Username; }
    public String getPassword() { return Password; }

    public String Username;
    public String Password;
    public String ID;
    public String Name;
    public String Address;
    public String City;
    public String State;
    public String Zip;
    public String Email;

    public Provider(String username, String password, String id, String name, String address, String city, String state, String zip, String email) {
        Username = username;
        Password = password;
        ID = id;
        Name = name;
        Address = address;
        City = city;
        State = state;
        Zip = zip;
        Email = email;
    }

    public String toString(){ return STR."\{Username},\{Password},\{ID},\{Name},\{Address},\{City},\{State},\{Zip},\{Email}"; }
}
