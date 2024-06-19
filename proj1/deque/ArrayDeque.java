package deque;

/**
 * (nextFirst + 1) % items.length is the current First
 * (nextLast - 1 + items.length) % items.length  is the current Last
 * use modulo here to make the array index circular within front and back
 * (nextLast - 1 + items.length) Note: + items.length here to avoid negative index
 * This invariance ensure the circular approach works well (Most likely for now??)
 */

public class ArrayDeque<Type> implements Deque<Type>{
    private Type[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (Type[]) new Object[8];
        nextFirst = items.length / 2;
        nextLast = (items.length / 2) + 1;
    }

    private void resize(int capacity) {
        Type[] a = (Type[]) new Object[capacity];
        int currI = (nextFirst + 1) % items.length;

        for (int i = 0; i < size; i++) {
            //copy the existing item from ori array to resized array
            // and this resized array starts from zero index based
            a[i] = items[currI];
            currI = (currI + 1) % items.length;
        }
        items = a;
        // since resized array is zero index based, the first element is items[0]
        // example: [0,1,2,3,4,5,6,7,nextLast,x,nextFirst]
        // so we let nextFirst = index 7 to fit the circular standard
        // and nextLast to be the size which is index 8
        nextFirst = capacity - 1;
        nextLast = size;
    }

    @Override
    public void addFirst(Type v) {
        // when the array is full, resize it do double size
        if (size >= items.length) {
            resize((items.length) * 2);
        }
        items[nextFirst] = v;
        // I use nextFirst + items.length here to avoid negative index
        nextFirst = (nextFirst + items.length - 1) % items.length;
        size++;
    }

    @Override
    public void addLast(Type v) {
        // when the array is full, resize it do double size
        if (size >= items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = v;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        // O(n)
        int t = 0;
        int firstIndex = (nextFirst + 1) % items.length;
        while (t < items.length) {
            System.out.print(items[firstIndex] + " ");
            firstIndex = (firstIndex + 1) % items.length;
            t++;
        }
        System.out.println("");
    }

    @Override
    public Type removeFirst() {
        if (!isEmpty()) {
            if (items.length >= 16 && ((float) size / items.length) <= 0.25) {
                resize(items.length / 2);
            }
            Type firstItem = items[(nextFirst + 1) % items.length];
            items[(nextFirst + 1) % items.length] = null;
            nextFirst = (nextFirst + 1) % items.length;
            size--;
            return firstItem;
        }
        return null;
    }

    @Override
    public Type removeLast() {
        if (!isEmpty()) {
            if (items.length >= 16 && ((float) size / items.length) <= 0.25) {
                resize(items.length / 2);
            }
            Type lastItem = items[(nextLast - 1 + items.length) % items.length];
            items[(nextLast - 1 + items.length) % items.length] = null;
            nextLast = (nextLast - 1 + items.length) % items.length;
            size--;
            return lastItem;
        }
        return null;
    }

    @Override
    public Type get(int i) {
        if (i < 0 || i >= size) {
            return null;
        }
        return items[(nextFirst + 1 + i) % items.length];
    }

    public Type getLast() {
        return items[(nextLast - 1 + items.length) % items.length];
    }

    public Type getFirst() {
        return items[(nextFirst + 1) % items.length];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> L1 = new ArrayDeque<>();
        L1.addFirst(4);
        System.out.println(L1.isEmpty());
        L1.addLast(1);
        L1.addLast(2);
        L1.addLast(3);
        L1.addFirst(10);
        System.out.println(L1.removeFirst());
        System.out.println(L1.size());

        L1.printDeque();
    }
}
