package clientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class InquiryManagerServer {

    private ServerSocket serverSocket;

    public void startServer(int port) {
        try {
            System.out.println("start server");
            serverSocket = new ServerSocket(port);

            acceptClients();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("cannot start the server " + e.getMessage());
        }
    }

    private void acceptClients() throws IOException {
        while (true) {
            Socket connectedClientSocket = serverSocket.accept();
            new HandleClient(connectedClientSocket).start();

        }
    }




}
