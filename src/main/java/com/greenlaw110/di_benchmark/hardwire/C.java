package com.greenlaw110.di_benchmark.hardwire;

import org.osgl.$;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class C {
	@Inject
    private D1 d1;
	
	@Inject
    private D2 d2;


	public void setD1(D1 d1){
		this.d1 = d1;
	}
	
	public void setD2(D2 d2){
		this.d2 = d2;
	}
	
    @Override
    public int hashCode() {
        return $.hc("c", d1, d2);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C) {
            C that = (C) obj;
            return $.eq(that.d1, d1) && $.eq(that.d2, d2);
        }
        return false;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }}
