import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList<E> {

    private final int DEFAULT_CAPACITY = 10;

    private int size;

    private Object[] elements;

    public MyArrayList() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        elements = new Object[capacity];

    }

    private void mergeSort(Object[] arr, int left, int right, Comparator cmp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, cmp);
            mergeSort(arr, mid + 1, right, cmp);
            merge(arr, left, mid, right, cmp);
        }
    }

    private void merge(Object[] arr, int left, int mid, int right, Comparator cmp) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Object[] L = new Object[n1];
        Object[] R = new Object[n2];
        System.arraycopy(arr, left, L, 0, n1);
        System.arraycopy(arr, mid + 1, R, 0, n2);
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (cmp.compare(R[j], L[i]) > 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) elements[index];
    }

    @SuppressWarnings("unchecked")
    private E elementCast(Object o) {
        return (E) o;
    }

    private void extend() {
        if (elements.length == 0) {
            elements = new Object[10];
            return;
        }
        Object[] temp = new Object[size * 2];
        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];
        }
        elements = temp;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void add(E e) {
        size++;
        if (size >= elements.length) extend();
        elements[size - 1] = e;
    }

    public void add(int index, E e) {
        checkIndex(index);
        size++;
        if (size >= elements.length) extend();
        Object buffer1;
        Object buffer2 = e;
        for (int i = index; i < size; i++) {
            buffer1 = elements[i];
            elements[i] = buffer2;
            buffer2 = buffer1;
        }

    }

    public E get(int index) {
        checkIndex(index);
        return elementData(index);
    }

    public E remove(int index) {
        checkIndex(index);
        E rem = elementData(index);
        Object[] temp = new Object[elements.length];
        for (int i = 0, j = 0; i < size; i++, j++) {
            if (i == index) j++;
            temp[i] = elements[j];
        }
        elements = temp;
        size--;
        return rem;
    }

    public void clear() {
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public void sort(Comparator<E> c) {
        if (size > 1) {
            this.mergeSort(elements, 0, size - 1, c);
        }
    }

    public String toString() {
        if (size == 0) return "[]";
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            str.append(elements[i]);
            if (i == size - 1) break;
            str.append(", ");
        }
        str.append("]");
        return str.toString();
    }
}

