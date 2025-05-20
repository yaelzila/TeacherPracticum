package Data;

import HandleStoreFiles.ForSaving;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Inquiry implements ForSaving {
    static Integer nextCodeVal = 0;
    private Integer code;
    private String description;
    private LocalDateTime creationDate;
    protected String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Inquiry() {
        className=this.getClass().getName();
        this.code=nextCodeVal++;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String lastdCode() {
        try {
            File file = new File(getFolderName(), "Code.txt");
            if (!file.exists()) {
                return "0";
            }
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine().trim();
            scanner.close();
            return line;

        } catch (Exception e) {
            System.out.println("שגיאה בטעינת קוד הפנייה: " + e.getMessage());
            return "0";
        }
    }

    public void fillDataByUser()
    {
        String lastCodeStr = lastdCode();
        try {
            nextCodeVal = Integer.parseInt(lastCodeStr);
        } catch (NumberFormatException e) {
            nextCodeVal = 0;
        }
        code = nextCodeVal++;
        Scanner scanner = new Scanner(System.in);
        System.out.print("insert description: ");
        description = scanner.nextLine();
        creationDate = LocalDateTime.now();

    }
    public String handling(){
       return ("handling "+ getClass().getSimpleName()+"code"+ getCode() );
    }
    @Override
    public String getFolderName() {
        return getClass().getSimpleName();
    }

    public String getPrefix() {
        return getClass().getSimpleName().substring(0, 1).toUpperCase();
    }

    @Override
    public String getFileName() {
        return getPrefix()+this.code.toString()+ ".csv";
    }

    @Override
    public String getData() {
        return getClassName()+","+ getFileName()+","+ description+","+getFolderName();
    }

    @Override
    public void parseFromFile(List<String> values) {
        String className = values.get(0).trim();
        setClassName(className);
        setCode(Integer.valueOf(values.get(1)));
        setDescription(values.get(2).trim());
        String creationDateStr = values.get(3).trim();
        setCreationDate(LocalDateTime.parse(creationDateStr));
        nextCodeVal = getCode() + 1;
    }

    public void createFolder() {
        File MyDir = new File(getFolderName());
        if (!MyDir.exists()) {
            MyDir.mkdirs();
        }
    }

        public void saveCode() {
            try {
                createFolder();
                File file = new File(getFolderName(), "Code.txt");
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write(nextCodeVal.toString());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

        public void saveToFile() {
            createFolder();
            File file = new File(this.getFolderName(), code + ".txt");
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(getData());
                System.out.println("הקובץ נשמר בהצלחה: " + file.getAbsolutePath());
                saveCode();
            } catch (IOException e) {
                System.out.println("שגיאה בשמירה" + e.getMessage());
            }
        }
    }

