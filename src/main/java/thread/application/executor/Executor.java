package thread.application.executor;

import thread.application.kernel.Kernel;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class Executor {

    /**
     * Starting the thread only, concurrent consistency should be handle by {@link Kernel}
     */
    public static Consumer<ArrayList<Thread>> executeThread() {
        return (threads) -> {
            threads.stream().forEach(Thread::start);
        };
    }

    /**
     * Thread.join() will not allow other thread
     * to start until the first join thread finish and release the main thread
     * so consistency is maintain
     */
    public static Consumer<ArrayList<Thread>> executeThreadAndWait() {
        return (threads) -> {
            threads.stream().forEach(Thread::start);
            try {
                for(Thread t : threads) t.join();
                System.out.println("finish all threads using thread join");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * Running all the Runnable using {@link ExecutorService}
     */
//    public static Consumer<ArrayList<Runnable>> executeRunnable() {
//        return (runners) -> {
//            ExecutorService executorService = Executors.newCachedThreadPool();
//            runners.stream().forEach(executorService::execute);
//            executorService.shutdown();
//            try {
//                boolean finished = executorService.awaitTermination(1, TimeUnit.MINUTES);
//                if(finished) System.out.println("finish all runnable threads using ExecutorService");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//    }

}
