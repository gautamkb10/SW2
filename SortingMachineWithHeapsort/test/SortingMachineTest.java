import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Keshab Gautam, Tej Patel
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    @Test
    public final void testAddToEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Mangoes");

        m.add("Mangoes");

        assertEquals(mExpected, m);

    }

    @Test
    public final void testAddNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Apples", "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Apples", "Mangoes", "Carrots");

        m.add("Carrots");

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Apples", "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Apples", "Mangoes");

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testChangeToExtractionEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        m.changeToExtractionMode();

        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstToNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Apples", "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Mangoes");

        String s = m.removeFirst();
        String sExpected = "Apples";

        assertEquals(s, sExpected);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testRemoveFirstToEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);

        String s = m.removeFirst();
        String sExpected = "Mangoes";

        assertEquals(mExpected, m);
        assertEquals(sExpected, s);
    }

    @Test
    public final void testIsInInsertionMode_True() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Mangoes");

        assertEquals(mExpected, m);
        assertTrue(m.isInInsertionMode());

    }

    @Test
    public final void testIsInInsertionMode_False() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Mangoes");

        assertEquals(mExpected, m);
        assertFalse(m.isInInsertionMode());

    }

    @Test
    public final void testOrder() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Mangoes");

        assertEquals(mExpected.order(), m.order());

    }

    @Test
    public final void testSizeEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true);

        int s = m.size();
        int sExpected = 0;

        assertEquals(mExpected, m);
        assertEquals(sExpected, s);

    }

    @Test
    public final void testSizeNonEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Apples", "Carrots", "Mangoes");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Apples", "Carrots", "Mangoes");

        int s = m.size();
        int sExpected = 3;

        assertEquals(mExpected, m);
        assertEquals(sExpected, s);

    }

    @Test
    public final void testSizeNonEmptyBig() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Apples", "Carrots", "Mangoes", "Dogs", "Cats", "Elephants",
                "Hippos");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Apples", "Carrots", "Mangoes", "Dogs", "Cats", "Elephants",
                "Hippos");

        int s = m.size();
        int sExpected = 7;

        assertEquals(mExpected, m);
        assertEquals(sExpected, s);

    }
}
