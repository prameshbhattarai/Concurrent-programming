package thread.application;

public class Main {

    public static void main(String... args) {
        Executor.executeReentrantLock();
        Executor.executeSynchronized();
        Executor.executeAtomic();
    }
}
