// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.edpichler.entity;

import com.edpichler.entity.City;
import com.edpichler.entity.CountryState;

privileged aspect City_Roo_JavaBean {
    
    public String City.getName() {
        return this.name;
    }
    
    public void City.setName(String name) {
        this.name = name;
    }
    
    public CountryState City.getState() {
        return this.state;
    }
    
    public void City.setState(CountryState state) {
        this.state = state;
    }
    
}
