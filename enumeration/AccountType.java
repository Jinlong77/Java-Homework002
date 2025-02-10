package enumeration;

public enum AccountType {
    CHECKING_ACCOUNT("Checking Account"),
    SAVING_ACCOUNT("Saving Account");

    private final String value;


    AccountType(String accountType) {
        this.value = accountType;
    }

    public String getValue() {
        return value;
    }
}