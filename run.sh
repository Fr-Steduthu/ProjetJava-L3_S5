#!/bin/bash

fileName="game.jar"

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

javaChecker

if ! [ -e $fileName ]; then
  whiptail --title "Run script" --msgbox "Unable to find the $fileName file.\nThis may happen because you've renamed the file.\nIf that's the case, modify the fileName variable in this script.\n\nExiting script." 0 0
  exit 2
fi


java -jar $fileName
