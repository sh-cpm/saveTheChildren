/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.save.children.referdata;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.stereotype.Service;

/**
 *
 * @author andre
 */
@Service
public class CryptingData {

    private static final String KEY = "AODVNUASDNVVAOVF";

    public String encrypting(String inputData) throws Exception {
        String strData = "";

        try {
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(KEY.getBytes("UTF-8")));
            byte[] encrypted = cipher.doFinal(inputData.getBytes("UTF-8"));
            strData = new String(encrypted);

        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception(e);
        }
        return strData;
    }

    public String decrypting(String encryptData) throws Exception {
        String strData = "";

        try {
            SecretKeySpec key = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "SunJCE");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(KEY.getBytes("UTF-8")));
            byte[] decrypted = cipher.doFinal(encryptData.getBytes());
            strData = new String(decrypted);

        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            throw new Exception(e);
        }
        return strData;
    }

}
