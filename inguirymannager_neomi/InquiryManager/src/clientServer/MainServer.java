package clientServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer {

    public static void main(String[] args) {
        InquiryManagerServer inquiryManagerServer = new InquiryManagerServer();
        inquiryManagerServer.startServer(5000);


    }



}
