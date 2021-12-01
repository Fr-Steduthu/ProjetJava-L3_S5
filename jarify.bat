@echo off
echo %JAVA_HOME% = "C:\Program Files\Java\jdk1.6\bin"


MKDIR %cd%\build

C:\Languages\Java\8JDK291\bin\javac src/core/Main.java -cp src/ -d build/

C:\Languages\Java\8JDK291\bin\jar cf game.jar -M core/Main -C ./

RMDIR %cd%\build /S
pause