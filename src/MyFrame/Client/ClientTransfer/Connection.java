package MyFrame.Client.ClientTransfer;

import MyFrame.Packet;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Connection<T> extends Thread implements Runnable, Closeable {

    private Socket client;

    public int userID;

    private List<T> texts = new ArrayList<>();

    private Listener onDataIn;

//    PrintWriter writer;
//    BufferedReader reader;

    ObjectOutputStream writer;
    ObjectInputStream reader;

    public Connection(Socket socket) throws IOException {
        client = socket;
        writer = new ObjectOutputStream(client.getOutputStream());
        reader = new ObjectInputStream(client.getInputStream());
    }

    public void addListener(Listener _listener) {
        onDataIn = _listener;
    }

//    public List getTexts() {
//        List<T> result = new ArrayList<>();
//        synchronized (texts) {
//            Collections.copy(result, texts);
//            texts.clear();
//        }
//        return result;
//    }

    @Override
    public void run() {

        while (true) {

            if (client.isClosed() || !client.isConnected()) break;

            Packet s = null;
            try {
                s = (Packet)reader.readObject();
                if (s == null) {
                    Thread.sleep(100);
                    continue;
                }
                if( s.type==0)
                {
                    userID = s.ID;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            onDataIn.onDataReceived(s);
        }
    }


    public void Send(T message) throws IOException {
        if (!client.isConnected()) return;
        if (message == null) return;
        writer.writeObject(message);
    }


    @Override
    public void close() throws IOException {

    }
}
