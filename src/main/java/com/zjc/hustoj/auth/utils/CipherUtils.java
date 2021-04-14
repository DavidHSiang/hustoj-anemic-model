package com.zjc.hustoj.auth.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

/**
 * @author david
 */
public class CipherUtils {

    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();

    public static boolean equals(String plainText,String cipherText){
        byte[] decode = decoder.decode(cipherText);
        byte[] saltByte =  Arrays.copyOfRange(decode,decode.length - 4 ,decode.length );
        String salt = new String(saltByte);

        byte[] sha1Bytes = Arrays.copyOfRange(decode,0 ,decode.length - 4 );

        byte[] plainTextSha1 = DigestUtils.sha1(plainText + salt);

        return Arrays.equals(plainTextSha1,sha1Bytes);
    }

    public static String getCipherText(String plainText){
        /**
         * 	if (!$md5ed) $password=md5($password);
         * 	$salt = sha1(rand());
         * 	$salt = substr($salt, 0, 4);
         * 	$hash = base64_encode( sha1($password . $salt, true) . $salt );
         * 	return $hash;
         */

        String salt = DigestUtils.sha1Hex(UUID.randomUUID().toString());
        salt = salt.substring(0, 4);
        byte[] plainTextSha1 = DigestUtils.sha1(plainText + salt);
        byte[] saltBytes = salt.getBytes();
        byte[] bytes = byteMerger(plainTextSha1, saltBytes);
        return encoder.encodeToString(bytes);
    }

    public static byte[] byteMerger(byte[] bt1, byte[] bt2){
        byte[] bt3 = new byte[bt1.length + bt2.length];
        System.arraycopy(bt1, 0, bt3, 0, bt1.length);
        System.arraycopy(bt2, 0, bt3, bt1.length, bt2.length);
        return bt3;
    }

    public static String getCipherTextWithMD5(String password) {
        password = MD5Util.MD5Encode(password,"UTF-8");
        return getCipherText(password);
    }

    public static boolean equalsWithMD5(String plainText,String cipherText){
        plainText = MD5Util.MD5Encode(plainText,"UTF-8");
        return equals(plainText, cipherText);
    }
}
