package ru.sbt.exceptions;

import ru.sbt.exceptions.localExceptions.*;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sania on 14.11.2016.
 */
public class TerminalImpl implements Terminal {
    private final TerminalServer server;

    private final PinValidator pinValidator;

    private Output output;

    private Client client;

    private boolean pinInserted;

    public TerminalImpl(TerminalServer server, Output output, Client client) {
        this.server = server;
        this.pinValidator = new PinValidator();
        this.output = output;
        this.client = client;
    }

    public void checkAccount() {
        server.checkAccount(client, output);
    }

    public void putMoney(int money) throws WrongAmountOfMoneyException {
        if (money % 100 == 0)
            server.putMoney(client, money, output);
        else throw new WrongAmountOfMoneyException();
    }

    public void getMoney(int money) throws CashWithdrawFailure, WrongAmountOfMoneyException {
        if (money % 100 == 0)
            server.getMoney(client, money, output);
        else throw new WrongAmountOfMoneyException();
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        output.printMessage("You can choose next actions:");
        output.printMessage("1. Check account state. Type: Check");
        output.printMessage("2. Put some money into your account. Type: Put *amount of money*");
        output.printMessage("3. Get some money into your account. Type Get *amount of money*");
        while (true) {
            try {
                output.printMessage("Enter your pin:");
                String in = scanner.next();
                Pin pin = new Pin(in);
                if (pinValidator.checkPin(pin, client)) {
                    output.printMessage("Choose next action");
                    in = scanner.next();
                    Pattern p = Pattern.compile("^(Check|check)$");
                    Matcher m = p.matcher(in);
                    if (m.matches()) {
                        checkAccount();
                    } else {
                        p = Pattern.compile("^(Put|put)$");
                        m = p.matcher(in);
                        int amount = scanner.nextInt();
                        if (m.matches()) {
                            //output.printMessage("type amount of money to put (must be divisible by 100):");
                            if (amount % 100 != 0) throw new WrongAmountOfMoneyException();
                            putMoney(amount);
                        } else {
                            p = Pattern.compile("^(Get|get)$");
                            m = p.matcher(in);
                            if (m.matches()) {
                                //output.printMessage("type amount of money to get (must be divisible by 100:");
                                if (amount % 100 != 0) throw new WrongAmountOfMoneyException();
                                getMoney(amount);
                            } else {
                                throw new InvalidCommandException();
                            }
                        }
                    }
                }
            } catch (InvalidPinFormatException e) {
                output.printErrorMessage("Please, type pin in correct format");
            } catch (InvalidUserPinException e) {
                //output.printErrorMessage("Please, enter your pin code before choosing some action");
                output.printErrorMessage("Entered pin is wrong. Try again");
            } catch (AccountIsLockedException e) {
                long tStart = System.currentTimeMillis();
                long tEnd = tStart + 5000;
                output.printErrorMessage("Your account is locked. Wait until " + new Date(tEnd));
                pinValidator.unlock(tStart);
                //waiting ...
            } catch (WrongAmountOfMoneyException e) {
                output.printErrorMessage("Invalid number format, please type \"Put *amount of money*\" or \"Get *amount of money*\" " +
                        "and amount of money must be divisible to 100");
            } catch (CashWithdrawFailure e) {
                output.printErrorMessage("Cash Failure");
            } catch (InputMismatchException e) {
                output.printErrorMessage("Please, type correct amount of money");
            } catch (InvalidCommandException e) {
                output.printErrorMessage("Unknown command, please, try again");
            }
        }

    }
}
