package simpletest;

import deque.ArrayDeque;
import deque.Deque;
import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list tests. */
public class ArrayDequeTest {
    @Test
    public void sizeTest() {
        ArrayDeque<Integer> L1 = new ArrayDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        ArrayDeque<Integer> L3 = new ArrayDeque<>();

        for (int i = 0; i < 10; i++) {
            L2.addFirst(i);
        }
        for (int i = 0; i < 10; i++) {
            L1.addLast(i);
        }

        assertEquals(10, L2.size());
        assertEquals(10, L1.size());
        assertEquals(0, L3.size());
    }

    @Test
    public void getTest() {
        ArrayDeque L1 = new ArrayDeque();
        ArrayDeque L2 = new ArrayDeque();
        for (int i = 0; i < 10; i++) {
            L1.addLast(i);
            assertEquals(L1.get(i), i);
        }

        for (int j = 12; j >= 0; j--) {
            L2.addFirst(j);
        }

        for (int k = 0; k <= 12; k++) {
            assertEquals(L2.get(k), k);
        }
        assertEquals(L2.get(12), 12);
    }

    @Test
    public void getFirstTest() {
        ArrayDeque L1 = new ArrayDeque();
        ArrayDeque L2 = new ArrayDeque();

        for (int i = 10; i >= 0; i--) {
            L2.addFirst(i);
        }

        for (int i = 10; i >= 0; i--) {
            L1.addFirst(i);
        }

        for (int j = 0; j <= 10; j++) {
            assertEquals(L2.get(j), j);
            //i++;
        }

        for (int j = 0; j <= 10; j++) {
            assertEquals(L1.get(j), j);
            //i++;
        }
    }

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("lld1 should be empty upon initialization", lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse("lld1 should contain 1 item", lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue("lld1 should be empty after removal", lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);

    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String>  lld1 = new ArrayDeque<String>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, lld1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, lld1.removeLast());
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) lld1.removeLast(), 0.0);
        }
    }

    @Test
    public void equalTest() {
        Deque<Integer> a1 = new ArrayDeque<>();
        Deque<Integer> a2 = new ArrayDeque<>();

        assertTrue(a1.equals(a2));

        for (int i = 0; i < 10; i++) {
            a1.addLast(i);
            a2.addLast(i);
        }
        assertTrue(a1.equals(a2));

        a1.removeLast();
        assertFalse(a1.equals(a2));

    }

    @Test
    public void forEachLoopTest() {
        ArrayDeque<Integer> a1 = new ArrayDeque<>();
        ArrayDeque<Integer> a2 = new ArrayDeque<>();

        for (int i = 0; i < 1000; i++) {
            a1.addLast(i);
        }

        for (Integer n: a1) {
            System.out.println(n);
        }
        for (Integer n: a2) {
            System.out.println(n);
        }
    }
}
