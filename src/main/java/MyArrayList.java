import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList<E> {

    private int size;

    private Object[] elements;

    public MyArrayList() {
        size = 0;
        elements = new Object[10];
    }

    public MyArrayList(int capacity) {
        if (capacity > 0){
            elements = new Object[capacity];
        } else if (capacity == 0) {
            elements = new Object[0];
        }else{
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }

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

    private E elementData(int index) {return (E) elements[index];}
    private E elementCast(Object o) {return (E) o;}
    private void extend(){
        if (elements.length == 0) {
            elements = new Object[10];
            return;
        }
        Object[] temp = new Object[size * 2];
        for(int i = 0; i < size; i++){
            temp[i] = elements[i];
        }
        elements = temp;
    }
    public void add(E e) {
        size++;
        if (size >= elements.length) extend();
        elements[size-1] = e;
    }
    public void add(int index, E e) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
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
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        return elementData(index);
    }

    public E remove(int index) {
        if(index < 0 || index > size) throw new IndexOutOfBoundsException();
        E rem = elementData(index);
        Object[] temp = new Object[elements.length];
        for(int i = 0, j = 0; i < size; i++, j++){
            if (i == index) j++;
            temp[i] = elements[j];
        }
        elements = temp;
        size--;
        return rem;
    }

    public void clear() {
        size = 0;
        elements = new Object[10];
    }

    public int length() {
        return size;
    }

    public void sort(Comparator<E> c) {
        if (size < 2) return;
        this.mergeSort(elements,0, size -1, c);
    }

    public String toString() {
        if (size == 0) return "[]";
        StringBuilder str = new StringBuilder("[");
        for(int i = 0; i < size; i++){
            str.append(elements[i]);
            if(i == size - 1) break;
            str.append(", ");
        }
        str.append("]");
        return str.toString();
    }
}

