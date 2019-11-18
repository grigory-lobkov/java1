package ru.progwards.java1.lessons.classes;

/*
Задача 1. Классы Animal, Cow, Hamster, Duck
        Реализовать класс Animal, содержащий:
        1.1 Метод:
public AnimalKind getKind(), который возвращает вид животного (enum AnimalKind - ANIMAL, COW, HAMSTER, DUCK).
В данном классе вернуть ANIMAL
        1.2 Метод
public FoodKind getFoodKind(), который возвращает вид еды, (enum FoodKind - UNKNOWN, HAY, CORN).
В данном классе вернуть UNKNOWN
        1.3 Метод
public String toString(), который возвращает информацию о животном в формате:
        I am <AnimalKind>, eat <FoodKind>
*/
/*
Задача 2. метод CalculateFoodWeight

2.1 В классе Animal реализовать метод:
public double getWeight(), который возвращает вес животного. В данном классе вернуть 1
2.2 Реализовать конструктор
public Animal(double weight), который сохраняет вес животного.
2.3 Реализовать метод
public double getFoodCoeff(), который возвращает коэффициент веса еды к весу тела животного.
Для класса Animal это 0.02

2.4 Для класса Cow, переопределить метод:
public double getFoodCoeff(), который должен возвращать 0.05

2.5 Для класса Hamster, переопределить метод:
public double getFoodCoeff(), который должен возвращать 0.03

2.6 Для класса Duck, переопределить метод:
public double getFoodCoeff(), который должен возвращать 0.04

2.7 Для класса Animal, реализовать метод:
public double CalculateFoodWeight(), который рассчитывает необходимый вес еды, по формуле -
вес-еды = вес-животного * коэффициент веса тела.

2.8 Для класса Animal, исправить метод
public String toString(), что бы он возвращал информацию о животном в формате:
I am <AnimalKind>, eat <FoodKind> <CalculateFoodWeight>
*/
public class Animal {

    static enum AnimalKind {ANIMAL, COW, HAMSTER, DUCK}
    static enum FoodKind {UNKNOWN, HAY, CORN}

    private AnimalKind animalKind = AnimalKind.ANIMAL;
    private FoodKind foodKind = FoodKind.UNKNOWN;

    private double weight = 1d; // вес животного
    double foodCoeff = 0.02; // коэффициент веса еды к весу тела животного

    Animal() {
    }

    Animal(double weight) { // не нужный объект, когда не задан тип животного
        this.weight = weight;
    }

    Animal(AnimalKind animalKind, FoodKind food, double weight, double foodCoeff) {
        this.animalKind = animalKind;
        this.foodKind = food;
        this.weight = weight;
        this.foodCoeff = foodCoeff;
    }

    public AnimalKind getKind() {
        return animalKind;
    }

    public FoodKind getFoodKind() {
        return foodKind;
    }

    @Override
    public String toString() {
        return "I am " + getKind() + ", eat " + getFoodKind() + " " + calculateFoodWeight();
    }

    public double getWeight() {
        return weight;
    }

    void setFoodCoeff(double foodCoeff) {
        this.foodCoeff = foodCoeff;
    }
    public double getFoodCoeff() {
        return foodCoeff;
    }
    public double getCoeff() {
        return foodCoeff;
    }

    // рассчитывает необходимый вес еды, по формуле - вес-еды = вес-животного * коэффициент веса тела
    public double calculateFoodWeight() {
        return getWeight() * getFoodCoeff();
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal);
    }

}
