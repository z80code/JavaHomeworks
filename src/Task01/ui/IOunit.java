package Task01.ui;

import Task01.model.Member;
import Task01.model.Phone;

import java.util.Scanner;

public final class IOunit {

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

    public static void show(String text){
        System.out.println(text);
    }

}
