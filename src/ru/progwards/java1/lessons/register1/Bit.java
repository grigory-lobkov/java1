package ru.progwards.java1.lessons.register1;

/*
Реализовать класс, содержащий один бит. Внутри данные хранить в переменной типа boolean, методы:

public Bit()

public Bit(boolean value)

public String toString() - должен выводить 1 или 0
*/

public class Bit {

    private boolean bit;

    public Bit() {
        bit = false;
    }

    public Bit(boolean value) {
        bit = value;
    }

    public void set(boolean value) {
        bit = value;
    }

    public boolean get() {
        return bit;
    }

    public String toString() {
        return bit ? "1" : "0";
    }

}
