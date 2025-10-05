# Assignment 2 – Algorithmic Analysis and Peer Code Review

## Overview

This repository contains my implementation and analysis for Assignment 2 of the Algorithms course.
The goals of the assignment are:

- Implement a fundamental algorithm with optimizations
- Collect and analyze performance metrics (comparisons, swaps, array accesses, memory, runtime)
- Validate theoretical complexity with empirical benchmarks
- Review a peer’s implementation and produce a detailed report

My assigned algorithm: Selection Sort (with early-termination optimizations)

---

## Repository structure

DAAass2/
├── src/main/java/
│ ├── algorithms/SelectionSort.java # Algorithm implementation
│ ├── metrics/PerformanceTracker.java # Tracks comparisons, swaps, etc.
│ └── cli/BenchmarkRunner.java # CLI tool for running benchmarks
├── src/test/java/
│ └── algorithms/SelectionSortTest.java # Unit tests with edge cases
├── docs/
│ ├── analysis-report.pdf # Full report (complexity + peer review)
│ └── performance-plots/ # Generated plots (time, comparisons, swaps, etc.)
├── performance-data.csv # Collected benchmark results
├── README.md # Project summary (this file)
└── pom.xml # Maven configuration

yaml
Копировать код

---

## How to run

1. Clone the repository:

```bash
git clone https://github.com/alisherabushemenov1/DAAass2.git
cd DAAass2
Compile and run benchmarks:

bash
Копировать код
mvn clean compile exec:java -Dexec.mainClass="cli.BenchmarkRunner"
(If BenchmarkRunner is in a different package, replace cli.BenchmarkRunner with the full class name.)

Run tests:

bash
Копировать код
mvn test
Results
Benchmarks were conducted for input sizes n = 100, 500, 1000, 2000, 5000, 10000

Tested input distributions: Random, Sorted, ReverseSorted, NearlySorted, FewUnique

Plots are available in docs/performance-plots/

Raw data is in performance-data.csv

Report
The complete analysis and peer review are documented in:
docs/analysis-report.pdf

Git workflow
main – stable releases only

feature/algorithm – algorithm implementation

feature/metrics – performance tracking

feature/testing – unit tests

feature/cli – CLI benchmark runner

feature/optimization – performance improvements

Author
Alisher Abushemenov
Course: Algorithms – Assignment 2
Year: 2025
