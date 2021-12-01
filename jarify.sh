#!/bin/bash

isManifestCreated="y"

# Checks for the right java version.. if java is even installed
javaChecker() {
  if ! command -v java &> /dev/null
  then
    whiptail --title "Java might not be installed" --msgbox "It seems you might not have installed java.\nPlease install it before running this script.\n\nExiting script." 0 0
    exit 1
  fi

  java=$(java -version 2>&1 | head -1 | cut -d'"' -f2 | sed '/^1\./s///' | cut -d'.' -f1)
  java=$((java + 0)) # We kind of need an integer so..
  if [ $java -gt 11 ]; then
    whiptail --title "Wrong java version detected" --msgbox "It seems you are using java $java according to the \"java -version\" command.\nPlease change your java version temporarily to execute this script.\nOne way to do that is to locate your java version and execute in your terminal a modified version of this command :\nexport PATH=\"/usr/lib/jvm/java-8-openjdk/bin/:\$PATH\"\n\nExiting script." 0 0
    exit 2
  fi
}

# Checks for the manifest file presence, creates it in case we need it
manifestChecker() {
  if ! [ -e "manifest.mf" ]; then
    echo -e "Manifest-Version: 1.0\nMain-Class: core.Main" > manifest.mf
    isManifestCreated="n"
  fi
}


javaChecker
manifestChecker


# Creates our build directory and compiles the class files there
mkdir build/
javac src/core/Main.java -cp src/ -d build/


# We move into the created build folder, create the jar from there and then delete the folder
cd build/ || { whiptail --title "Jarify script" --msgbox "Unable to access newly created build folder. Exiting." 0 0; exit 3; }
jar -cfm ../game.jar ../manifest.mf .
cd ../
rm -r build/


# For some reason, the manifest file wasn't here when the script was loaded, so we remove it so that the user doesn't have to do so again
if [ $isManifestCreated = "n" ]; then
  rm manifest.mf
fi
