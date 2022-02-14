package com.example.chess_mobile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    Driver driver;
    @Before
    public  void primero(){
        driver = new Driver();
    }
    @Test
    public void stringToNumber() {
        assertEquals(0, Tools.withNotation("a1")[0]);
        assertEquals(0, Tools.withNotation("a1")[1]);
    }
//Estoy aquí
    @Test
    public void string

}