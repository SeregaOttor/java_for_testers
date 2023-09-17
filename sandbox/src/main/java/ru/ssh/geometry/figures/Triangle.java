package ru.ssh.geometry.figures;

public class Triangle {
    private double a;
    private double b;
    private double c;
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
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

}
