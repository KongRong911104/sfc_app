package AES;

import library.library;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;

import java.security.SecureRandom;


public class AES256 {
    private final IvParameterSpec ivSpec;
    private final SecretKey secretKey;
    public AES256(){
        String keyString = "MySecretKey12345";
        byte[] keyBytes = keyString.getBytes();
        secretKey = new SecretKeySpec(keyBytes, "AES");

        // 創建初始化向量（IV），在CTR模式中，IV是必需的
        byte[] ivBytes = new byte[16]; // 16字節的IV，與AES區塊大小相同
        SecureRandom random = new SecureRandom();
        random.nextBytes(ivBytes);
        ivSpec = new IvParameterSpec(ivBytes);
    }

    public static byte[] encrypt(String plaintext, SecretKey secretKey, IvParameterSpec ivSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decrypt(byte[] encryptedBytes, SecretKey secretKey, IvParameterSpec ivSpec) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    public void FileEncryption(String filePath) throws FileNotFoundException {
        String EncFile = ".\\src\\main\\java\\AES\\AESEncryption\\" + library.FileNameSplit(filePath);
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(EncFile))) {

                byte[] buffer = new byte[inputStream.available()];

                while (inputStream.read(buffer) != -1) {
                    String str = new String(buffer);
                    int[] enc = library.ByteTOInt(encrypt(str, this.secretKey,this.ivSpec));

                    outputStream.write(library.IntTOByte(enc));

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void FileDecryption(String filePath)  {
        String DecFile = ".\\src\\main\\java\\AES\\AESDecryption\\" + library.FileNameSplit(filePath);
        try (InputStream inputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(DecFile))) {
                byte[] buffer = new byte[inputStream.available()];
                while (inputStream.read(buffer) != -1) {
                    String str = new String(buffer);
                    int[] enc = library.StringToInt(decrypt(str.getBytes(), this.secretKey,this.ivSpec));
                    outputStream.write(library.IntTOByte(enc));
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[]args) throws Exception {
//        String plainText = "Hello, World!sib";
//        String key = "MySecretKey12345"; // 16字元的金鑰 (128位元)
//
//        // 加密
//        String encryptedText = encrypt(plainText, key);
//        System.out.println("加密後的文字: " + encryptedText);
//
//        // 解密
//        String decryptedText = decrypt(encryptedText, key);
//        System.out.println("解密後的文字: " + decryptedText);
        AES256 aes256 = new AES256();
        String inputFile = "1.txt";
        aes256.FileEncryption(".\\src\\main\\java\\txt_file\\" + inputFile);
        aes256.FileDecryption(".\\src\\main\\java\\AES\\AESEncryption\\" + inputFile);
    }


}
