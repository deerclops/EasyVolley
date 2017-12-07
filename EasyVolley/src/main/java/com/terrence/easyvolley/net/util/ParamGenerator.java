package com.terrence.easyvolley.net.util;

import com.terrence.easyvolley.annotation.Encrypt;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * Created by DarkSouls on 2017/12/23.
 */

public class ParamGenerator {

    public static void obj2Map(Object obj, HashMap<String, String> encryptedMap, HashMap<String, String> notEncryptedMap) {
        if (encryptedMap == null) {
            encryptedMap = new HashMap<>();
        }
        if (notEncryptedMap == null) {
            notEncryptedMap = new HashMap<>();
        }
        Field[] fields = obj.getClass().getFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            boolean needEncrypted = true;
            if (field.isAnnotationPresent(Encrypt.class)) {
                Encrypt encrypt = field.getAnnotation(Encrypt.class);
                needEncrypted = encrypt.value();
            }
            String fieldName = field.getName();
            try {
                boolean accessFlag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(obj);
                if (o != null) {
                    if (needEncrypted) {
                        encryptedMap.put(fieldName, o.toString());
                    } else {
                        notEncryptedMap.put(fieldName, o.toString());
                    }
                }
                field.setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }
}
