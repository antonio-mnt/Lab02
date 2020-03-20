package it.polito.tdp.alien;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class AlienDictionary {
	
	private List<Word> dizionario = new ArrayList<>();
	private List<WordEnhanced> dizionario2 = new ArrayList<>();
	
	public boolean controllaCaratteri(String s) {
		
		int i = 0;
		
		for(int n = 0; n<s.length(); n++) {
			int x = s.charAt(n);
			if((x >=97 && x <= 122) || x >=65 && x<=90) {
				i++;
			}
		}
		
		if(i==s.length())
			return true;
		else
			return false;
		
	}
	
	public void addWord(String alienWord, String translation) {
		
		if(controllaCaratteri(alienWord)==false || controllaCaratteri(translation)==false )
			throw new InvalidParameterException("\nSono ammessi solomente i caratteri [a-z;A-Z].");
		
		String newAlienWord = alienWord.toLowerCase();
		String newTranslation = translation.toLowerCase();
		
		List<String> translations;
		
		/*for(Word w: this.dizionario) {
			if(w.equals(newAlienWord)==true) {
				w.setTranslation(newTranslation);
				return;
			}
		}*/
		
		for(WordEnhanced we: this.dizionario2) {
			if(we.equals(newAlienWord)==true) {
				translations = new ArrayList(we.getTranslations());
				translations.add(newTranslation);
				we.setTranslations(translations);
				return;
			
		}
		}
		
		translations = new ArrayList();
	    translations.add(newTranslation);
		this.dizionario2.add(new WordEnhanced(newAlienWord,translations));
	}
	
	public String translateWord(String alienWord) {
		
		String newAlienWord = alienWord.toLowerCase();
		
		for(WordEnhanced we: this.dizionario2) {
			if(we.equals(newAlienWord)==true) {
				return we.toString();
			}
		}
		
		throw new NullPointerException("\nLa parola aliena cercata non è presente nel dizionario\no è stato inserito uno spazio di troppo.");
	}

}
