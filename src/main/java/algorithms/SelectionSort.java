package algorithms;

import metrics.PerformanceTracker;

public class SelectionSort {

    private PerformanceTracker tracker;

    public SelectionSort() {
        this.tracker = new PerformanceTracker();
    }

    public SelectionSort(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    /**
     * Sorts an array using optimized selection sort.
     *
     * @param arr the array to sort
     * @throws IllegalArgumentException if array is null
     */
    public void sort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        tracker.reset();
        tracker.startTimer();

        int n = arr.length;

        // Handle edge cases
        if (n <= 1) {
            tracker.stopTimer();
            return;
        }

        // Selection sort with early termination
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            boolean swapOccurred = false;

            // Find minimum element in unsorted portion
            for (int j = i + 1; j < n; j++) {
                tracker.incrementComparisons();
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // Optimization: Only swap if needed
            if (minIdx != i) {
                swap(arr, i, minIdx);
                swapOccurred = true;
            }

            // Early termination: If no swaps and remaining elements are sorted
            if (!swapOccurred && isSortedFrom(arr, i)) {
                tracker.stopTimer();
                return;
            }
        }

        tracker.stopTimer();
    }

    /**
     * Checks if array is sorted from given index onwards.
     * Used for early termination optimization.
     *
     * @param arr the array to check
     * @param start starting index
     * @return true if sorted from start index
     */
    private boolean isSortedFrom(int[] arr, int start) {
        for (int i = start; i < arr.length - 1; i++) {
            tracker.incrementComparisons();
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Swaps two elements in the array.
     *
     * @param arr the array
     * @param i first index
     * @param j second index
     */
    private void swap(int[] arr, int i, int j) {
        tracker.incrementSwaps();
        tracker.incrementArrayAccesses(3); // 3 array accesses for swap
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Alternative implementation: Bidirectional selection sort.
     * Finds both min and max in each pass, sorting from both ends.
     * Can reduce passes by half in best scenarios.
     *
     * @param arr the array to sort
     */
    public void sortBidirectional(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        tracker.reset();
        tracker.startTimer();

        int n = arr.length;
        if (n <= 1) {
            tracker.stopTimer();
            return;
        }

        int left = 0;
        int right = n - 1;

        while (left < right) {
            int minIdx = left;
            int maxIdx = right;

            // Find both min and max in one pass
            for (int i = left; i <= right; i++) {
                tracker.incrementComparisons(2);
                if (arr[i] < arr[minIdx]) {
                    minIdx = i;
                }
                if (arr[i] > arr[maxIdx]) {
                    maxIdx = i;
                }
            }

            // Handle special case: max is at left position
            if (maxIdx == left) {
                maxIdx = minIdx;
            }

            // Place min at left
            if (minIdx != left) {
                swap(arr, left, minIdx);
            }

            // Place max at right
            if (maxIdx != right) {
                swap(arr, right, maxIdx);
            }

            left++;
            right--;
        }

        tracker.stopTimer();
    }

    /**
     * Gets the performance tracker for metrics collection.
     *
     * @return the performance tracker
     */
    public PerformanceTracker getTracker() {
        return tracker;
    }

    /**
     * Validates that an array is sorted in ascending order.
     *
     * @param arr the array to validate
     * @return true if sorted
     */
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return true;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
