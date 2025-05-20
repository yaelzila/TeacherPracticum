package Data;
import java.util.List;
import java.util.Scanner;

public class Complaint extends Inquiry{
    private String assignedBranch;

    @Override
    public void fillDataByUser() {
        super.fillDataByUser();
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter brunch name");
        assignedBranch=scanner.nextLine();
    }

    @Override
    public String handling() {
        return "Handling Complaint number" + this.getCode();
    }

//    @Override
//    public String getFolderName() {
//        return "Complaint";
//    }

//    @Override
//    public String getFileName() {
//        return ""+getCode();
//    }

//    @Override
//    public String getData() {
//        return super.getData();
//    }
    @Override
    public void parseFromFile(List<String> values) {

    }

}
