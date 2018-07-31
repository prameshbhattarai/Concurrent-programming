package thread.application.kernel;

import thread.application.account.Account;

public interface Kernel<T extends Account> {

    void transferFund(final T sourceAccount, final T destinationAccount, int amount) throws IllegalAccessException;
}
