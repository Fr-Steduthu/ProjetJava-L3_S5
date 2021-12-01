#!/bin/bash

mkdir build/
javac src/core/Main.java -cp src/ -d build/
cd build/
jar --create --file game.jar --main-class core/Main -c ./
cd ../
rm -r build/
