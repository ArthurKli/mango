package com.g3net.type;

public class TShort {

	private short value;


	public TShort(){
		
	}
	public TShort(short i){
		this.value=i;
	}
	
	public short getValue() {
		return value;
	}
	public void setValue(short value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
