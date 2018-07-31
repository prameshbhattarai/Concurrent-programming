package thread.application;

import thread.application.account.BankAccount;
import thread.application.kernel.ReentrantLockKernel;
import thread.application.kernel.SynchronizedKernel;
import thread.application.utility.Log;

import java.util.ArrayList;

public class Executor {

    private static void executeAndWait(ArrayList<Thread> threads) {
        threads.stream().forEach(Thread::start);
        try {
            for(Thread t : threads) t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeReentrantLock() {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);

        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        ArrayList<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 10, new ReentrantLockKernel())));
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 20, new ReentrantLockKernel())));
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 5, new ReentrantLockKernel())));

        executeAndWait(threads);

        Log.logger("All threads are done", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }

    public static void executeSynchronized() {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);

        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        ArrayList<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 10, new SynchronizedKernel())));
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 20, new SynchronizedKernel())));
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 5, new SynchronizedKernel())));

        executeAndWait(threads);

        Log.logger("All threads are done", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }


}
