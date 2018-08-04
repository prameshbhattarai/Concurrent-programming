package thread.application.kernel;

import thread.application.account.BankAccount;
import thread.application.utility.Log;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicKernel implements Kernel<BankAccount> {

    /**
     * Implementation using {@link AtomicReference}
     * @param sourceAccount
     * @param destinationAccount
     * @param amount
     * @throws IllegalAccessException
     */
    @Override
    public void transferFund(BankAccount sourceAccount, BankAccount destinationAccount, int amount) throws IllegalAccessException {
        // setting atomic reference in the source account and destination account
        AtomicReference<BankAccount> atomicSourceAccount = new AtomicReference<>(sourceAccount);
        AtomicReference<BankAccount> atomicDestinationAccount = new AtomicReference<>(destinationAccount);

        // assigning for logger
        StringBuilder message = new StringBuilder(Thread.currentThread().getName())
                .append(" source account ID ").append(sourceAccount.getAccountId())
                .append(" destination account ID ").append(destinationAccount.getAccountId())
                .append(" amount ").append(amount);


        if (sourceAccount.getAvailableFund() <= amount)
            throw new IllegalAccessException("Insufficient funds in source account with ID: " + sourceAccount.getAccountId() + " in thread " + Thread.currentThread().getName());

        BankAccount cachedSourceAccountToUpdate = atomicSourceAccount.get();
        sourceAccount.withdraw(amount);
        if(atomicSourceAccount.compareAndSet(cachedSourceAccountToUpdate, sourceAccount))
            Log.logger(message.toString() + " -> Before process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        BankAccount cachedDestinationAccountToUpdate = atomicDestinationAccount.get();
        destinationAccount.deposit(amount);
        if(atomicDestinationAccount.compareAndSet(cachedDestinationAccountToUpdate, destinationAccount))
            Log.logger(message.toString() + " -> After process ", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }
}
