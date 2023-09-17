public class Geometry {
    public static void main(String[] args) {
        printSqvareArea(7.0);
        printSqvareArea(5.0);
        printSqvareArea(3.0);

        printRectargleArea(3.0, 5.0);
        printRectargleArea(7.0, 9.0);

    }

    private static void printRectargleArea(double a, double b) {
        System.out.println("Площадь прямоугольника со сторонами " + a + " и " + b + " = " + rectangleArea(a, b));
    }
    private static double rectangleArea(double a, double b) {
        return a * b;
    }

    static void printSqvareArea(double side) {
        System.out.println("Площадь квадрата со стороной " + side + " = " + sqvareArea(side));
    }
    private static double sqvareArea(double a) {
        return a * a;
    }
}
