@echo off
echo %JAVA_HOME% = "C:\Program Files\Java\jdk1.6\bin"

setlocal EnableDelayedExpansion
(set \n=^
%=Do not remove this line=%
)

MKDIR %cd%\build

C:\Languages\Java\8JDK291\bin\javac src/core/Main.java -cp src/ -d build/

cd %cd%\build
echo Manifest-Version: 1.0!\n!Main-Class: core.Main > manifest.mf
C:\Languages\Java\8JDK291\bin\jar -cfm ..\game.jar ..\manifest.mf .
cd..

RMDIR %cd%\build /S
pause
