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
                //Number.newNumber(segment instanceof IntNumber ? "4" : "" + Math.PI)), // * pi
                Number.newNumber(segment.numType == Number.NumType.INT ? "3" : "" + Math.PI)), // * pi
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
        Number n = Number.newNumber("3");
        Figure3D figure = new Ball(n);
        System.out.println(figure);
        n = Number.newNumber("1.5");
        figure = new Cube(n);
        System.out.println(figure);
        n = Number.newNumber("1.0");
        figure = new Cube(n);
        System.out.println(figure);
    }

}
