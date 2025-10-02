package metrics;

/**
 * Tracks performance metrics for algorithm analysis.
 * Collects comparisons, swaps, array accesses, and execution time.
 */
public class PerformanceTracker {
    private long comparisons;
    private long swaps;
    private long arrayAccesses;
    private long memoryAllocations;
    private long startTime;
    private long endTime;
    private boolean timerRunning;

    public PerformanceTracker() {
        reset();
    }

    /**
     * Resets all metrics to zero.
     */
    public void reset() {
        comparisons = 0;
        swaps = 0;
        arrayAccesses = 0;
        memoryAllocations = 0;
        startTime = 0;
        endTime = 0;
        timerRunning = false;
    }

    /**
     * Starts the execution timer.
     */
    public void startTimer() {
        startTime = System.nanoTime();
        timerRunning = true;
    }

    /**
     * Stops the execution timer.
     */
    public void stopTimer() {
        if (timerRunning) {
            endTime = System.nanoTime();
            timerRunning = false;
        }
    }

    /**
     * Gets elapsed time in nanoseconds.
     */
    public long getElapsedTimeNanos() {
        if (timerRunning) {
            return System.nanoTime() - startTime;
        }
        return endTime - startTime;
    }

    /**
     * Gets elapsed time in milliseconds.
     */
    public double getElapsedTimeMillis() {
        return getElapsedTimeNanos() / 1_000_000.0;
    }

    public void incrementComparisons() {
        comparisons++;
    }

    public void incrementComparisons(long count) {
        comparisons += count;
    }

    public void incrementSwaps() {
        swaps++;
    }

    public void incrementSwaps(long count) {
        swaps += count;
    }

    public void incrementArrayAccesses() {
        arrayAccesses++;
    }

    public void incrementArrayAccesses(long count) {
        arrayAccesses += count;
    }

    public void incrementMemoryAllocations() {
        memoryAllocations++;
    }

    public void incrementMemoryAllocations(long count) {
        memoryAllocations += count;
    }

    // Getters
    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getArrayAccesses() {
        return arrayAccesses;
    }

    public long getMemoryAllocations() {
        return memoryAllocations;
    }

    /**
     * Exports metrics as CSV line.
     */
    public String toCSV(int inputSize) {
        return String.format("%d,%d,%d,%d,%d,%.3f",
                inputSize,
                comparisons,
                swaps,
                arrayAccesses,
                memoryAllocations,
                getElapsedTimeMillis()
        );
    }

    /**
     * Gets CSV header.
     */
    public static String getCSVHeader() {
        return "InputSize,Comparisons,Swaps,ArrayAccesses,MemoryAllocations,TimeMs";
    }

    @Override
    public String toString() {
        return String.format(
                "Performance Metrics:\n" +
                        "  Comparisons: %,d\n" +
                        "  Swaps: %,d\n" +
                        "  Array Accesses: %,d\n" +
                        "  Memory Allocations: %,d\n" +
                        "  Execution Time: %.3f ms",
                comparisons, swaps, arrayAccesses, memoryAllocations, getElapsedTimeMillis()
        );
    }
}
