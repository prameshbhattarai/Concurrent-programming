package thread.application;

import thread.application.account.BankAccount;
import thread.application.kernel.ReentrantLockKernel;

public class Main {

    public static void main(String... args) {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);

        Thread t1 = new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 10, new ReentrantLockKernel()));
        Thread t2 = new Thread(new Processor<BankAccount>(destinationAccount, sourceAccount, 20,new ReentrantLockKernel()));
        Thread t3 = new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 5, new ReentrantLockKernel()));

        System.out.println("Source account balance " + sourceAccount.getAvailableFund());
        System.out.println("Destination account balance " + sourceAccount.getAvailableFund());

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All threads are done");
        System.out.println("Source account balance " + sourceAccount.getAvailableFund());
        System.out.println("Destination account balance " + sourceAccount.getAvailableFund());
    }
}
