package ru.sbt.Generics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sania on 23.10.2016.
 */
public class CountMapImpl<T> implements CountMap<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private int capacity;
    private int size;
    private Map<T, Integer> container;


    @SuppressWarnings("unchecked")
    public CountMapImpl(int size) {
        this.capacity = size;
        this.container = new HashMap<>(size);
        this.size = container.size();
    }

    @SuppressWarnings("unchecked")
    public CountMapImpl() {
        this.capacity = DEFAULT_CAPACITY;
        this.container = new HashMap<>(DEFAULT_CAPACITY);
        this.size = container.size();
    }


    @Override
    public void add(T o) {
        if (container.containsKey(o)) container.put(o, container.get(o) + 1);
        else {
            container.put(o, 1);
            size = container.size();
        }
    }


    @Override
    public int getCount(T o) {
        if (container.containsKey(o)) {
            return container.get(o);
        }
        return -1;
    }

    /*
    ** Returns number of occurrence of T
     */
    @SuppressWarnings("unchecked")
    @Override
    public int remove(T o) {
        if (container.containsKey(o)) {
            int tmp = container.get(o);
            container.remove(o);
            return tmp;
        }
        else return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        Map <T, Integer> map = new HashMap<>(source.size());
        source.toMap(map);
        if (container.isEmpty()) {
            container.putAll(map);
        }
        else {
            for (T t : map.keySet()) {
                this.add(t);
            }
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        return container;
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        destination.putAll(container);
    }

    public static void main(String[] args) {
        CountMap<Integer> map = new CountMapImpl<>();

        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);
        map.add(10);
        map.add(10);
        map.add(10);
        map.add(5);
        map.add(6);
        map.add(5);

        map.remove(10);

        int count = map.getCount(5); // 2
        System.out.println(count);
        count = map.getCount(6); // 1
        System.out.println(count);
        count = map.getCount(10); // 3
        System.out.println(count);

    }
}