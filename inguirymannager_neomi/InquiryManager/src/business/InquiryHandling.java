package business;

import Data.Complaint;
import Data.Inquiry;
import Data.Question;
import Data.Request;
import java.util.Scanner;

public class InquiryHandling extends Thread {
    private Inquiry currentInquiry;
    Scanner scanner = new Scanner(System.in);

    public Inquiry getCurrentInquiry() {
        return currentInquiry;
    }

    public void setCurrentInquiry(Inquiry currentInquiry) {
        this.currentInquiry = currentInquiry;
    }

    @Deprecated
    public void createInquiry() {
        System.out.println(" Deprecated: Use InquiryManager.inquiryCreation() instead.");
    }

    @Override
    public void run() {
        try {
            int sleepTime;
            if (currentInquiry instanceof Request) {
                sleepTime = 3000;
                System.out.println(" Processing Request: Sleeping for 3 seconds...");
            } else {
                sleepTime = 5000;
                System.out.println(" Processing Inquiry: Sleeping for 5 seconds...");
            }
            Thread.sleep(sleepTime);
            currentInquiry.handling();
        } catch (InterruptedException e) {
            System.out.println(" Thread interrupted: " + e.getMessage());
        }
    }
}
