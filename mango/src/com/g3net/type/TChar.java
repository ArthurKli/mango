package com.g3net.type;

public class TChar {

	private char value;


	public TChar(){
		
	}
	public TChar(char i){
		this.value=i;
	}
	
	public char getValue() {
		return value;
	}
	public void setValue(char value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
