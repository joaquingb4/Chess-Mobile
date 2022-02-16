package com.example.chess_mobile;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import android.util.Log;


import static org.junit.Assert.*;

import android.util.Log;

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
        driver.buildBoxes();
        driver.buildPieces();
    }
    @Test
    public void getBox(){
        assertEquals("a2", driver.getBox(0,1).getName());//Hay un error aquí
    }

    @Test
    public void stringToNumber() {
        assertEquals(0, Tools.withNotation("a1")[0]);
        assertEquals(0, Tools.withNotation("a1")[1]);
    }

    //Estoy aquí
    @Test
    public void testNextBoxes(){
        assertEquals("a2", driver.canMoveTo(0,0)[0].getName());
        assertEquals("a3", driver.canMoveTo(0,0)[1].getName());

    }
    @Test
    public void hola(){

    }

}