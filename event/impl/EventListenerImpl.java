package event.impl;
import event.*;
import event.listener.EventListener;

import static config.Color.LIGHT_CYAN;
import static config.Color.RESET;
import static config.Message.*;
import static enumeration.AccountType.CHECKING_ACCOUNT;
import static enumeration.AccountType.SAVING_ACCOUNT;

public class EventListenerImpl implements EventListener {

    @Override
    public void onEvent(BaseEvent event) {
        switch (event.getEventType()) {
            case CREATE_ACCOUNT -> handleCreateAccountEvent((CreateAccountEvent) event);
            case DEPOSIT_MONEY -> handleDepositMoneyEvent((DepositMoneyEvent) event);
            case WITHDRAW_MONEY -> handleWithdrawMoneyEvent((WithdrawMoneyEvent) event);
            case TRANSFER_MONEY -> handleTransferMoneyEvent((TransferMoneyEvent) event);
            case DISPLAY_ACCOUNT -> printSuccess("Displaying account information...");
            case DELETE_ACCOUNT -> handleDeleteAccountEvent((DeleteAccountEvent) event);
        }
    }

    private void handleCreateAccountEvent(CreateAccountEvent event) {
        switch (event.getAccountType()) {
            case CHECKING_ACCOUNT -> printSuccess("Your name is " + LIGHT_CYAN + event.getUser().getUsername() + RESET + " and your Checking Account has been created successfully!");
            case SAVING_ACCOUNT -> printSuccess("Your name is " + LIGHT_CYAN + event.getUser().getUsername() + RESET + " and your Saving Account has been created successfully!");
        }
    }

    private void handleDepositMoneyEvent(DepositMoneyEvent event) {
        switch (event.getAccountType()) {
            case CHECKING_ACCOUNT -> {
                event.getCheckingAccount().deposit(event.getBalance());
                printSuccessDeposit(event.getAccountType().getValue(), event.getBalanceString(), event.getCheckingAccount().getBalanceString());
            }
            case SAVING_ACCOUNT -> {
                event.getSavingAccount().deposit(event.getBalance());
                printSuccessDeposit(event.getAccountType().getValue(), event.getBalanceString(), event.getSavingAccount().getBalanceString());
            }
        }
    }

    private void handleWithdrawMoneyEvent(WithdrawMoneyEvent event) {
        switch (event.getAccountType()) {
            case CHECKING_ACCOUNT -> printSuccessWithdraw(CHECKING_ACCOUNT.getValue(), event.getBalanceString(), event.getTotalBalanceString());
            case SAVING_ACCOUNT -> printSuccessWithdraw(SAVING_ACCOUNT.getValue(), event.getBalanceString(), event.getTotalBalanceString());
        }
    }

    private void handleTransferMoneyEvent(TransferMoneyEvent event) {
        printSuccess("Transfer successful! from " + event.getEventType() + " to " + event.getToAccountType().getValue());
    }

    private void handleDeleteAccountEvent(DeleteAccountEvent event) {
        switch (event.getAccountType()) {
            case CHECKING_ACCOUNT -> printSuccess("Your Checking Account has been deleted successfully!");
            case SAVING_ACCOUNT -> printSuccess("Your Saving Account has been deleted successfully!");
        }
    }
}
