package Terminals;

import Verification.VerifyOperator;
import OperatorActions.ManageMembers;
import OperatorActions.ManageProviders;

public class OperatorTerminal implements Terminal {
    public void startTerminal() {
        VerifyOperator.authenticate();
        operatorTerminal();
    }

    private void operatorTerminal(){
        String[] options = {"Add Provider", "Delete Provider", "Manage Provider", "Add Member", "Delete Member", "Manage Member"};
        Runnable[] actions = {ManageProviders::addProvider, ManageProviders::deleteProvider, ManageProviders::manageProvider, ManageMembers::addMember, ManageMembers::deleteMember, ManageMembers::manageMember};
        Terminal.terminal(options, actions);
    }
}
