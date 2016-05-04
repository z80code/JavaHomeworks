package Task01.bll;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSVReader<T> extends BufferedReader {
    private final String filename = "data.csv";
    Reader reader;

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
        if ((string = readLine()) == null) return null;
        String[] str = string.split(",");
        int num = 0;
        Class cl = obj.getClass();

        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                    field.set(obj, gelValue(field, str[num++]));
                } else {
                    Class c = field.get(obj).getClass();
                    Field[] subFields = c.getDeclaredFields();
                    Object ob = null;
                    ob = c.newInstance();
                    for (Field subfield : subFields) {
                        subfield.setAccessible(true);
                        subfield.set(ob, gelValue(subfield, str[num++]));
                    }
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
    private Object gelValue(Field field, String str) throws IllegalAccessException, InstantiationException {

        if (field.getType() == int.class) {
            return Integer.parseInt(str.trim());
        } else
            return str.trim();
    }

}

