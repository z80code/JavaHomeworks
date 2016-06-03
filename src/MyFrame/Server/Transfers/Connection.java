package MyFrame.Server.Transfers;

import MyFrame.Packet;

import java.io.*;
import java.net.Socket;

public class Connection extends Thread implements Runnable, Closeable {

    public boolean isActiveConnection = false;

    private Socket clientSocket;

    ObjectInputStream reader;
    ObjectOutputStream writer;

    public int userID;

//    BufferedReader reader;
//    PrintWriter writer;

    public Listener onDataIn;

    public void addListener(Listener _listener) {
        onDataIn = _listener;
    }

    public Connection(Socket clientSocket, int userID) throws IOException {
        this.userID = userID;
        this.clientSocket = clientSocket;
        writer = new ObjectOutputStream(clientSocket.getOutputStream());
        reader = new ObjectInputStream(clientSocket.getInputStream());
        isActiveConnection = true;
    }

    public void send(Packet message) throws IOException {
        if (!clientSocket.isConnected()) return;
        if (message == null) return;
        //message.ID = userID;
        writer.writeObject(message);
    }

    @Override
    public void run() {
        System.out.println(" Клиент: " + clientSocket.getInetAddress()
                + ": " + clientSocket.getPort() + " подключился.");

        while (true) {

            if (clientSocket.isClosed() || !clientSocket.isConnected()) break;

            Packet s = null;
            try {
                s = (Packet)reader.readObject();
                if (s == null) {
                    Thread.sleep(100);
                    continue;
                }
            } catch (IOException e) {
                //e.printStackTrace();
                break;

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            System.out.println(" Клиент "+ userID +": " + clientSocket.getInetAddress() + " -> " + s);
            try {
                onDataIn.onDataReceived(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(" Клиент: " + clientSocket.getInetAddress()
                + ": " + " отключился.");
    }

    @Override
    public void close() throws IOException {

        if (reader != null) reader.close();
        if (writer != null) writer.close();
        clientSocket.close();
        isActiveConnection = false;
    }
}
