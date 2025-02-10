package event;

import enumeration.AccountType;

import static enumeration.EventType.WITHDRAW_MONEY;

public class WithdrawMoneyEvent extends BaseEvent {

    private final AccountType accountType;
    private final double balance;
    private final double totalBalance;

    public WithdrawMoneyEvent(AccountType accountType, double balance, double totalBalance) {
        super(WITHDRAW_MONEY);
        this.accountType = accountType;
        this.balance = balance;
        this.totalBalance = totalBalance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public String getBalanceString() {
        return String.format("%.2f", balance);
    }

    public String getTotalBalanceString() {
        return String.format("%.2f", totalBalance);
    }
}
