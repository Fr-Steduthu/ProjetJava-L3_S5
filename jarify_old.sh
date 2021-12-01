#!/bin/bash
mkdir build/
javac src/core/Main.java -cp src/ -d build
cd build/
jar --create -f game.jar --main-class core/Main -c ./
mv game.jar ../
rm -r ../build/
