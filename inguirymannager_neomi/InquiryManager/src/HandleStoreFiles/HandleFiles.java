package HandleStoreFiles;

import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class HandleFiles
{
//    public void saveFile(ForSaving forSaving) throws IOException
//    {
//        String path = getDirectoryPath(forSaving); // קבל את הנתיב לקובץ
//        File file = new File(path);
//        // יצירת תיקיה אם לא קיימת
//        File folder = file.getParentFile();
//        if (folder != null && !folder.exists()) {
//            folder.mkdirs();
//        }
//        // אם הקובץ לא קיים, יצירת קובץ חדש
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
//            // כותב את הנתונים לקובץ
//            writer.write(forSaving.getData());
//            writer.flush();
//        }
//        System.out.println("Saving to: " + file.getAbsolutePath());
//    }

    public void saveFile(ForSaving forSaving) throws IOException {
        String path = getDirectoryPath(forSaving);
        File newFile = new File(path);

        // קובץ ישן (נניח שיש לו אותה התחלה בשם בלי .csv)
        File oldFile = new File(newFile.getParent(), forSaving.getFileName().replace(".csv", ".txt"));

        // יצירת תיקיה אם לא קיימת
        File folder = newFile.getParentFile();
        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }

        // אם הקובץ הישן קיים, העבר אותו לתיקיית OldBusinessInquiry
        if (oldFile.exists()) {
            File oldFolder = new File(folder, "OldBusinessInquiry");
            if (!oldFolder.exists()) {
                oldFolder.mkdirs();
            }

            File movedFile = new File(oldFolder, oldFile.getName());
            boolean success = oldFile.renameTo(movedFile);
            if (success) {
                System.out.println("Old file moved to: " + movedFile.getAbsolutePath());
            } else {
                System.out.println("Failed to move old file.");
            }
        }
        // יצירת הקובץ החדש
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(newFile)))) {
            writer.write(forSaving.getData()); // כבר מופרד בפסיקים כמו שצריך
            writer.flush();
        }
        System.out.println("Saving to: " + newFile.getAbsolutePath());
    }


    public void deleteFile(ForSaving forSaving){
        String path=forSaving.getFolderName()+ "\\"+forSaving.getFileName();
        File f=new File(path);
        f.delete();
        System.out.printf("deleatrd");
    }
    public void updateFile(ForSaving forSaving){
        File file = new File(getDirectoryPath(forSaving));
        file.delete();
    }
    private String getFileName(ForSaving forSaving){
        return forSaving.getFileName();
    }

    private String getDirectoryPath(ForSaving forSaving){
       return forSaving.getFolderName()+ "\\"+forSaving.getFileName();
    }

    public void saveFiles(List forSavingList){
        //File f=new File()
    }

    public  ForSaving readFile(File file){
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String fileText = bufferedReader.readLine();
            bufferedReader.close();
            String[] fileData = fileText.split(",");
            Class newClass=Class.forName(fileData[0]);
            ForSaving newObj=(ForSaving) newClass.getConstructor().newInstance();
            List<String>fileDatalist= Arrays.asList(fileData);
            newObj.parseFromFile(fileDatalist);
            return  newObj;
        }

        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public String getCSVDataRecursive(Object obj){
        if (obj == null) return "";
        StringBuilder result = new StringBuilder();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(obj);
                result.append(value != null ? value.toString() : "null").append(",");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    public boolean saveCSV(Object obj, String filePath) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath+".csv"));
            String csvData = getCSVDataRecursive(obj);
            bufferedOutputStream.write(csvData.getBytes());
            bufferedOutputStream.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String readCsv(String filePath) {
        File file = new File(filePath);
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n"); // או "," אם את רוצה להפריד בפסיקים
            }
            return content.toString().trim();
        } catch (IOException e) {
            System.out.println("שגיאה בקריאת הקובץ: " + e.getMessage());
            return null;
        }
    }



}
