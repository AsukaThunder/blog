package com.ford.blog.encrypt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5EncryptUtils {

    public static String  md5Encrypt(String string){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] input = string.getBytes();
            byte[] buff = md.digest(input);
            return HexUtils.bytes2Hex(buff);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
