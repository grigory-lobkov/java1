package ru.progwards.java1.lessons.abstractnum;

/*
3.3 Реализовать класс Cube, переопределяющий метод
public Number volume(), который будет возвращать объем куба по формуле Segment*Segment*Segment;
*/

public class Cube extends Figure3D {

    @Override
    //объем куба по формуле Segment*Segment*Segment
    public Number volume() {
        return segment.mul(segment, segment.mul(segment, segment));
    }

    public Cube(Number segment) {
        super(segment);
    }

    @Override
    public String toString() {
        return "Куб со стороной " + segment.toString() + ", объемом " + volume();
    }

}
