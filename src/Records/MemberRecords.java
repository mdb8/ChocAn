package Records;

import Users.Member;
import java.io.*;
import java.util.*;

public class MemberRecords {
    private static MemberRecords all_members = null;
    public ArrayList<Member> members;
    private MemberRecords(ArrayList<Member> m) { this.members = m; }

    public static synchronized void initializeMembers() {
        if (all_members == null) {
            ArrayList<Member> records = new ArrayList<>();

            InputStream inputStream;
            String filePath = STR."\{System.getProperty("user.home")}/Documents/ChocAn/MemberList.txt";
            if (new File(STR."\{filePath}").exists()) {
                try { inputStream = new FileInputStream(STR."\{filePath}"); }
                catch (FileNotFoundException e) { throw new RuntimeException(e); }
            }
            else inputStream = ManagerRecords.class.getResourceAsStream("/DataFiles/MemberList.txt");

            if (inputStream != null) {
                Scanner fileScanner = new Scanner(inputStream);
                while (fileScanner.hasNextLine()) {
                    String[] data = fileScanner.nextLine().split(",");
                    if (data[0] != null) {
                        records.add(new Member(data[0], data[1], data[2], data[3], data[4], data[5], data[6]));
                    }
                }
            }
            else System.out.println("File not found.");
            all_members = new MemberRecords(records);
        }
    }

    public static synchronized void addMember(Member member) {
        if (all_members == null) throw new IllegalStateException("Member not initialized");
        all_members.members.add(member);
    }
    public static synchronized void update() {
        if (all_members == null) throw new IllegalStateException("Service not initialized");

        File file = new File(STR."\{System.getProperty("user.home")}/Documents/ChocAn/MemberList.txt");
        try { file.createNewFile();} catch (IOException e) { throw new RuntimeException(e); }
        java.io.FileWriter myWriter;
        try { myWriter = new java.io.FileWriter(file); } catch (IOException e) { throw new RuntimeException(e); }
        for (Member member : all_members.members) {
            try { myWriter.write(member.toString() + "\n");} catch (IOException e) {throw new RuntimeException(e);}
        }
        try { myWriter.close(); } catch (IOException e) { throw new RuntimeException(e); }
    }

    public static synchronized MemberRecords getInstance() {
        if (all_members == null) throw new IllegalStateException("Member not initialized");
        return all_members;
    }

}