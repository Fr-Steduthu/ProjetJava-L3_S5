#!/bin/bash

mkdir build/
javac src/core/Main.java -cp src/ -d build
cd build/
echo "Manifest-Version: 1.0\nMain-Class: core.Main" > manifest.mf
jar cfm game.jar manifest.mf ./
mv game.jar ../
rm -r ../build/
