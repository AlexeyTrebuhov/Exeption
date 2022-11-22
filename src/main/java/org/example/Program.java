package org.example;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Program extends Main {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("¬ведите им€ файла: ");
        String fileName = sc.nextLine();
        System.out.println("¬ведите контент: ");
        String content = sc.nextLine();
        save(fileName, content);
    }

    private static void save(String fileName, String content) throws IOException {
        Files.write(Paths.get(fileName), content.getBytes());
    }

}