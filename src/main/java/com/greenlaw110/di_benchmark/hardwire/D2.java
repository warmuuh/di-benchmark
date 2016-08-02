package com.greenlaw110.di_benchmark.hardwire;

import org.osgl.$;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class D2 {
	@Inject
	private E e;
	
	public void setE(E e) {
		this.e = e;
	}

    @Override
    public int hashCode() {
        return $.hc("d2", e);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof D2 && $.eq(((D2) obj).e, e));
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
