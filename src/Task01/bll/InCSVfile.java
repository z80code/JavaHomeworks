package Task01.bll;

import Task01.model.Member;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InCSVfile extends abstractBase {
    @Override
    protected List<Member> read() {
        List<Member> members = new ArrayList<>();
        File file = new File("data.csv");
        String s;
        BufferedReader buffReader = null;
        try {
            buffReader = new BufferedReader(new FileReader(file));
            s = buffReader.readLine();
            while ((s = buffReader.readLine())!=null)
            {
                String[] str = s.split(",");
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

        File file = new File("data.csv");
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file));
            String s;
            s = "id, firstName, lastName, phoneNumber, phoneType, relative";
            bufferedWriter.append(s);
            for (Member member: members) {
                s = String.format("%d, %s, %s, %s, %d, %s",
                        member.getId(),
                        member.getFname(),
                        member.getLname(),
                        member.getPhoneNumber(),
                        member.getPhoneType(),
                        member.getRelative());
                bufferedWriter.newLine();
                bufferedWriter.append(s);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
