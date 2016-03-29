package Task01.ui;

import Task01.model.Member;
import Task01.model.Phone;

import java.awt.*;
import java.util.*;
import java.util.List;

public final class IOconsole {

    private static Scanner scan = new Scanner(System.in);;

    public static int scannerInt(){
        int result =-1;
        do{
            if (scan.hasNextInt()){
                result = scan.nextInt();
            }
            else{
                show(Message.NotNumber);
            }
        } while (result==-1);

//        while (scan.hasNext()) {
//            scan.next();
//        }
        return result;
    }

    public static String scannerStr(){
        String result="";
        do{
            if (scan.hasNext()){
                result = scan.next();
            }
            else{
                show(Message.NotNumber);
            }
        } while (result=="");
        return result;
    }

    public static void show(String text){
        System.out.println(text);
    }
    public static void show(int text){
        System.out.println(text);
    }

    public static void showList(List<Member> list){
        for (Member member: list) {
            System.out.println(member.toString());
        }
        System.out.format("Найдено: %d", list.size());
    }
}
