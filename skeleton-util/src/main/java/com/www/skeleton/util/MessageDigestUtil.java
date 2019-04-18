package com.www.skeleton.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ljy
 * @date 2019/4/15 17:39
 */
public class MessageDigestUtil {
    public static byte[] sha256(String content){
        MessageDigest sha256 = null;//256/8=32字节
        try {
            sha256 = MessageDigest.getInstance("sha-256");
            sha256.update(content.getBytes("utf-8"));
            return sha256.digest();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
