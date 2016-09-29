package no.avec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by avec on 29/09/2016.
 */
public class DummyTest {
    @Test
    public void hello() {
        assertEquals(new Dummy().hello(), "Hello");
    }
}