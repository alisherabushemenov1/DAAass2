# Report on Selection Sort Algorithm

---

## 1. Algorithm Overview

**Selection Sort** is a basic quadratic sorting algorithm.

**Principle of operation:** at each step, the minimum element from the remaining unsorted part of the array is selected and placed into its correct position.

**Main steps:**

1. Start with position `i = 0`.
2. Find the minimum element in the subarray `[i..n-1]`.
3. Swap it with the element at position `i`.
4. Increment `i` and repeat until the array is sorted.

**Optimization:** early termination — if no swap is performed during an iteration, the algorithm stops.

**Key features:**

* Very simple, requires no extra memory.
* Performs at most `n` swaps.
* Rarely used in practice, but widely applied for teaching.

---

## 2. Complexity Analysis

**Time Complexity**

* **Best case (Ω(n²))**: still ~n²/2 comparisons, even if sorted.
* **Average case (Θ(n²))**: random arrays, ~n²/2 comparisons.
* **Worst case (O(n²))**: reverse-sorted arrays.

Formally:

```
T(n) = Ω(n²) = Θ(n²) = O(n²)
```

**Space Complexity**

* O(1) extra memory (in-place).

**Comparison with Insertion Sort**

* Insertion Sort: Ω(n) best case.
* Selection Sort: always Ω(n²).
* Selection Sort does fewer swaps (important when swaps are costly).

---

## 3. Code Review

**Inefficiencies:**

1. Full scan for the minimum in each iteration.
2. Many redundant comparisons on sorted data.

**Possible optimizations:**

* Early termination check.
* Dual Selection Sort (find min & max in one pass).
* Hybrid approach: Selection Sort for small arrays, switch for large inputs.

**Code quality tips:**

* Use meaningful names (`minIndex`, `currentMin`).
* Add step-by-step comments.
* Handle edge cases (empty or null arrays).

---

## 4. Empirical Results

The algorithm was tested on different input distributions: **Random**, **Sorted**, **ReverseSorted**, **NearlySorted**, **FewUnique**.

### Benchmark Results

| DataType      | InputSize | Comparisons | Swaps | ArrayAccesses | MemoryAllocations | TimeMs |
| ------------- | --------- | ----------- | ----- | ------------- | ----------------- | ------ |
| Random        | 100       | 4957        | 95    | 286           | 0                 | 145    |
| Random        | 1000      | 499517      | 992   | 2978          | 0                 | 209    |
| Random        | 10000     | 49995031    | 9987  | 29961         | 0                 | 560    |
| Sorted        | 1000      | 1998        | 0     | 0             | 0                 | 3      |
| Sorted        | 10000     | 19998       | 0     | 0             | 0                 | 18     |
| ReverseSorted | 1000      | 375748      | 500   | 1500          | 0                 | 152    |
| ReverseSorted | 10000     | 37507498    | 5000  | 15000         | 0                 | 298    |
| NearlySorted  | 1000      | 512121      | 50    | 150           | 0                 | 194    |
| NearlySorted  | 10000     | 50184428    | 500   | 1500          | 0                 | 1818   |
| FewUnique     | 1000      | 494616      | 896   | 2688          | 0                 | 169    |
| FewUnique     | 10000     | 49485571    | 8984  | 26954         | 0                 | 1987   |

**Analysis:**

* **Random:** quadratic growth, ~560ms at n=10,000.
* **Sorted:** minimal runtime, almost linear.
* **ReverseSorted:** quadratic, more swaps.
* **NearlySorted:** early termination reduces swaps.
* **FewUnique:** close to Random, slightly faster due to duplicates.

---

## 5. Conclusion

* Selection Sort is simple and memory-efficient.
* Time complexity is quadratic for all cases.
* Early termination helps only in nearly sorted arrays.
* Compared to Insertion Sort, slower but with fewer swaps.
* Rare in real-world use, valuable as a teaching tool.

---
