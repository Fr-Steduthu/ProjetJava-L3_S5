package core.hmi;

import core.character.Character;
import core.game.Quest;
import core.item.Item;
import core.place.Exit;
import core.place.Place;

public final class Regex {
	 
	/**Static methods**/
	
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
	
	public static String regex(String[] stringsToRegexify) {
		String regex = "";
		for(String s : stringsToRegexify) {
			regex += "|" + regex(s);
		}
		
		return regex.substring(1);
	}
	
	public static String regex(Character e) {
		return Regex.regex(e.getName());
	}
	
	public static String regex(Place e) {
		return Regex.regex(e.getName());
	}
	
	public static String regex(Quest q) {
		return Regex.regex(q.getObjective());
	}
	
	public static String regex(Exit e, Place placeToOmmit) {
		String regex = "";
		
		for(Place p : e.getRooms()) {
			if(p != placeToOmmit) {
				regex += "|" + regex(p.getName());
			}
		}
		
		return regex.substring(1);
	}
	
	public static String regex(Item e) {
		return Regex.regex(e.getName());
	}
	
	
	//TABS
	
	public static String regex(Character[] e) {
		String regex = "";
		
		for(Character c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex.substring(1);
	}
	
	public static String regex(Place[] e) {
		String regex = "";
		
		for(Place c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex.substring(1);
	}
	
	public static String regex(Exit[] e, Place placeToOmmit) {
		String regex = "";
		
		for(Exit c : e) {
			regex += "|" + Regex.regex(c, placeToOmmit);
		}
		return regex.substring(1);
	}
	
	public static String regex(Item[] e) {
		String regex = "";
		
		for(Item c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex.substring(1);
	}
	
	public static String regex(Quest[] q) {
		String regex = "";
		
		for(Quest quest : q) {
			regex += "|" + Regex.regex(quest);
		}
		return regex.substring(1);
	}
	
	public static boolean areEquals(String str1, String str2) {
		return str1.toLowerCase().equals(str2.toLowerCase());
	}
}
