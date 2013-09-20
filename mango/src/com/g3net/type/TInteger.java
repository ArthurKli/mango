package com.g3net.type;

public class TInteger {

	private int value;


	public TInteger(){
		
	}
	public TInteger(int i){
		this.value=i;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
