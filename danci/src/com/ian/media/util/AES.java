package com.ian.media.util;

/**
 * Created by 栋 on 2014/5/8.
 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.*;
import javax.crypto.spec.*;

/**
 *
 * @author wchun
 *
 * AES128 算法，加密模式为ECB，填充模式为 pkcs7（实际就是pkcs5）
 *
 *
 */
public class AES {

    static final String algorithmStr="AES/ECB/PKCS5Padding";

    static private KeyGenerator keyGen;

    static private Cipher cipher;

    static boolean isInited=false;


    /**
     * 加密
     *
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static byte[] encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(byteContent);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //初始化
    static private void init()
    {

        //初始化keyGen
        try {
            keyGen=KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        keyGen.init(128);

        //初始化cipher
        try {
            cipher=Cipher.getInstance(algorithmStr);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        isInited=true;
    }

    public static byte[] GenKey()
    {
        if(!isInited)//如果没有初始化过,则初始化
        {
            init();
        }
        return keyGen.generateKey().getEncoded();
    }

    public static byte[] Encrypt(byte[] content,byte[] keyBytes)
    {
        byte[] encryptedText=null;

        if(!isInited)//为初始化
        {
            init();
        }

        Key key=new SecretKeySpec(keyBytes,"AES");

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            encryptedText=cipher.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return encryptedText;
    }

    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128, new SecureRandom(password.getBytes()));
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    //解密为byte[]
    public static byte[] DecryptToBytes(byte[] content,byte[] keyBytes)
    {
        byte[] originBytes=null;
        if(!isInited)
        {
            init();
        }

        Key key=new SecretKeySpec(keyBytes,"AES");

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //解密
        try {
            originBytes=cipher.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BadPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return originBytes;
    }
//
//    public static void main(String[] args){
////        AES aes = new AES();
////        System.out.println("n=%E7%8E%8B%E6%A0%8B&u=oezFhuGD7Ec7mj9l-q7aabfTFHaU&t=2014-05-12-14-44-37".length());
//
////        byte[]  a = encrypt("n=%E7%8E%8B%E6%A0%8B&u=oezFhuGD7Ec7mj9l-q7aabfTFHaU&t=2014-05-12-14-44-37&l=73","sangfor");
////        byte[] url = decrypt(a,"sangfor");
//
////        System.out.println(parseByte2HexStr(a)+"长度:"+parseByte2HexStr(a).length());
////        System.out.println(new String(url));
////            System.out.print(url.length);
////            System.out.print("c29cd80c3cafe7d3a0cdb72de859a4d2ecaa85ce4f6bfdc967c977f8a6580c6ea74c554daab167b9766131abf73a58f1b290dcb6214c022c943d96ac67f10bbea728bcbc5f299b4df6cdb3e6d60b02d1".length());
////        System.out.println("2890830f164a449c7e7af8c4f399e33c98d75493e3f18f23c349f04281b930ff5eaa89d088c70a6d7d215eb7be3fc2a81ae73c9593c17a8d9f78fe4b9156ce80".length());
//
////        System.out.println("c29cd80c3cafe7d3a0cdb72de859a4d2ecaa85ce4f6bfdc967c977f8a6580c6ea74c554daab167b9766131abf73a58f110632e12121de81f8db857a8dfbab9af8b1d9fb7fbb4afe4bc56c295b0f26e0e".length());
//
////        String res = "9cd68470a326f63dd54cc094a9c7653975efcf1625424529fcea861dcadf08aaaf1be71226088c188b7349a2b9245c006d0ae88110fe82b20ce20c7c09ac9fb0214df595ffb74a149863ad796e8f1654";
////        byte[] b = parseHexStr2Byte(res);
////        byte[] u = decrypt(b,"sangfor");
////        System.out.println(new String(u));
//        System.out.println( WeixinUtil.sendGet("http://115.28.21.49:8085/aes/index.php?nickname=王栋&openid=oezFhuJKmV1dk_P4n5_j8YQAWXtc",""));
//    }

    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toLowerCase());
        }
        return sb.toString();
    }




    /**将16进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}