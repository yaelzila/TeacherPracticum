package Data;

public class Request extends Inquiry{
    public Request(){
        super();
        fillDataByUser();
    }

    @Override
    public String handling() {
        return "Handling Request number" + this.getCode();
    }

//    @Override
//    public String getFolderName() {
//        return "Request";
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
