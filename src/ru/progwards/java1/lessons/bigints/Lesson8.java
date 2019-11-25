package ru.progwards.java1.lessons.bigints;

import java.math.BigDecimal;

public class Lesson8 {
    class Person {
        public String name;
        public Person(String name) {
            this.name = name;
        }
    }

    abstract class PersonCompare {
        public int compare(Person p1, Person p2) {
            return 0;
        }
    }

/*Напишите фрагмент кода, в котором создайте переменную класса PersonCompare personCompare
и проинициализируйте ее анонимным классом, который перекрывает метод compare реализуя его
через p1.name.compareTo(p2.name)*/
    public static void main(String[] args) {
        /*Integer number = 22;
        System.out.println(number);*/

        Rectangle r1 = new Rectangle(BigDecimal.ONE, BigDecimal.TEN);
        Rectangle r2 = new Rectangle(BigDecimal.valueOf(1.0), BigDecimal.TEN);
        System.out.println(r1 + " ? " + r2 + " : " + rectCompare(r1, r2));
    }

    public void test(){
        PersonCompare personCompare = new PersonCompare(){
            @Override
            public int compare(Person p1, Person p2) {
                return p1.name.compareTo(p2.name);
            }
        };
    }

    public static void someFunction(Integer number) {
        number += 1;
    }

    static class Rectangle {

        Rectangle(BigDecimal a, BigDecimal b) {
            this.a = a;
            this.b = b;
        }
        public BigDecimal a;
        public BigDecimal b;

        @Override
        public String toString() {
            return "Rectangle{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }

    // возвращает true если площади прямоугольников равны
    static boolean rectCompare(Rectangle r1, Rectangle r2) {
        BigDecimal s1 = r1.a.multiply(r1.b);
        BigDecimal s2 = r2.a.multiply(r2.b);
        return s1.compareTo(s2) == 0;
    }
}
