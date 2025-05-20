import NightProcess.FileRename;
import business.InquiryManager;

public class Main {
    public static void main(String[] args) {
        InquiryManager inquiryManager = new InquiryManager();
        inquiryManager.inquiryCreation();
        inquiryManager.inquiryCreation();
        inquiryManager.inquiryCreation();
        inquiryManager.inquiryCreation();
        inquiryManager.inquiryCreation();
        inquiryManager.processInquiryManager();

        // דוגמה להרצת תהליך על שתי תיקיות שונות במקביל
        //Thread t1 = new Thread(new FileRename("C:\\Users\\1\\Desktop\\לימודים\\שנה ב\\practikum\\inguirymannager_neomi", "prefix1_"));
        //Thread t2 = new Thread(new FileRename("C:\\MyFolder2", "prefix2_"));
        //t1.start();
        //t2.start();

    }
}
