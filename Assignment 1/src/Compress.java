import java.io.*;
import java.util.*;

public class Compress {
	
	public static void main(String[] args) {
		String outputName = args[0].substring(0, args[0].length() - 3) + "zzz";
		
		// opens file for reading
		TextFile codes = new TextFile(args[1], "read");		// codes.txt file
		
		ArrayCode codepairArray = new ArrayCode(20);
		
		char c = codes.readChar();
		String code = null;
		c = codes.readChar();
		
		// reads characters and corresponding codes from codes.txt and makes objects to put in array
		while (c != 0) {
			code = codes.readLine();
			CodePair codepairObject = new CodePair(c, code);
			codepairArray.add(codepairObject);
			c = codes.readChar();
		}
		
		// file to write to
		TextFile text1 = new TextFile(args[0], "read");	// text1.txt file
		
		CompressedFile inputF = new CompressedFile(outputName, "write");		// text1.zzz files
		
		char c2 = text1.readChar();
		
		while (c2 != 0) {
			String CC = codepairArray.getCode(codepairArray.findCharacter(c2));
			for (int b = 0; b < CC.length(); b++) {
				char ccC = CC.charAt(b);
				inputF.writeBit(ccC);
			}
			c2 = text1.readChar();
		}
		
		text1.close();
		codes.close();
		inputF.close();
	}
}
