package com.robot.api.util;

import com.alibaba.nacos.common.utils.Md5Utils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RSAUtils {

    private final static String RSA_ALGORITHM = "RSA";
    private final static String SIGNATURE_ALGORITHM = "MD5withRSA";
    private final static String RSA_PUBLIC_KEY = "RSAPublicKey";
    private final static String RSA_PRIVATE_KEY = "RSAPrivateKey";


    public static Map<String, Object> getKey() throws NoSuchAlgorithmException {
        // 因为只存公钥和私钥，所以指明Map的长度是2
        Map<String, Object> keyMap = new HashMap<String, Object>(2);

        // 获取RSA算法实例
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA_ALGORITHM);

        // 1024代表密钥二进制位数
        keyPairGen.initialize(1024);

        // 产生KeyPair工厂
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        keyMap.put(RSA_PUBLIC_KEY, publicKey);
        keyMap.put(RSA_PRIVATE_KEY, privateKey);

        return keyMap;
    }

    /**
     * 使用私钥对数据进行加密
     */
    public static byte[] encryptPrivateKey(byte[] binaryData, String privateKey) throws Exception {
        byte[] keyBytes = decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        // 获取RSA算法实例
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(keySpec);

        // 初始化加密器
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, priKey);
        return cipher.doFinal(binaryData);
    }

    /**
     * 使用公钥对数据进行加密
     */
    public static byte[] encryptPublicKey(byte[] binaryData, String publicKey) throws Exception {
        byte[] keyBytes = decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // 获取RSA算法实例
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(keySpec);

        // 初始化加密器
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return cipher.doFinal(binaryData);
    }

    /**
     * 使用私钥对数据进行解密
     */
    public static byte[] decryptPrivateKey(byte[] binaryData, String privateKey) throws Exception {
        byte[] keyBytes = decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        // 获取RSA算法实例
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key priKey = keyFactory.generatePrivate(keySpec);

        // 初始化加密器
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return cipher.doFinal(binaryData);
    }

    public static String encodeBase64(byte[] binaryData) {
        return Base64Util.encode(binaryData);
    }

    public static byte[] decodeBase64(String encoded) {
        return Base64Util.decodeBite(encoded);
    }

    /**
     * 使用公钥对数据进行解密
     */
    public static byte[] decryptPublicKey(byte[] binaryData, String publicKey) throws Exception {
        byte[] keyBytes = decodeBase64(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);

        // 获取RSA算法实例
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        Key pubKey = keyFactory.generatePublic(x509KeySpec);

        // 初始化加密器
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, pubKey);
        return cipher.doFinal(binaryData);
    }

    /**
     * 使用私钥对数据进行签名
     */
    public static String sign(byte[] binaryData, String privateKey)
            throws Exception {
        byte[] keyBytes = decodeBase64(privateKey);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        // 获取RSA算法实例
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PrivateKey priKey = keyFactory.generatePrivate(keySpec);

        // 获取签名算法
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(priKey);
        signature.update(binaryData);
        return encodeBase64(signature.sign());
    }


    /**
     * 使用公钥对数据签名进行验证
     */
    public static boolean verify(byte[] binaryData, String publicKey, String sign)
            throws Exception {
        byte[] keyBytes = decodeBase64(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        // 获取RSA算法实例
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PublicKey pubKey = keyFactory.generatePublic(keySpec);

        // 获取签名算法
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(pubKey);
        signature.update(binaryData);
        return signature.verify(decodeBase64(sign));
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = org.apache.tomcat.util.codec.binary.Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    public static boolean verifyPwd(String encryptPwd, String sqlData) throws Exception {
        //现将加密字符串解密
        byte[] decodeContent2 = decryptPrivateKey(Base64Util.decodeBite(encryptPwd), StaticUtil.privateKey);
        String decryptPwd = new String(decodeContent2);
        String md5Pwd= Md5Utils.getMD5(decryptPwd.getBytes());
        if (StringUtils.isBlank(sqlData)) {
            return false;
        }
        return StringUtils.equals(sqlData, md5Pwd);
    }

    //base64 解码
    public static String decode(byte[] bytes) {
        return new String(Base64.decodeBase64(bytes));
    }

    //base64 编码
    public static String encode(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

    public static void main(String[] args) {
        try {
            System.out.println("============   分隔符     ===========");
            // 4.使用公钥加密
            byte[] encodeContent2 = encryptPublicKey(("123456").getBytes(), StaticUtil.publicKey);
            String aa=Base64Util.encode(encodeContent2);
            System.out.println(Arrays.toString(encodeContent2));
            System.out.println("公钥加密后的数据：" + Base64Util.encode(encodeContent2));
            System.out.println(Arrays.toString(Base64Util.decodeBite(aa)));
            String key="TCgF8/5t/JyYeHeW/KTy3JkdbR+m1eUgKNvczWD2ouaPDPKLREVi9SBTgmmy4w/tdTDLjBs+NS6HC7stB5fD3Hk4SihVs+lMWhSkLngUPStH+KZNukdTQochXSBkfRYmvQiDHM+i2DVH9MU6lORhU75aIEwbEwDTJslN4KFcePCKbWkjCUCf+QJtXhQI7B6DdaVUco474vU73sa75OVMS3l83hatGLvxMH7KP7n/V3opuI4DGAvPxOGFP4coNYVkEnFt7UyxuOfgSLMvbe74Qb9Lk4B59skJbKOJta/5R8a2OF26fbHAwvTjV8mxR9jsex//3G76yHVJkpKJ50UNYw==";
            byte[] decodeContent2 = decryptPrivateKey(Base64Util.decodeBite(aa), StaticUtil.privateKey);
            System.out.println("私钥解密后的数据：" + new String(decodeContent2));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("加解密异常");
        }
    }
}