package ru.progwards.java1.lessons.register1;

/*
Реализовать класс, ByteRegister, содержащий 8 бит, реализованных на классе Bit, как целое беззнаковое число, хранить
биты в массиве. Методы:

public ByteRegister() - инициализация нулями

public ByteRegister(byte value) - обратить внимание на то, что в Java byte знаковый, а у нас нет. При сохранении просто
копируем побитно, просто по другому интерпретируем значение.

public String toString() - вывод в двоичном виде

public String toDecString() - вывод в десятичной системе счисления, при преобразовании рассматриваем значение как целое
положительное число
*/

public class ByteRegister {

    Bit[] bits;
    final int size = 8; // количество бит

    public ByteRegister() {
        bits = new Bit[size];
        for (int i = 0; i < size; i++) {
            bits[i] = new Bit();
        }
    }

    public ByteRegister(byte value) {
        this();
        set(value);
    }

    public void set(byte value) {
        int last = size - 1;
        bits[last].set(value < 0); // запоминаем знаковый бит
        value &= 0x7F; // отбрасываем знаковый бит
        for (int i = 0; i < last; i++) {
            bits[i].set((value & 1) == 1);
            value >>>= 1;
        }
    }

    // вывод в двоичном виде
    public String toString() {
        String r = "";
        for (int i = size - 1; i >= 0; i--) r += bits[i];
        return r;
    }

    // вывод в десятичной системе счисления
    public String toDecString() {
        int sum = 0, a = 1;
        for (Bit b : bits) {
            if (b.get()) sum += a;
            a <<= 1;
        }
        return Integer.toString(sum);
    }

    public void print() {
        System.out.println("0b" + this.toString() + " = (int)" + Integer.parseInt(this.toString(), 2) + " = (dec)" + this.toDecString());
    }

    // tests
    public static void main(String[] args) {
        ByteRegister r = new ByteRegister();
        r.print();
        r = new ByteRegister((byte)0b1001_0001);
        r.print();
        r.set((byte)0b1111_1111);
        r.print();
    }

}
