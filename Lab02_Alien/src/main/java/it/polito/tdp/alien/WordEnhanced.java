package it.polito.tdp.alien;

import java.util.ArrayList;
import java.util.List;

public class WordEnhanced {
	
	private String alienWord;
	private List<String> translations = new ArrayList<>();
	
	public WordEnhanced(String alienWord, List<String> translations) {
		super();
		this.alienWord = alienWord;
		this.translations = translations;
	}

	public String getAlienWord() {
		return alienWord;
	}

	public void setAlienWord(String alienWord) {
		this.alienWord = alienWord;
	}

	public List<String> getTranslations() {
		return translations;
	}

	public void setTranslations(List<String> translations) {
		this.translations = translations;
	}

	public boolean equals (String parolaAliena) {
		if(this.alienWord.equals(parolaAliena))
			return true;
		else
			return false;
	}

	@Override
	public String toString() {
		
		String elenco = "";
		for(String s: this.translations) {
			if(this.translations.size()==1) {
				elenco = "\nLa traduzione della parola aliena '"+this.alienWord+"' è: "+s+".";
				return elenco;
			}else {
				if(elenco.equals("")) {
					elenco = s;
				}else {
					elenco += ", "+s;				}
			}
		}
		return "\nLe traduzioni della parola aliena '"+this.alienWord+"' sono: "+elenco+".";
	}
	
	
	
	

}
