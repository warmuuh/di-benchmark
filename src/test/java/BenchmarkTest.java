import com.google.inject.Injector;
import com.greenlaw110.di_benchmark.A;
import dagger.ObjectGraph;
import org.codejargon.feather.Feather;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.osgl.inject.Genie;
import org.picocontainer.PicoContainer;
import org.springframework.context.ApplicationContext;

import static com.greenlaw110.di_benchmark.DIFactory.*;

public class BenchmarkTest extends Assert {

    static Injector guice;
    static Feather feather;
    static ObjectGraph dagger;
    static PicoContainer pico;
    static Genie genie;
    static ApplicationContext spring;

    @BeforeClass
    public static void setup() {
        guice = guice();
        feather = feather();
        dagger = dagger();
        pico = pico();
        genie = genie();
        spring = spring(true);
    }

    @org.junit.Test
    public void equality() {
        A guiceA = guice.getInstance(A.class);
        A featherA = feather.instance(A.class);
        A daggerA = dagger.get(A.class);
        A picoA = pico.getComponent(A.class);
        A genieA = genie.get(A.class);
        A springA = spring.getBean(A.class);

        eq(guiceA, featherA);
        eq(featherA, daggerA);
        eq(daggerA, picoA);
        eq(picoA, genieA);
        eq(genieA, springA);
    }

    @org.junit.Test
    public void identity() {
        A guiceA = guice.getInstance(A.class);
        A featherA = feather.instance(A.class);
        A daggerA = dagger.get(A.class);
        A picoA = pico.getComponent(A.class);
        A genieA = genie.get(A.class);
        A springA = spring.getBean(A.class);

        A guiceB = guice.getInstance(A.class);
        A featherB = feather.instance(A.class);
        A daggerB = dagger.get(A.class);
        A picoB = pico.getComponent(A.class);
        A genieB = genie.get(A.class);
        A springB = spring.getBean(A.class);

        assertNotSame(guiceA, guiceB);
        assertNotSame(featherA, featherB);
        assertNotSame(daggerA, daggerB);
        assertNotSame(picoA, picoB);
        assertNotSame(genieA, genieB);
        assertNotSame(springA, springB);
    }

    private void eq(Object a, Object b) {
        assertEquals(a, b);
    }

}
