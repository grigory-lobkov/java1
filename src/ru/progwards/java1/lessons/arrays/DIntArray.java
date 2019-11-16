package ru.progwards.java1.lessons.arrays;

/*
Реализовать динамический, саморастущий массив целых чисел, по следующей спецификации:

3.1 в классе разместить private переменную - массив целых чисел.
3.2 конструктор - по умолчанию.
3.2 метод
public void add(int num) - добавляет элемент num в конец массива, при этом размер массива должен увеличиться на 1.
Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый, и добавить в хвост элемент num.
3.3 метод
public void atInsert(int pos, int num) - добавляет элемент num в позицию pos массива, при этом размер массива должен
увеличиться на 1. Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый, c учетом
того, что новый элемент окажется где-то в середине, и потом положить в нужный индекс элемент num.
3.4 метод
public void atDelete(int pos) - удаляет элемент в позиции pos массива, при этом размер массива должен уменьшиться на 1.
Для этого нужно будет разместить новый массив нужного размера, скопировать в него старый, уже без элемента, который был
в позиции pos.
3.5 метод
public int at(int pos) - возвращает элемент по индексу pos.
*/

import java.util.Arrays;

public class DIntArray {

    private int[] nums = {};

    DIntArray() {};

    // добавляет элемент num в конец массива
    public void add(int num) {
        int[] nums1 = new int[nums.length + 1];
        System.arraycopy(nums, 0, nums1, 0, nums.length);
        nums1[nums.length] = num;
        nums = nums1;
    }
    // добавляет элемент num в позицию pos массива
    public void atInsert(int pos, int num) {
        int[] nums1 = new int[nums.length + 1];
        System.arraycopy(nums, 0, nums1, 0, pos);
        System.arraycopy(nums, pos, nums1, pos + 1, nums.length - pos);
        nums1[pos] = num;
        nums = nums1;
    }
    // удаляет элемент в позиции pos массива
    public void atDelete(int pos) {
        int[] nums1 = new int[nums.length - 1];
        System.arraycopy(nums, 0, nums1, 0, pos);
        System.arraycopy(nums, pos + 1, nums1, pos, nums.length - pos - 1);
        nums = nums1;
    }
    // возвращает элемент по индексу pos
    public int at(int pos) {
        return nums[pos];
    }

    @Override
    public String toString() {
        return Arrays.toString(nums);
    }
    public void print() {
        System.out.println(this.toString());
    }

    // test
    public static void main(String[] args) {
        DIntArray a = new DIntArray();
        a.print();
        a.add(1); a.add(2); a.add(4); a.add(5);
        a.print();
        a.atInsert(2, 3);
        a.print();
        a.atDelete(1);
        a.print();
        System.out.println("a[2] = " + a.at(2));
    }

}

/*
[]
[1, 2, 4, 5]
[1, 2, 3, 4, 5]
[1, 3, 4, 5]
a[2] = 4
*/