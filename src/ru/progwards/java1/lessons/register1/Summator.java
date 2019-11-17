package ru.progwards.java1.lessons.register1;

/*
Класс Summator - сумматор, должен содержать 1 статический метод

public static boolean add(ByteRegister value1, ByteRegister value2)


Класс должен корректно реализовывать сложение целых положительных чисел,
результат помещается в первый регистр value1. Метод возвращает true если
не было переполнения (выход за границы значения числа) и false если было
*/

public class Summator {

    public static boolean add(ByteRegister value1, ByteRegister value2) {
        boolean pr = false; // бит переноса с предыдущего регистра
        boolean nv; // новое значение бита для value1
        for(int i = 0; i < value1.size; i++) {
            nv = value1.bits[i].get() ^ value2.bits[i].get() ^ pr;
            pr = value1.bits[i].get() && value2.bits[i].get() || value1.bits[i].get() && pr || value2.bits[i].get() && pr;
            value1.bits[i].set(nv);
        }
        return !pr;
    }

    public static void main(String[] args) {
        ByteRegister a = new ByteRegister((byte)0b1001_0111);
        ByteRegister b = new ByteRegister((byte)0b1001_0111);
        a.print();
        b.print();
        System.out.println(Summator.add(a, b));
        a.print();
        System.out.println(Summator.add(a, a));
        a.print();
    }

}
