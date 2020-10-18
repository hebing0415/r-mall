package com.robot.api.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author robot
 * @date 2020/1/7 10:44
 */
public class SHAUtil {

    public static final String KEY_SHA = "SHA-256";
    public static final String ra = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String getSHA256(String str) {
        MessageDigest messageDigest;
        str = str + getSalt();
        String encodeStr = "";
        try {
            messageDigest = MessageDigest.getInstance(KEY_SHA);
            messageDigest.update(str.getBytes(StandardCharsets.UTF_8));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制
     * @param bytes
     * @return
     */
    private static String byte2Hex(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                // 1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    /**
     * 加盐
     * @return
     */
    private static String getSalt() {
        SecureRandom random = new SecureRandom();
        int length = random.nextInt(5) + 8;
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = ra.charAt(random.nextInt(ra.length()));
        }
        return new String(text);
    }

    public static void main(String[] args) {
        System.out.println(getSHA256("123456"));
    }
}
