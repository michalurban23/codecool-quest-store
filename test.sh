#!/bin/bash
rm bin/Test.class
javac -d bin -sourcepath src/ src/Test"$@".java
java -classpath bin/ Test"$@"
