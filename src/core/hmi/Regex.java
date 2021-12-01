package core.hmi;

import core.character.Character;
import core.item.Item;
import core.place.Exit;
import core.place.Place;

public final class Regex {
	 
	/**Static methods**/
	
        /**
         * Returns a regex expression
         * 
         * @param stringToRegexify
         * The string to regexify
         * 
         * @return a regexified expression
         */
	public static String regex(String stringToRegexify) {
		//Exemple : "la muerte" deviens "[lL][aA] [mM][uU][eE][rR][tT][eE]"
		if(stringToRegexify.isEmpty()){
			return "";
			
		}else if(stringToRegexify.charAt(0) == ' ') {
			return " "+regex(stringToRegexify.substring(1));
		
		}else {
			return "["+stringToRegexify.toLowerCase().charAt(0)+stringToRegexify.toUpperCase().charAt(0)+"]"+regex(stringToRegexify.substring(1));
		}
	}
	
        /**
         * Returns a regex expression
         * 
         * @param stringsToRegexify
         * The strings to regexify
         * 
         * @return a regexified expression
         */
	public static String regex(String[] stringsToRegexify) {
		String regex = "";
		for(String s : stringsToRegexify) {
			regex += "|" + regex(s);
		}
		
		return regex.substring(1);
	}
	
        /**
         * Transforms a Character into a regex expression
         * 
         * @param e
         * The character
         * 
         * @return a regexified character name
         */
	public static String regex(Character e) {
		return Regex.regex(e.getName());
	}
	
        /**
         * Transforms a Place into a regex expression
         * 
         * @param e
         * The place
         * 
         * @return a regexified place name
         */
	public static String regex(Place e) {
		return Regex.regex(e.getName());
	}
	
        /**
         * Transforms the exit's place names into a regex expression, with an ommited place
         * 
         * @param e
         * The exit
         * 
         * @param placeToOmmit
         * The place to ignore
         * 
         * @return a regexified string containing some place's names
         */
	public static String regex(Exit e, Place placeToOmmit) {
		String regex = "";
		
		for(Place p : e.getRooms()) {
			if(p != placeToOmmit) {
				regex += "|" + regex(p.getName());
			}
		}
		
		return regex.substring(1);
	}
	
        /**
         * Transforms an item name into a regex expression
         * 
         * @param e
         * The item
         * 
         * @return a regexified item name
         */
	public static String regex(Item e) {
		return Regex.regex(e.getName());
	}
	
	
	//TABS
	
        /**
         * Transforms a Character array into a regex expression
         * 
         * @param e
         * The character array
         * 
         * @return a regex expression
         */
	public static String regex(Character[] e) {
		String regex = "";
		
		for(Character c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex.substring(1);
	}
	
        /**
         * Transforms a Place array into a regex expression
         * 
         * @param e
         * The place array
         * 
         * @return a regex expression
         */
	public static String regex(Place[] e) {
		String regex = "";
		
		for(Place c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex.substring(1);
	}
	
        /**
         * Transforms an exit array into a regex expression
         * 
         * @param e
         * The place array
         * 
         * @param placeToOmmit
         * The place to ommit
         * 
         * @return a regex expression
         */
	public static String regex(Exit[] e, Place placeToOmmit) {
		String regex = "";
		
		for(Exit c : e) {
			regex += "|" + Regex.regex(c, placeToOmmit);
		}
		return regex.substring(1);
	}
	
        /**
         * Transforms an item array into a regex expression
         * 
         * @param e
         * The item array
         * 
         * @return a regex expression
         */
	public static String regex(Item[] e) {
		String regex = "";
		
		for(Item c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex.substring(1);
	}
	
        /**
         * Returns if the given strings are equal
         * 
         * @param str1
         * A string
         * 
         * @param str2
         * A string
         * 
         * @return if the strings are equal or not
         */
	public static boolean areEquals(String str1, String str2) {
		return str1.toLowerCase().equals(str2.toLowerCase());
	}
}
