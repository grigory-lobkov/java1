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

    public Bit[] bits;
    public int size = 8; // количество бит
    protected boolean signed = false; // знаковое ли число
    protected enum NegStoreType {STRIGHT, FORWARD, ADDITIONAL}
    protected NegStoreType negStoreType = NegStoreType.STRIGHT;

    public ByteRegister() {
        init();
    }

    public ByteRegister(byte value) {
        this();
        set(value);
    }

    public ByteRegister(ByteRegister value) {
        this();
        set(value);
    }

    protected void init() {
        bits = new Bit[size];
        for (int i = 0; i < size; i++) {
            bits[i] = new Bit();
        }
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

    public void set(ByteRegister value) {
        boolean differ = size != value.size;
        size = value.size;
        signed = value.signed;
        negStoreType = value.negStoreType;
        if (differ) init();
        for (int i = 0; i < size; i++) {
            bits[i].set(value.bits[i].get());
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
        int sum, a = 1, last;
        boolean lastBitVal = false; // инициализация не нужна, но как её избежать - не знаю :( компилятор не пропускает с ошибкой
        /*for (Bit b : bits) {
            if (b.get()) sum += a;
            a <<= 1;
        }*/
        if (signed) {
            last = size - 1;
            lastBitVal = bits[last].get();
            if (negStoreType == NegStoreType.ADDITIONAL && lastBitVal) {
                // отрицательное число в дополнительной форме записи
                sum = -1;
                for (int i = 0; i < last; i++) {
                    if (!bits[i].get()) sum -= a;
                    a <<= 1;
                }
                return Integer.toString(sum);
            }
        } else {
            last = size;
        }
        sum = 0;
        for (int i = 0; i < last; i++) {
            if (bits[i].get()) sum += a;
            a <<= 1;
        }
        if (signed && lastBitVal) {
                sum *= -1;
        }
        return Integer.toString(sum);
    }

    public void print() {
        System.out.println("0b" + this.toString() + " = (int)" + Integer.parseInt(this.toString()) + " = (dec)" + this.toDecString());
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
