package ru.progwards.java1.lessons.intensive1;

public class Order {

    Menu menu;
    RollsBot bot;

    Order(RollsBot bot, Menu menu) {
        this.bot = bot;
        this.menu = menu;
        int maxGroup = 0;
        //for (Meal m : menu.meals) if (m.group > maxGroup) maxGroup = m.group;
        //group = new int[maxGroup + 1];
    }

    int getUserData(Integer userid, String dataName) {
        Integer result = 0;
        String str = bot.getUserData(userid, dataName);
        if (str != null)
            result = Integer.parseInt(str);
        return result;
    }
    int setUserData(Integer userid, String dataName, int increment) {
        Integer result = getUserData(userid, dataName);
        if (result > 0) {
            result += increment;
            bot.setUserData(userid, dataName, result.toString());
        }
        return result;
    }


    void add(Integer userid, Meal meal, int increment) {
        setUserData(userid, bot.groupKey + "[" + meal.group + "]", increment);
        setUserData(userid, bot.orderKey + "[" + meal.index + "]", increment);
        setUserData(userid, bot.orderKey, increment);
    }
    void add(Integer userid, Meal meal) {
        add(userid, meal, 1);
    }
    void del(Integer userid, Meal meal) {
        add(userid, meal, -1);
    }

    int countMeal(Integer userid, Meal meal) {
        int result = 0;
        getUserData(userid, bot.orderKey + "[" + meal.index + "]");
        return result;
    }
    int countGroup(Integer userid, int group) {
        int result = 0;
        for (Meal m : menu.meals)
            if (m.group == group)
                result += getUserData(userid, bot.groupKey + "[" + m.index + "]");
        return result;
    }
}
