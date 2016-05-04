package test;

import java.io.Closeable;
import java.io.IOException;

public class tests {
    public static void main(String[] args) {

        a s = new a("ERWERERwer");
        System.out.println(s);
        a s2 = new a(15489);
        System.out.println(s2);
        a2 s3 = new a2(15489,"ererwer");
        System.out.println(s3);
        a s4 = new a(new perec(12,"Коля"));
        System.out.println(s4);
        System.gc();
    }
}

class perec
{
    int value;
    String s;

    public perec() {
    }

    public perec(int value, String s) {
        this.value = value;
        this.s = s;
    }

    @Override
    public String toString() {
        return "perec{" +
                "value=" + value +
                ", s='" + s + '\'' +
                '}';
    }
}


class a<T> implements Closeable {
    T s;
    public a(T s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return  s.toString();
    }

    @Override
    public void close() throws IOException {
        System.out.println("Конец.");
    }
}

class a2<T, U> extends a {
    U u;
    public a2(T s, U u) {
        super(s);
        this.u = u;
    }

    @Override
    public String toString() {
        return u.toString() + " "+ super.toString();
    }
}



