package algorithms;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Random;

/**
 * Comprehensive test suite for SelectionSort implementation.
 * Covers edge cases, correctness, and optimization validation.
 */
class SelectionSortTest {

    private SelectionSort sorter;

    @BeforeEach
    void setUp() {
        sorter = new SelectionSort();
    }

    @Test
    @DisplayName("Test empty array")
    void testEmptyArray() {
        int[] arr = {};
        sorter.sort(arr);
        assertEquals(0, arr.length);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test single element array")
    void testSingleElement() {
        int[] arr = {42};
        sorter.sort(arr);
        assertEquals(1, arr.length);
        assertEquals(42, arr[0]);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test two element array")
    void testTwoElements() {
        int[] arr = {2, 1};
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test already sorted array")
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test reverse sorted array")
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test array with duplicates")
    void testDuplicates() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
        sorter.sort(arr);
        int[] expected = {1, 1, 2, 3, 3, 4, 5, 5, 6, 9};
        assertArrayEquals(expected, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test all same elements")
    void testAllSame() {
        int[] arr = {7, 7, 7, 7, 7};
        sorter.sort(arr);
        assertArrayEquals(new int[]{7, 7, 7, 7, 7}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test negative numbers")
    void testNegativeNumbers() {
        int[] arr = {-3, -1, -4, -1, -5};
        sorter.sort(arr);
        assertArrayEquals(new int[]{-5, -4, -3, -1, -1}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test mixed positive and negative")
    void testMixedNumbers() {
        int[] arr = {-2, 5, -8, 3, 0, -1, 10};
        sorter.sort(arr);
        assertArrayEquals(new int[]{-8, -2, -1, 0, 3, 5, 10}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test null array throws exception")
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            sorter.sort(null);
        });
    }

    @Test
    @DisplayName("Test large random array")
    void testLargeRandomArray() {
        Random rand = new Random(42); // Fixed seed for reproducibility
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(10000);
        }

        sorter.sort(arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test nearly sorted array")
    void testNearlySorted() {
        int[] arr = {1, 2, 3, 4, 10, 6, 7, 8, 9, 5};
        sorter.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Test bidirectional sort")
    void testBidirectionalSort() {
        int[] arr = {5, 2, 8, 1, 9, 3, 7, 4, 6};
        sorter.sortBidirectional(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Compare standard vs bidirectional on random data")
    void testBidirectionalCorrectness() {
        Random rand = new Random(123);
        int[] arr1 = new int[100];
        int[] arr2 = new int[100];

        for (int i = 0; i < 100; i++) {
            int val = rand.nextInt(1000);
            arr1[i] = val;
            arr2[i] = val;
        }

        SelectionSort sorter1 = new SelectionSort();
        SelectionSort sorter2 = new SelectionSort();

        sorter1.sort(arr1);
        sorter2.sortBidirectional(arr2);

        assertArrayEquals(arr1, arr2);
    }

    @Test
    @DisplayName("Verify metrics collection")
    void testMetricsCollection() {
        int[] arr = {3, 1, 4, 1, 5};
        sorter.sort(arr);

        assertTrue(sorter.getTracker().getComparisons() > 0,
                "Should record comparisons");
        assertTrue(sorter.getTracker().getSwaps() >= 0,
                "Should record swaps");
        assertTrue(sorter.getTracker().getElapsedTimeNanos() > 0,
                "Should record execution time");
    }

    @Test
    @DisplayName("Test extreme values")
    void testExtremeValues() {
        int[] arr = {Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -1, 1};
        sorter.sort(arr);
        assertArrayEquals(new int[]{Integer.MIN_VALUE, -1, 0, 1, Integer.MAX_VALUE}, arr);
        assertTrue(SelectionSort.isSorted(arr));
    }

    @Test
    @DisplayName("Property test: sorted output equals Arrays.sort")
    void testAgainstStandardSort() {
        Random rand = new Random(789);

        for (int trial = 0; trial < 10; trial++) {
            int size = 50 + rand.nextInt(100);
            int[] arr1 = new int[size];
            int[] arr2 = new int[size];

            for (int i = 0; i < size; i++) {
                int val = rand.nextInt(1000) - 500;
                arr1[i] = val;
                arr2[i] = val;
            }

            sorter.sort(arr1);
            Arrays.sort(arr2);

            assertArrayEquals(arr2, arr1,
                    "SelectionSort output should match Arrays.sort");
        }
    }
}
