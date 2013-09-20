package com.g3net.type;

public class TString {
	
	private String value;


	public TString(){
		
	}
	public TString(String i){
		this.value=i;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
