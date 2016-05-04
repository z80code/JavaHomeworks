package Task01.bll;

import Task01.model.Phone;

import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

/*
* Декоратор класса BufferedWriter
* */
public final class CSVWriter<T> extends BufferedWriter {
    private final String filename = "data.csv";
    Writer writer;

    public CSVWriter(Writer out) {
        super(out);
        this.writer = out;
    }

    public CSVWriter(Writer out, int sz) {
        super(out, sz);
        this.writer = out;
    }

    /*
    * Добавленный метод в BufferedWriter
    * */
    public Writer appendObject(T obj) throws IOException {
        List<String> value = getObjectToStr(obj);
        String s = "";
        for (String member : value) {
            s += member + ", ";
        }
        s = s.substring(0, s.length() - 2);
        write(s);
        return this;
    }

    /*
     * Воспомогательный рекурсивный метод класса
     */
    private List<String> getObjectToStr(Object obj) {
        List<String> result = new ArrayList<>();
        Class cl = obj.getClass();
        Field[] fields = cl.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.getType().isPrimitive() || field.getType().equals(String.class)) {
                    result.add(field.get(obj).toString());
                } else {
                    Object o = field.get(obj);
                    List<String> subResult = getObjectToStr(o); //  рекурсия!!!
                    result.addAll(subResult);
                }
                }catch(IllegalAccessException e){
                    e.printStackTrace();
                }
            }
            return result;
        }
    }
