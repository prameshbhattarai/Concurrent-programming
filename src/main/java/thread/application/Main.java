package thread.application;

import thread.application.executor.Execute;
import thread.application.executor.Executor;

public class Main {

    public static void main(String... args) {

        // using reentrant lock
        Execute.executeReentrantLock(Executor.executeThread());
        Execute.executeReentrantLock(Executor.executeThreadAndWait());
        Execute.executeReentrantLock(Executor.executorService());

        // using synchronized
        Execute.executeSynchronized(Executor.executeThread());
        Execute.executeSynchronized(Executor.executeThreadAndWait());
        Execute.executeSynchronized(Executor.executorService());

        // using atomic
        Execute.executeAtomic(Executor.executeThread());
        Execute.executeAtomic(Executor.executeThreadAndWait());
        Execute.executeAtomic(Executor.executorService());

        // not using concurrent, just running threads ...
        Execute.executeNoConcurrent(Executor.executeThread());
    }
}
