#!/bin/bash
javac -d bin -sourcepath src/ src/*.java
javac -d bin -sourcepath src/ src/*/*.java
javac -d bin -sourcepath src/ src/*/*/*.java
