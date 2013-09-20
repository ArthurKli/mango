package com.g3net.type;

public class TFloat {
	private float value;


	public TFloat(){
		
	}
	public TFloat(float i){
		this.value=i;
	}
	
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public String toString(){
		return value+"";
	}
}
