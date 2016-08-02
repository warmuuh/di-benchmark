package com.greenlaw110.di_benchmark.hardwire;

import javax.inject.Singleton;

import org.osgl.$;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Singleton
public class E {

    @Override
    public int hashCode() {
        return $.hc("e");
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof E);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

}
