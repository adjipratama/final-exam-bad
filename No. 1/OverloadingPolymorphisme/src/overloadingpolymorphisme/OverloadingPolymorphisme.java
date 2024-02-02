/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package overloadingpolymorphisme;

/**
 *
 * @author Adji
 */

// Abstract class sebagai super class
abstract class Shape {
    // Metode abstract yang akan diimplementasikan oleh kelas anak
    abstract void draw();

    // Metode overloading dengan satu parameter
    public void draw(String color) {
        System.out.println("Drawing shape with color: " + color);
    }
}

// Kelas anak pertama yang meng-extend dari abstract class Shape
class Triangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a triangle");
    }
}

// Kelas anak kedua yang meng-extend dari abstract class Shape
class Rectangle extends Shape {
    @Override
    public void draw() {
        System.out.println("Drawing a rectangle");
    }
}

public class OverloadingPolymorphisme {
    public static void main(String[] args) {
        // Membuat objek dari kelas anak Circle
        Shape triangle = new Triangle();
        // Memanggil metode draw() yang di-overload
        triangle.draw();  // Output: Drawing a circle
        // Memanggil metode draw() yang di-overload dengan parameter
        triangle.draw("red");  // Output: Drawing shape with color: red

        // Membuat objek dari kelas anak Rectangle
        Shape rectangle = new Rectangle();
        // Memanggil metode draw() yang di-overload
        rectangle.draw();  // Output: Drawing a rectangle
        // Memanggil metode draw() yang di-overload dengan parameter
        rectangle.draw("blue");  // Output: Drawing shape with color: blue
    }
}
