package thread.application.executor;

import thread.application.ThreadsProvider;
import thread.application.account.BankAccount;
import thread.application.kernel.AtomicKernel;
import thread.application.kernel.NoLocking;
import thread.application.kernel.ReentrantLockKernel;
import thread.application.kernel.SynchronizedKernel;
import thread.application.utility.Log;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Execute {

    public static void executeReentrantLock(Consumer<ArrayList<Thread>> executor) {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);
        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
        executor.accept(ThreadsProvider.getThreads(sourceAccount, destinationAccount, new ReentrantLockKernel()));
        Log.logger("Final balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }

    public static void executeSynchronized(Consumer<ArrayList<Thread>> executor) {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);
        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
        executor.accept(ThreadsProvider.getThreads(sourceAccount, destinationAccount, new SynchronizedKernel()));
        Log.logger("Final balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }

    public static void executeAtomic(Consumer<ArrayList<Thread>> executor) {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);
        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
        executor.accept(ThreadsProvider.getThreads(sourceAccount, destinationAccount, new AtomicKernel()));
        Log.logger("Final balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }

    public static void executeNoLocking(Consumer<ArrayList<Thread>> executor) {
        BankAccount sourceAccount = new BankAccount(1L, 100);
        BankAccount destinationAccount = new BankAccount(2L, 80);
        Log.logger("Initial balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
        executor.accept(ThreadsProvider.getThreads(sourceAccount, destinationAccount, new NoLocking()));
        Log.logger("Final balance", sourceAccount.getAvailableFund(), destinationAccount.getAvailableFund());
    }
}
