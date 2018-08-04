package thread.application.kernel;

import thread.application.account.BankAccount;
import thread.application.utility.Log;

public class NoLocking implements Kernel<BankAccount>{

    /**
     * Process the fund transfer process
     * @param sourceAccount
     * @param destinationAccount
     * @param amount
     * @throws IllegalAccessException
     */
    @Override
    public void transferFund(BankAccount sourceAccount, BankAccount destinationAccount, int amount) throws IllegalAccessException {
        StringBuilder message = new StringBuilder(Thread.currentThread().getName())
                .append(" source account ID ").append(sourceAccount.getAccountId())
                .append(" destination account ID ").append(destinationAccount.getAccountId())
                .append(" amount ").append(amount);
        Log.logger(message.toString() + " -> Before process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        if (sourceAccount.getAvailableFund() <= amount)
            throw new IllegalAccessException("Insufficient funds in source account with ID: " + sourceAccount.getAccountId() + " in thread " + Thread.currentThread().getName());

        sourceAccount.withdraw(amount);
        destinationAccount.deposit(amount);

        Log.logger(message.toString() + " -> After process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }
}
