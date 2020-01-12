package ru.progwards.java1.lessons.datetime;

/*
Класс для хранения пользовательских сессий для сервера, который проверяет аутентификацию пользователей.
Менеджер работает по следующему принципу: при логине (считаем что проверка логин-пароль уже прошла)
данные о сессии пользователя заносятся в список и возвращается хэндл сессии.
Затем пользователи запрашивают информацию используя хэндл, авторизация идет по хендлу сессии, который валиден
определенное время, с момента крайнего запроса. Проверка сессии по хендлу должна работать максимально быстро
*/

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

class UserSession {
    static private int handleNextNum = 0;
    private int sessionHanle;
    private String userName;
    private ZonedDateTime lastAccess;

    // создать сессию пользователя
    UserSession(String userName) {
        Random rnd = new Random();
        //this.sessionHanle = (int)(Math.random()*Integer.MAX_VALUE);// по заданию
        this.sessionHanle = handleNextNum++; // считаю так лучше для хэш таблицы + полная уникальность
        this.userName = userName;
    }

    public void setSessionHanle(int sessionHanle) {
        this.sessionHanle = sessionHanle;
    } // чисто для проверки ДЗ, потом лучше убрать

    public int getSessionHanle() {
        return sessionHanle;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setLastAccess(ZonedDateTime lastAccess) {
        this.lastAccess = lastAccess;
    }

    public ZonedDateTime getLastAccess() {
        return lastAccess;
    }
}

public class SessionManager {
    List<UserSession> sessions; // список сессий пользователей по sessionHanle
    Hashtable<Integer, UserSession> hashSessions; // поиск сессии по sessionHanle
    Hashtable<String, UserSession> userSessions; // поск сессии по userName

    int sessionValid; // период валидности сессии в секундах

    SessionManager() {
        sessions = new ArrayList<UserSession>();
        hashSessions = new Hashtable<Integer, UserSession>();
        userSessions = new Hashtable<String, UserSession>();
    }

    // добавляет новую сессию пользователя
    public void add(UserSession userSession) {
        int handle = userSession.getSessionHanle();
        String name = userSession.getUserName();
        sessions.get(handle);

    }

    // проверяет наличие существующей сессии по userName. Если срок валидности истек, или такой нет, возвращает null
    public UserSession find(String userName) {

    }

    // проверяет наличие существующей сессии по хендлу. Если срок валидности истек, или такой нет, возвращает null
    public UserSession get(int sessionHandle) {

    }

    // удаляет указанную сессию пользователя
    public void delete(int sessionHandle) {

    }

    // удаляет все сессии с истекшим сроком годности.
    public deleteExpired() {

    }

}
