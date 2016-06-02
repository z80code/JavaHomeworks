package MyFrame;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Packet implements Serializable{
    public int type;
    public int ID;
    public String name ="";
    public List<String> strings = new ArrayList<>();

    private static int currentId = 0;

    public Packet(int type, int ID, String name, List<String> strings) {
        this.type = type;
        this.ID = ID;
        this.name = name;
        this.strings = strings;
    }


    public void setID(int id){
        ID = id;
    }
}
