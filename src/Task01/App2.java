package Task01;

public class App2 {
    public static void main(String[] args) {
        Members memberList = new Members();

        //закоментируйте, чтобы создать пустую базу
        // начало блока автоввода
        Member Number = new Member("Иван", "Иванов", new Phone("697-45-87", 1), "друг");
        memberList.add(Number);
        Member Number1 = new Member("Сергей", "Петров", new Phone("622-02-13", 2), "знакомый");
        memberList.add(Number1);
        Member Number2 = new Member("Петр", "Сидоров", new Phone("648-56-23", 3), "враг");
        memberList.add(Number2);
        Member Number3 = new Member("Ольга", "Ященко", new Phone("613-82-12", 4), "коллега");
        memberList.add(Number3);
        Member Number4 = new Member("Зина", "Петрова", new Phone("652-34-14", 2), "враг");
        memberList.add(Number4);
        // конец блока автоввода

        int mode;
        Menu menu = new Menu();
        do {
            mode = menu.mainMenu(memberList.getCount());
            switch (mode) {
                case 1: {// добавление
                    Member newMember = memberList.EnterNewMemberAttribute(0, -1, false);
                    memberList.add(newMember);
                    break;
                }
                case 2: {// вывод всего
                    memberList.showAll();
                    break;
                }
                case 3: {// поиск
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего искать.\n");
                        break;
                    }
                    int index = menu.findMenu();
                    if (index == 0) break;
                    Member newMember = memberList.EnterNewMemberAttribute(index, -1, true);
                    memberList.find(newMember, index);
                    break;
                }
                case 4: {// удаление
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего удалять.\n");
                        break;
                    }
                    int index = menu.findMenu();
                    if (index == 0) break;
                    Member newMember = memberList.EnterNewMemberAttribute(index, -1, true);
                    memberList.delete(newMember, index);
                    break;
                }
                case 5: {// сортировка
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего сортировать.\n");
                        break;
                    }
                    int index = menu.sortMenu();
                    if (index == 0) break;
                    memberList.sort(index);
                    break;
                }
                case 6: {// изменение
                    if (memberList.getCount() <= 0) {
                        System.out.println(" В пустой базе нечего изменять.\n");
                        break;
                    }
                    int index = menu.changeMenu();// выбор атрибута для смены
                    if (index == 0) break;
                    memberList.showAll();
                    int select;
                    do {
                        select = menu.selectMenu();// выбор ID для смены
                        select = memberList.idExist(select);
                    } while (select < 0);
                    memberList.EnterNewMemberAttribute(index, select, false);
                    break;
                }
            }
        } while (mode != 0);
    }
}