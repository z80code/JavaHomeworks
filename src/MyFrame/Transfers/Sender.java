package MyFrame.Transfers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;


public class Sender<T extends String> {

   private Socket client = new Socket();

    public boolean Send(T message) throws IOException {

        client.connect(new InetSocketAddress("127.0.0.1", 8080));

        //BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));

        PrintWriter writer = new PrintWriter(client.getOutputStream(),true);

        writer.println(message);
        //String ans = reader.readLine();
        //System.out.println(ans);
        client.close();

        return true;
    }

}
