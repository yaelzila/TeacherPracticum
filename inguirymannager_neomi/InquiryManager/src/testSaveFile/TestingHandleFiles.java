package testSaveFile;

import HandleStoreFiles.HandleFiles;

import java.io.IOException;
import java.util.Arrays;

public class TestingHandleFiles {
    public static void main(String[] args) throws IOException {
        PersonForTestSaving p1 = new PersonForTestSaving("1234","aaa");
        PersonForTestSaving p2 = new PersonForTestSaving("5432","bbb");
        PersonForTestSaving p3 = new PersonForTestSaving("9999","ccc");
        PersonForTestSaving p4 = new PersonForTestSaving("0090","ccdc");

//        HandleFiles handleFiles = new HandleFiles();
//        handleFiles.saveFile(p1);
//        handleFiles.saveFile(p2);
//        handleFiles.saveFile(p3);
//        handleFiles.saveFile(p4);
//        handleFiles.saveFiles(Arrays.asList(p1,p2,p3,p4));

//        handleFiles.deleteFile(p1);
//        handleFiles.deleteFile(p2);
//        handleFiles.deleteFile(p3);
//        handleFiles.deleteFile(p4);


//
//        PersonForTestSaving p5 = new PersonForTestSaving("123456789","sucess BH!");
//        handleFiles.saveCSV(p5, "id");
//
//        PersonForTestSaving readP5 = new PersonForTestSaving("123456789","sucess BH!");
//        handleFiles.saveCSV(p5, "id");
//        PersonForTestSaving readP5 = handleFiles. readCsv ("123456789.csv");
//        System.out.println(readP5);


        PersonForTestSaving p5 = new PersonForTestSaving("123456789", "success BH!");
        HandleFiles handleFiles = new HandleFiles();
        boolean success = handleFiles.saveCSV(p5, "id.csv");
        if (success) {
            System.out.println("הקובץ נשמר בהצלחה");
        } else {
            System.out.println("שמירת הקובץ נכשלה");
        }
        handleFiles.saveCSV(p5, "id");
        String fileContent = handleFiles.readCsv("123456789.csv");
        System.out.println(fileContent);
    }

}
