@echo off
echo %JAVA_HOME% = "C:\Program Files\Java\jdk1.6\bin"

setlocal EnableDelayedExpansion
(set \n=^
%=Do not remove this line=%
)

MKDIR %cd%\build

C:\Languages\Java\8JDK291\bin\javac src/core/Main.java -cp src/ -d build/
echo "Manifest-Version: 1.0!\n!Main-Class: core.Main" > manifest.mf

cd %cd%\build
C:\Languages\Java\8JDK291\bin\jar -cfm ..\game.jar ..\manifest.mf .
cd..

RMDIR %cd%\build /S
del /f manifest.mf
pause
