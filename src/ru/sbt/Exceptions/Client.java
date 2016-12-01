package ru.sbt.exceptions;

/**
 * Created by Sania on 15.11.2016.
 */
public class Client {
    private Pin pin;
    private int account;

    public Client(Pin pin, int account) {
        this.pin = pin;
        this.account = account;
    }

    public Pin getPin() {
        return pin;
    }

    public int getAccount() {
        return account;
    }

    public void increaseAccount(int money) {
        account += money;
    }

    public void decreaseAccount(int money) {
        account -= money;
    }
}
