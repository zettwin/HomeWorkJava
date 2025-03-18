import java.util.Arrays;
import java.util.Comparator;

public class MyLinkedList<E> {

    private int size = 0;

    private Node<E> first;

    private Node<E> last;

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public void addFirst(E e) {
        linkFirst(e);
    }

    public void addLast(E e) {
        linkLast(e);
    }

    public void add(int index, E element) {
        checkIndex(index);
        if (index == size)
            linkLast(element);
        else
            linkBefore(element, getNode(index));
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        checkIndex(index);
        return getNode(index).item;
    }

    public E peek() {
        Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    public E poll() {
        Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    public E pollFirst() {
        Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    public E pollLast() {
        Node<E> l = last;
        return (l == null) ? null : unlinkLast(l);
    }

    public E set(int index, E element) {
        checkIndex(index);
        Node<E> x = getNode(index);
        E oldVal = x.item;
        x.item = element;
        return oldVal;
    }

    public E remove(int index) {
        checkIndex(index);
        return unlink(getNode(index));
    }

    public E remove() {
        return unlink(this.first);
    }

    public String toString() {
        if (size == 0) return "[]";
        StringBuilder str = new StringBuilder("[");
        Node tmp = first;
        for(int i = 0; i < size; i++){
            str.append(tmp.item);
            if(i == size - 1) break;
            str.append(", ");
            tmp = tmp.next;
        }
        str.append("]");
        return str.toString();
    }

    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    private void linkFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    private void linkLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    private void linkBefore(E e, Node<E> NextNode) {
        Node<E> pred = NextNode.prev;
        Node<E> newNode = new Node<>(pred, e, NextNode);
        NextNode.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
    }

    private E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    private E unlinkLast(Node<E> l) {
        E element = l.item;
        Node<E> prev = l.prev;
        l.item = null;
        l.prev = null;
        last = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        return element;
    }

    private E unlink(Node<E> eNode) {
        E element = eNode.item;
        Node<E> next = eNode.next;
        Node<E> prev = eNode.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            eNode.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            eNode.next = null;
        }

        eNode.item = null;
        size--;
        return element;
    }

    private Node<E> getNode(int index) {
        Node<E> node = first;
        for (int i = 0; i < index; i++)
            node = node.next;
        return node;
    }

    private void checkIndex(int index) {
        if (index < 0 || size < index){
            throw new IndexOutOfBoundsException();
        }
    }

    private Node[] quickSort(Node<E>[] a, Comparator<E> cmp) {
        if (a.length <= 1) return a;
        Node<E> pivot = a[0];
        Node<E>[] left = new Node[a.length - 1];
        Node<E>[] right = new Node[a.length - 1];
        int lIndex = 0;
        int rIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (cmp.compare(a[i].item, pivot.item) > 0) {
                right[rIndex] = a[i];
                rIndex++;
            } else {
                left[lIndex] = a[i];
                lIndex++;
            }
        }
        if (lIndex > 0) left = (Node<E>[]) quickSort(Arrays.copyOfRange(left, 0, lIndex), cmp);
        if (rIndex > 0) right = (Node<E>[]) quickSort(Arrays.copyOfRange(right, 0, rIndex), cmp);
        Node[] result = new Node[lIndex + rIndex + 1];
        for (int i = 0; i < lIndex; i++) {
            result[i] = left[i];
        }
        result[lIndex] = pivot;
        for (int i = 0; i < rIndex; i++) {
            result[lIndex + 1 + i] = right[i];
        }
        return result;
    }

    public void sort(Comparator<E> cmp) {
       if(size < 2) return;
       Node[] arr = new Node[size];
       arr[0] = first;
        for (int i = 1; i < size; i++) {
            arr[i] = arr[i - 1].next;
        }
        arr = quickSort(arr, cmp);
        first = arr[0];
        Node tmp = first;
        for (int i = 1; i < size; i++) {
            tmp.next = arr[i];
            arr[i].prev = tmp;
            tmp = arr[i];
        }
        last = tmp;
    }
}
