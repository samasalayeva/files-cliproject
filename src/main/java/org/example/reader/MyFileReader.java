package org.example.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.data.DataBox;
import org.example.manage.MainManager;
import org.example.model.Student;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.xmlpull.v1.XmlPullParser.*;

public class MyFileReader {
    public static void jsonReader(String fileName) throws IOException {

        final ObjectMapper objectMapper = new ObjectMapper();
        DataBox.students = objectMapper.readValue(new File(fileName), new TypeReference<>() {
        });
    }

    public static void xmlReader(String fileName) throws IOException {
        Student student = null;
        String text = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            InputStream is = new FileInputStream(fileName);

            parser.setInput(is, null);

            int eventType = parser.getEventType();
            while (eventType != END_DOCUMENT) {
                String tagname = parser.getName();
                switch (eventType) {
                    case START_TAG:
                        if (tagname.equalsIgnoreCase("student")) {
                            // create a new instance of employee
                            student = new Student();
                        }
                        break;

                    case TEXT:
                        text = parser.getText();
                        break;

                    case END_TAG:
                        if (tagname.equalsIgnoreCase("student")) {
                            // add employee object to list
                            DataBox.students.add(student);
                        } else if (tagname.equalsIgnoreCase("id")) {
                            student.setId(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("name")) {
                            student.setName(text);
                        } else if (tagname.equalsIgnoreCase("surname")) {
                            student.setSurname(text);
                        } else if (tagname.equalsIgnoreCase("age")) {
                            student.setAge(Integer.parseInt(text));
                        } else if (tagname.equalsIgnoreCase("speciality")) {
                            student.setSpeciality(text);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void excelReader(String fileName) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            Student student = new Student();

            for (Cell cell : row) {
                if (cell.getColumnIndex() == 0) {
                    student.setId((int) cell.getNumericCellValue());
                } else if (cell.getColumnIndex() == 1) {
                    student.setName(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 2) {
                    student.setSurname(cell.getStringCellValue());
                } else if (cell.getColumnIndex() == 3) {
                    student.setAge((int) cell.getNumericCellValue());
                } else if (cell.getColumnIndex() == 4) {
                    student.setSpeciality(cell.getStringCellValue());
                }
            }

            DataBox.students.add(student);
        }

    }

    public static void convertTextFileToStudentList(String filePath) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            while ((line = br.readLine()) != null){
                String[] data = line.split(",");
                if(data.length == 5){
                    Student student = new Student();
                    student.setId(Integer.parseInt(data[0].trim()));
                    student.setName(data[1].trim());
                    student.setSurname(data[2].trim());
                    student.setSpeciality(data[3].trim());
                    student.setAge(Integer.parseInt(data[4].trim()));

                    DataBox.students.add(student);
                }
            }
        }
    }
}
