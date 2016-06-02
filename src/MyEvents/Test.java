package MyEvents;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

    Math adc = new Math(1,5);
        adc.addMyListener(new MyListener() {
            @Override
            public void OnListener() {
                System.out.println("Сработало событие...");
            }
        });


       System.out.println(adc.getAdd());


    }
}

interface MyListener{
    public void OnListener();
}

class Math
{
    private int a;
    private int b;

    public MyListener listener;

    public void addMyListener(MyListener _listener){
        listener = _listener;
    }

    public Math(int _a, int _b)
    {
        a=_a;
        b=_b;
    }

    public int getAdd()
    {
        listener.OnListener();
        return  a+b;
    }

    public int getSub()
    {
        return  a-b;
    }

    public int getMul()
    {
        return  a*b;
    }

    public int getDiv()
    {
        return  a/b;
    }

}