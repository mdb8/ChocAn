//WRITTEN BY RILEY FEE

package ProviderActions;

import Records.MemberRecords;
import Users.Member;

public class ValidateMembers {
    public static Member validateMember(String memberNumber) { //Validates the member number
        for (Member member : MemberRecords.getInstance().members) {
            if (member.ID.equals(memberNumber)) {
                return member; //Returns the member if the member number is valid
            }
        }
        return null; //Returns null if the member number is invalid
    }
}
