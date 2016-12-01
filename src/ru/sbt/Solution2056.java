package ru.sbt;

import java.util.*;

/**
 * Created by Sania on 14.10.2016.
 * 2056. Структуры данных. Самое популярное слово
 * ограничение времени на тест: 0.75 seconds
 * ограничение памяти на тест: 64 megabytes
 * ввод: standard
 * вывод: standard
 *
 * Дан текст, ваша задача — найти слово, которое встречается в тексте наибольшее количество раз.
 * Текст состоит только из латинских букв, пробелов, переводов строк.
 *
 * Слово — это последовательность подряд идущих латинских букв, регистр не имеет значения.
 *
 * Если искомых слов несколько, ваша задача — найти все такие слова.
 *
 * Входные данные
 * Входные данные состоят только из латинских букв, пробелов и символов перевода строки. Гарантируется, что хотя бы одно слово в текст присутствует.
 * Выходные данные
 * Выведите все слова, которые встречаются наибольшее количество раз, при их следут приводить к нижнему регистру, каждое слово выводите на отдельной строке. Слова выводите в лексикографическом порядке. Размер входного файла не превосходит 100 Кб.
 */
public class Solution2056 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SortedMap<String, Integer> map = new TreeMap<>();
        while (scanner.hasNext()) {
            String next = scanner.next();
            next = next.toLowerCase();
            if (map.containsKey(next))
                map.put(next, map.get(next) + 1);
            else map.put(next, 1);
        }

        Integer maxValue = map.get(map.firstKey());
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            Integer value = entry.getValue();
            if (maxValue < value) {
                maxValue = value;
            }
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (value == maxValue) {
                //max.add(key);
                System.out.println(key);
            }
        }
    }
}
