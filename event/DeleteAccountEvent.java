package event;

import enumeration.AccountType;

import static enumeration.EventType.DELETE_ACCOUNT;

public class DeleteAccountEvent extends BaseEvent{
    private final AccountType accountType;

    public DeleteAccountEvent(AccountType accountType) {
        super(DELETE_ACCOUNT);
        this.accountType = accountType;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}
