package Users;

public class Member {
    public String Name;
    public String ID;
    public String Address;
    public String City;
    public String State;
    public String Zip;
    public String Email;

    public Member (String name, String id, String address, String city, String state, String zip, String email){
        this.Name = name;
        this.ID = id;
        this.Address = address;
        this.City = city;
        this.State = state;
        this.Zip = zip;
        this.Email = email;
    }

    public String toString(){ return STR."\{Name},\{ID},\{Address},\{City},\{State},\{Zip},\{Email}"; }
}
