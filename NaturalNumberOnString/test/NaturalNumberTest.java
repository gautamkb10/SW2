import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Keshab Gautam, Tej Patel
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /**
     * Test constructor
     */
    @Test
    public final void testConstructor() {

        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with ints
     */
    @Test
    public final void testConstructorInt0() {

        NaturalNumber n = this.constructorTest(0);
        NaturalNumber nExpected = this.constructorRef(0);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with ints
     */
    @Test
    public final void testConstructorInt() {

        NaturalNumber n = this.constructorTest(5678);
        NaturalNumber nExpected = this.constructorRef(5678);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with ints
     */
    @Test
    public final void testConstructorInt1() {

        NaturalNumber n = this.constructorTest(1234567891);
        NaturalNumber nExpected = this.constructorRef(1234567891);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with ints
     */
    @Test
    public final void testConstructorIntMax() {

        NaturalNumber n = this.constructorTest(Integer.MAX_VALUE);
        NaturalNumber nExpected = this.constructorRef(Integer.MAX_VALUE);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with strings
     */
    @Test
    public final void testConstructorStr0() {

        NaturalNumber n = this.constructorTest("0");
        NaturalNumber nExpected = this.constructorRef("0");

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with strings
     */
    @Test
    public final void testConstructorStr1() {

        NaturalNumber n = this.constructorTest("578");
        NaturalNumber nExpected = this.constructorRef("578");

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with strings
     */
    @Test
    public final void testConstructorStr2() {
        NaturalNumber n = this.constructorTest("12345678910111213");
        NaturalNumber nExpected = this.constructorRef("12345678910111213");

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with strings
     */
    @Test
    public final void testConstructorStr3() {

        NaturalNumber n = this.constructorTest("57");
        NaturalNumber nExpected = this.constructorRef("57");

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with NN
     */
    @Test
    public final void testConstructorNN() {
        NaturalNumber nnTest = this.constructorRef();

        NaturalNumber n = this.constructorTest(nnTest);
        NaturalNumber nExpected = this.constructorRef(nnTest);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with NN
     */
    @Test
    public final void testConstructorNN1() {
        NaturalNumber nnTest = this.constructorRef();

        NaturalNumber n = this.constructorTest(nnTest);
        NaturalNumber nExpected = this.constructorRef(0);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with NN
     */
    @Test
    public final void testConstructorNN2() {
        NaturalNumber nnTest = this.constructorRef(2);

        NaturalNumber n = this.constructorTest(nnTest);
        NaturalNumber nExpected = this.constructorRef(2);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with NN
     */
    @Test
    public final void testConstructorNN3() {
        NaturalNumber nnTest = this.constructorRef(123456);

        NaturalNumber n = this.constructorTest(nnTest);
        NaturalNumber nExpected = this.constructorRef(123456);

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with NN
     */
    @Test
    public final void testConstructorNN4() {
        NaturalNumber nnTest = this.constructorRef("123456");

        NaturalNumber n = this.constructorTest(nnTest);
        NaturalNumber nExpected = this.constructorRef("123456");

        assertEquals(n, nExpected);
    }

    /**
     * Test constructor with NN
     */
    @Test
    public final void testConstructorNN5() {
        NaturalNumber nnTest = this.constructorRef("12345678910111213");

        NaturalNumber n = this.constructorTest(nnTest);
        NaturalNumber nExpected = this.constructorRef("12345678910111213");

        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10
     */
    @Test
    public final void testmultiplyBy10() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef();

        n.multiplyBy10(0);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10
     */
    @Test
    public final void testmultiplyBy10T1() {
        NaturalNumber n = this.constructorTest(15);
        NaturalNumber nExpected = this.constructorRef("150");

        n.multiplyBy10(0);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10
     */
    @Test
    public final void testmultiplyBy10T2() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorRef("2");

        n.multiplyBy10(2);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10
     */
    @Test
    public final void test2multiplyBy10T3() {
        NaturalNumber n = this.constructorTest(146);
        NaturalNumber nExpected = this.constructorRef("1468");

        n.multiplyBy10(8);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10
     */
    @Test
    public final void test2multiplyBy10T4() {
        NaturalNumber n = this.constructorTest(1234567891);
        NaturalNumber nExpected = this.constructorRef("12345678913");
        int num = 3;
        n.multiplyBy10(num);
        assertEquals(n, nExpected);
    }

    /**
     * Test multiplyBy10
     */
    @Test
    public final void test2multiplyBy10T5() {
        NaturalNumber n = this.constructorTest(123456789);
        NaturalNumber nExpected = this.constructorRef("1234567890");
        int num = 0;
        n.multiplyBy10(num);
        assertEquals(n, nExpected);
    }

    /**
     * Test divideBy10 for empty
     */
    @Test
    public final void test1DivideBy10() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorTest();

        int remainder = n.divideBy10();
        int remainderExpected = 0;

        assertEquals(n, nExpected);
        assertEquals(remainder, remainderExpected);
    }

    /**
     * Test divideBy10 with one digit
     */
    @Test
    public final void test2DivideBy10() {
        NaturalNumber n = this.constructorTest(4);
        NaturalNumber nExpected = this.constructorTest();

        int remainder = n.divideBy10();
        int remainderExpected = 4;

        assertEquals(n, nExpected);
        assertEquals(remainder, remainderExpected);
    }

    /**
     * Test divideBy10 multi-digit number
     */
    @Test
    public final void test3DivideBy10() {
        NaturalNumber n = this.constructorTest(12345);
        NaturalNumber nExpected = this.constructorTest(1234);

        int remainder = n.divideBy10();
        int remainderExpected = 5;

        assertEquals(n, nExpected);
        assertEquals(remainder, remainderExpected);
    }

    /**
     * Test divideBy10 big number
     */
    @Test
    public final void test4DivideBy10() {
        NaturalNumber n = this.constructorTest(123456789);
        NaturalNumber nExpected = this.constructorTest(12345678);

        int remainder = n.divideBy10();
        int remainderExpected = 9;

        assertEquals(n, nExpected);
        assertEquals(remainder, remainderExpected);
    }

    /**
     * Test divideBy10 using string
     */
    @Test
    public final void test5DivideBy10() {
        NaturalNumber n = this.constructorTest("212409");
        NaturalNumber nExpected = this.constructorTest("21240");

        int remainder = n.divideBy10();
        int remainderExpected = 9;

        assertEquals(n, nExpected);
        assertEquals(remainder, remainderExpected);
    }

    /**
     * Test isZero with empty
     */
    @Test
    public final void test1IsZero() {
        NaturalNumber n = this.constructorTest();
        NaturalNumber nExpected = this.constructorTest();

        boolean check = n.isZero();

        assertEquals(n, nExpected);
        assertTrue(check);
    }

    /**
     * Test isZero with multiple zeros
     */
    @Test
    public final void test2IsZero() {
        NaturalNumber n = this.constructorTest(00000);
        NaturalNumber nExpected = this.constructorTest(00000);

        boolean check = n.isZero();

        assertEquals(n, nExpected);
        assertTrue(check);
    }

    /**
     * Test isZero with multi-digit number
     */
    @Test
    public final void test3IsZero() {
        NaturalNumber n = this.constructorTest(12320);
        NaturalNumber nExpected = this.constructorTest(12320);

        boolean check = n.isZero();

        assertEquals(n, nExpected);
        assertFalse(check);
    }

    /**
     * Test isZero with big number
     */
    @Test
    public final void test4IsZero() {
        NaturalNumber n = this.constructorTest(1234567890);
        NaturalNumber nExpected = this.constructorTest(1234567890);

        boolean check = n.isZero();

        assertEquals(n, nExpected);
        assertFalse(check);
    }

    /**
     * Test isZero using string
     */
    @Test
    public final void test5IsZero() {
        NaturalNumber n = this.constructorTest("12");
        NaturalNumber nExpected = this.constructorTest("12");

        boolean check = n.isZero();

        assertEquals(n, nExpected);
        assertFalse(check);
    }

    /**
     * Test isZero using NN
     */
    @Test
    public final void test6IsZero() {
        NaturalNumber n = this.constructorTest(00);
        NaturalNumber nExpected = this.constructorTest(n);

        boolean check = n.isZero();

        assertEquals(n, nExpected);
        assertTrue(check);
    }

}
