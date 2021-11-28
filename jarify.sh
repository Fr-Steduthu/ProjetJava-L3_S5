#!/bin/bash

isManifestCreated="y"

javaChecker() {
  java=$(java -version 2>&1 | head -1 | cut -d'"' -f2 | sed '/^1\./s///' | cut -d'.' -f1)
  java=$((java + 0))
  if [ $java -gt 11 ]; then
    whiptail --title "Wrong java version detected" --msgbox "It seems you are using java $java according to the \"java -version\" command.\nPlease change your java version temporarily to execute this script.\nOne way to do that is to locate your java version and execute in your terminal a modified version of this command :\nexport PATH=\"/usr/lib/jvm/java-17-openjdk/bin/:\$PATH\"\n\nExiting script." 0 0
    exit 1
  fi
}

manifestChecker() {
  if ! [ -e "manifest.mf" ]; then
    echo -e "Manifest-Version: 1.0\nMain-Class: core.Main" > manifest.mf
    isManifestCreated="n"
  fi
}

javaChecker
manifestChecker

echo "Java test passed"

# Prepare a build folder for every single class files
echo "Creating build/ folder and copying the game's code"
mkdir build
cp -r src/main/* build/
cd build/ || { whiptail --title "Jarify script" --msgbox "Unable to access newly created build folder. Exiting." 0 0; exit 2; }

echo "Creating .class files"
javac -cp . core/Main.java

echo "Deleting .java files from the build folder"
find . -name "*.java" -type f -delete

# Let some time to the system to have the java files removed and the class files created
sleep 1

# Creates the jar file and put it in our current folder
echo "Creating jar file"
jar -cfm executable.jar ../manifest.mf .


echo "Moving the executable jar file out of the build/ folder, removing said folder"
mv executable.jar ../
cd ../
rm -r build


# For some reason, the manifest file wasn't here when the script was loaded, so we remove it so that the user doesn't have to do so again
if [ $isManifestCreated = "n" ]; then
  echo "Removing temporarily created manifest.mf file"
  rm manifest.mf
fi


echo "End of script execution. Thanks for using it !"
exit 0
