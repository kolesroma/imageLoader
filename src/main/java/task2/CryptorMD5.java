package task2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptorMD5 implements Cryptor {
    public byte[] crypt(byte[] msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(msg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
