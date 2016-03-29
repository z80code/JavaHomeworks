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
    public boolean delete(int index, List<Member> listForDelete) {
        List<Member> members = read();
        int size = members.size();
        int[] forDelete = new int[size];

        int count_wr = 0;
        switch (index) {
            case 1:/// Удаление по номеру ID.
            {
                for (int i = 0; i < size; i++) {
                    if (members.get(i).equals() .getId() == newMember.getId()) {
                        System.out.println(memberList.get(i).toString());
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 2:/// Удаление по Имени.
            {
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getFname().compareTo(newMember.getFname()) == 0) {
                        System.out.println(memberList.get(i).toString());
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 3:/// Удаление по фамилии.
            {
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getLname().compareTo(newMember.getLname()) == 0) {
                        System.out.println(memberList.get(i).toString());
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 4:/// Удаление по номеру телефона.
            {
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getPhoneNumber().getNum().compareTo(newMember.getPhoneNumber().getNum()) == 0) {
                        System.out.println(memberList.get(i).toString());
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 5:/// Удаление по типу телефона.
            {
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getPhoneNumber().getType() == newMember.getPhoneNumber().getType()) {
                        System.out.println(memberList.get(i).toString());
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            case 6:/// Удаление по типу телефона.
            {
                for (int i = 0; i < memberList.size(); i++) {
                    if (memberList.get(i).getRelative().compareTo(newMember.getRelative()) == 0) {
                        System.out.println(memberList.get(i).toString());
                        forDelete[count_wr] = i;
                        count_wr++;
                    }
                }
                break;
            }
            //ShellsSortNumber(memberList, baseSize, 1);

        }
        if (count_wr == 0) {
            System.out.println(" Нет записей ...");
        } else {
            System.out.format("Найдено записей: %d\n", count_wr);
        }
        //-----------------------------------------------------------
        if (count_wr != 0) {
            if (chose())/// если ДА - Удаляем
            {
                int baseSize = memberList.size();
                for (int i = count_wr - 1; i >= 0; i--) {
                    if (forDelete[i] != baseSize - 1) {
                        memberList.set(forDelete[i],memberList.get(baseSize - 1));
                        //memberList[forDelete[i]] = memberList[baseSize - 1];
                    }
                    memberList.remove(baseSize - 1);
                    baseSize--;
                }
                System.out.println(" Данные удалены.");
            } else System.out.println(" Удаление отменено.");
        } else {
            System.out.println(" Нечего удалять.\n");
        }



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
