package com.lifeblood.client;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test() {
        int oldThr = 10;
        int newThr = oldThr << 1;
        System.out.print(newThr);
    }
}