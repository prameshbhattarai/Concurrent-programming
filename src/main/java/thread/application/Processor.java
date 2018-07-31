package thread.application;

import thread.application.account.Account;
import thread.application.kernel.Kernel;

public class Processor<T extends Account> implements Runnable {

    private final T sourceBankAccount;
    private final T destinationBankAccount;
    private final int transactionAmount;
    private final Kernel<T> kernel;

    public Processor(T sourceBankAccount, T destinationBankAccount, int transactionAmount, Kernel<T> kernel) {
        this.sourceBankAccount = sourceBankAccount;
        this.destinationBankAccount = destinationBankAccount;
        this.transactionAmount = transactionAmount;
        this.kernel = kernel;
    }

    public void run() {
        try {
            kernel.transferFund(sourceBankAccount, destinationBankAccount, transactionAmount);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
