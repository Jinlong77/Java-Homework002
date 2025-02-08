package Model;

import static Config.Config.*;
import static Config.Message.printError;

public class User {
    private String username;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;

    public boolean setUsername(String username) {
        if (username.matches(REGEX_USERNAME)) {
            this.username = username;
            return true;
        }
        printError("Please enter a valid username using A-Z or a-z.");
        return false;
    }

    public boolean setDateOfBirth(String dateOfBirth) {
        if (dateOfBirth.matches(REGEX_BIRTHDATE)){
            this.dateOfBirth = dateOfBirth;
            return true;
        }
        printError("Please enter a valid date of birth using dd-mm-yyyy format.");
        return false;
    }

    public boolean setGender(String gender) {
        if (gender.matches(REGEX_GENDER)) {
            this.gender = gender;
            return true;
        }
        printError("Please enter a valid gender using [Male/female].");
        return false;
    }

    public boolean setPhone(String phoneNumber) {
        if (phoneNumber.matches(REGEX_PHONE)) {
            this.phoneNumber = phoneNumber;
            return true;
        }
        printError("Please enter a valid phone number using 0********.");
        return false;
    }
}
