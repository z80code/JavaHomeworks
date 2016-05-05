package Task01.bll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Iterator;

public class CSVReader<T> extends BufferedReader implements Iterator {
    private final String filename = "data.csv";
    private Reader reader;
    private String[] str;
    private int index = 0;

    public CSVReader(Reader in, int sz) {
        super(in, sz);
        this.reader = in;
    }

    public CSVReader(Reader in) {
        super(in);
        this.reader = in;
    }

    public T readObject(T obj) throws IOException {
        String string;
        index = 0;
        if ((string = readLine()) == null) return null;
        str = string.split(",");
        return (T)getObject(obj);
    }

       private Object getObject(Object obj)
    {
        Class cl = obj.getClass();
        Field[] fields = cl.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                    field.set(obj, getValue(field, next()));
                } else {
                    Class c = field.get(obj).getClass();
                    Object ob = getObject(c.newInstance());
                    field.set(obj, ob);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    /*
    * Воспомогательный метод класса
    */
    private Object getValue(Field field, String str) throws IllegalAccessException, InstantiationException {

        if (field.getType() == int.class) {
            return Integer.parseInt(str.trim());
        } else
            return str.trim();
    }

    @Override
    public boolean hasNext() {
        return index < str.length;
    }

    @Override
    public String next() {
        return str[index++];
    }
}

