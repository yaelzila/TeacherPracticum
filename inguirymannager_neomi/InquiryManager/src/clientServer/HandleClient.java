package clientServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class HandleClient extends Thread {

    private Socket connectedClientSocket;

    public HandleClient(Socket connectedClientSocket) {
        this.connectedClientSocket = connectedClientSocket;
    }

    public void handleClient() {
        System.out.println("client connected !!");
        System.out.println(connectedClientSocket.getRemoteSocketAddress());
        Object objFromClient = null;

        // read data from client
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(connectedClientSocket.getInputStream());
            objFromClient = objectInputStream.readObject();
            System.out.println(objFromClient);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("fail read from client");
        }

        try {
            Thread.currentThread().sleep(1000 * 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
//            RequestData requestData = (RequestData)
            // answer to client
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(connectedClientSocket.getOutputStream());
            objectOutputStream.writeObject("hello " + objFromClient + " from server");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("fail answer to client");
        }
    }

    @Override
    public void run() {
        handleClient();
    }
}
