package Task01.bll;

import Task01.model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UInCSVfile extends abstractBase {
    private final String filename = "data.csv";

    @Override
    protected List<Member> read() {
        List<Member> members = new ArrayList<>();
        File file = new File(filename);
        Member member;
        CSVReader<Member> reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
            String s = reader.readLine();
            while ((member = reader.readObject(new Member())) != null) {
                members.add(member);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return members;
    }

    @Override
    protected void save(List<Member> members) {
        File file = new File(filename);
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(file)); // Декорированный класс
            String s;
            s = "id, firstName, lastName, phoneNumber, phoneType, relative";
            writer.append(s);                // добавить строку
            for (Member member : members) {
                writer.newLine();            // перевод на следующую строку
                writer.appendObject(member); // принимаем любой объект
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
