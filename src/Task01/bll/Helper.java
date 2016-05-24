package Task01.bll;

import java.lang.reflect.Field;

/**
 * Воспомогательный класс
 */
public final class Helper<T> {
    public final Object getValue(Class<T> type, String str) {
        if (type == int.class) {
            return Integer.parseInt(str.trim());
        } else
            return str.trim();
    }
}