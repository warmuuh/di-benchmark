package com.greenlaw110.di_benchmark.hardwire;

import org.osgl.$;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class A {
	@Inject
    private B b;

	
    public void setB(B b) {
    	this.b = b;
    }

    @Override
    public int hashCode() {
        return $.hc("a", b);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof A && $.eq(((A) obj).b, b));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
