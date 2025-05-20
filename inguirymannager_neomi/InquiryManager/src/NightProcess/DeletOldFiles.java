package ProcessNight;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class DeletOldFiles extends Thread {
  private String filePath;
  private int days;

    public DeletOldFiles(String filePath,int days) {
        this.filePath = filePath;
        this.days=days;
    }

    @Override
    public void run() {
        File directory = new File(filePath);

        if (!directory.exists()) {
            System.out.println("לא נמצאה תיקייה בשם זה.");
            return;
        }

        LocalDateTime cutOffDate = LocalDateTime.now().minusDays(days);
        File[] files = directory.listFiles();

        boolean fileDeleted = false;

        if (files != null) {
            for (File file : files) {
                try {
                    BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
                    Instant creationTime = attrs.creationTime().toInstant();
                    LocalDateTime creationDate = LocalDateTime.ofInstant(creationTime, ZoneId.systemDefault());

                    if (creationDate.isBefore(cutOffDate)) {
                        if (file.delete()) {
                            System.out.println("הקובץ: " + file.getName() + " נמחק.");
                            fileDeleted = true;
                        } else {
                            System.out.println("הקובץ: " + file.getName() + " לא ניתן למחיקה.");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("אין גישה לקובץ: " + file.getName());
                    e.printStackTrace();
                }
            }
        }

        if (!fileDeleted) {
            System.out.println("לא נמחקו קבצים.");
        }
    }
}

