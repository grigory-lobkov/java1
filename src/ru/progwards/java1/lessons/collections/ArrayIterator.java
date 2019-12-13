package ru.progwards.java1.lessons.collections;

import java.util.Iterator;

/*
Сделать итератор по одномерному массиву, реализовать методы hasNext() и next()
Шаблон пустого итератора:
	public class ArrayIterator<T> implements Iterator<T> {

		private T[] array;

		ArrayIterator(T[] array) {
			this.array = array;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public T next() {
			// TODO Auto-generated method stub
			return null;
		}
	}
*/
public class ArrayIterator<T> implements Iterator<T> {

    private T[] array;
    int lastIdx;
    int idx;

    ArrayIterator(T[] array) {
        this.array = array;
        lastIdx = array == null ? -1 : array.length - 1;
        idx = -1;
    }

    @Override
    public boolean hasNext() {
        return idx < lastIdx;
    }

    @Override
    public T next() {
        return array[++idx];
    }

    public static void main(String[] args) {
        ArrayIterator<Integer> i = new ArrayIterator<>(new Integer[]{2, 1, 3});
        while (i.hasNext()) System.out.println(i.next());
    }
}
