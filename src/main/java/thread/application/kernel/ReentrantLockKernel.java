package thread.application.kernel;

import thread.application.account.BankAccount;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockKernel implements Kernel<BankAccount> {

    private final ReentrantLock transactionLock = new ReentrantLock();

    public void transferFund(final BankAccount sourceAccount, final BankAccount destinationAccount, int amount) throws IllegalAccessException {
        System.out.println("Source account " + sourceAccount.getAccountId());
        System.out.println("Destination account " + destinationAccount.getAccountId());

        try {
            transactionLock.tryLock(2, TimeUnit.MINUTES);
            System.out.println("Lock acquired for fund transfer.");
            if (sourceAccount.getAvailableFund() <= amount)
                throw new IllegalAccessException("Insufficient funds in source account with ID: " + sourceAccount.getAccountId());
            sourceAccount.withdraw(amount);
            destinationAccount.deposit(amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            transactionLock.unlock();
        }
    }

}
