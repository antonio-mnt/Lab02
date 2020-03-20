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
				for(String t: we.getTranslations()) {
					if(t.equals(newTranslation)) {
						return;
					}
				}
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
	
	public int controllaPuntiInterrogativi(String a){
		
		int count = 0;
		
		for(int i = 0; i<a.length(); i++) {
			int x = a.charAt(i);
			if(x == 63) {
				count++;
			}
		}
		
		return count;
	}
	
	public String translateWord(String alienWord) {
		
		String newAlienWord = alienWord.toLowerCase();

		if(controllaPuntiInterrogativi(newAlienWord) >= 2) {
			throw new InvalidParameterException("\nLa parola aliena cercata può contenere al massimo un '?'.");
		}else if(controllaPuntiInterrogativi(newAlienWord) == 1) {
			
			int x = newAlienWord.indexOf("?");
			
			if(x==0) {
				int count = 0;
				String s = "";
				for(WordEnhanced we: this.dizionario2) {
					if(we.containsSeconda(newAlienWord.substring(1))) {
						count++;
						s = we.toString();
					}
				}
				if(count >= 2) {
					throw new InvalidParameterException("\nNon è possibile risalire alla parola aliena cercata,\nne esistono "+count+" con la struttura inserita.");
				}else if(count == 1){
					return s;
				}
			}else if(x == (newAlienWord.length()-1)) {
				int count = 0;
				String s = "";
				for(WordEnhanced we: this.dizionario2) {
					if(we.containsPrima(newAlienWord.substring(0, newAlienWord.length()-1))) {
						count++;
						s = we.toString();
					}
				}
				if(count >= 2) {
					throw new InvalidParameterException("\nNon è possibile risalire alla parola aliena cercata,\nne esistono "+ count+"con la struttura inserita.");
				}else if(count == 1){
					return s;
				}
			}else {
				int count = 0;
				String s = "";
				for(WordEnhanced we: this.dizionario2) {
					if(we.contains(newAlienWord.substring(0,x), newAlienWord.substring(x+1),x)) {
						count++;
						s = we.toString();
					}
				}
				if(count >= 2) {
					throw new InvalidParameterException("\nNon è possibile risalire alla parola aliena cercata,\nne esistono "+count+" con la struttura inserita.");
				}else if(count == 1){
					return s;
				}
			}
			
		}
		
		for(WordEnhanced we: this.dizionario2) {
			if(we.equals(newAlienWord)==true) {
				return we.toString();
			}
		}
		
		throw new NullPointerException("\nLa parola aliena cercata non è presente nel dizionario\no è stato inserito uno spazio di troppo.");
	}

}
