package ru.sbt.exceptions;

import ru.sbt.exceptions.localExceptions.InvalidPinFormatException;

/**
 * Created by Sania on 14.11.2016.
 */
public class Pin {
    private int pin;
    //private int quantOfEntering;

    public Pin(Integer pin) {
        this.pin = pin;
    }

    public Pin(String pin) throws InvalidPinFormatException {
        try {
            this.pin = new Integer(pin);
        } catch (NumberFormatException e) {
            throw new InvalidPinFormatException();
        }
    }

    public int getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pin) {
            Pin tmp = (Pin) obj;
            Integer t = tmp.pin;
            return t.equals(this.pin);
        }
        return super.equals(obj);
    }
}
