package Users;

public class Service {
    public String ServiceDate;
    public String EntryDate;
    public String ProviderID;
    public String MemberID;
    public String ServiceCode;
    public String Comment;

    public Service(String serviceDate, String entryDate, String providerID, String memberID, String serviceCode, String comment){
        ServiceDate = serviceDate;
        EntryDate = entryDate;
        ProviderID = providerID;
        MemberID = memberID;
        ServiceCode = serviceCode;
        Comment = comment;
    }

    public String toString(){ return STR."\{ServiceDate},\{EntryDate},\{ProviderID},\{MemberID},\{ServiceCode},\{Comment}"; }
}
