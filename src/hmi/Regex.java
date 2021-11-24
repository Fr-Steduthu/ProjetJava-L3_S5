package hmi;

import core.items.Item;
import core.places.Exit;
import core.places.Place;
import core.character.Character;

public final class Regex {
	/*
	public final String contents;
	
	public Regex(String s) {
		this.contents = s;
	}
	
	public Regex(Place s) {
		this.contents = regex(s);
	}
	
	public Regex(Exit e) {
		/**Do not use**
		this.contents = regex(e, null);
	}
	
	public Regex(Item e) {
		this.contents = regex(e);
	}
	
	public Regex(Character e) {
		this.contents = regex(e);
	}*/
	 
	/**Static methods**/
	
	public static String regex(String stringToRegexify) {
		//Exemple : "la muerte" deviens "[l|L][a|A] [m|M][u|U][e|E][r|R][t|T][e|E]"
		if(stringToRegexify.isEmpty()){
			return "";
			
		}else {
			return "["+stringToRegexify.toLowerCase().charAt(0)+stringToRegexify.toUpperCase().charAt(0)+"]"+regex(stringToRegexify.substring(0));
		}
	}
	
	public static String regex(String[] stringsToRegexify) {
		String regex = "";
		for(String s : stringsToRegexify) {
			regex += "|" + regex(s);
		}
		
		return regex;
	}
	
	public static String regex(Character e) {
		return Regex.regex(e.getName());
	}
	
	public static String regex(Place e) {
		return Regex.regex(e.getName());
	}
	
	public static String regex(Exit e, Place placeToOmmit) {
		String regex = "";
		for(Place p : e.getRooms()) {
			if(p == placeToOmmit) {
				regex += "|" + regex(p.getName());
			}
		}
		
		return regex;
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
		return regex;
	}
	
	public static String regex(Place[] e) {
		String regex = "";
		
		for(Place c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex;
	}
	
	public static String regex(Exit[] e, Place placeToOmmit) {
		String regex = "";
		
		for(Exit c : e) {
			regex += "|" + Regex.regex(c, placeToOmmit);
		}
		return regex;
	}
	
	public static String regex(Item[] e) {
		String regex = "";
		
		for(Item c : e) {
			regex += "|" + Regex.regex(c);
		}
		return regex;
	}
}
