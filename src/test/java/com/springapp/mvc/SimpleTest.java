package com.springapp.mvc;

import org.junit.*;

/**
 * Created by Nikita on 05.02.2016.
 */
public class SimpleTest {
    private int a = 3;

    @After
    public void setUp() {
        a++;
        System.out.println("in after a = " + a);
        System.out.println(this);
    }

    @Test
    public void simpleTest() {
        Assert.assertEquals(3, a);
        System.out.println("in first testObject = " + this);
    }

    @Test
    public void simpleTestSecond() {
        Assert.assertEquals(3, a);
        System.out.println("in second testObject = " + this);
    }

    @Test
    public void simpleTestThird() {
        Assert.assertEquals(3, a);
        System.out.println("in third testObject = " + this);
    }
}
