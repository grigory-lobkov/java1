package ru.progwards.java1.lessons.maps;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.currentTimeMillis;

/*
Кеш для чисел Фибоначчи на Map

1.1 Определить приватную локальную переменную fiboCache как Map<Integer, BigDecimal>

1.2 Определить конструктор public FiboMapCache(boolean cacheOn) - включен ли кэш.
При cacheOn = true кэш работает, при cacheOn = false - выключен

1.3 Реализовать public BigDecimal fiboNumber(int n). Алгоритм работы следующий:

в функции проверить, находится ли вычисленное значение для n в кэше, и
если да - вернуть его из кэша, если нет - рассчитать и добавить в кэш.
Учитывать значение переменной cacheOn

1.4 Реализовать метод public void clearCache() который устанавливает переменную fiboCache в null

1.5 Для проверки работы реализовать public static void test() - тест для расчета чисел Фибоначчи
от n = 1 до 1000 включительно и замерить разницу во времени с on = true и on = false,
результат вывести на экран в формате "fiboNumber cacheOn=??? время выполнения ???"
для cacheOn=true и cacheOn=false, вместо ??? вывести реальные значения в мсек.
*/

public class FiboMapCache {

    private boolean cacheOn; // cacheOn = true кэш работает, при cacheOn = false - выключен

    // Конструктор

    FiboMapCache(boolean cacheOn) {
        this.cacheOn = cacheOn;
        clearCahe();
    }

    // Очистка кеша

    public void clearCahe() {
        if (cacheOn) {
            fiboCache = new HashMap<Integer, BigDecimal>();
            fiboCache.put(1, BigDecimal.ONE);
            fiboCache.put(2, BigDecimal.ONE);
            lastFiboCache_n = 2;
            lastFibo_f1 = BigDecimal.ZERO;
            lastFibo_f2 = BigDecimal.ONE;
            lastFibo_n = 1;
        }
    }

    // Рассчитываем Фибоначчи

    public BigDecimal fiboNumber(int n) {
        return cacheOn ? fiboCacheMap(n) : fiboNoCache(n);
    }

    // Рассчитываем Фибоначчи с кешем всех значений

    Map<Integer, BigDecimal> fiboCache;
    int lastFiboCache_n;

    public BigDecimal fiboCacheMap(int n) {

        if (fiboCache.containsKey(n)) return fiboCache.get(n); // если есть в кеше

        BigDecimal f1 = fiboCache.get(lastFiboCache_n - 1);
        BigDecimal f2 = fiboCache.get(lastFiboCache_n);
        BigDecimal t;
        int i = lastFiboCache_n;

        while (i++ < n) {
            t = f2;
            f2 = f2.add(f1);
            f1 = t;
            fiboCache.put(i, f2);
        }

        lastFiboCache_n = n;
        return f2;
    }

    // Рассчитываем Фибоначчи с кешем последнего расчета

    int lastFibo_n = 1;
    BigDecimal lastFibo_f1 = BigDecimal.ZERO;
    BigDecimal lastFibo_f2 = BigDecimal.ONE;

    public BigDecimal fiboCacheLast(int n) {

        if (lastFibo_n == n) return lastFibo_f2; // если последнее число кеша

        BigDecimal f1;
        BigDecimal f2;
        BigDecimal t;
        int i;

        if (lastFibo_n < n) { // используем кеш
            f1 = lastFibo_f1;
            f2 = lastFibo_f2;
            i = lastFibo_n;
        } else { // считаем сначала
            f1 = BigDecimal.ZERO;
            f2 = BigDecimal.ONE;
            i = 1;
        }

        while (i++ < n) {
            t = f2;
            f2 = f2.add(f1);
            f1 = t;
        }

        lastFibo_n = n;
        lastFibo_f1 = f1;
        lastFibo_f2 = f2;
        return f2;
    }

    // Рассчитываем Фибоначчи с нуля без кеша

    public BigDecimal fiboNoCache(int n) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException ex) {
        }
        BigDecimal f1 = BigDecimal.ZERO;
        BigDecimal f2 = BigDecimal.ONE;
        BigDecimal t;
        int i = 1;
        while (i++ < n) {
            t = f2;
            f2 = f2.add(f1);
            f1 = t;
        }
        return f2;
    }

    public static void test() {
        long start;
        FiboMapCache f;

        start = currentTimeMillis();
        f = new FiboMapCache(false);
        for (int i = 1; i <= 1000; i++) f.fiboNumber(i);
        System.out.println("fiboNumber cacheOn=" + false + " время выполнения " + (currentTimeMillis() - start));
        //fiboNumber cacheOn=false время выполнения 6216 - для 10_000

        start = currentTimeMillis();
        f = new FiboMapCache(true);
        for (int i = 1; i <= 1000; i++) f.fiboNumber(i);
        System.out.println("fiboNumber cacheOn=" + true + " время выполнения " + (currentTimeMillis() - start));
        //fiboNumber cacheOn=true время выполнения 523 - для 100_000
        //java.lang.OutOfMemoryError: Java heap space - для 1_000_000

        /*start = currentTimeMillis();
        f = new FiboMapCache(false);
        for (int i = 1; i <= 1000; i++) f.fiboCacheLast(i);
        System.out.println("fiboNumber cacheOn=my время выполнения " + (currentTimeMillis() - start));
        //fiboNumber cacheOn=my время выполнения 350 - для 100_000
        //fiboNumber cacheOn=my время выполнения 15592 - для 1_000_000 */
    }

    public static void main(String[] args) {
        FiboMapCache f = new FiboMapCache(true);
        for (int i = 1; i <= 10; i++) System.out.println(f.fiboNumber(i));
        test();
    }

}
