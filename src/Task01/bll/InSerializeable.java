package Task01.bll;

import Task01.model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InSerializeable extends abstractBase
{
    @Override
    protected List<Member> read() {
        List<Member> members = new ArrayList<>();
        File fr = new File("data.dsf");
        ObjectInputStream istream = null;
        try {
            FileInputStream fis = new FileInputStream(fr);
            istream = new ObjectInputStream(fis);
            // десериализация
            members = (List<Member>)istream.readObject();
        } catch (ClassNotFoundException ce) {
            System.err.println("Класс не существует: " + ce);
        } catch (FileNotFoundException e) {
            System.err.println("Файл для десериализации не существует: "+ e);
        } catch (InvalidClassException ioe) {
            System.err.println("Несовпадение версий классов: " + ioe);
        } catch (IOException ioe) {
            //System.err.println("Общая I/O ошибка: " + ioe);
        } finally {
            try {
                if (istream != null) {
                    istream.close();
                }
            } catch (IOException e) {
                System.err.println("ошибка закрытия потока ");
            }
        }
        return  members;
    }

    @Override
    protected void save(List<Member> members) {
        File file = new File("data.dsf");
        ObjectOutputStream ostream = null;
        try {
            FileOutputStream fos = new FileOutputStream(file);
            if (fos != null) {
                ostream = new ObjectOutputStream(fos);
                ostream.writeObject(members);
                // сериализация
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл не может быть создан: "+ e);
        } catch (NotSerializableException e) {
            System.err.println("Класс не поддерживает сериализацию: " + e);
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                if (ostream != null) {
                    ostream.close();
                }
            } catch (IOException e) {
                System.err.println("ошибка закрытия потока");
            }
        }
    }
}
