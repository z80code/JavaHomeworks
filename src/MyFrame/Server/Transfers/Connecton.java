package MyFrame.Server.Transfers;

import java.io.*;
import java.net.Socket;

public class Connecton extends Thread implements Runnable, Closeable {

    public boolean isActiveConnection = false;

    private Socket clientSocket;

    BufferedReader reader;
    PrintWriter writer;

    public Listener onDataIn;

    public void addListener(Listener _listener) {
        onDataIn = _listener;
    }

    public Connecton(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        writer = new PrintWriter(clientSocket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        isActiveConnection = true;
    }

    public void send(String message) {
        if (!clientSocket.isConnected()) return;
        if (message == null) return;
        writer.println(message);
    }

    @Override
    public void run() {
        System.out.println(" Клиент: " + clientSocket.getInetAddress()
                + ": " + clientSocket.getPort() + " подключился.");

        while (true) {

            if (clientSocket.isClosed() || !clientSocket.isConnected()) break;

            String s = null;
            try {
                s = reader.readLine();
                if (s == null) {
                    Thread.sleep(100);
                    continue;
                }
            } catch (IOException e) {

                break;
                // e.printStackTrace();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" Клиент " + clientSocket.getInetAddress() + ": " + s);
            onDataIn.onDataReceived(s);
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
