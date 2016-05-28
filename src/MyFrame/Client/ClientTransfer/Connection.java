package MyFrame.Client.ClientTransfer;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Connection<T> {

    private Socket client;

    private List<T> texts = new ArrayList<>();

    private ArrayList<Listener> listeners = new ArrayList<Listener>();

    public Connection(Socket socket) {
        client = socket;
    }

    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    public List getTexts() {
        List<T> result = new ArrayList<>();
        synchronized (texts) {
            Collections.copy(result, texts);
            texts.clear();
        }
        return result;
    }

    public boolean Send(T message) throws IOException {
        //BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter writer = new PrintWriter(client.getOutputStream(),true);
        writer.println(message);
        //String ans = reader.readLine();
        //System.out.println(ans);
        return true;
    }





}
