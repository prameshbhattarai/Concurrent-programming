package thread.application;

import thread.application.account.BankAccount;
import thread.application.kernel.Kernel;

public class Processor<T extends BankAccount> implements Runnable {

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
        StringBuilder log =  new StringBuilder(Thread.currentThread().getName())
                .append(" starting transaction from ")
                .append(sourceBankAccount.getAccountId())
                .append(" to ")
                .append(destinationBankAccount.getAccountId());
        System.out.println(log);

        try {
            kernel.transferFund(sourceBankAccount, destinationBankAccount, transactionAmount);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
