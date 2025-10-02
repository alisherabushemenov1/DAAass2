package cli;

import algorithms.SelectionSort;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

/**
 * CLI tool for benchmarking SelectionSort across various input configurations.
 * Generates CSV output for empirical analysis.
 */
public class BenchmarkRunner {

    private static final int[] INPUT_SIZES = {100, 500, 1000, 2000, 5000, 10000};
    private static final int WARMUP_RUNS = 3;
    private static final int MEASUREMENT_RUNS = 5;
    private static final Random RANDOM = new Random(42);

    public static void main(String[] args) {
        System.out.println("=== SelectionSort Benchmark Suite ===\n");

        String outputFile = args.length > 0 ? args[0] : "benchmark_results.csv";

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            writer.println("DataType," + PerformanceTracker.getCSVHeader());

            System.out.println("Running benchmarks...\n");

            // Test on different data distributions
            runBenchmark("Random", writer, BenchmarkRunner::generateRandomArray);
            runBenchmark("Sorted", writer, BenchmarkRunner::generateSortedArray);
            runBenchmark("ReverseSorted", writer, BenchmarkRunner::generateReverseSortedArray);
            runBenchmark("NearlySorted", writer, BenchmarkRunner::generateNearlySortedArray);
            runBenchmark("FewUnique", writer, BenchmarkRunner::generateFewUniqueArray);

            System.out.println("\nBenchmark complete! Results saved to: " + outputFile);

        } catch (IOException e) {
            System.err.println("Error writing results: " + e.getMessage());
            System.exit(1);
        }

        // Run comparison between standard and bidirectional
        runComparisonBenchmark();
    }

    /**
     * Runs benchmark for a specific data distribution.
     */
    private static void runBenchmark(String dataType, PrintWriter writer,
                                     ArrayGenerator generator) {
        System.out.println("Testing " + dataType + " data:");

        for (int size : INPUT_SIZES) {
            PerformanceTracker avgTracker = new PerformanceTracker();

            // Warmup
            for (int i = 0; i < WARMUP_RUNS; i++) {
                int[] arr = generator.generate(size);
                SelectionSort sorter = new SelectionSort();
                sorter.sort(arr);
            }

            // Measurement runs
            long totalComparisons = 0;
            long totalSwaps = 0;
            long totalArrayAccesses = 0;
            long totalTime = 0;

            for (int i = 0; i < MEASUREMENT_RUNS; i++) {
                int[] arr = generator.generate(size);
                SelectionSort sorter = new SelectionSort();
                sorter.sort(arr);

                if (!SelectionSort.isSorted(arr)) {
                    System.err.println("ERROR: Array not sorted correctly!");
                    System.exit(1);
                }

                PerformanceTracker tracker = sorter.getTracker();
                totalComparisons += tracker.getComparisons();
                totalSwaps += tracker.getSwaps();
                totalArrayAccesses += tracker.getArrayAccesses();
                totalTime += tracker.getElapsedTimeNanos();
            }

            // Calculate averages
            avgTracker.incrementComparisons(totalComparisons / MEASUREMENT_RUNS);
            avgTracker.incrementSwaps(totalSwaps / MEASUREMENT_RUNS);
            avgTracker.incrementArrayAccesses(totalArrayAccesses / MEASUREMENT_RUNS);

            double avgTimeMs = (totalTime / MEASUREMENT_RUNS) / 1_000_000.0;

            String csvLine = String.format("%s,%d,%d,%d,%d,0,%.3f",
                    dataType, size,
                    avgTracker.getComparisons(),
                    avgTracker.getSwaps(),
                    avgTracker.getArrayAccesses(),
                    avgTimeMs
            );

            writer.println(csvLine);

            System.out.printf("  n=%6d: %,10d comparisons, %,8d swaps, %.3f ms%n",
                    size, avgTracker.getComparisons(), avgTracker.getSwaps(), avgTimeMs);
        }

        System.out.println();
    }

    /**
     * Compares standard vs bidirectional selection sort.
     */
    private static void runComparisonBenchmark() {
        System.out.println("=== Standard vs Bidirectional Comparison ===\n");

        int[] testSizes = {1000, 5000, 10000};

        for (int size : testSizes) {
            int[] arr1 = generateRandomArray(size);
            int[] arr2 = Arrays.copyOf(arr1, arr1.length);

            SelectionSort sorter1 = new SelectionSort();
            SelectionSort sorter2 = new SelectionSort();

            sorter1.sort(arr1);
            sorter2.sortBidirectional(arr2);

            PerformanceTracker t1 = sorter1.getTracker();
            PerformanceTracker t2 = sorter2.getTracker();

            System.out.printf("n=%d:%n", size);
            System.out.printf("  Standard:      %,d comparisons, %.3f ms%n",
                    t1.getComparisons(), t1.getElapsedTimeMillis());
            System.out.printf("  Bidirectional: %,d comparisons, %.3f ms%n",
                    t2.getComparisons(), t2.getElapsedTimeMillis());
            System.out.println();
        }
    }

    // Array generators for different data distributions

    private static int[] generateRandomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(size * 10);
        }
        return arr;
    }

    private static int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    private static int[] generateReverseSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    private static int[] generateNearlySortedArray(int size) {
        int[] arr = generateSortedArray(size);
        // Swap 5% of elements
        int swaps = Math.max(1, size / 20);
        for (int i = 0; i < swaps; i++) {
            int idx1 = RANDOM.nextInt(size);
            int idx2 = RANDOM.nextInt(size);
            int temp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = temp;
        }
        return arr;
    }

    private static int[] generateFewUniqueArray(int size) {
        int[] arr = new int[size];
        int uniqueValues = Math.min(10, size);
        for (int i = 0; i < size; i++) {
            arr[i] = RANDOM.nextInt(uniqueValues);
        }
        return arr;
    }

    @FunctionalInterface
    interface ArrayGenerator {
        int[] generate(int size);
    }
}
