package com.example.chess_mobile;

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
    //Inicio un "driver" para hacer pruebas
    @Before
    public  void primero(){
        driver = new Driver();
        driver.buildBoxes();
        driver.buildPieces();
    }
    //Prueba que la función "getBox" me devuelva la casilla esperada
    @Test
    public void getBox(){
        assertEquals("a2", driver.getBox(0,1).getName());//Hay un error aquí
    }
    //Prueba que se pueda pasar de String a número
    @Test
    public void stringToNumber() {
        assertEquals(0, Tools.tagToChessNotation("a1")[0]);
        assertEquals(0, Tools.tagToChessNotation("a1")[1]);
    }
    //Prueba la función "canMoveTo"
    @Test
    public void testNextBoxes(){
        assertEquals("a2", driver.canMoveTo(driver.getBox(new int[]{0,0}))[0].getName());
        assertEquals("a3", driver.canMoveTo(driver.getBox(new int[]{0,0}))[1].getName());
        assertEquals("b2", driver.canMoveTo(driver.getBox(new int[]{1,0}))[0].getName());
        assertEquals("b3", driver.canMoveTo(driver.getBox(new int[]{1,0}))[1].getName());

    }
}