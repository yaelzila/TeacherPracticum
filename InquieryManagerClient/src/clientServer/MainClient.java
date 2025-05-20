package clientServer;

import data.InquiryManagerAction;
import data.RequestData;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {


    public static void main(String[] args) {

        Socket clientSocket;

        {
            try {
                System.out.println("client connected to server");
                Scanner scanner = new Scanner(System.in);
                System.out.println("enter 1 for all inquiry, 2 for add inquiry, or exit");
                String clientSelection = scanner.next();

                while (!clientSelection.equals("exit")) {
                    clientSocket = new Socket("localhost", 5000);
                    RequestData requestData = null;
                    if(clientSelection.equals("1")){
                        requestData = new RequestData(InquiryManagerAction.ALL_INQUIRY, null);
                    }
                    // send to server
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                    objectOutputStream.writeObject(requestData);


                    //read server answer
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
                        System.out.println(objectInputStream.readObject());
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        System.err.println("fail read server answer");
                    }
                    System.out.println("enter 1 for all inquiry, 2 for add inquiry, or exit");
                    clientSelection = scanner.next();
                }


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
