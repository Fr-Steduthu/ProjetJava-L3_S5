@echo off

setlocal EnableDelayedExpansion
(set \n=^
%=Do not remove this line=%
)

MKDIR %cd%\build

%JAVA_HOME%javac src/core/Main.java -cp src/ -d build/

cd %cd%\build

echo Manifest-Version: 1.0!\n!Main-Class: core.Main > manifest.mf
%JAVA_HOME%jar -cfm ..\game.jar manifest.mf .
cd..

RMDIR %cd%\build /S
pause
