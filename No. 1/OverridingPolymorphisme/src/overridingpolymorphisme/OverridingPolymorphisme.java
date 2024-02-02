/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package overridingpolymorphisme;

/**
 *
 * @author Adji
 */

// Abstract class sebagai super class
abstract class Shape {
    // Metode abstract yang akan diimplementasikan oleh kelas anak
    abstract void draw();
}

// Kelas anak pertama yang meng-extend dari abstract class Shape
class Triangle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a triangle");
    }
}

// Kelas anak kedua yang meng-extend dari abstract class Shape
class Rectangle extends Shape {
    @Override
    void draw() {
        System.out.println("Drawing a rectangle");
    }
}

// Main class
public class OverridingPolymorphisme {
    public static void main(String[] args) {
        // Membuat objek dari subclass
        Shape triangle = new Triangle();
        Shape rectangle = new Rectangle();

        // Memanggil metode draw dari objek yang memiliki referensi Shape
        triangle.draw(); // Output: Drawing a triangle
        rectangle.draw(); // Output: Drawing a rectangle
    }
}
