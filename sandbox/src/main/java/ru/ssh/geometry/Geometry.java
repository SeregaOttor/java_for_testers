package ru.ssh.geometry;

import ru.ssh.geometry.figures.Rectangle;
import ru.ssh.geometry.figures.Square;
import ru.ssh.geometry.figures.Triangle;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(new Square(7.0));
        Square.printSquareArea(new Square(5.0));
        Square.printSquareArea(new Square(3.0));

        Rectangle.printRectargleArea(3.0, 5.0);
        Rectangle.printRectargleArea(7.0, 9.0);

        Triangle.printTrianglePerimetr(new Triangle(7.0, 7.0, 6.0));
        Triangle.printTriangleArea(new Triangle(7.0, 7.0, 6.0));

    }

}
