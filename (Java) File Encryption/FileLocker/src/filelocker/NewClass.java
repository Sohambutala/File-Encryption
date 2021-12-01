package filelocker;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.spec.AlgorithmParameterSpec;
 
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class NewClass 
{
    static String str="FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF";
    static int pass=123;
    static String clearFile = "C:\\Users\\hplaptop\\Desktop\\newenc\\hello.docx";
    static String encryptedFile = "C:\\Users\\hplaptop\\Desktop\\newenc\\helloenc.docx";
    static String decryptedFile = "C:\\Users\\hplaptop\\Desktop\\newenc\\hellodec.docx";
    
    
    private static Cipher encrypt;
    private static Cipher decrypt;
 
    private static final byte[] initialization_vector = { 22, 33, 11, 44, 55, 99, 66, 77 };
 
    public static void main(String[] args) 
    {
        
        System.out.println("End of Encryption/Decryption procedure!");
 
    }
    
    
    static SecretKey genkey(int password)
    {
        int key=password; 
        
        //encryption decryption program starts here
        String kp="";
        byte[] arr;
        kp=String.valueOf(key);
        arr=kp.getBytes();
        while(arr.length!=8)
        {
            key=key*10;
            kp=String.valueOf(key);
            arr=kp.getBytes();
        }
       SecretKey secret_key=new SecretKeySpec(arr,"DES");
       System.out.println("Secret key value "+secret_key);
        
        return secret_key;
    }
    
    
    static void encrypt(InputStream input, OutputStream output,SecretKey secret_key)
            throws IOException 
    {
        try
        {
        AlgorithmParameterSpec alogrithm_specs = new IvParameterSpec(initialization_vector);
        encrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
        encrypt.init(Cipher.ENCRYPT_MODE, secret_key, alogrithm_specs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        output = new CipherOutputStream(output, encrypt);
        writeBytes(input, output);
    }
 
    static void decrypt(InputStream input, OutputStream output,SecretKey secret_key)
            throws IOException 
    {
        try
        {
        AlgorithmParameterSpec alogrithm_specs = new IvParameterSpec(initialization_vector); 
        decrypt = Cipher.getInstance("DES/CBC/PKCS5Padding");
        decrypt.init(Cipher.DECRYPT_MODE, secret_key, alogrithm_specs);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        input = new CipherInputStream(input, decrypt);
        writeBytes(input, output);
    }
 
    private static void writeBytes(InputStream input, OutputStream output)
            throws IOException {
        byte[] writeBuffer = new byte[512];
        int readBytes = 0;
 
        while ((readBytes = input.read(writeBuffer)) >= 0) {
            output.write(writeBuffer, 0, readBytes);
        }
 
        output.close();
        input.close();
    }

}
