package org.example.manage;

import org.example.reader.MyFileReader;

import java.io.IOException;
import java.util.Scanner;

public class MyFileReaderManager {

    private static void selectFileToRead(int command) throws IOException {
        Scanner scan = new Scanner(System.in);
        String inputDirectory;

        switch (command) {
            case 1:
                System.out.println("Input file directory for JSON: ");
                inputDirectory = scan.nextLine();
                MyFileReader.jsonReader(inputDirectory);
                break;
            case 2:
                System.out.println("Input file directory for XML: ");
                inputDirectory = scan.nextLine();
                MyFileReader.xmlReader(inputDirectory);
                break;
            case 3:
                System.out.println("Input file directory for Excel: ");
                inputDirectory = scan.nextLine();
                MyFileReader.excelReader(inputDirectory);
                break;
            case 4:
                System.out.println("Input file directory for txt: ");
                inputDirectory = scan.nextLine();
                MyFileReader.convertTextFileToStudentList(inputDirectory);
                break;
            case 5:
                MainManager.manage(); // Corrected method name to 'manage'
                break;
            default:
                System.out.println("wrong command ");
        }
    }

    public static void printOpFileReader() throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Choose input file format and enter number: \n" +
                "json - 1\n" +
                "xml - 2\n" +
                "excel - 3\n" +
                "txt - 4\n" +
                "exit - 5\n");

        int command = in.nextInt();
        selectFileToRead(command);
    }
}