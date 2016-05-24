package Task01.bll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Iterator;

public class CSVReader<T extends Class> extends BufferedReader implements Iterator {
    private final String filename = "data.csv";
    private BufferedReader reader;
    private String[] str;
    private int index = 0;

    public CSVReader(BufferedReader in, int sz) {
        super(in, sz);
        this.reader = in;
    }

    public CSVReader(BufferedReader in) {
        super(in);
        this.reader = in;
    }

    public Object readObject(T _class) throws IOException {
        String string;
        index = 0;
        if ((string = readLine()) == null) return null;
        str = string.split(",");
        Object result = null;
        try {
            result = getObject(_class);
        } catch (InstantiationException e) {
            new RuntimeException("Creating object error in Reflection.", e);
        } catch (IllegalAccessException e) {
            new RuntimeException("Access denied to field.", e);
        }
        return result;
    }

    /*
     * Воспомогательный рекурсивный метод класса
     */
    private Object getObject(Class _class) throws InstantiationException, IllegalAccessException {
        Object obj = newObject(_class);
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                field.set(obj, getValue(field, next()));
            } else {
                Class cl = field.getType();
                Object ob = getObject(cl);
                field.set(obj, ob);
            }
        }
        return obj;
    }

    private Object newObject(Class _class) throws IllegalAccessException, InstantiationException {
        Object result = null;
        result = _class.newInstance();
        return result;
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