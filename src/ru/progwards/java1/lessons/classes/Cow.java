package ru.progwards.java1.lessons.classes;

/*
Реализовать класс Cow, потомок класса Animal, перегружающий методы:
1.4 public AnimalKind getKind(), который возвращает COW.
1.5 public FoodKind getFoodKind(), который возвращает HAY
*/
public class Cow extends Animal {

    Cow() {
        super(AnimalKind.COW, FoodKind.HAY, 0d);
    }

    Cow(double weight) {
        super(AnimalKind.COW, FoodKind.HAY, weight);
        setFoodCoeff(0.05);
    }

    //public AnimalKind getKind()
    //public FoodKind getFoodKind()
    //Извеняюсь, что не реализовал, но мне кажется, что мой вариант реализации того же функционала интересней
    //А применение @Override есть в родительском классе :)

    public static void main(String[] args) {
        Cow animal = new Cow(250);
        System.out.println(animal);
    }

}
