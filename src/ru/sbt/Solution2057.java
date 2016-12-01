package ru.sbt;

import java.util.*;


/**
 * Created by Sania on 14.10.2016.
 * 2057. Структуры данных. Множество
 * Ваша задача — реализовать структуру данных, которая умеет хранить мультимножество натуральных чисел,
 * т.е. в этой структуре могут одновременно храниться несколько равных элементов.
 * Эта структура должна поддерживать две операции:
 * 1. добавить элемент x в множество
 * 2. удалить минимальный элемент в множестве и вернуть его значение (если минимальных элементов несколько,
 * то удаляется только один из них)
 * Входные данные
 * Первая строка входных данных содержит число n (1 ≤ n ≤ 106) — количество операций.
 * Далее в n строках даны описания операций над множеством. Описание представляет собой число — тип запроса (1 или 2)
 * и число x (1 ≤ x ≤ 109) если это запрос первого типа.
 * Выходные данные
 * Для каждого запроса второго типа выведите результат на отдельной строке.
 */
public class Solution2057 {
    public static void main(String[] args) {
        HashSet<Integer> struc = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int oper;
        int j = 0;
        //int res[] = new int[n];
        for (int i = 0; i < n; i++) {
            oper = scanner.nextInt();
            if (oper == 1)
                //int tmp
                struc.add(scanner.nextInt());
            else {
                //struc.remove(struc.first());
                //res[j++] = min;
                //System.out.println("to remove -> " + struc.first());
            }
        }
        /*
        for (int i = 0; i < res.length; i++) {
            System.out.println(res[i]);
        }
        */

    }
}
