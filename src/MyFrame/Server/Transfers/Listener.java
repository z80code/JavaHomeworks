package MyFrame.Server.Transfers;

import MyFrame.Packet;

import java.io.IOException;

public interface Listener {
    void onDataReceived(Packet message) throws IOException;
}

