package ru.sbt;

import java.util.HashMap;
import java.util.Map;

public class CountMapImpl<T> implements CountMap<T> {
    private static final int MAX_CAPACITY = 30;
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    private int capacity;
    private int size;
    private T[] container;
    private Integer[] counter;
    //private Map<T, Integer> container;


    @SuppressWarnings("unchecked")
    public CountMapImpl(int size) {
        if (size <= MAX_CAPACITY) {
            this.size = 0;
            this.capacity = size;
            this.container = (T[]) new Object[size];
        } else {
            this.size = 0;
            this.capacity = size;
            this.container = (T[]) new Object[MAX_CAPACITY];
            this.counter = new Integer[MAX_CAPACITY];
        }
    }

    @SuppressWarnings("unchecked")
    public CountMapImpl() {
        this.size = 0;
        this.capacity = DEFAULT_CAPACITY;
        this.container = (T[]) new Object[DEFAULT_CAPACITY];
        this.counter = new Integer[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    @Override
    public void add(T o) {
        int index= find(o);
        //new element
        if (index == -1) {
            if (size + 1 >= capacity) {
                container = resize(container, capacity + capacity);
                //container = resize(container, capacity + capacity);
                counter = (Integer[]) resize((T[])counter, capacity + capacity);
                container[size] = o;
                counter[size++] = 1;
                System.out.println("added after resizing " + o);
            } else {
                container[size] = o;
                counter[size++] = 1;
                System.out.println("added " + o);
            }
        } else {//old element incrementing counter
            counter[index]++;
            System.out.println("incremented for " + o + " index " + counter[index]);
        }

    }


    @Override
    public int getCount(T o) {
        int index = find(o);
        return index != -1 ? counter[index] : index;

    }

    public int find(T o) {
        if (o == null) {
            for (int i = 0; i < container.length; i++)
                if (container[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(container[i]))
                    return i;
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int remove(T o) {
        if (o == null) {
            for (int index = 0; index < size; index++)
                if (container[index] == null) {
                    int numMoved = size - index - 1;
                    if (numMoved > 0)
                        System.arraycopy(container, index + 1, container, index,
                                numMoved);
                    container[--size] = null; // clear to let GC do its work
                    return 1;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equals(container[index])) {
                    int numMoved = size - index - 1;
                    if (numMoved > 0)
                        System.arraycopy(container, index + 1, container, index,
                                numMoved);
                    container[--size] = null; // clear to let GC do its work
                    return 1;
                }
        }
        return -1;
    }


    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void addAll(CountMap<? extends T> source) {
        if (capacity > size + source.size()) {
            System.arraycopy(source, 0, container, size, source.size());
        } else {
            resize(container, size + source.size() + 1);
            System.arraycopy(source, 0, container, size, source.size());
        }
    }

    @Override
    public Map<T, Integer> toMap() {
        HashMap<T, Integer> map = new HashMap<>(size);
        for (int i = 0; i < size; i++) {
            map.put(container[i], counter[i]);
        }
        return map;
    }

    @Override
    public void toMap(Map<? super T, Integer> destination) {
        for (int i = 0; i < size; i++) {
            destination.put(container[i], counter[i]);
        }
    }

    @SuppressWarnings("unchecked")
    private T[] resize(T[] oldContainer, int newSize) {

        if (newSize > capacity) {
            T[] newCont = (T[]) new Object[newSize];
            System.arraycopy(oldContainer, 0, newCont, 0, oldContainer.length);
            container = newCont;
            capacity = newSize;
            return newCont;
        }
        return oldContainer;
    }
}
