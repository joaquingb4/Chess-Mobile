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
    //Prueba de la dunción "translate" y "tagToArrayNotation"
    /*
    @Test
    public void translate(){
        //TagChessNotation()
        assertEquals("a1", driver.getBox(Tools.tagToArrayNotation("a1")).getName());
        assertEquals("h1", driver.getBox(Tools.tagToArrayNotation("h1")).getName());
        assertEquals(7, Tools.tagToArrayNotation("h8")[0]);
        assertEquals(7, Tools.tagToArrayNotation("h8")[1]);
        assertEquals("h8", driver.getBox(Tools.tagToArrayNotation("h8")).getName());
        //---
        assertEquals(0, Tools.tagToArrayNotation("a8")[0]);
        assertEquals(7, Tools.tagToArrayNotation("a8")[1]);
        //
        //Translate()
        assertEquals("a2", Tools.translate(new int[]{0,1}));
    }
    //Prueba que la función "getBox" me devuelva la casilla esperada
    @Test
    public void getBox(){
       // assertFalse(driver.getBox("c2").isEmpty());
        assertEquals("c2", driver.getBox("c2").getName());
        assertEquals("a1", driver.getBox(0,0).getName());
        assertEquals("h1", driver.getBox(7,0).getName());
        assertEquals("a8", driver.getBox(0,7).getName());
        assertEquals("h8", driver.getBox(7,7).getName());

    }
    //Prueba que se pueda pasar de String a número
    @Test
    public void stringToNumber() {
        assertEquals(0, Tools.tagToArrayNotation("a1")[0]);
        assertEquals(0, Tools.tagToArrayNotation("a1")[1]);
    }
    //Prueba la función "canMoveTo"
    @Test
    public void testNextBoxes(){
        assertEquals("a2", driver.getAvailablePositions(driver.getBox(new int[]{0,0}))[0].getName());
        assertEquals("a3", driver.getAvailablePositions(driver.getBox(new int[]{0,0}))[1].getName());
        assertEquals("b2", driver.getAvailablePositions(driver.getBox(new int[]{1,0}))[0].getName());
        assertEquals("b3", driver.getAvailablePositions(driver.getBox(new int[]{1,0}))[1].getName());
    }
     */
    //Obtener una casilla con solo el nombre
    @Test
    public void getBoxes() {
        //Probamos la función UNKNOWNBOXGETX()
        assertEquals(0, Box.unknownBoxGetX("a1"));
        assertEquals(0, Box.unknownBoxGetY("a1"));
        assertEquals(0, Box.unknownBoxGetX("a2"));
        assertEquals(1, Box.unknownBoxGetY("a2"));
        assertEquals(7, Box.unknownBoxGetX("h8"));
        assertEquals(7, Box.unknownBoxGetY("h8"));

        //Probamos si podemos obtener una casilla por el nombre
        assertEquals("a1", driver.getBox("a1").getName());
        assertEquals("a2", driver.getBox("a2").getName());
    }
}