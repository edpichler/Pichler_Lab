package com.edpichler.entity;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class CountryState {
	private String sign;
	private String name;
	@OneToOne(cascade = CascadeType.PERSIST)
	private Country country;
}
