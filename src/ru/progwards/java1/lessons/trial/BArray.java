package ru.progwards.java1.lessons.trial;

public class BArray<T extends Object> {
    /*
    Задача 3. Класс BArray
    Доработать ранее созданный класс BArray, взять исходные тексты из дополнительных материалов к занятию из src-v1.zip
    2.1 Перенести самую оптимальную реализацию
    void copyData(T[] src, Object[] dst), через System.arraycopy, из src-v2.zip
    2.2 Перенести реализацию функции
    public int size(), из src-v1.zip
    2.3 добавить реализацию функции
    public void insert(int index, T item), которая добавляет элемент в массив, размещая его по индексу, заданному параметром index.
    При этом все элементы, с индексом > index, сдвигаются вправо, и количество элементов массива увеличивается на 1.
    2.4 добавить реализацию функции
    public void delete(int index), которая удаляет элемент, расположенный по индексу, заданному параметром index. При этом все элементы,
    с индексом > index, сдвигаются влево, и количество элементов массива уменьшается на 1
     */
    T[] array;
    int size;
    int blockSize;

    public BArray(int inilialSize, int blockSize) {
        this.blockSize = blockSize;
        array = (T[]) new Object[inilialSize];
    }

    public void add(T item) {
        if (array.length == size) {
            int newSize = array == null ? 1 : array.length + blockSize;
            Object[] newArray = new Object[newSize];
            if (array != null)
                copyData(array, newArray);
            array = (T[]) newArray;
        }
        array[size++] = item;
    }

    void copyData(T[] src, Object[] dst) {
        //for (int i = 0; i < src.length; i++)
        //    dst[i] = src[i];
        System.arraycopy(src,0, dst, 0, src.length );
    }

    public T get(int index) {
        return array[index];
    }

    public int size() {
        return size;
    }

    public void print() {
        String s = "";
        for (T t : array) s += t + " ";
        System.out.println(s);
    }

    public void insert(int index, T item) {
        //if (index > size) raise;
        if (array.length == size) {
            int newSize = array == null ? 1 : array.length + blockSize;
            Object[] newArray = new Object[newSize];
            if (array != null) {
                copyData(array, newArray);
            }
            array = (T[]) newArray;
        }
        System.arraycopy(array, index, array, index + 1, size - index );
        size++;
        array[index] = item;
    }

    public void delete(int index) {
        System.arraycopy(array, index + 1, array, index, size-- - index );
    }

    public static int[] subArrayMul(int[] array,  int mul) {
        if (mul < 1) return null;
        int c = (array.length) / mul;
        int[] rslt = new int[c];
        for (int i = 0; i < c; i++) rslt[i] = array[ i * mul ];
        return rslt;
    }

    public static void printInt(int[] array) {
        if (array == null) {
            System.out.println("null");
        } else {
            String s = "";
            for (int i1 : array) s += i1 + " ";
            System.out.println(s);
        }
    }

}