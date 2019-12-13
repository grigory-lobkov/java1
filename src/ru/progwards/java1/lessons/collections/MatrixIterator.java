package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

/*
Сделать итератор MatrixIterator по двумерному массиву (матрице),
который разворачивает матрицу в линейную последовательность построчно:
a[0][0], a[0][1], ...a[0][N],a[1][0], a[1][1]...a[1][N]... a[M][N]
Шаблон для итератора взять от ArrayIterator
*/

public class MatrixIterator<T> implements Iterator<T> {

    private T[][] array;
    int len;
    int idx;
    ArrayIterator<T> iterator;
    ArrayIterator<T> nextIterator;

    MatrixIterator(T[][] array) {
        this.array = array;
        len = array == null ? -1 : array.length;
        idx = 0;
        calcNextIterator();
        iterator = nextIterator;
    }

    void calcNextIterator() {
        int i = idx + 1;
        while (i < len) {
            if (array[i] != null && array[i].length > 0) {
                nextIterator = new ArrayIterator<>(array[i]);
                return;
            }
            i++;
        }
        nextIterator = null;
    }

    @Override
    public boolean hasNext() {
        return nextIterator != null;
    }

    @Override
    public T next() {
        if (!iterator.hasNext()) {
            idx++;
            iterator = nextIterator;
            if (!iterator.hasNext()) calcNextIterator();
        }
        return iterator.next();
    }

    public static void main(String[] args) {
        MatrixIterator<Integer> i = new MatrixIterator<>(new Integer[][]{new Integer[]{2, 1, 3}, new Integer[]{1, 2, 3}, new Integer[]{3, 2, 1}});
        while (i.hasNext()) System.out.println(i.next());
    }
}