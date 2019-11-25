package ru.progwards.java1.lessons.interfaces;

public class CalculateFibonacci {

    static CacheInfo lastFibo;
    static {
        lastFibo = new CacheInfo();
    }

    static class CacheInfo {

        int n; //число, для которого рассчитываем Фибоначчи
        int fibo; //результат расчета

        CacheInfo(){
            n = -1;
        }

        CacheInfo(int n, int fibo) {
            this.n = n;
            this.fibo = fibo;
        }
    }

    // Fibonacci n number
    public static int fiboNumber(int n) {
        if (lastFibo.n == n) return lastFibo.fibo;
        int f1 = 0;
        int f2 = 1;
        int t;
        int i = 1;
        while (i++ < n) {
            t = f2;
            f2 += f1;
            f1 = t;
        }
        lastFibo.n = n;
        lastFibo.fibo = f2;
        return f2;
    }

    CalculateFibonacci() {
        lastFibo = new CacheInfo();
    }
}
