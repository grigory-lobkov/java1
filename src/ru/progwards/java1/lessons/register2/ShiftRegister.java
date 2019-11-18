package ru.progwards.java1.lessons.register2;

/*
Класс ShiftRegister - регистр со сдвигом, должен содержать 2 статических метода

public static void left(ByteRegister value)

public static void right(ByteRegister value)

Класс должен корректно реализовывать сдвиг влево и сдвиг вправо значения целого числа, работая непосредственно в
ByteRegister value

*/

public class ShiftRegister {

    public static void left(ByteRegister value) {
        for (int i = value.size - 1; i > 0; i--) value.bits[i].set(value.bits[i-1].get());
        value.bits[0].set(false);
    }

    public static void right(ByteRegister value) {
        for (int i = 1; i < value.size; i++) value.bits[i-1].set(value.bits[i].get());
        value.bits[value.size - 1].set(false);
    }


    public static void main(String[] args) {
        ByteRegister r = new ByteRegister((byte)0b1001_0111);
        r.print();
        ShiftRegister.left(r);
        r.print();
        ShiftRegister.left(r);
        r.print();
        ShiftRegister.left(r);
        r.print();
        System.out.println("Right:");
        r.set((byte)0b1011_1110);
        r.print();
        ShiftRegister.right(r);
        r.print();
        ShiftRegister.right(r);
        r.print();
        ShiftRegister.right(r);
        r.print();
    }

}
