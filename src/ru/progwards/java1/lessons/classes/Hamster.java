package ru.progwards.java1.lessons.classes;

/*
Реализовать класс Hamster, потомок класса Animal, перегружающий методы:
1.6  public AnimalKind getKind(), который возвращает HAMSTER.
1.7 public FoodKind getFoodKind(), который возвращает CORN
*/
public class Hamster extends Animal {

    Hamster() {
        super(AnimalKind.HAMSTER, FoodKind.CORN, 0d);
    }

    Hamster(double weight) {
        super(AnimalKind.HAMSTER, FoodKind.CORN, weight);
        setFoodCoeff(0.03);
    }

    public static void main(String[] args) {
        Hamster animal = new Hamster(5);
        System.out.println(animal);
    }

}
