package utilities;

import sparkService.sparkController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        sparkController server = new sparkController();
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
