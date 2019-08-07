package cn.tsxxdw.service.wechat;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.AlgorithmParameterSpec;

public class AESDecodeUtils {
   /* public static void main(String[] args) throws Exception {
        byte[] encrypData = Base64.decodeBase64("W+ud6m343YMIkf5jbDTWOGM2uVPQT9h6koD5d62ck51IPdABZG2/pFWNlgse0HZ36oaPHdNIv6nn8NdI+0qE+YR/8EoYrFfvFAWrbTjwoMvpaKVt+IFfi8u+2WFzS2l8Exmqld2MauRfx3swPs8d2Shf11jtsegYbj8iyn1MAxujUTPcwxhEOh8x3ujBsm4q5l1qplGb0FOILhS0OMdmb3tucmgtmsXBxm0I5rBVU6EPrDJRGXTW+pJK1iDo1vl63otMxvZWpcQLhQ6PLI94Dj5C9HDsPgVXTjK/3ZoG3BGLQprmIoAckIHksytKk05tWxSdFNYgItmuKZJsufhMfpoy4OdFNd8eOULeVvRiE4lWV2II5XKeiY2rkH3Q+36BZ/XPaW6Lj2Wn05Z3SlimmCuA4cTpbxIcq98r1xz3rDtzwiCFST0HsVP4WmKVxtVgwL15K52rG+5WE6D9FmAD4d/R1vW7JTyVV7M1UMMgGu0=,iv=EE2HaDmZ86BWZ589qfCE3A==");
        byte[] ivData = Base64.decodeBase64("EE2HaDmZ86BWZ589qfCE3A==");
        byte[] sessionKey = Base64.decodeBase64("yYhqmfEhhxbWeW55DeQbuQ==");
        System.out.println(decrypt(sessionKey,ivData,encrypData));
    }
*/
    public static String decrypt(byte[] key, byte[] iv, byte[] encData) throws Exception {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        return new String(cipher.doFinal(encData),"UTF-8");
    }


}
