package rikkei.academy.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config <T>{
    public static Scanner sc;

    public static Scanner scanner() {
        if (sc == null) {
            sc = new Scanner(System.in);
            return sc;
        }
        return sc;
    }

    public T readFile(String path) {
        try (
                FileInputStream fis = new FileInputStream(path);
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (T) ois.readObject();
        } catch (Exception e) {
            System.out.println("Error reading");
            return null;
        }
    }

    public void writeFile(String path, T data) {
        try (
                FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println("Error writing");
        }
    }
}