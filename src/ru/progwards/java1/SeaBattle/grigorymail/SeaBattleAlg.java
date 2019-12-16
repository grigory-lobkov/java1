package ru.progwards.java1.SeaBattle.grigorymail;

import ru.progwards.java1.SeaBattle.SeaBattle;
import ru.progwards.java1.SeaBattle.SeaBattle.FireResult;

import static ru.progwards.java1.SeaBattle.SeaBattle.FireResult.MISS;

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
        UNKNOWN,
        CANTBE
    }

    public class Field {
        FieldDot[][] field;
        int maxX, maxY;
        int[] shipsLeft;
        int maxShip;

        Field(SeaBattle seaBattle) {
            maxX = seaBattle.getSizeX();
            maxY = seaBattle.getSizeY();
            field = new FieldDot[maxX][maxY];
            for (int y = 0; y < maxX; y++)
                for (int x = 0; x < maxY; x++)
                    field[x][y] = FieldDot.UNKNOWN;
            shipsLeft = new int[]{0, 4, 3, 2, 1};
            maxShip = 4;
        }

        public void mark(int x, int y, SeaBattle.FireResult fireResult) {
            switch (fireResult) {
                case HIT:
                    field[x][y] = FieldDot.HIT;
                    break;
                case DESTROYED:
                    markDestroyed(x, y);
                    minusShip(x, y);
                    break;
                case MISS:
                    field[x][y] = FieldDot.MISS;
                    break;
            }
        }

        void markCantBe(int x, int y) {
            if (x < 0 || y < 0 || x >= maxX || y >= maxY) return;
            if (field[x][y] == FieldDot.UNKNOWN)
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

        int getShipSize(int x, int y, int dir) {
            if (x < 0 || y < 0 || x >= maxX || y >= maxY) return 0;
            if (field[x][y] != FieldDot.DESTROYED) return 0;
            switch (dir) {
                case 0:
                    return 1 + getShipSize(x + 1, y, dir);
                case 1:
                    return 1 + getShipSize(x - 1, y, dir);
                case 2:
                    return 1 + getShipSize(x, y + 1, dir);
                case 3:
                    return 1 + getShipSize(x, y - 1, dir);
            }
            return 0;
        }

        int getShipSize(int x, int y) {
            return 1 + getShipSize(x + 1, y, 0) + getShipSize(x - 1, y, 1)
                    + getShipSize(x, y + 1, 2) + getShipSize(x, y - 1, 3);
        }

        void minusShip(int x, int y) {
            int s = getShipSize(x, y);
            shipsLeft[s]--;
            if (s == maxShip && shipsLeft[s] == 0) {
                maxShip = 0;
                for (int i = 0; i < shipsLeft.length; i++) if (shipsLeft[i] > 0) maxShip = i;
            }
        }

        boolean isShipsLeft() {
            return maxShip > 0;
        }

        int getMaxShipSize(int x, int y) {
            return maxShip;
        }

        @Override
        public String toString() {
            String s = "\n 0123456789\n";
            for (int y = 0; y < maxX; y++) {
                s += y;
                for (int x = 0; x < maxY; x++)
                    switch (field[x][y]) {
                        case MISS:
                            s += ".";
                            break;
                        case HIT:
                            s += "x";
                            break;
                        case DESTROYED:
                            s += "D";
                            break;
                        case UNKNOWN:
                            s += " ";
                            break;
                        case CANTBE:
                            s += "-";
                            break;
                    }
                s += "\n";
            }
            return s;
        }
    }

    public void battleAlgorithm1(SeaBattle seaBattle) {
        Field field = new Field(seaBattle);
        for (int y = 0; y < seaBattle.getSizeX(); y++) {
            for (int x = 0; x < seaBattle.getSizeY(); x++) {
                if (field.field[x][y] == FieldDot.UNKNOWN)
                    field.mark(x, y, seaBattle.fire(x, y));
            }
            if (y > 5) System.out.println(field);
        }
    }

    Field field;
    int phase;
    boolean findInjured;
    int x, y;

    boolean checkValue(int xx, int yy, FieldDot fieldDot) {
        System.out.println("checkValue x="+xx+" y="+yy+" dot="+fieldDot);
        if (xx < 0 || yy < 0 || xx >= field.maxX || yy >= field.maxY) return false;
        return field.field[xx][yy] == fieldDot;
    }

    boolean tryDot(int xx, int yy) {
        if (checkValue(xx, yy, FieldDot.UNKNOWN)) {
            x = xx;
            y = yy;
            return true;
        }
        return false;
    }

    boolean findInjuredNear(int startX, int startY) {
        boolean dirX = false;
        boolean dirY = false;
        if (checkValue(startX - 1, startY, FieldDot.HIT)) {
            dirX = true;
        } else if (checkValue(startX + 1, startY, FieldDot.HIT)) {
            dirX = true;
        } else if (checkValue(startX, startY - 1, FieldDot.HIT)) {
            dirY = true;
        } else if (checkValue(startX, startY + 1, FieldDot.HIT)) {
            dirY = true;
        } else if (checkValue(startX-1, startY, FieldDot.UNKNOWN)) {
            x = startX - 1;
            y = startY;
            return true;
        } else if (checkValue(startX+1, startY, FieldDot.UNKNOWN)) {
            x = startX + 1;
            y = startY;
            return true;
        } else if (checkValue(startX, startY-1, FieldDot.UNKNOWN)) {
            x = startX;
            y = startY - 1;
            return true;
        } else if (checkValue(startX, startY+1, FieldDot.UNKNOWN)) {
            x = startX;
            y = startY + 1;
            return true;
        } else return false;
        System.out.println("x="+dirX+" y="+dirY);
        if (dirX) {
            y = startY;
            if (checkValue(startX - 1, y, FieldDot.UNKNOWN)) {
                x = startX - 1;
                return true;
            }
            if (checkValue(startX - 1, y, FieldDot.HIT)) {
                if (checkValue(startX - 2, y, FieldDot.UNKNOWN)) {
                    x = startX - 2;
                    return true;
                }
                if (checkValue(startX - 2, y, FieldDot.HIT) && checkValue(startX - 3, y, FieldDot.UNKNOWN)) {
                    x = startX - 3;
                    return true;
                }
            }
            if (checkValue(startX + 1, y, FieldDot.UNKNOWN)) {
                x = startX + 1;
                return true;
            }
            if (checkValue(startX + 1, y, FieldDot.HIT)) {
                if (checkValue(startX + 2, y, FieldDot.UNKNOWN)) {
                    x = startX + 2;
                    return true;
                }
                if (checkValue(startX + 2, y, FieldDot.HIT) && checkValue(startX + 3, y, FieldDot.UNKNOWN)) {
                    x = startX + 3;
                    return true;
                }
            }
        } else if (dirY) {
            x = startX;
            if (checkValue(x, startY - 1, FieldDot.UNKNOWN)) {
                y = startY - 1;
                return true;
            }
            if (checkValue(x, startY - 1, FieldDot.HIT)) {
                if (checkValue(x, startY - 2, FieldDot.UNKNOWN)) {
                    y = startY - 2;
                    return true;
                }
                if (checkValue(x, startY - 2, FieldDot.HIT) && checkValue(x, startY - 3, FieldDot.UNKNOWN)) {
                    y = startY - 3;
                    return true;
                }
            }
            if (checkValue(x, startY + 1, FieldDot.UNKNOWN)) {
                y = startY + 1;
                return true;
            }
            if (checkValue(x, startY + 1, FieldDot.HIT)) {
                if (checkValue(x, startY + 2, FieldDot.UNKNOWN)) {
                    y = startY + 2;
                    return true;
                }
                if (checkValue(x, startY + 2, FieldDot.HIT) && checkValue(x, startY + 3, FieldDot.UNKNOWN)) {
                    y = startY + 3;
                    return true;
                }
            }
        }
        return false;
    }

    boolean findPopular() {
        if (tryDot(0, 2) || tryDot(0, field.maxY - 3)
                || tryDot(2, 0) || tryDot(2, field.maxY - 1)
                || tryDot(field.maxX - 3, 0) || tryDot(field.maxX - 3, field.maxY - 1)
                || tryDot(field.maxX - 1, 2) || tryDot(field.maxX - 1, field.maxY - 3))
            return true;
        return false;
    }

    boolean findFirstMaxShip() {
        int shipSize = field.maxShip;
        int lastEmpty;
        for (int yy = 0; yy < field.maxY; yy++) {
            lastEmpty = 0;
            for (int xx = 0; xx < field.maxX; xx++)
                if (field.field[xx][yy] != FieldDot.UNKNOWN) {
                    int freeSpace = xx - lastEmpty;
                    if (freeSpace >= shipSize) {
                        y = yy;
                        if(shipSize>1) {
                            if (freeSpace == shipSize || freeSpace > shipSize && freeSpace % (shipSize-1) == 1) { //?
                                x = lastEmpty + shipSize - 1 - y % 2;
                            }
                        }
                        x = lastEmpty + shipSize - 1;
                        return true;
                    }
                    lastEmpty = xx + 1;
                }
        }
        return false;
    }

    boolean findFirstEmpty() {
        for (int yy = 0; yy < field.maxY; yy++)
            for (int xx = 0; xx < field.maxX; xx++)
                if (field.field[xx][yy] == FieldDot.UNKNOWN) {
                    x = xx;
                    y = yy;
                    return true;
                }
        return false;
    }

    public void battleAlgorithm(SeaBattle seaBattle) {
        //самая заполненная клетка — A3 (и симметричные ей) — на них корабли есть в 475795243932227 случаях (25.6%),
        // самая незаполненная — Б2 (и симметричные) — она заполнена в 273993917558420 случаях (14.7%)
        field = new Field(seaBattle);
        phase = 10;
        findInjured = false;
        boolean fire = true;
        int iter = 0;
        x = 0;
        y = 0;
        int injX = 0, injY = 0;
        while (phase >= 0) {
            iter++;
            System.out.println("="+iter+"=");
            fire = false;
            if (findInjured) {
                System.out.println("findInjuredNear ()");
                if (findInjuredNear(injX, injY)) fire = true;
                //else findInjured = false;
            }
            if (!fire && phase == 10) {
                System.out.println("findPopular()");
                if (findPopular())fire = true;
                else phase = 20;
            }
            if (!fire && phase == 20) {
                System.out.println("findFirstMaxShip()");
                if (findFirstMaxShip()) fire = true;
            }
            if (!fire) {
                System.out.println("findFirstEmpty()");
                //if (findFirstEmpty()) fire = true;
            }
            if (fire) {
                field.mark(x, y, seaBattle.fire(x, y));
                if (findInjured) {
                    findInjured = field.field[x][y] != FieldDot.DESTROYED;
                } else {
                    findInjured = field.field[x][y] == FieldDot.HIT;
                    if (findInjured) {
                        injX = x;
                        injY = y;
                    }
                }
                if (field.field[x][y] != FieldDot.MISS)
                    if (!field.isShipsLeft()) phase = -1;
            } else
                phase = -2;
            if(iter>15) {
                System.out.println(field);
                try {
                    System.in.read();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
        System.out.println(field);
        System.out.println("iteration="+iter+" phase="+phase);
    }//152->172


    // функция для отладки
    public static void main(String[] args) {
        SeaBattle seaBattle = new SeaBattle(true);
        //SeaBattle seaBattle = new SeaBattle();
        new SeaBattleAlg().battleAlgorithm(seaBattle);
        System.out.println(seaBattle.getResult());
    }
}