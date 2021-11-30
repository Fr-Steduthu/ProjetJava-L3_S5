@echo off
%JAVA_HOME%="C:\Program Files\Java\jdk1.6\bin"

C:\Languages\Java\8JDK291\bin\javac < DIR "./src/main/ *.java" /S
C:\Languages\Java\8JRE-1.8.0-291\bin\java src/main/core/Main
pause