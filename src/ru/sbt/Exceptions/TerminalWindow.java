package ru.sbt.exceptions;

/**
 * Created by Sania on 15.11.2016.
 */
public class TerminalWindow {
    public static void main(String[] args) {
        //TerminalImpl terminal = new TerminalImpl();
        TerminalServer server = new TerminalServer();
        Output output = new ConsoleOutput();
        Client client = new Client(new Pin(12345), 300000);
        TerminalImpl terminal = new TerminalImpl(server, output, client);
        terminal.run();



    }
}
