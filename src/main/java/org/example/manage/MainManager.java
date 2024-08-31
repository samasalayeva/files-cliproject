package org.example.manage;


import java.io.IOException;
import java.util.Scanner;

public class MainManager {
    public static void manage() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int command = 0; // Initialize command variable

        while (command != 3) {
            printOpMenu();
            command = scanner.nextInt();

            switch (command) {
                case 1:
                    MyFileReaderManager.printOpFileReader();
                    break;
                case 2:
                    MyFileWriterManager.printOpFileWriter();
                    break;
                case 3:
                    break; // Exit loop
                default:
                    throw new IllegalStateException("Unexpected value: " + command);
            }
        }
    }

    private static void printOpMenu() {
        System.out.println("Please choose operation: \n" +
                "read to file - 1 \n" +
                "write to file - 2 \n" +
                "exit - 3");
    }

}
