package ru.progwards.java1.lessons.abstractnum;

/*
Реализовать классы Cube, Ball, вычисляющие объем фигуры в абстрактных числах класса Number,
реализованный в целых числах, и в числах с плавающей точкой.

Реализовать класс Figure3D, содержащий
3.1 конструктор
public Figure3D(Number segment), который будет сохранять в классе значение длины отрезка.
Для куба это будет длина стороны, для шара - радиус, для тетраэдра - длина ребра
3.2 метод
public Number volume(), который будет возвращать объем фигуры. Для класса Figure3D это будет null;
*/

public class Figure3D {

    Number segment;

    public Figure3D() {}
    public Figure3D(Number segment) {
        this.segment = segment;
    }

    public Number volume() {
        return null;
    }

    @Override
    public String toString() {
        return "Объемная фигура";
    }

}
