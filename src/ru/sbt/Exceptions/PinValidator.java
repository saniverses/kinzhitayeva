package ru.sbt.exceptions;

import ru.sbt.exceptions.localExceptions.AccountIsLockedException;
import ru.sbt.exceptions.localExceptions.InvalidUserPinException;

/**
 * Created by Sania on 14.11.2016.
 */
public class PinValidator {
    private int quantityOfEntering;
    private boolean isLocked;
    private Pin pin;
    private boolean timerStarted;
    private long time;


    public PinValidator() {
        quantityOfEntering = 0;
        isLocked = false;
        timerStarted = false;
        time = 0;
    }

    boolean checkPin(Pin pin, Client client) throws InvalidUserPinException, AccountIsLockedException {
        if (!isLocked) {
            if (pin.equals(client.getPin())) {
                //quantityOfEntering++;
                return true;
            } else if (quantityOfEntering < 3) {
                quantityOfEntering++;
                throw new InvalidUserPinException();
            } else {
                isLocked = true;
                quantityOfEntering = 0;
                throw new AccountIsLockedException();
            }
        } else {
            throw new AccountIsLockedException();
        }
    }

    public long unlock(long tStart) {
        long tEnd;
        time = tStart + 5000;
        int k = 2;
        while (System.currentTimeMillis() < time) {
            tEnd = time;
            //if (System.currentTimeMillis() % 1000 == 0) System.out.print(".");
        }
        //System.out.println();
        isLocked = false;
        return time;
    }

    public long getEndTime() {
        return time;
    }
}
