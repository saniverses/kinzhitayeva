package ru.sbt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Sania on 02.11.2016.
 */
public class CollectionUtils<T> {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List newArrayList() {
        return new ArrayList<T>();
    }

    public static <T> int indexOf(List<? extends T> source, Object o) {
        return source.indexOf(o);
    }

    public static <T> List limit(List<? extends T> source, int size) {
        if (size < source.size())
            return source.subList(0, size);
        else return null;
    }

    public static <T> void add(List<? super T> source, Object o) {
        if (o != null) {
            try {
                T t = (T) o;
                source.add(t);
            } catch(ClassCastException e) {
            }
        }
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        removeFrom.removeAll(c2);
    }

    //true если первый лист содержит все элементы второго
    public static <T> boolean containsAll(List<? super T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    //true если первый лист содержит хотя-бы 1 второго
    public static <T> boolean containsAny(List<? super T> c1, List<? extends T> c2) {
        for (T o : c2) {
            if (c1.contains(o)) return true;
        }
        return false;
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    //<T extends Object & Comparable<? super T>>
    public static <T extends Object & Comparable<? super T>> List range(List<? extends T> list, Object min, Object max) {
        List<T> result = new ArrayList<>();
        T oMin = null;
        T oMax = null;
        try {
            if (min != null) oMin = (T) min;
            try {
                if (max != null) oMax = (T) max;
                for (int i = 0; i < list.size(); i++) {
                    T tmp = list.get(i);
                    if ((tmp.compareTo(oMin) >= 0) && (tmp.compareTo(oMax) <= 0)) result.add(tmp);
                    Collections.sort(list);
                }
                return result;
            } catch (ClassCastException e) {
                return null;
            }
        } catch (ClassCastException e) {
            return null;
        }
    }

    //Возвращает лист, содержащий элементы из входного листа в диапазоне от min до max.
    // Элементы сравнивать через Comparable.
    // Прмер range(Arrays.asList(8,1,3,5,6, 4), 3, 6) вернет {3,4,5,6}
    public static <T> List range(List<? extends T> list, Object min, Object max, Comparator<? super T> comparator) {
        List<T> result = new ArrayList<>();
        T oMin = null;
        T oMax = null;
        try {
            if (min != null) oMin = (T) min;
            try {
                if (max != null) oMax = (T) max;
                for (int i = 0; i < list.size(); i++) {
                    T tmp = list.get(i);
                    //if ((list.get(i).compareTo(oMin) >= 0) && (list.get(i).compareTo(oMax) <= 0)) result.add((T)list.get(i));
                    //comparator.
                    if ((comparator.compare(tmp, oMin) >= 0) && (comparator.compare(tmp, oMax) <= 0)) result.add(tmp);
                    Collections.sort(list, comparator);
                }
                return result;
            } catch (ClassCastException e) {
                return null;
            }
        } catch (ClassCastException e) {
            return null;
        }
    }
}
