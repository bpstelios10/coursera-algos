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
To run tests of week3 assignment, from commandline, you need to go to /week4-collinear/build folder and run:
```shell
java -classpath :../../.lift/algs4.jar LineSegmentsClient ../input6.txt
```
NOTE: if it fails with error for GraphicsEnvironment, you should use `java` directly from your jdk/bin path

and from intellij, just run LineSegmentsClient::main with argument "week4-collinear/input6.txt". Make sure working directory is coursera-algos.
