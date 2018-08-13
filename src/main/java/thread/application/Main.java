package thread.application;

import thread.application.executor.Execute;
import thread.application.executor.Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    public static void main(String... args) {

        // list of different implementation of Thread executors
        List<Consumer<ArrayList<Thread>>> executors = new ArrayList<>();

        // starting thread using thread.join() to control thread execution
        // execute one thread after completion of another
        executors.add(Executor.executeThreadAndWait());


        // starting using ExecutorService thread pools
        // awaiting for completion of threads and shutdown the ExecutorService
        executors.add(Executor.executorService());

        reentrantLock(executors.get(0)); // using reentrant locking
        synchronize(executors.get(0)); // using synchronized block
        atomic(executors.get(0)); // using optimistic lock
//        noLocking(executors.get(0)); // buggy

    }

    /**
     * ReentrantLock - Using try lock and unlock in the object
     * @param executor
     */
    private static void reentrantLock(Consumer<ArrayList<Thread>> executor) {
        // using reentrant lock
        Execute.executeReentrantLock(executor);
    }

    /**
     * Synchronized - Using synchronized block to control execution
     * @param executor
     */
    private static void synchronize(Consumer<ArrayList<Thread>> executor) {
        // using synchronized
        Execute.executeSynchronized(executor);
    }

    /**
     * AtomicReference - Using atomic reference in the executing object
     * @param executor
     */
    private static void atomic(Consumer<ArrayList<Thread>> executor) {
        // using atomic
        Execute.executeAtomic(executor);
    }

    /**
     * Not using any mechanism just starting the thread, buggy
     * @param executor
     */
    private static void noLocking(Consumer<ArrayList<Thread>> executor) {
        // not using concurrent, just running threads ...
        Execute.executeNoLocking(executor);
    }
}
