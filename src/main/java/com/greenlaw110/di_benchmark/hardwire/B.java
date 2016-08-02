package com.greenlaw110.di_benchmark.hardwire;

import org.osgl.$;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class B {
	@Inject
    private C c;

	public void setC(C c) {
		this.c = c;
	}

    @Override
    public int hashCode() {
        return $.hc("b", c);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof B && $.eq(((B) obj).c, c));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
