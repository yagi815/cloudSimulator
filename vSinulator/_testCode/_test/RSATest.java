package _test;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSATest {

	/**
	 * Desc :
	 * 
	 * @Method Name : main
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
			KeyPair keyPair = generator.generateKeyPair();
			PublicKey pubKey = keyPair.getPublic();
			PrivateKey priKey = keyPair.getPrivate();

			Cipher cipher = Cipher.getInstance("RSA");
			
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			String text = "hello world hello nice good ";
			
			byte[] text0 = text.getBytes();
//			System.out.println("P: " + ByteUtils.toHexString(text0));
			byte[] text1;
			
			text1 = cipher.doFinal(text0);
			// System.out.println("E: " + ByteUtils.toHexString(text1));
						
			
			cipher.init(Cipher.DECRYPT_MODE, priKey);
			byte[] b1 =  cipher.doFinal(text1);
			
			
			
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class ByteUtils {

	public String toHexString(Byte[] bStr){
		
		return new String();
	}
}
