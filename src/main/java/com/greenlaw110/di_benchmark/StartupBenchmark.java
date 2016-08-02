package com.greenlaw110.di_benchmark;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.greenlaw110.di_benchmark.hardwire.Module;

import dagger.ObjectGraph;
import org.codejargon.feather.Feather;
import org.osgl.inject.Genie;
import org.osgl.util.S;
import org.picocontainer.MutablePicoContainer;
import org.springframework.context.ApplicationContext;

import static com.greenlaw110.di_benchmark.DIFactory.*;

/**
 Measures bootstrap cost of different DI tools.
 An iteration includes creating an injector and instantiating the dependency graph.
 */
public class StartupBenchmark {

    public void run(final int warmup, final int iterations) {
        benchmarkExplanation(iterations);
        boolean springScan = false;
        String s = System.getProperties().getProperty("spring_scan");
        if (S.notBlank(s)) {
            springScan = Boolean.parseBoolean(s);
        }
        System.out.printf("Spring scan: %s\n", springScan ? "enabled" : "disabled");
        for (int i = 0; i < warmup; ++i) {
            Feather.with().instance(A.class);
            genie().get(A.class);
            Guice.createInjector().getInstance(A.class);
            pico().getComponent(A.class);
            dagger().get(A.class);
            spring(springScan).getBean(A.class);
        }
        StopWatch.millis("Guice", () -> {
            for (int i = 0; i < iterations; ++i) {
                Injector injector = Guice.createInjector();
                injector.getInstance(A.class);
            }
        });
        StopWatch.millis("Feather", () -> {
            for (int i = 0; i < iterations; ++i) {
                Feather feather = Feather.with();
                feather.instance(A.class);
            }
        });
        StopWatch.millis("Dagger", () -> {
            for (int i = 0; i < iterations; ++i) {
                ObjectGraph dagger = dagger();
                dagger.get(A.class);
            }
        });
        StopWatch.millis("Pico", () -> {
            for (int i = 0; i < iterations; ++i) {
                MutablePicoContainer pico = pico();
                pico.getComponent(A.class);
            }
        });
        StopWatch.millis("Genie", () -> {
            for (int i = 0; i < iterations; ++i) {
                Genie genie = genie();
                genie.get(A.class);
            }
        });
        StopWatch.millis("Hardwire", () -> {
            for (int i = 0; i < iterations; ++i) {
                Module m= new Module();
                m.start();
                m.getA();
            }
        });
        int limit = springScan ? 5000 : 10000;
        if (iterations < limit) {
            final boolean scan = springScan;
            StopWatch.millis("Spring", () -> {
                for (int i = 0; i < iterations; ++i) {
                    ApplicationContext applicationContext = spring(scan);
                    applicationContext.getBean(A.class);
                }
            });
        }
    }

    private void benchmarkExplanation(int iterations) {
        System.out.println(
                String.format(
                        "Starting up DI containers & instantiating a dependency graph %s times:\n%s",
                        iterations,
                        "---------------------------------------------------------------------------------------")
        );
    }


}
