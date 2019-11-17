package ru.progwards.java1.lessons.register2;

import ru.progwards.java1.lessons.register1.ByteRegister;
import ru.progwards.java1.lessons.register1.Counter;

public class Summator {

    public static void add(ByteRegister value1, ByteRegister value2) {
        boolean pr = false; // бит переноса с предыдущего регистра
        boolean nv; // новое значение бита для value1
        boolean b1, b2;
        for(int i = 0; i < value1.size; i++) {
            b1 = value1.bits[i].get();
            b2 = value2.bits[i].get();
            nv = b1 ^ b2 ^ pr;
            pr = b1 && b2 || b1 && pr || b2 && pr;
            value1.bits[i].set(nv);
        }
        //return !pr;
    }

    // второе дополнение в дополнительном коде
    protected static ByteRegister invert(ByteRegister value) {
        ByteRegister result = new ByteRegister(value);
        /*if (result.signed && result.negStoreType != ByteRegister.NegStoreType.ADDITIONAL && result.bits[result.size - 1].get()) {
            result.bits[result.size - 1].set(false); // инвертируем знаковый бит для отрицательных (?)
        }*/
        for (int i = 0; i < result.size; i++) {
            result.bits[i].set(!result.bits[i].get());
        }
        Counter.inc(result);
        //result.print();
        return result;
    }

    public static void sub(ByteRegister value1, ByteRegister value2) {
        ByteRegister value3 = invert(value2);
        /*return */add(value1, value3);
    }

    public static void main(String[] args) {
        ByteRegister a = new IntRegister(123456789);
        ByteRegister b = new IntRegister(-12345678);
        a.print();
        b.print();
        Summator.add(a, b);
        a.print();
        Summator.add(a, a);
        a.print();
    }

}
