package com.g3net.type;

public class TByte {

	private byte value;


	public TByte(){
		
	}
	public TByte(byte i){
		this.value=i;
	}
	
	public byte getValue() {
		return value;
	}
	public void setValue(byte value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
