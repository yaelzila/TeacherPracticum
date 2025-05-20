package clientServer;

import java.io.Serializable;

public class RequestData implements Serializable {

    private InquiryManagerAction action;
    private Object object;

    public RequestData(InquiryManagerAction action, Object object) {
        this.action = action;
        this.object = object;
    }
}
