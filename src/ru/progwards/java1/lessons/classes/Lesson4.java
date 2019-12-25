package ru.progwards.java1.lessons.classes;

public class Lesson4 {

    public static void main(String[] args) {
        Person p = new Person("Tom", 16);
        System.out.println(p.getAge());
        System.out.println(p.getName());
        System.out.println(p.getCountry());

        Calculator c = new Calculator();
        System.out.println(c.getResult());
        c.set(5);
        c.add(5);
        c.sub(1);
        System.out.println(c.getResult());

        Point2D p2 = new Point2D(1, 2);
        Point3D p3 = new Point3D(3, 4, 5);
        System.out.println(p2);
        System.out.println(p3);
    }

}

/*
Создайте класс Person, содержащий

приватную переменную String name;
приватную переменную int age;
приватную переменную String country;
конструктор по умолчанию, устанавливающий country = "RU";
конструктор с параметрами String name, int age, вызывающий конструктор по умолчанию,
и устанавливающий значения переменным name и age из соответствующих параметров;
публичные функции String getName(), int getAge(), String getCountry(), возвращающие значения
соответствующих переменных*/
class Person {
    private String name;
    private int age;
    private String country;

    public Person() {
        country = "RU";
    }
    public Person(String name, int age) {
        this();
        this.name = name;
        this.age = age;
    }
    public String getName() {return name;}
    public int getAge() {return age;}
    public String getCountry() {return country;}
}

/*
Создайте класс Calculator, содержащий
приватную переменную int result;
конструктор по умолчанию, устанавливающий result = 0;
публичную функцию set(int num), устанавливающую значение result равный num
публичную функцию add(int num), увеличивающую result на num
публичную функцию sub(int num), уменьшающую result на num
публичную функцию int getResult()
*/
class Calculator {
    private int result;

    public Calculator(){
        result = 0;
    }
    public void set(int num) {
        result = num;
    }
    public void add(int num) {
        result += num;
    }
    public void sub(int num) {
        result -= num;
    }
    public int getResult() {
        return result;
    }
}

/*
Создайте классы Point2D, Point3D, в одном окошке, последовательно один, за другим

Класс Point2D - точка с 2-мя координатами, содержит

приватные переменные int x; int y;
конструктор с параметрами int x, int y, устанавливающий соответствующие переменные
переопределение функции toString(), которая должна возвращать результат в формате х,у, например 23,12

Класс Point3D - точка с 3-мя координатами

наследуется от Point2D
содержит приватную переменную int z;
конструктор с параметрами int x, int y, int z, вызывающий конструктор родителя и устанавливающий
соответствующую свою переменную
переопределение функции toString(), которая должна возвращать результат в формате х,у,z,
например 23,12,37 и вызывать функцию toString() родителя внутри себя
*/
class Point2D1 {

    int x;
    int y;

    Point2D1(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return x + "," + y;
    }
}

class Point3D1 extends Point2D1 {

    int z;

    Point3D1(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }
    @Override
    public String toString() {
        return super.toString() + "," + z;
    }
}

class Point2D {
    private int x;
    private int y;

    Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        String dv = Integer.toString(x) + "," + Integer.toString(y);
        return dv;
    }

}


class Point3D extends Point2D {
    private int z;

    Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    @Override
    public String toString() {
        String dv = super.toString() + "," + Integer.toString(z);
        return dv;
    }
}