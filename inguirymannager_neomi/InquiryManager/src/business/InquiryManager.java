package business;

import Data.Inquiry;
import Data.Question;
import Data.Request;
import Data.Complaint;
import HandleStoreFiles.HandleFiles;

import java.io.File;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class InquiryManager {
    private final BlockingQueue<Inquiry> inquiryQueue;
    static  private  InquiryManager singeltonInstence=null;
    private boolean isRunning = true; // משתנה לבקרה על הלולאה
    private static int nextVal = 1;

    public int getNextVal() {
        return nextVal++;
    }

    {
        inquiryQueue = new LinkedBlockingQueue<>();
        LoadingFromFile();
    }


    public void LoadingFromFile(){
        File QuestionFolder=new File(Question.class.getSimpleName());
        File RequestFolder=new File(Request.class.getSimpleName());
        File ComplaintFolder=new File(Complaint.class.getSimpleName());

        List<File> allFiles = new ArrayList<>();
        if(QuestionFolder.exists()){
            File[]QuestionFiles=QuestionFolder.listFiles();
            if(QuestionFiles!=null)
                allFiles.addAll(Arrays.asList(QuestionFiles));
        }
        if(RequestFolder.exists()){
            File[]RequestFiles=RequestFolder.listFiles();
            if(RequestFiles!=null)
                allFiles.addAll(Arrays.asList(RequestFiles));
        }
        if(ComplaintFolder.exists()){
            File[]ComplaintFiles=ComplaintFolder.listFiles();
            if(ComplaintFiles!=null)
                allFiles.addAll(Arrays.asList(ComplaintFiles));
        }

        HandleFiles handleFiles=new HandleFiles();
        for(File file :allFiles){
            Inquiry inquiryFromFiles=(Inquiry) handleFiles.readFile(file);
            this.inquiryQueue.add((inquiryFromFiles));
        }

    }

    public InquiryManager() {
        // מפעיל Thread קבוע שמטפל בפניות
        new Thread(this::processInquiryManager).start();
    }

    public void inquiryCreation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select the type of inquiry : ");
        System.out.println("1 - Question");
        System.out.println("2 - Request");
        System.out.println("3 - Complaint");
        Inquiry newInquiry = null;
        switch (scanner.nextInt()) {
            case 1:
                newInquiry = new Question();
                break;
            case 2:
                newInquiry = new Request();
                break;
            case 3:
                newInquiry = new Complaint();
                break;
        }
        if (newInquiry != null) {
            newInquiry.fillDataByUser();
            newInquiry.createFolder();
            newInquiry.saveToFile();
            inquiryQueue.add(newInquiry);
        }
    }

    public void processInquiryManager() {
        while (isRunning) {
            try {
                // שולף פנייה מהתור ומטפל בה
                Inquiry inquiry = inquiryQueue.take(); // מחכה אם אין פניות
                new Thread(() -> handleInquiry(inquiry)).start(); // מפעיל פנייה ב-Thread משלה
            } catch (InterruptedException e) {
                System.out.println(" Thread נקטע!");
                Thread.currentThread().interrupt();
            }
        }
    }

    private void handleInquiry(Inquiry inquiry) {
        try {
            if (inquiry instanceof Request) {
                System.out.println(" טיפול בבקשה - השהייה 3 שניות...");
                Thread.sleep(3000);
            } else {
                System.out.println("טיפול בפנייה - השהייה 5 שניות...");
                Thread.sleep(5000);
            }
            inquiry.handling();
        } catch (InterruptedException e) {
            System.out.println("שגיאה בטיפול בפנייה!");
            Thread.currentThread().interrupt();
        }
    }
}
