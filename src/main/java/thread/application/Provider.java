package thread.application;

import thread.application.account.BankAccount;
import thread.application.kernel.Kernel;

import java.util.ArrayList;

public class Provider {

    /**
     * Provide list of Runnable instances
     * @param sourceAccount
     * @param destinationAccount
     * @param kernel
     * @return
     */
    public static ArrayList<Runnable> getRunners(BankAccount sourceAccount, BankAccount destinationAccount, Kernel<BankAccount> kernel) {
        ArrayList<Runnable> runners = new ArrayList<>();
        runners.add(new Processor<>(sourceAccount, destinationAccount, 10, kernel));
        runners.add(new Processor<>(sourceAccount, destinationAccount, 20, kernel));
        runners.add(new Processor<>(sourceAccount, destinationAccount, 5, kernel));
        return runners;
    }

    /**
     * Provide list of Thread instances
     * @param sourceAccount
     * @param destinationAccount
     * @param kernel
     * @return
     */
    public static ArrayList<Thread> getThreads(BankAccount sourceAccount, BankAccount destinationAccount, Kernel<BankAccount> kernel) {
        ArrayList<Thread> threads = new ArrayList<>();
        threads.add(new Thread(new Processor<>(sourceAccount, destinationAccount, 10, kernel)));
        threads.add(new Thread(new Processor<>(sourceAccount, destinationAccount, 20, kernel)));
        threads.add(new Thread(new Processor<>(sourceAccount, destinationAccount, 5, kernel)));
        return threads;
    }
}
