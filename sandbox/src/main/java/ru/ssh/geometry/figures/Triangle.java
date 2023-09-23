package ru.ssh.geometry.figures;

public class Triangle {
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

}
