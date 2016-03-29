package Task01.bll;

import Task01.model.Member;

import java.util.ArrayList;
import java.util.List;

public abstract class abstractBase implements Ibase
{
    protected abstract List<Member> read();
    protected abstract void save(List<Member> members);

    @Override
    public int count() {
        List<Member> members = read();
        return members.size();
    }

    @Override
    public int getSelector() {
        List<Member> members = read();
            int max = -1;
            for (int i = 0; i < members.size(); i++) {
                max = (members.get(i).getId() > max) ? members.get(i).getId() : max;
            }
            return max + 1;
    }

    /*  Поиск в базе по значению поля
             *  index - номер поля в записи. Начиная с "1".
             *  value - упакованное значение
             */
    @Override
    public List<Member> find(int index, Object value) {
        List<Member> members = read();
        List<Member> result = new ArrayList<>();
        if (index > 0) {
            int count_wr = 0;
            switch (index) {
                case 1:/// Поиск по номеру id.
                {
                    int id=(int)value;
                    for (int i = 0; i < members.size(); i++) {
                        if (members.get(i).getId() == id) {
                            result.add(members.get(i));
                            count_wr++;
                        }
                    }
                    break;
                }
                case 2:/// Поиск по имени.
                {
                    String firstName = (String)value;
                    for (int i = 0; i < members.size(); i++) {
                        if (members.get(i).getFname().compareTo(firstName) == 0) {
                            result.add(members.get(i));
                            count_wr++;
                        }
                    }
                    break;
                }
                case 3:/// Поиск по фамилии.
                {
                    String lastName = (String)value;
                    for (int i = 0; i < members.size(); i++) {
                        if (members.get(i).getLname().compareTo(lastName) == 0) {
                            result.add(members.get(i));
                            count_wr++;
                        }
                    }
                    break;
                }
                case 4:/// Поиск по номеру телефона.
                {
                    String phone = (String)value;
                    for (int i = 0; i < members.size(); i++) {
                        if (members.get(i).getPhoneNumber().compareTo(phone) == 0) {
                            result.add(members.get(i));
                            count_wr++;
                        }
                    }
                    break;
                }
                case 5:/// Поиск по типу номера.
                    int type=(int)value;
                    for (int i = 0; i < members.size(); i++) {
                        if (members.get(i).getPhoneType() == type) {
                            System.out.println(members.get(i).toString());
                            count_wr++;
                        }
                    }
                    break;
                case 6:/// Поиск по отношениям.
                {
                    String relative = (String)value;
                    for (int i = 0; i < members.size(); i++) {
                        if (members.get(i).getRelative().compareTo(relative) == 0) {
                            System.out.println(members.get(i).toString());
                            count_wr++;
                        }
                    }
                    break;
                }
            }
        }

        return result;
    }

    @Override
    public boolean add(Member member) {
        List<Member> members = read();
        members.add(member);
        save(members);
        return false;
    }

    @Override
    public boolean delete(Member member) {
        List<Member> members = read();

        return false;
    }

    @Override
    public void sort(int index) {
        List<Member> members = read();

    }

    @Override
    public String toString(Member member) {
        List<Member> members = read();
        return null;
    }

    @Override
    public List<String> toStringAll() {
        List<Member> members = read();
        List<String> list = new ArrayList<>();
        for (Member member:members) {

            list.add(member.toString());
        }
        return list;
    }
}
