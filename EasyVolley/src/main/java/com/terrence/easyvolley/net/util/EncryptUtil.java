package com.terrence.easyvolley.net.util;

import com.terrence.easyvolley.app.Rop;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by DarkSouls on 2017/12/23.
 */

public class EncryptUtil {

    public static String createSignParam(Map<String, String> paramsMap) {
        if (paramsMap == null)
            return null;
        StringBuilder sb = new StringBuilder(Rop.getAppSecret());
        List<String> paramNames = new ArrayList<>(paramsMap.size());
        Iterator<Map.Entry<String, String>> iterator = paramsMap.entrySet().iterator();
        Map.Entry<String, String> entry;
        while (iterator.hasNext()) {
            entry = iterator.next();
            if (entry.getValue() != null)
                paramNames.add(entry.getKey());
        }
        Collections.sort(paramNames);

        for (String paramName : paramNames) {
            if (paramsMap.get(paramName) != null)
                sb.append(paramName).append(paramsMap.get(paramName));
        }
        sb.append(Rop.getAppSecret());

        byte[] sha1Digest = Digest(sb.toString());
        return getHexString(sha1Digest).toUpperCase();
    }

    private static byte[] Digest(String data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            return md.digest(data.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
//            throw new Exception("NoSuchAlgorithmException", e);
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
//            throw new Exception("UnsupportedEncodingException", e);
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getHexString(byte[] bytes) {
        if (bytes == null)
            return "";
        StringBuilder ret = new StringBuilder(bytes.length << 1);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(Character.forDigit((bytes[i] >> 4) & 0xf, 16));
            ret.append(Character.forDigit(bytes[i] & 0xf, 16));
        }
        return ret.toString();
    }
}
