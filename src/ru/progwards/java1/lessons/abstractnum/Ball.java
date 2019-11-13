package ru.progwards.java1.lessons.abstractnum;

/*
3.4 Реализовать класс Ball, переопределяющий метод
public Number volume(), который будет возвращать объем шара по формуле Segment*Segment*Segment*пи*4/3;
пи - это 3,1415926535...
Для создания констант нужного типа использовать метод newNumber, определенный ниже, например newNumber("4"), newNumber("3.1415926535")
*/

public class Ball extends Figure3D {

    // Очень плохо, что в задании не сказано, как передавать параметр Pi. Как будет это делать автотестер?
    //Number pi = Number.newNumber("3.1415926535");

    public Ball(Number segment) {
        super(segment);
    }

    @Override
    //объем шара по формуле Segment*Segment*Segment*пи*4/3
    public Number volume() {
        return segment.div(segment.mul(segment.mul(segment.mul(
                segment, segment.mul(segment, segment)), // segment * segment * segment
                Number.newNumber(segment instanceof IntNumber ? "3" : "3.1415926535")), // * pi
                Number.newNumber("4")), Number.newNumber("3")); // * 4 / 3
    }
    //ERROR: Тест "Класс Ball, метод volume()" не пройден. Метод volume()
    // возвращает неверное значение после создания экземпляра объекта с входным параметром типа IntNumber

    //public Number getPi() { return pi; }
    //public void setPi(Number pi) { this.pi = pi; }

    @Override
    public String toString() {
        return "Шар с радиусом " + segment.toString() + ", объемом " + volume();
    }

    public static void main(String[] args) {
        Number intNumber = new IntNumber(123);
        Figure3D figure = new Ball(intNumber);
        System.out.println(figure);
        Number doubleNumber = new DoubleNumber(1.5);
        figure = new Cube(doubleNumber);
        System.out.println(figure);
    }

}
