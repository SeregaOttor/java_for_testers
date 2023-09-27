package ru.ssh.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    void canCalculateArea() {
        var s = new Triangle(7.0,7.0,6.0);
        double result = s.triangleArea();
        Assertions.assertEquals(19, result);
    }
    @Test
    void canCalculatePerimetr() {
        var s = new Triangle(7.0,7.0,6.0);
        double result = s.trianglePerimetr();
        Assertions.assertEquals(20, result);
    }

    @Test
    void cannotCreateTriangleWithNegativeSide(){
        try {
            new Triangle(5.0, 9.0, 3.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {
            //ok
        }
    }
    @Test
    void testEquality(){
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 4.0, 5.0);
        Assertions.assertEquals(t1, t2);
    }
    @Test
    void testNotEquality(){
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(2.0, 4.0, 3.0);
        Assertions.assertNotEquals(t1, t2);
    }
    @Test
    void testFail(){
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 5.0, 4.0);;
        Assertions.assertTrue(t1.equals(t2));
    }
}
