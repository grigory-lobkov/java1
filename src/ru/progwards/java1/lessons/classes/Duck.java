package ru.progwards.java1.lessons.classes;

/*
Реализовать класс Duck, потомок класса Animal, перегружающий методы:
1.8 public AnimalKind getKind(), который возвращает DUCK.
1.9 public FoodKind getFoodKind(), который возвращает CORN
*/
public class Duck extends Animal {

    Duck() {
        super(AnimalKind.DUCK, FoodKind.CORN, 0d);
    }

    Duck(double weight) {
        super(AnimalKind.DUCK, FoodKind.CORN, weight);
        setFoodCoeff(0.04);
    }

    public static void main(String[] args) {
        Duck animal = new Duck(3);
        System.out.println(animal);
    }

}
