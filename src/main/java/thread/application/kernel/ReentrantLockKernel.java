package thread.application.kernel;

import thread.application.account.BankAccount;
import thread.application.utility.Log;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockKernel implements Kernel<BankAccount> {

    private final ReentrantLock transactionLock = new ReentrantLock();

    public void transferFund(final BankAccount sourceAccount, final BankAccount destinationAccount, int amount) throws IllegalAccessException {

        try {
            // assigning for logger
            StringBuilder message = new StringBuilder(Thread.currentThread().getName())
                    .append(" source account ID ").append(sourceAccount.getAccountId())
                    .append(" destination account ID ").append(destinationAccount.getAccountId())
                    .append(" amount " + amount);
            // acquired lock for fund transfer.
            transactionLock.tryLock(2, TimeUnit.MINUTES);
            Log.logger(message.toString() + " -> Before process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
            if (sourceAccount.getAvailableFund() <= amount)
                throw new IllegalAccessException("Insufficient funds in source account with ID: " + sourceAccount.getAccountId() + " in thread " + Thread.currentThread().getName());
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
            Log.logger(message.toString() + " -> After process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // lock released after fund transfer.
            transactionLock.unlock();
        }

    }

}
