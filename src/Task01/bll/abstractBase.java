package Task01.bll;

import Task01.model.Member;

import java.util.*;

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

    @Override
    public List<Member> getAll() {
        return read();
    }

    @Override
    public Member getById(int id) {
        List<Member> members = read();
        Member member = null;
        for (int i = 0; i < members.size(); i++) {

            if (members.get(i).getId() == id) return members.get(i);
        }
        return member;
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
                            result.add(members.get(i));
                            count_wr++;
                        }
                    }
                    break;
                case 6:/// Поиск по отношениям.
                {
                    String relative = (String)value;
                    for (int i = 0; i < members.size(); i++) {
                        if (members.get(i).getRelative().compareTo(relative) == 0) {
                            result.add(members.get(i));
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
        return true;
    }

    @Override
    public boolean change(int id, Member member) {
        List<Member> members = read();
        int size = members.size();
        int i= members.indexOf(member);
        if(i<0) return false;
        members.set(i,member);
        save(members);
        return true;
    }

    @Override
    public void delete(List<Member> listForDelete) {
        List<Member> members = read();
        int size = members.size();
        int[] forDelete = new int[size];
        for (int i = 0; i < listForDelete.size(); i++) {
            forDelete[i] = listForDelete.get(i).getId();
        }
        for (int i = listForDelete.size()-1; i >= 0; i--) {
            members.remove(forDelete[i]);
        }
        save(members);
    }

    @Override
    public void sort(int index) {
        List<Member> members = read();
        Collections.sort(members, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                switch (index){
                    case 1:
                        return (o1.getId()>o2.getId())?-1:1;
                    case 2:
                        return o1.getFname().compareTo(o2.getFname());
                    case 3:
                        return o1.getLname().compareTo(o2.getLname());
                    case 4:
                        return o1.getPhoneNumber().compareTo(o2.getPhoneNumber());
                    case 5:
                        return (o1.getPhoneType()>o2.getPhoneType())?-1:1;
                    case 6:
                        return o1.getRelative().compareTo(o2.getRelative());
                }
                return 0;
            }
        });
        save(members);
    }

    @Override
    public String toString(int index) {
        List<Member> members = read();
        return members.get(index).toString();
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
