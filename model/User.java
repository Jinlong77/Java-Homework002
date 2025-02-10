package model;

import java.time.LocalDate;
import java.time.Period;

import static config.Config.*;
import static config.Message.printError;

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

        if (!dateOfBirth.matches(REGEX_BIRTHDATE)) {
            printError("Please enter a valid date of birth using dd-MM-yyyy format.");
            return false;
        }

        String[] parts = dateOfBirth.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (!isValidDate(day, month, year)) {
            printError("Invalid date: The date does not exist.");
            return false;
        }

        LocalDate birthDate = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        if (birthDate.isAfter(currentDate)) {
            printError("Date of birth cannot be in the future.");
            return false;
        }

        Period age = Period.between(birthDate, currentDate);
        int years = age.getYears();

        if (years < 16 || years > 150) {
            printError("Age must be between 16 and 150 years.");
            return false;
        }

        this.dateOfBirth = dateOfBirth;
        return true;
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
        printError("Please enter a valid phone number using 0 followed by 9 digits(e.g. 0123456789).");
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    private boolean isValidDate(int day, int month, int year) {
        if (year < 1) {
            return false;
        }

        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1 || day > 31) {
            return false;
        }

        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }

        if (month == 2) {
            if (isLeapYear(year)) {
                return day <= 29;
            } else {
                return day <= 28;
            }
        }
        return true;
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
