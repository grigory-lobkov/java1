package ru.progwards.java1.lessons.interfaces;

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
public class Animal implements FoodCompare, Comparable {

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

    @Override
    // возвращает true, если объекты равны и false если не равны по параметру - вес животного
    public boolean equals(Object anObject) {
        if (anObject == this) return true;
        if (anObject == null || anObject.getClass() != this.getClass()) return false;
        Animal o = (Animal) anObject;
        return Double.compare(o.calculateFoodWeight(), this.calculateFoodWeight()) == 0;
    }

    // информация о цене 1 кг еды
    public double getFood1kgPrice() {
        switch (foodKind) {
            case HAY: return 20d;
            case CORN: return 50d;
        }
        return 0d;
    }

    // возвращает информацию о цене еды для данного животного
    public double getFoodPrice() {
        return calculateFoodWeight() * getFood1kgPrice();
    }

    @Override
    // результаты сравнения цены еды для данного животного с ценой еды для другого животного
    public int сompareFoodPrice(Animal animal) {
        return Double.compare(getFoodPrice(), animal.getFoodPrice());
    }

    @Override
    public int compareTo(Object o) {
        if (o == this) return 0;
        if (o == null || o.getClass() != this.getClass()) return -2;
        double w = this.getWeight();
        double wo = ((Animal) o).getWeight();
        if (w == wo) return 0;
        if (w > wo) return 1;
        return -1;
    }

    public static void main(String[] args) {
        Animal animal = new Animal();
        System.out.println(animal);
    }

}
