package ru.progwards.java1.lessons.interfaces;

public class Lesson7 {

    class Rectangle {
        private double a;
        private double b;

        public Rectangle(double a, double b) {
            this.a = a;
            this.b = b;
        }

        public double area() {

            return a*b;
        }

        public boolean equals(Object anObject) {
            if(this == anObject) return true;
            if(anObject == null || getClass() != anObject.getClass()) return false;
            Rectangle o = (Rectangle)anObject;
            return Double.compare(area(), o.area()) == 0;
        }
    }

/*переопределите метод equals с сигнатурой
public boolean equals(Object anObject) который должен сравнивать прямоугольники по величине их площади.

Например

прямоугольник 2x2 != прямоугольнику 1x1
прямоугольник 2x3 == прямоугольнику 3x2
*/

    //Описаны интерфейсы

    public interface Speaking {
        public String say();
    }

    public interface Eating {
        public String eat();
    }

    /*реализовать 2 класса, Dog и Goat.
    У класса Dog метод say() должен вернуть "Гав"
    У класса Dog метод eat() должен вернуть "Мясо"
    У класса Goat метод say() должен вернуть "Мее"
    У класса Goat метод eat() должен вернуть "Сено"

    public boolean equals(Object anObject) который должен сравнивать прямоугольники по величине их площади.

    Например

    прямоугольник 2x2 != прямоугольнику 1x1
    прямоугольник 2x3 == прямоугольнику 3x2*/

    class Dog implements Speaking, Eating {
        public String say() { return "Гав"; }
        public String eat() { return "Мясо"; }
    }
    class Goat implements Speaking, Eating {
        public String say() { return "Мее"; }
        public String eat() { return "Сено"; }
    }

/*
Создайте класс Person, а внутри него 2 класса

вложенный класс Child1
внутренний класс Child2
У каждого класса, Child1 и Child2 создайте метод String hello()

У Child1 String hello() должен вернуть "привет"
У Child2 String hello() должен вернуть "servus"
*/

    static class Person {
        static class Child1 {
            String hello() {
                return "привет";
            }
        }
        class Child2 {
            String hello() {
                return "servus";
            }
        }
    }

}
