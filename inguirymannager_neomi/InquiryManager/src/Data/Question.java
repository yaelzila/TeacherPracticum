package Data;

public class Question extends Inquiry{
    public Question() {
        super();
        fillDataByUser();
    }

    @Override
    public String handling() {
        return  "Handling Question number" + this.getCode();
    }

//    @Override
//    public String getFolderName() {
//        return "Question";
//    }

//    @Override
//    public String getFileName() {
//        return ""+getCode();
//    }

//    @Override
//    public String getData() {
//        return super.getData();
//    }
}
