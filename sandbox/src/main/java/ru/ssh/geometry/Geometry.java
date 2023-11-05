package ru.ssh.geometry;

import ru.ssh.geometry.figures.Rectangle;
import ru.ssh.geometry.figures.Square;
import ru.ssh.geometry.figures.Triangle;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {
        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));
        var squares = Stream.generate(randomSquare).limit(5);
        squares.forEach(Square::printSquareArea);

        //Rectangle.printRectargleArea(3.0, 5.0);
        //Rectangle.printRectargleArea(7.0, 9.0);
//
        //Triangle.printTrianglePerimetr(new Triangle(7.0, 7.0, 6.0));
        //Triangle.printTriangleArea(new Triangle(7.0, 7.0, 6.0));

    }

}
