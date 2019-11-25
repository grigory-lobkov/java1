package ru.progwards.java1.lessons.intensive1;

public class Meal {

    String name;
    String tags;
    int group;
    int cost;
    int index;
    Meal[] links = {};

    Meal(String name, String tags, int group, int cost, int index) {
        this.name = name;
        this.tags = tags;
        this.group = group;
        this.cost = cost;
        this.index = index;
    }

    void linkTo(Meal[] meals) {
        int len = links.length;
        Meal[] arr = new Meal[len + meals.length];
        System.arraycopy(links, 0, arr, 0, len);
        System.arraycopy(meals, 0, arr, len, meals.length);
        links = arr;
    }
    void linkTo(Meal meal) {
        linkTo(new Meal[]{meal});
    }
    boolean isLinkedTo(Meal meal) {
        for (Meal m : links) if (m == meal) return true;
        return false;
    }

}
