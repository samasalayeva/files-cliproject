package org.example.manage;

import org.example.writer.MyFileWriter;

import java.io.IOException;
import java.util.Scanner;

public class MyFileWriterManager {
    private static void selectFileToWrite(int command) throws IOException {

        Scanner scan = new Scanner(System.in);
        String inputDirectory;

        switch (command) {
            case 1:
                System.out.println("Input file directory for JSON: ");
                inputDirectory = scan.nextLine();
                MyFileWriter.writeToJsonFile(inputDirectory);
                break;
            case 2:
                System.out.println("Input file directory for XML: ");
                inputDirectory = scan.nextLine();
                MyFileWriter.writeToXmlFile(inputDirectory);
                break;
            case 3:
                System.out.println("Input file directory for Text: ");
                inputDirectory = scan.nextLine();
                MyFileWriter.writerToText(inputDirectory);
                break;
            case 4:
                System.out.println("Input file directory for Excel - *.xlsx: ");
                inputDirectory = scan.nextLine();
                MyFileWriter.writeStudentsToExcel(inputDirectory);
                break;
            case 5:
                MainManager.manage();
                break;
            default:
                System.out.println("wrong command ");
        }
    }

    public static void printOpFileWriter() throws IOException {
        Scanner in = new Scanner(System.in);

        System.out.println("Choose output file format and enter number: \n" +
                "json - 1\n" +
                "xml - 2\n" +
                "txt - 3\n" +
                "excel - 4\n" +
                "exit - 5\n");

        int command = in.nextInt();
        selectFileToWrite(command);
    }
}
