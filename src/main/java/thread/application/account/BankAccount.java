package thread.application.account;

import thread.application.utility.MimicDBAccess;

public class BankAccount implements Account {

    private final Long accountId;
    private int accountAmount;

    public BankAccount(Long accountId, int accountAmount) {
        this.accountId = accountId;
        this.accountAmount = accountAmount;
    }

    public long getAccountId() {
        return this.accountId;
    }

    public int getAvailableFund() {
        return this.accountAmount;
    }

    public void withdraw(int amount) {
        MimicDBAccess.simulateTransaction(100L);
        this.accountAmount -= amount;
    }

    public void deposit(int amount) {
        MimicDBAccess.simulateTransaction(100L);
        this.accountAmount += amount;
    }
}
