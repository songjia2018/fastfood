package com.jonas.fastfood.common.utils;

import java.security.MessageDigest;


public class MD5Util {

    /**
     * MD5加密字符串
     */
    public static String getMD5str(String str) {
        try {
            MessageDigest cmd = MessageDigest.getInstance("MD5");
            cmd.update(str.getBytes("utf-8"));
            byte b[] = cmd.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];

                if (i < 0) {
                    i += 256;
                }

                if (i < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(i));
            }

            return buf.toString().toUpperCase();
        } catch (Exception ex) {
            return "";
        }
    }
}
