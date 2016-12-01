package ru.sbt.exceptions;

/**
 * Created by Sania on 14.11.2016.
 */
public class ConsoleOutput implements Output {
    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void printErrorMessage(String error) {
        System.err.println(error);
    }
}
