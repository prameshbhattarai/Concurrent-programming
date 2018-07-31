package thread.application.utility;

public class Log {

    public synchronized static void logger(String message, int sourceAvailableFund, int destinationAvailableFund) {
        System.out.println("------------------------------------------------");
        System.out.println(message);
        System.out.println("Source account balance " + sourceAvailableFund);
        System.out.println("Destination account balance " + destinationAvailableFund);
        System.out.println("------------------------------------------------");
    }
}
