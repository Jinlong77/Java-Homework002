package event;

import enumeration.AccountType;
import model.User;

import static enumeration.EventType.CREATE_ACCOUNT;

public class CreateAccountEvent extends BaseEvent {
    private final AccountType accountType;
    private final User user;

    public CreateAccountEvent(AccountType accountType, User user) {
        super(CREATE_ACCOUNT);
        this.accountType = accountType;
        this.user = user;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public User getUser() {
        return user;
    }
}
