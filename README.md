# Algorithms from coursera

## Week 2
To run tests of week2 assignment, from commandline, you need to go to /week2-unions/build folder and run:
```shell
java -classpath :../../.lift/algs4.jar PercolationStats
```
and from intellij, just run PercolationStats::main

## Week 3
To run tests of week3 assignment, from commandline, you need to go to /week3-queues/build folder and run:
```shell
java -classpath :../../.lift/algs4.jar Permutation 5 < ../distinct.txt
```
and from intellij... no idea. the "while file not empty" line doesnt work

## Week 4
To run tests of week4 assignment, from commandline, you need to go to /week4-collinear/build folder and run:
```shell
java -classpath :../../.lift/algs4.jar LineSegmentsClient ../input6.txt
```
NOTE: if it fails with error for GraphicsEnvironment, you should use `java` directly from your jdk/bin path

and from intellij, just run LineSegmentsClient::main with argument "week4-collinear/input6.txt". Make sure working directory is coursera-algos.

## Week 5
To run tests of week5 assignment, from commandline, you need to go to /week5-8puzzle/build folder and run:
```shell
java -classpath :../../.lift/algs4.jar PuzzleChecker ../puzzle04.txt
java -classpath :../../.lift/algs4.jar Solver ../puzzle28.txt
```
and from intellij, just run PuzzleChecker::main with argument "week5-8puzzle/puzzle04.txt". Make sure working directory is coursera-algos.
and from intellij, just run Solver::main with argument "week5-8puzzle/puzzle28.txt". Make sure working directory is coursera-algos.

## Week 6
To run tests of week6 assignment, from commandline, you need to go to /week6-kdtree/build folder and run:
```shell
java -classpath :../../.lift/algs4.jar NearestNeighborVisualizer ../input10.txt
java -classpath :../../.lift/algs4.jar RangeSearchVisualizer ../input10K.txt
```
NOTE: if it fails with error for GraphicsEnvironment, you should use `java` directly from your jdk/bin path

and from intellij, just run NearestNeighborVisualizer::main with argument "week6-kdtree/input10.txt". Make sure working directory is coursera-algos.
and from intellij, just run RangeSearchVisualizer::main with argument "week6-kdtree/input10K.txt". Make sure working directory is coursera-algos.
