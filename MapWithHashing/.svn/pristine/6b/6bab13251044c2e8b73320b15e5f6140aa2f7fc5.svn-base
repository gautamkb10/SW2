import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Keshab Gautam, Tej Patel
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /**
     * Test for constructor.
     */
    @Test
    public final void testConstructor() {
        Map<String, String> s = this.constructorTest();
        Map<String, String> sExpected = this.constructorRef();

        assertEquals(s, sExpected);
    }

    /**
     * Test for add.
     */
    @Test
    public final void testAddEmpty() {
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef("Jack", "50");

        s.add("Jack", "50");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("Jack", "50");
        Map<String, String> sExpected = this.createFromArgsRef("Jack", "50",
                "computer", "now");

        s.add("computer", "now");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddExtra1() {
        Map<String, String> s = this.createFromArgsTest("Jack", "50",
                "computer", "now");
        Map<String, String> sExpected = this.createFromArgsRef("Jack", "50",
                "computer", "now", "school", "OSU");

        s.add("school", "OSU");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddExtra2() {
        Map<String, String> s = this.createFromArgsTest("Jack", "50",
                "computer", "now", "five", "ten", "four", "eight", "two", "one",
                "red", "blue", "green", "orange");
        Map<String, String> sExpected = this.createFromArgsRef("Jack", "50",
                "computer", "now", "school", "OSU", "five", "ten", "four",
                "eight", "two", "one", "red", "blue", "green", "orange");

        s.add("school", "OSU");

        assertEquals(s, sExpected);
    }

    @Test
    public final void testAddExtra3() {
        Map<String, String> s = this.createFromArgsTest("Jack", "50");
        Map<String, String> sExpected = this.createFromArgsRef("Jack", "50",
                "computer", "now", "five", "eight", "six", "ten", "school",
                "OSU");

        s.add("computer", "now");
        s.add("five", "eight");
        s.add("six", "ten");
        s.add("school", "OSU");

        assertEquals(s, sExpected);
    }

    /**
     * Test for remove.
     */
    @Test
    public final void testRemoveToEmpty() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10");
        Map<String, String> sExpected = this.createFromArgsRef();

        s.remove("Tej");

        assertEquals(s, sExpected);

    }

    @Test
    public final void testRemoveToNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10", "Jack",
                "50");
        Map<String, String> sExpected = this.createFromArgsRef("Jack", "50");

        s.remove("Tej");

        assertEquals(s, sExpected);

    }

    @Test
    public final void testRemoveExtra1() {
        Map<String, String> s = this.createFromArgsTest("Jack", "50",
                "computer", "now", "school", "OSU", "five", "ten", "four",
                "eight", "two", "one", "red", "blue", "green", "orange");
        Map<String, String> sExpected = this.createFromArgsRef("Jack", "50",
                "computer", "now", "five", "ten", "four", "eight", "two", "one",
                "red", "blue", "green", "orange");

        s.remove("school");

        assertEquals(s, sExpected);

    }

    /**
     * Test for remove any.
     */
    @Test
    public final void testRemoveAnyToNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10", "Jack",
                "50");
        Map<String, String> sExpected = this.createFromArgsRef("Tej", "10",
                "Jack", "50");

        components.map.Map.Pair<String, String> removed = s.removeAny();

        int size = s.size();

        assertTrue(sExpected.hasKey(removed.key()));
        assertTrue(sExpected.hasValue(removed.value()));
        assertEquals(1, size);
    }

    /**
     * Test for remove any with one pair
     */
    @Test
    public final void testRemoveAnyToEmpty() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10");
        Map<String, String> sExpected = this.createFromArgsRef("Tej", "10");

        components.map.Map.Pair<String, String> removed = s.removeAny();
        int size = s.size();

        assertEquals(0, size);
        assertTrue(sExpected.hasKey(removed.key()));
        assertTrue(sExpected.hasValue(removed.value()));
    }

    /**
     * Test for value.
     */
    @Test
    public final void testValue() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10", "Jack",
                "50");
        Map<String, String> sExpected = this.createFromArgsRef("Tej", "10",
                "Jack", "50");

        String t1 = s.value("Tej");
        String t1Expected = "10";
        String t2 = s.value("Jack");
        String t2Expected = "50";

        assertEquals(t1, t1Expected);
        assertEquals(t2, t2Expected);
        assertEquals(s, sExpected);
    }

    /**
     * Test for value.
     */
    @Test
    public final void testValueOnePair() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10");
        Map<String, String> sExpected = this.createFromArgsRef("Tej", "10");

        String t1 = s.value("Tej");
        String t1Expected = "10";

        assertEquals(t1, t1Expected);
        assertEquals(s, sExpected);
    }

    /**
     * Test for has key.
     */
    @Test
    public final void testHasKeyNonEmpty() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10", "Jack",
                "50");
        Map<String, String> sExpected = this.createFromArgsRef("Tej", "10",
                "Jack", "50");

        assertTrue(s.hasKey("Jack"));
        assertFalse(s.hasKey("Obama"));
        assertTrue(sExpected.hasKey("Jack"));
        assertFalse(sExpected.hasKey("Obama"));
    }

    /**
     * Test for has key with empty map.
     */
    @Test
    public final void testHasKeyEmpty() {
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef();

        assertFalse(s.hasKey("Obama"));
        assertFalse(sExpected.hasKey("Obama"));
    }

    /**
     * Test for size.
     */
    @Test
    public final void testSize() {
        Map<String, String> s = this.createFromArgsTest("Tej", "10", "Jack",
                "50");
        Map<String, String> sExpected = this.createFromArgsRef("Tej", "10",
                "Jack", "50");
        int size = s.size();
        int sizeExpected = 2;
        int size1 = sExpected.size();
        int sizeExpected1 = 2;

        assertEquals(size, sizeExpected);
        assertEquals(size1, sizeExpected1);

    }

    /**
     * Test for size empty.
     */
    @Test
    public final void testSizeEmpty() {
        Map<String, String> s = this.createFromArgsTest();
        Map<String, String> sExpected = this.createFromArgsRef();
        int size = s.size();
        int sizeExpected = 0;
        int size1 = sExpected.size();
        int sizeExpected1 = 0;

        assertEquals(size, sizeExpected);
        assertEquals(size1, sizeExpected1);
    }

}
