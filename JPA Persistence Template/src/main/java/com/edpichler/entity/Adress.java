package com.edpichler.entity;

import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Adress {
	private String street, complement;
	/**
	 * CEP or Postal Code
	 * */
	private String postalCode;
	@ManyToOne(optional = false)
	private City city;
}
