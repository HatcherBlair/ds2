package algs34;
import stdlib.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
/* ***********************************************************************
 *  Compilation:  javac OneWay.java
 *  Execution:    java OneWay password
 *
 *  Compute the SHA1 of the command line argument.
 *
 *
 * % java OneWay "The quick brown fox jumps over the lazy dog"
 * 2fd4e1c6 7a2d28fc ed849ee1 bb76e739 1b93eb12
 *
 *  % java OneWay test
 *  a94a8fe5 ccb19ba6 1c4c0873 d391e987 982fbbd3
 *
 *  % java OneWay ""
 *  da39a3ee 5e6b4b0d 3255bfef 95601890 afd80709
 *
 *************************************************************************/
import java.security.NoSuchAlgorithmException;

public class XOneWay {

	public static void main(String[] args) {
		args = new String[] { "test" };
		args = new String[] { "test1" };
		//args = new String[] { "The quick brown fox jumps over the lazy dog" };

		// get the sha1 encoding object
		MessageDigest sha1;
		try { sha1 = MessageDigest.getInstance("SHA1"); } catch (NoSuchAlgorithmException e) { StdOut.println(e); return; }

		// get the input string as a byte array
		String password = args[0];
		byte[] input;
		try { input = password.getBytes("ISO-8859-1"); } catch (UnsupportedEncodingException e) { StdOut.println(e); return; }

		// perform the conversion
		byte[] hash = sha1.digest(input);
		System.out.println("hash array tostring: " + hash);

		// careless printing of bytes
		System.out.print("careless hash array printing ");
		for (int i=0; i<hash.length; i++) {
			System.out.print(hash[i] + " ");
		}
		//
		System.out.println();

		StdOut.print("hash as unsigned bytes: ");
		for (int i = 0; i < hash.length; i++) {
			StdOut.print(Byte.toUnsignedInt(hash[i]) + " ");	

		}
		System.out.println();

		System.out.print("binary hash array ");
		// slightly more careful printing of bytes
		for (int i=0; i<hash.length; i++) {
			System.out.print(Integer.toBinaryString(hash[i]) + " ");
		}

		System.out.println();

		// https://mkyong.com/java/java-convert-bytes-to-unsigned-bytes/
        // one byte/item
		
		// print bytes as hex, careful to handle leading 0s and 2s complement
		StdOut.print(hash.length + ": " + password.hashCode () + ":");
		String hex = "0123456789abcdef";
		for (int i = 0; i < hash.length; i++) {
			if (i % 4 == 0) StdOut.print(" ");
			StdOut.print(hex.charAt((hash[i] & 0xF0) >> 4));
			StdOut.print(hex.charAt((hash[i] & 0x0F) >> 0));
		}
		StdOut.println();

		

		

	}


}
