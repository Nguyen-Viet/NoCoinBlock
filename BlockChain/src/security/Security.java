package security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import com.sun.javafx.scene.traversal.Algorithm;

public class Security
{
	
	private final static String[] algorithm = { "MD2", "MD5", "SHA-1", "SHA-256", "SHA-384", "SHA-512" };
	
	
	
	public static String hashIt( String input ) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance( algorithm[3] );
		
		md.update(input.getBytes());
		
		
		// Applies hash algorithm to our input
		byte hash[] = md.digest();
		
		
		// This will contain hash value as hexidecimal
		StringBuffer hexString = new StringBuffer();
		
		
		// Byte to hex converter to get the hashed value in hexadecimal
		for (int i = 0; i < hash.length; i++) 
		{
			String hex = Integer.toHexString(0xff & hash[i]);
			if(hex.length() == 1)
				hexString.append('0');
			hexString.append(hex);
		}
		
		
		return hexString.toString();
	}
}
