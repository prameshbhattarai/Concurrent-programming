package thread.application;

import thread.application.account.BankAccount;
import thread.application.kernel.*;
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

    private static ArrayList<Thread> getThreads(BankAccount sourceAccount, BankAccount destinationAccount, Kernel<BankAccount> kernel) {
        ArrayList<Thread> threads = new ArrayList<Thread>();
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 10, kernel)));
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 20, kernel)));
        threads.add(new Thread(new Processor<BankAccount>(sourceAccount, destinationAccount, 5, kernel)));
        return threads;
    }

    public static void executeReentrantLock() {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);

        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        executeAndWait(getThreads(sourceAccount, destinationAccount, new ReentrantLockKernel()));

        Log.logger("All threads are done", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }

    public static void executeSynchronized() {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);

        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        executeAndWait(getThreads(sourceAccount, destinationAccount, new SynchronizedKernel()));

        Log.logger("All threads are done", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }

    public static void executeAtomic() {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);

        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        executeAndWait(getThreads(sourceAccount, destinationAccount, new AtomicKernel()));

        Log.logger("All threads are done", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }

    public static void executeNoConcurrent() {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);

        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());

        executeAndWait(getThreads(sourceAccount, destinationAccount, new NoConcurrentKernel()));

        Log.logger("All threads are done", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }


}
