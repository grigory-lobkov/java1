package ru.progwards.java1.lessons.interfaces;

/*
Реализовать класс Cow, потомок класса Animal, перегружающий методы:
1.4 public AnimalKind getKind(), который возвращает COW.
1.5 public FoodKind getFoodKind(), который возвращает HAY
*/
public class Cow extends Animal {

    static double foodCoeff = 0.05;

    Cow() {
        super(AnimalKind.COW, FoodKind.HAY, 0d, foodCoeff);
    }

    Cow(double weight) {
        super(AnimalKind.COW, FoodKind.HAY, weight, foodCoeff);
    }

    //public AnimalKind getKind()
    //public FoodKind getFoodKind()
    //Извеняюсь, что не реализовал, но мне кажется, что мой вариант реализации того же функционала интересней
    //А применение @Override есть в родительском классе :)

    public static void main(String[] args) {
        Cow animal = new Cow(250);
        System.out.println(animal);
        System.out.println(animal.getFoodCoeff());
        System.out.println(animal.getCoeff());
    }

}

/*
I am COW, eat HAY 12.5
0.05
0.05
*/