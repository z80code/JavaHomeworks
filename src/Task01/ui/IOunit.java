package Task01.ui;

import java.util.Scanner;

/**
 * Created by Scorpion on 25.03.2016.
 */
public final class IOunit {
    private static Scanner scan;

    IOunit(){
        scan = new Scanner(System.in);
    }

    public static int scannner(){
        int result=0;
        if (scan.hasNextInt())
            result = scan.nextInt();
        //else
        return result;
    }



}
