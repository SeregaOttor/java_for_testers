package ru.ssh.geometry.figures;

public class Square {
    public static void printSqvareArea(double side) {
        String text = String.format("Площадь квадрата со стороной %f = %f", side, sqvareArea(side));
        System.out.println(text);
    }

    private static double sqvareArea(double a) {
        return a * a;
    }
}
