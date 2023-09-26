package ru.ssh.geometry.figures;

import java.util.Objects;

public class Triangle {
    //Если вместо class использовать record то не придется писать вручную метод equens
    //Но так как нам все равно его надо писать то оставляю class
    private double a;
    private double b;
    private double c;
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        /*if (a < 0||b < 0||c < 0||(a+b)<c||(a+c)<b||(b+c)<a){ //То же рабочее но возвращается одно сообщение
            throw new IllegalArgumentException("Triangle side should be not-negative");
        }*/
        if (a < 0||b < 0||c < 0){
            throw new IllegalArgumentException("Triangle side should be not-negative");
        }
        if ((a+b)<c||(a+c)<b||(b+c)<a){
            throw new IllegalArgumentException("Violating Triangle inequality");
        }
    }
    public static void printTrianglePerimetr(Triangle t) {
        String text = String.format("Периметр треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.trianglePerimetr());
        System.out.println(text);
    }
    public double trianglePerimetr() {
        return this.a + this.b + this.c;
    }

    public static void printTriangleArea(Triangle t) {
        String text = String.format("Площадь треугольника со сторонами %f, %f и %f = %f", t.a, t.b, t.c, t.triangleArea());
        System.out.println(text);
    }
    private double halfTerianglePerimetr() {
        return trianglePerimetr()/2;
    }
    public double triangleArea() {
        return Math.ceil(Math.sqrt(halfTerianglePerimetr() * (halfTerianglePerimetr() - this.a) * (halfTerianglePerimetr() - this.b) * (halfTerianglePerimetr() - this.c)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return (Double.compare(this.a, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                ||(Double.compare(this.b, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)
                ||(Double.compare(this.c, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)
                ||(Double.compare(this.a, triangle.a) == 0 && Double.compare(this.c, triangle.b) == 0 && Double.compare(this.b, triangle.c) == 0)
                ||(Double.compare(this.b, triangle.a) == 0 && Double.compare(this.a, triangle.b) == 0 && Double.compare(this.c, triangle.c) == 0)
                ||(Double.compare(this.c, triangle.a) == 0 && Double.compare(this.b, triangle.b) == 0 && Double.compare(this.a, triangle.c) == 0)
                ;
    }
    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
