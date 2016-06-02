package MyFrame.Client.ClientTransfer;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Connection<T> extends Thread implements Runnable, Closeable {

    private Socket client;

    private List<T> texts = new ArrayList<>();

    private Listener onDataIn;

    PrintWriter writer;

    BufferedReader reader;

    public Connection(Socket socket) throws IOException {
        client = socket;
        writer = new PrintWriter(client.getOutputStream(),true);
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
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

            String s = null;
            try {
                s = reader.readLine();
                if(s==null){
                    Thread.sleep(100);
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" Клиент " + client.getInetAddress() + ": " + s);
            onDataIn.onDataReceived(s);
        }
        System.out.println(" Server: " + client.getInetAddress()
                + ": " + " отключился.");

    }


    public void Send(T message) throws IOException {
        if (!client.isConnected()) return;
        if(message==null) return;
        writer.println(message);
    }


    @Override
    public void close() throws IOException {

    }
}
