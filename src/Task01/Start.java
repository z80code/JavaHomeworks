package Task01;

import Task01.bll.InMemory;
import Task01.model.Member;
import Task01.model.Phone;
import Task01.ui.Enter;
import Task01.ui.IOconsole;
import Task01.ui.Menu;

import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        InMemory Members = new InMemory();

        //закоментируйте, чтобы создать пустую базу
        // начало блока автоввода
        Member Number = new Member(0, "Иван", "Иванов", new Phone("697-45-87", 1), "друг");
        Members.add(Number);
        Member Number1 = new Member(1, "Сергей", "Петров", new Phone("622-02-13", 2), "знакомый");
        Members.add(Number1);
        Member Number2 = new Member(2, "Петр", "Сидоров", new Phone("648-56-23", 3), "враг");
        Members.add(Number2);
        Member Number3 = new Member(3, "Ольга", "Ященко", new Phone("613-82-12", 4), "коллега");
        Members.add(Number3);
        Member Number4 = new Member(4, "Зина", "Петрова", new Phone("652-34-14", 2), "враг");
        Members.add(Number4);
        // конец блока автоввода

        int mode;
        Menu menu = new Menu();
        do {
            mode = menu.mainMenu(Members.count());
            switch (mode) {
                case 1: {// добавление
                    Members.add(Enter.Member(Members.getSelector()));
                    break;
                }
                case 2: {// вывод всего
                    List<String> list = Members.toStringAll();
                    for (String line: list) {
                        IOconsole.show(line);
                    }
                    break;
                }
                case 3: {// поиск
                    if (Members.count() <= 0) {
                        System.out.println(" В пустой базе нечего искать.\n");
                        break;
                    }

                    int index = menu.findMenu();
                    if (index == 0) break;

                    Object object = Enter.getValue(index);
                    List<Member> listM = Members.find(index, object);
                    IOconsole.showList(listM);
                    break;
                }
                case 4: {// удаление
                    if (Members.count() <= 0) {
                        System.out.println(" В пустой базе нечего удалять.\n");
                        break;
                    }
                    int index = menu.findMenu();
                    if (index == 0) break;

                    Object object = Enter.getValue(index);
                    List<Member> listM = Members.find(index, object);
                    IOconsole.showList(listM);

                    if (listM.size() == 0){
                        IOconsole.show("Нечего удалять.");
                        break;
                    }
                    Members.delete(index, listM);
                    break;
                }
//                case 5: {// сортировка
//                    if (memberList.getCount() <= 0) {
//                        System.out.println(" В пустой базе нечего сортировать.\n");
//                        break;
//                    }
//                    int index = menu.sortMenu();
//                    if (index == 0) break;
//                    memberList.sort(index);
//                    break;
//                }
//                case 6: {// изменение
//                    if (memberList.getCount() <= 0) {
//                        System.out.println(" В пустой базе нечего изменять.\n");
//                        break;
//                    }
//                    int index = menu.changeMenu();// выбор атрибута для смены
//                    if (index == 0) break;
//                    memberList.showAll();
//                    int select;
//                    do {
//                        select = menu.selectMenu();// выбор ID для смены
//                        select = memberList.idExist(select);
//                    } while (select < 0);
//                    memberList.enterNewMemberAttribute(index, select, false);
//                    break;
//                }
            }
        } while (mode != 0);
    }
}