//package Task01.bll;
//
//import Task01.model.Member;
//import Task01.model.Phone;
//
//import java.util.ArrayList;
//import java.util.Scanner;
//
///**
// * Created by Scorpion on 23.03.2016.
// */
//// TODO очень сложный класс, отмечу основыные недостатки
//// 1. используется консольный вывод, тем самым привязывает класс к консоли, не позволяет повторно использовать код
//// 2. Написано в процедурном стиле,  и раздробление ответственности, т.е. этот класс занимается слишком многим
//    // и ввоидт с консоли и хранит записи и выполняет операции, нужно разделять по логике, инече это очень сложно
//    // потдерживать и исправлять
//// 3. в методах очень много не очевидных параметров, затрудняет использование  (int index, int select, boolean idSet)
//    // типичная ошибка, можно мерепутать аргументы, лучше, по возможности, использовать перечисления
//// 4. Очень размыт интерфейс класса (подробнее на занятии)
//// 5. Очень много дублирования код:
////do {
////        try {
////        System.out.print(" Имя: ");
////        Scanner scan = new Scanner(System.in);
////        newMember.setFname(scan.next());
////        rezult = 0;
////        } catch (Exception e) {
////        System.out.println(" Строка содержит недопустимые символы.");
////        rezult = 1;
////        }
////        } while (rezult > 0);
////
////for (int i = 0; i < memberList.size(); i++) {
////        if (memberList.get(i).phoneNumber.getType() == newMember.phoneNumber.getType()) {
////        System.out.println(memberList.get(i).toString());
////        forDelete[count_wr] = i;
////        count_wr++;
////        }
////        }
//// подробнее об этом на занятии
//// TODO сортировку не нужно реализовывать руками (только для изучения полезно)
//class Members {
//
//    private ArrayList<Member> memberList = new ArrayList();
//    private int getSelector() {
//        int max = 0;
//        for (int i = 0; i < this.getCount(); i++) {
//            max = (memberList.get(i).getId() > max) ? memberList.get(i).getId() : max;
//        }
//        return max + 1;
//    }
//
//    /*
//    *   поиск по атрибуту (указывается индексом),
//    *   заданному в поле "newMember"
//    */
//    public int getCount() {
//        if (memberList != null) return memberList.size();
//        else return 0;
//    }
//
//    /*
//        public void ShowMember(int index) {
//            memberList[index].showMember();
//        }
//    */
//    public Member enterNewMemberAttribute(int index, int select, boolean idSet) {
//        Member newMember;
//        if (select < 0) newMember = new Member();
//        else newMember = this.memberList.get(select);
//        Phone phone = new Phone();
//        System.out.println("\n Введите следующие данные:\n");
//        int rezult = 1;
//        if ((index == 1) || (index == 0)) {
//            do {
//                try {
//                    System.out.print(" Номер ID: ");
//                    if (idSet) {
//                        Scanner scan = new Scanner(System.in);
//                        newMember.setId(scan.nextInt());
//                    }
//                    else {
//                        System.out.println(getSelector());
//                        newMember.setId(getSelector());
//                    }
//
//                    rezult = 0;
//                } catch (Exception e) {
//                    System.out.println(" Это целочисленное значение!");
//                    rezult = 1;
//                }
//            } while (rezult > 0);
//        }
//        if ((index == 2) || (index == 0)) {
//            do {
//                try {
//                    System.out.print(" Имя: ");
//                    Scanner scan = new Scanner(System.in);
//                    newMember.setFname(scan.next());
//                    rezult = 0;
//                } catch (Exception e) {
//                    System.out.println(" Строка содержит недопустимые символы.");
//                    rezult = 1;
//                }
//            } while (rezult > 0);
//        }
//        if ((index == 3) || (index == 0)) {
//            do {
//                try {
//                    System.out.print(" Фамилию: ");
//                    Scanner scan = new Scanner(System.in);
//                    newMember.setLname(scan.next());
//                    rezult = 0;
//                } catch (Exception e) {
//                    System.out.println(" Строка содержит недопустимые символы.");
//                    rezult = 1;
//                }
//            } while (rezult > 0);
//        }
//        if ((index == 4) || (index == 0)) {
//            do {
//                try {
//                    System.out.print(" Телефон: ");
//                    Scanner scan = new Scanner(System.in);
//                    phone.setNum(scan.next());
//                    rezult = 0;
//                } catch (Exception e) {
//                    System.out.println(" Строка содержит недопустимые символы.");
//                    rezult = 1;
//                }
//            } while (rezult > 0);
//        }
//        if ((index == 5) || (index == 0)) {
//            do {
//                System.out.print(" Тип номера: \n");
//                String[] list = {"Not set", "домашний", "рабочий", "мобильный", "velcom", "mtc", "life"};
//                int n = 0;
//                for (String temp : list
//                        ) {
//                    System.out.format(" %d - %s\n", n, temp);
//                    n++;
//                }
//                try {
//                    System.out.print(" Ваш выбор: ");
//                    Scanner scan = new Scanner(System.in);
//                    n = scan.nextInt();
//                    if ((n >= 0) && (n < list.length)) {
//                        phone.setType(n);
//                        rezult = 0;
//                    } else System.out.println(" Нет такого значения.");
//                } catch (Exception e) {
//                    System.out.println(" Введите число в указанном диапазоне.");
//                    rezult = 1;
//                }
//            } while (rezult > 0);
//        }
//        if ((index == 0) || (index == 4) || (index == 5)) newMember.setPhoneNumber(phone);
//        if ((index == 6) || (index == 0)) {
//            do {
//                try {
//                    System.out.print(" Он(а) Вам: ");
//                    Scanner scan = new Scanner(System.in);
//                    newMember.setRelative(scan.next());
//                    rezult = 0;
//                } catch (Exception e) {
//                    System.out.println(" Строка содержит недопустимые символы.");
//                    rezult = 1;
//                }
//            } while (rezult > 0);
//        }
//        return newMember;
//    }
//
//    /*
//    *   поиск по атрибуту (указывается индексом),
//    *   заданному в поле "newMember"
//    */
//    public void find(Member newMember, int index) {
//        if (index > 0) {
//            int count_wr = 0;
//            switch (index) {
//                case 1:/// Поиск по номеру id.
//                {
//                    System.out.print(" Поиск по номеру ID.\n");
//                    for (int i = 0; i < memberList.size(); i++) {
//                        if (memberList.get(i).getId() == newMember.getId()) {
//                            System.out.println(memberList.get(i).toString());
//                            count_wr++;
//                        }
//                    }
//                    break;
//                }
//                case 2:/// Поиск по имени.
//                {
//                    System.out.print(" Поиск по имени.\n");
//                    for (int i = 0; i < memberList.size(); i++) {
//                        if (memberList.get(i).getFname().compareTo(newMember.getFname()) == 0) {
//                            System.out.println(memberList.get(i).toString());
//                            count_wr++;
//                        }
//                    }
//                    break;
//                }
//                case 3:/// Поиск по фамилии.
//                {
//                    System.out.print(" Поиск по фамилии.\n");
//                    for (int i = 0; i < memberList.size(); i++) {
//                        if (memberList.get(i).getLname().compareTo(newMember.getLname()) == 0) {
//                            System.out.println(memberList.get(i).toString());
//                            count_wr++;
//                        }
//                    }
//                    break;
//                }
//
//                case 4:/// Поиск по номеру телефона.
//                {
//                    System.out.print(" Поиск по номеру телефона.\n");
//                    for (int i = 0; i < memberList.size(); i++) {
//                        if (memberList.get(i).getPhoneNumber().getNum().compareTo(newMember.getPhoneNumber().getNum()) == 0) {
//                            System.out.println(memberList.get(i).toString());
//                            count_wr++;
//                        }
//                    }
//                    break;
//                }
//                case 5:/// Поиск по типу номера.
//                    System.out.print(" Поиск по типу номера.\n");
//                    for (int i = 0; i < memberList.size(); i++) {
//                        if (memberList.get(i).getPhoneNumber().getType() == newMember.getPhoneNumber().getType()) {
//                            System.out.println(memberList.get(i).toString());
//                            count_wr++;
//                        }
//                    }
//                    break;
//
//                case 6:/// Поиск по отношениям.
//                {
//                    System.out.print(" Поиск по  отношениям.\n");
//                    for (int i = 0; i < memberList.size(); i++) {
//                        if (memberList.get(i).getRelative().compareTo(newMember.getRelative()) == 0) {
//                            System.out.println(memberList.get(i).toString());
//                            count_wr++;
//                        }
//                    }
//                    break;
//                }
//            }
//            if (count_wr == 0) {
//                System.out.println(" Нет записей ...");
//            } else {
//                System.out.format(" Найдено записей: %d\n\n", count_wr);
//            }
//
//        }
//
//    }
//
//    /*
//    *   Добавление новой персоны
//    *   заданной в поле "newMember"
//    */
//    public void add(Member newMember) {
//        newMember.setId(getSelector());
//        memberList.add(newMember);
//        //
//        System.out.println(" Добавлено!");
//    }
//
//    /*
//    *   Удаление персоны по атрибуту (указывается индексом),
//    *   заданному в поле "newMember"
//    */
//    public void delete(Member newMember, int index) {
//
//        int[] forDelete = new int[memberList.size()];
//        int count_wr = 0;
//        switch (index) {
//            case 1:/// Удаление по номеру ID.
//            {
//                for (int i = 0; i < memberList.size(); i++) {
//                    if (memberList.get(i).getId() == newMember.getId()) {
//                        System.out.println(memberList.get(i).toString());
//                        forDelete[count_wr] = i;
//                        count_wr++;
//                    }
//                }
//                break;
//            }
//            case 2:/// Удаление по Имени.
//            {
//                for (int i = 0; i < memberList.size(); i++) {
//                    if (memberList.get(i).getFname().compareTo(newMember.getFname()) == 0) {
//                        System.out.println(memberList.get(i).toString());
//                        forDelete[count_wr] = i;
//                        count_wr++;
//                    }
//                }
//                break;
//            }
//            case 3:/// Удаление по фамилии.
//            {
//                for (int i = 0; i < memberList.size(); i++) {
//                    if (memberList.get(i).getLname().compareTo(newMember.getLname()) == 0) {
//                        System.out.println(memberList.get(i).toString());
//                        forDelete[count_wr] = i;
//                        count_wr++;
//                    }
//                }
//                break;
//            }
//            case 4:/// Удаление по номеру телефона.
//            {
//                for (int i = 0; i < memberList.size(); i++) {
//                    if (memberList.get(i).getPhoneNumber().getNum().compareTo(newMember.getPhoneNumber().getNum()) == 0) {
//                        System.out.println(memberList.get(i).toString());
//                        forDelete[count_wr] = i;
//                        count_wr++;
//                    }
//                }
//                break;
//            }
//            case 5:/// Удаление по типу телефона.
//            {
//                for (int i = 0; i < memberList.size(); i++) {
//                    if (memberList.get(i).getPhoneNumber().getType() == newMember.getPhoneNumber().getType()) {
//                        System.out.println(memberList.get(i).toString());
//                        forDelete[count_wr] = i;
//                        count_wr++;
//                    }
//                }
//                break;
//            }
//            case 6:/// Удаление по типу телефона.
//            {
//                for (int i = 0; i < memberList.size(); i++) {
//                    if (memberList.get(i).getRelative().compareTo(newMember.getRelative()) == 0) {
//                        System.out.println(memberList.get(i).toString());
//                        forDelete[count_wr] = i;
//                        count_wr++;
//                    }
//                }
//                break;
//            }
//            //ShellsSortNumber(memberList, baseSize, 1);
//
//        }
//        if (count_wr == 0) {
//            System.out.println(" Нет записей ...");
//        } else {
//            System.out.format("Найдено записей: %d\n", count_wr);
//        }
//        //-----------------------------------------------------------
//        if (count_wr != 0) {
//            if (chose())/// если ДА - Удаляем
//            {
//                int baseSize = memberList.size();
//                for (int i = count_wr - 1; i >= 0; i--) {
//                    if (forDelete[i] != baseSize - 1) {
//                        memberList.set(forDelete[i],memberList.get(baseSize - 1));
//                        //memberList[forDelete[i]] = memberList[baseSize - 1];
//                    }
//                    memberList.remove(baseSize - 1);
//                    baseSize--;
//                }
//                System.out.println(" Данные удалены.");
//            } else System.out.println(" Удаление отменено.");
//        } else {
//            System.out.println(" Нечего удалять.\n");
//        }
//    }
//
//    public void sort(int index) {
//        int i, j, k, N = memberList.size();
//        Member t;
//        for (k = N / 2; k > 0; k /= 2)
//            for (i = k; i < N; i += 1) {//с середины и до конца
//                t = memberList.get(i);
//                for (j = i; j >= k; j -= k) {
//                    if (index == 1) {
//                        if (t.getId() < memberList.get(j - k).getId()) {
//                            memberList.set(j, memberList.get(j - k));
//                        } else
//                            break;
//                    }
//                    if (index == 2) {
//                        if (t.getFname().compareTo(memberList.get(j - k).getFname()) < 0) {
//
//                            memberList.set(j, memberList.get(j - k));
//                        } else
//                            break;
//                    }
//                    if (index == 3) {
//                        if (t.getLname().compareTo(memberList.get(j - k).getLname()) < 0) {
//
//                            memberList.set(j, memberList.get(j - k));
//                        } else
//                            break;
//                    }
//                    if (index == 4) {
//                        if (t.getPhoneNumber().getNum().compareTo(memberList.get(j - k).getPhoneNumber().getNum()) < 0) {
//                            memberList.set(j, memberList.get(j - k));
//                        } else
//                            break;
//                    }
//                    if (index == 5) {
//                        if (t.getPhoneNumber().getType() < memberList.get(j - k).getPhoneNumber().getType()) {
//
//                            memberList.set(j, memberList.get(j - k));
//                        } else
//                            break;
//                    }
//                    if (index == 6) {
//                        if (t.getRelative().compareTo(memberList.get(j - k).getRelative()) < 0) {
//
//                            memberList.set(j, memberList.get(j - k));
//                        } else
//                            break;
//                    }
//                }
//                memberList.set(j, t);
//            }
//        System.out.println(" Отсортировано.\n");
//    }
//
//    public void showAll() {
//        if (this.getCount() > 0) {
//            for (int i = 0; i < this.getCount(); i++) {
//                System.out.println(memberList.get(i).toString());
//            }
//        } else {
//            System.out.println(" Нет записей ...\n");
//        }
//    }
//
//    public int idExist(int id) {
//        for (int i = 0; i < this.getCount(); i++) {
//            if (this.memberList.get(i).getId() == id) return i;
//        }
//        System.out.println("Нет такого ID. Повторите ввод ID.");
//        return -1;
//    }
//
//    /*
//    *   Метод предоставления выбора
//    *   "да" или "нет"
//    */
//    private boolean chose() {
//        do {
//            System.out.println(" Удалить выбранное (д/н или y/n) -> ");
//            try {
//                Scanner scan = new Scanner(System.in);
//                String value = scan.next();
//                return (value.compareTo("y") == 0 || value.compareTo("д") == 0 || value.compareTo("Y") == 0 || value.compareTo("Д") == 0);
//            } catch (Exception e) {
//                System.out.println("Выберите утверждение.");
//            }
//        } while (true);
//    }
//}
