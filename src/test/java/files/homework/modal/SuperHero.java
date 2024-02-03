package files.homework.modal;

import files.homework.modal.Members;

import java.util.List;

public class SuperHero {
    private String squadName;
    private String homeTown;
    private int formed;
    private String secretBase;
    private boolean active;
    private List<Members> members;


    public String getSquadName() {
        return squadName;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public int getFormed() {
        return formed;
    }

    public String getSecretBase() {
        return secretBase;
    }

    public boolean isActive() {
        return active;
    }

    public List<Members> getMembers() {
        return members;
    }

}
