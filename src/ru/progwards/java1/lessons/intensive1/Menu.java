package ru.progwards.java1.lessons.intensive1;

public class Menu {

    Meal[] meals = {};
    RollsBot bot;


    Menu(){}

    Menu(RollsBot bot) {
        this.bot = bot;
    }

    int add(Meal meal) {
        int len = meals.length;
        Meal[] arr = new Meal[len + 1];
        System.arraycopy(meals, 0, arr, 0, len);
        arr[len] = meal;
        meals = arr;
        return len;
    }
    Meal add(String name, String tags, int group, int cost) {
        Meal m = new Meal("Тянь шань 360р.", "ролл, шань, тянь, снежный краб, краб, огурец, сливочный сыр, угорь, лосось", 1, 360, meals.length);
        add(m);
        return m;
    }

    void del(int pos) {
        Meal[] arr = new Meal[meals.length - 1];
        System.arraycopy(meals, 0, arr, 0, pos);
        System.arraycopy(meals, pos + 1, arr, pos, meals.length - pos - 1);
        meals = arr;
    }

    void del(Meal meal) {
        for (int i = 0; i < meals.length; i++) if (meals[i] == meal) { del(i); return; }
    }

    void fillTags() {
        for (Meal m : meals) bot.addTags(m.name, m.tags);
    }

    Meal getMeal(String name) {
        for (Meal m : meals) if (m.name.compareTo(name) == 0) return m;
        return null;
    }
    Meal getMeal(RollsBot.FoundTags tags) {
        for (Meal m : meals) if (bot.checkLastFound(tags, m.name)) return m;
        return null;
    }

}
