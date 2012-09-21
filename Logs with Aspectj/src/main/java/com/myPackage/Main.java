package com.myPackage;

public class Main {
	public static void main(String[] args) {
		Casa c = new Casa();
		c.setNumero(200);
		c.getRoomsAvailable(); 
		try{
			c.setNumero(-1, 1);
		}catch(Throwable e){
			//caught by aspect
		}
		//throw exception below
		c.setNumero(-2, 2);
		
	}	
}

