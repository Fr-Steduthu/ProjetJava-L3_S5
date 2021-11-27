
# TODO : Check java version, check manifest file presence


# Prepare a build folder for every single class files
mkdir build
cp -r src/main/* build/
cd build/

# Creates all class files
javac -cp . core/Main.java

# Removes all java files
find . -name "*.java" -type f -delete

# Let some time to the system to have the java files removed and the class files created
sleep 1

# Creates the jar file and put it in our current folder
jar -cfm executable.jar ../manifest.mf .
cp executable.jar ../

cd ../
rm -r build
