package com.myPackage;

import aspects.Loggable;

public class Casa {
	int numero;

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(int numero) {
		if (numero < 0) {
			throw new IllegalArgumentException();
		}
		this.numero = numero;
	}
	
	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(int numero, int numero2) {
		if (numero < 0) {
			throw new IllegalArgumentException();
		}
		this.numero = numero;
	}
	public int getRoomsAvailable() {
		return 10;
	}
}