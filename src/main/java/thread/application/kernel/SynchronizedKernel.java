package thread.application.kernel;

import thread.application.account.BankAccount;
import thread.application.utility.Log;

public class SynchronizedKernel implements Kernel<BankAccount> {

    public void transferFund(BankAccount sourceAccount, BankAccount destinationAccount, int amount) throws IllegalAccessException {

        // assigning for logger
        StringBuilder message = new StringBuilder(Thread.currentThread().getName())
                .append(" source account ID ").append(sourceAccount.getAccountId())
                .append(" destination account ID ").append(destinationAccount.getAccountId());


        synchronized (sourceAccount) {
            Log.logger(message.toString() + " -> Before process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
            // Lock acquired for Source account.
            if (sourceAccount.getAvailableFund() <= amount)
                throw new IllegalAccessException("Insufficient funds in source account with ID: " + sourceAccount.getAccountId());
            sourceAccount.withdraw(amount);
            synchronized (destinationAccount) {
                // Lock acquired for Destination account.
                destinationAccount.deposit(amount);
            }
            Log.logger(message.toString() + " -> After process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
        }

    }
}
