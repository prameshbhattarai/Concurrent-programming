package thread.application;

import thread.application.executor.Execute;
import thread.application.executor.Executor;

public class Main {

    public static void main(String... args) {
        Execute.executeReentrantLock(Executor.executeThread());
//        Execute.executeSynchronized(Executor.executeThread()); // working fine
//        Execute.executeAtomic(Executor.executeThread());
//        Execute.executeNoConcurrent(Executor.executeThread());
    }
}
