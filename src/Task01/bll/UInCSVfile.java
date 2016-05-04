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
        String s;
        BufferedReader buffReader = null;
        try {
            buffReader = new BufferedReader(new FileReader(file));
            s = buffReader.readLine();
            while ((s = buffReader.readLine())!=null)
            {
                String[] str = s.split(", ");
                int id = Integer.parseInt(str[0].trim());
                int phoneType = Integer.parseInt(str[4].trim());
                members.add(new Member(id, str[1].trim(), str[2].trim(), str[3].trim(), phoneType, str[5].trim()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                buffReader.close();
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
            writer.append(s);
            for (Member member: members) {
                writer.newLine();
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
