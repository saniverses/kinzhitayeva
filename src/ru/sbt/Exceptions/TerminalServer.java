package ru.sbt.exceptions;

import ru.sbt.exceptions.localExceptions.CashWithdrawFailure;

/**
 * Created by Sania on 14.11.2016.
 */
public class TerminalServer {
    void checkAccount(Client client, Output output) {
        output.printMessage("Your account has " + client.getAccount() + " money.");
    }


    public void getMoney(Client client, int money, Output output) throws CashWithdrawFailure {
        if (client.getAccount() < money) throw new CashWithdrawFailure();
        else {
            client.decreaseAccount(money);
            output.printMessage("Account decreased.");
        }
    }

    public void putMoney(Client client, int money, Output output) {
        client.increaseAccount(money);
        output.printMessage("Account increased.");

    }
}
