#!/bin/bash
javac -d bin -sourcepath src/ src/Test"$@".java
java -classpath bin/ Test"$@"
