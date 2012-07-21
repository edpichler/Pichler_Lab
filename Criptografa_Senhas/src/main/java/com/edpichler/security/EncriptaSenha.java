package com.edpichler.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncriptaSenha {

	public static void main(String[] args) throws NoSuchAlgorithmException {
	
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update("senhaMinha".getBytes());
		byte[] criptografada = digest.digest();
		System.out.println(new String(criptografada));
	}
}