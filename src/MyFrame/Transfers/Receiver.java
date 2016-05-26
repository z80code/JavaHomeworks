package MyFrame.Transfers;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.List;

public class Receiver<T extends String> {

    private ServerSocket server;

    private List<T> texts = new ArrayList<>();

    public Receiver() throws IOException{

        server = new ServerSocket(8080);
    }

    public List getTexts(){
        List<T> result = new ArrayList<>();
        synchronized (texts) {
            Collections.copy(result, texts);
            texts.clear();
        }
        return result;
    }



    public T Receipt() throws IOException {

        while (true) {

            // Проверка соединения с сервером?
            Socket socket = server.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String s = reader.readLine();
            //System.out.println("recv:" + s);
            //writer.println("server: " + s);
            socket.close();
            return (T)s;
        }


    }

}
