package ru.progwards.java1.SeaBattle.grigorymail;

import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

import static ru.progwards.java1.SeaBattle.SeaBattle.FireResult.*;

public class SeaBattleAlg {
    // Тестовое поле создаётся конструктором
    //     SeaBattle seaBattle = new SeaBattle(true);
    //
    // Обычное поле создаётся конструктором по умолчанию:
    //     SeaBattle seaBattle = new SeaBattle();
    //     SeaBattle seaBattle = new SeaBattle(false);
    //
    // Посомтреть результаты стрельбы можно в любой момент,
    // выведя объект класса SeaBattle на консоль. Например так:
    //     System.out.println(seaBattle);
    //
    //
    // Вид тестового поля:
    //
    //           0 1 2 3 4 5 6 7 8 9    координата x
    //         0|.|.|.|.|.|.|.|X|.|.|
    //         1|.|.|.|.|.|X|.|.|.|.|
    //         2|X|X|.|.|.|.|.|.|.|.|
    //         3|.|.|.|.|.|.|.|X|X|X|
    //         4|.|.|.|.|X|.|.|.|.|.|
    //         5|.|.|.|.|X|.|.|Х|.|.|
    //         6|.|.|.|.|.|.|.|Х|.|X|
    //         7|X|.|X|.|.|.|.|Х|.|X|
    //         8|X|.|.|.|.|.|.|X|.|.|
    //         9|X|.|.|.|X|.|.|.|.|.|

    public void battleAlgorithm0(SeaBattle seaBattle) {
        // пример алгоритма:
        // стрельба по всем квадратам поля полным перебором
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
            String row = "";
            for (int x = 0; x < seaBattle.getSizeY(); x++) {
                FireResult fireResult = seaBattle.fire(x, y);
                row += fireResult == MISS ? ' ' : 's';
            }
            System.out.println(row);
        }
    }

    public enum FieldDot {
        MISS,
        HIT,
        DESTROYED,
        NOTFIRED,
        CANTBE
    }

    public class Field {
        FieldDot[][] field;
        int maxX, maxY;

        Field(SeaBattle seaBattle) {
            maxX = seaBattle.getSizeX();
            maxY = seaBattle.getSizeY();
            field = new FieldDot[maxX][maxY];
            for (int y = 0; y < maxX; y++)
                for (int x = 0; x < maxY; x++)
                    field[x][y] = FieldDot.NOTFIRED;
        }

        public void mark(int x, int y, SeaBattle.FireResult fireResult) {
            switch (fireResult) {
                case HIT:
                    field[x][y] = FieldDot.HIT;
                    break;
                case DESTROYED:
                    markDestroyed(x, y);
                    break;
                case MISS:
                    field[x][y] = FieldDot.MISS;
                    break;
            }
        }

        void markCantBe(int x, int y) {
            if (x < 0 || y < 0 || x >= maxX || y >= maxY) return;
            if (field[x][y] == FieldDot.NOTFIRED)
                field[x][y] = FieldDot.CANTBE;
            else if (field[x][y] == FieldDot.HIT) {
                markDestroyed(x, y);
            }
        }

        void markDestroyed(int x, int y) {
            field[x][y] = FieldDot.DESTROYED;
            markCantBe(x - 1, y + 1);
            markCantBe(x + 1, y + 1);
            markCantBe(x - 1, y - 1);
            markCantBe(x + 1, y - 1);
            markCantBe(x - 1, y);
            markCantBe(x + 1, y);
            markCantBe(x, y + 1);
            markCantBe(x, y - 1);
        }
    }

    public void battleAlgorithm(SeaBattle seaBattle) {
        Field field = new Field(seaBattle);
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
            for (int x = 0; x < seaBattle.getSizeY(); x++) {
                if (field.field[x][y] == FieldDot.NOTFIRED)
                    field.mark(x, y, seaBattle.fire(x, y));
            }
        }
    }

    // функция для отладки
    public static void main(String[] args) {
        //SeaBattle seaBattle = new SeaBattle(true);
        SeaBattle seaBattle = new SeaBattle();
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }
}