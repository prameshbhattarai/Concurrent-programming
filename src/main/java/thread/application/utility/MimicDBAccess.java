package thread.application.utility;

public class MimicDBAccess {

    public static void simulateTransaction(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
