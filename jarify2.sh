mkdir build/
javac -cp build/ main/core/Main.java
jar -cf game.jar build/main/core/Main.class
rm -r build/
