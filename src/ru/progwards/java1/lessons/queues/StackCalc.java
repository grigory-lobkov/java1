package ru.progwards.java1.lessons.queues;
/*
Стековый калькулятор (есть даже такой стековый язык программирования - Forth).
Стек, это структура данных, работающая по принципу LIFO - last in first out
(последний вошедший выходит первый), это противоположность привычной очереди
FIFO - first in first out - первый вошедший выходит первый
*/

import java.util.ArrayDeque;

/*
Реализовать class StackCalc, который содержит стек чисел с плавающей точкой (double).
Выбрать наиболее удобную для этого коллекцию. Реализовать методы работы со стеком:

3.1 public void push(double value) - положить value на вершину стека
3.2 public double pop() - взять (убрать) значение с вершины стека
3.3 public void add() - сложить 2 верхних значения на стеке, результат положить на стек.
В итогу в стеке должно быть на один элемент меньше
3.4 public void sub() - вычесть верхнее значение на стеке, из следующего по глубине,
результат положить на стек. В итоге в стеке должно быть на один элемент меньше
3.5 public void mul() - умножить 2 верхних значения на стеке, результат положить на стек.
В итогу в стеке должно быть на один элемент меньше
3.6 public void div() - поделить на верхнее значение на стеке, следующее по глубине,
результат положить на стек. В итоге в стеке должно быть на один элемент меньше
*/
public class StackCalc {

    private ArrayDeque<Double> queue = new ArrayDeque<Double>();

    // положить value на вершину стека
    public void push(double value) {
        queue.offerLast(value);
    }

    // взять (убрать) значение с вершины стека
    public double pop() {
        return queue.pollLast();
    }

    // сложить 2 верхних значения на стеке, результат положить на стек
    // В итогу в стеке должно быть на один элемент меньше
    public void add() {
        if (queue.size() < 2) return;
        queue.offerLast(queue.pollLast() + queue.pollLast());
    }

    // вычесть верхнее значение на стеке, из следующего по глубине
    // результат положить на стек. В итоге в стеке должно быть на один элемент меньше
    public void sub() {
        if (queue.size() < 2) return;
        queue.offerLast(-queue.pollLast() + queue.pollLast());
    }

    // умножить 2 верхних значения на стеке, результат положить на стек.
    // В итогу в стеке должно быть на один элемент меньше
    public void mul() {
        if (queue.size() < 2) return;
        queue.offerLast(queue.pollLast() * queue.pollLast());
    }

    // поделить на верхнее значение на стеке, следующее по глубине,
    // результат положить на стек. В итоге в стеке должно быть на один элемент меньше
    public void div() {
        if (queue.size() < 2) return;
        Double e1 = queue.pollLast();
        queue.offerLast(queue.pollLast() / e1);
    }
}

/*
Class Calculate, который содержит методы:
3.7 public static double calculation1(), возвращающую результат вычисления следующей формулы:
2.2*(3+12.1), используя класс StackCalc


3.8 public static double calculation2(), возвращающую результат вычисления следующей формулы:
(737.22+24)/(55.6-12.1)+(19-3.33)*(87+2*(13.001-9.2)), используя класс StackCalc
*/

class Calculate {

    public static double calculation1() {
        StackCalc c = new StackCalc();
        c.push(2.2);
        c.push(3);
        c.push(12.1);
        c.add();
        c.mul();
        return c.pop();
    }

    public static double calculation2() {
        StackCalc c = new StackCalc();
        c.push(737.22);
        c.push(24);
        c.add();
        c.push(55.6);
        c.push(12.1);
        c.sub();
        c.div(); // (737.22+24)/(55.6-12.1)+
        c.push(19);
        c.push(3.33);
        c.sub(); // (19-3.33)*(
        c.push(87);
        c.push(2);
        c.push(13.001);
        c.push(9.2);
        c.sub();
        c.mul();
        c.add(); // 87+2*(13.001-9.2)
        c.mul();
        c.add();
        return c.pop();
    }

    public static void main(String[] args) {
        System.out.println(calculation1());
        System.out.println(calculation2());
    }
}