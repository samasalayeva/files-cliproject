package org.example.writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.example.data.DataBox;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Student;
import org.w3c.dom.Element;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;


public class MyFileWriter {
    public  static void writerToText(String fileName) throws IOException {
        FileWriter fw  = new FileWriter(fileName);
        for (Student student : DataBox.students) {
            fw.write(student + System.lineSeparator());
        }
        fw.close();
        System.out.println("Students written to file");
    }

    public static void writeToXmlFile(String fileName) throws IOException {
        try {
            // Create DocumentBuilderFactory and DocumentBuilder
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // Create a new Document
            Document doc = docBuilder.newDocument();

            // Create root element
            Element rootElement = doc.createElement("students");
            doc.appendChild(rootElement);

            // Iterate through the list of students and create 'student' elements
            for (Student student : DataBox.students) {
                Element studentElement = doc.createElement("student");

                // Create 'name' element
                Element nameElement = doc.createElement("name");
                nameElement.appendChild(doc.createTextNode(student.getName()));
                studentElement.appendChild(nameElement);

                Element surnameElement = doc.createElement("surname");
                surnameElement.appendChild(doc.createTextNode(student.getSurname()));
                studentElement.appendChild(surnameElement);

                Element specialityElement = doc.createElement("speciality");
                specialityElement.appendChild(doc.createTextNode(student.getSpeciality()));
                studentElement.appendChild(specialityElement);


                // Create 'age' element
                Element ageElement = doc.createElement("age");
                ageElement.appendChild(doc.createTextNode(String.valueOf(student.getAge())));
                studentElement.appendChild(ageElement);

                Element idElement = doc.createElement("id");
                idElement.appendChild(doc.createTextNode(String.valueOf(student.getId())));
                studentElement.appendChild(idElement);

                // Add 'student' element to the root
                rootElement.appendChild(studentElement);
            }

            // Write the content into an XML file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(fileName));

            transformer.transform(source, result);

            System.out.println("XML file saved at: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Students written to file");
    }

    public static void writeToJsonFile(String fileName) throws IOException {

        Path path = Paths.get(fileName);

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            JsonElement tree = gson.toJsonTree(DataBox.students);
            gson.toJson(tree, writer);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Students written to file");
    }

    public static void writeStudentsToExcel(String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Students");

        // Create headers
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("id");
        headerRow.createCell(1).setCellValue("name");
        headerRow.createCell(2).setCellValue("surname");
        headerRow.createCell(3).setCellValue("age");
        headerRow.createCell(4).setCellValue("speciality");

        // Write student data to rows
        int rowNum = 1;
        for (Student student : DataBox.students) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(student.getId());
            row.createCell(1).setCellValue(student.getName());
            row.createCell(2).setCellValue(student.getSurname());
            row.createCell(3).setCellValue(student.getAge());
            row.createCell(4).setCellValue(student.getSpeciality());
        }

        // Write the workbook content to a file
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            workbook.write(outputStream);
        }

        workbook.close();
    }
}
