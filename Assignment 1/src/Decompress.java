
public class Decompress {
	public static void main(String[] args) {
		String outputName = args[0].substring(0, args[0].length() - 3) + "txt";
		
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
		
		//---------------------- everything same up until here
		
		CompressedFile cFile = new CompressedFile(args[0], "read");
		TextFile tFile = new TextFile("text2.txt", "write");		// text2.txt file
		
		char currentChar = cFile.readBit();
		String fullBit = null;
		char towrite = 0;
		
		while (currentChar != 0) {
			//currentChar = cFile.readBit();
			fullBit = fullBit + Character.toString(currentChar);
			
			for (int a = 0; a < codepairArray.getNumPairs(); a ++) {
				if (fullBit.equals(codepairArray.getCode(a))) {
					towrite = codepairArray.getCharacter(a);
					tFile.writeChar(towrite);
				}
				/*
				else {
					fullBit = fullBit + Character.toString(currentChar);

				}
				*/
				
				/*
				while (!fullBit.equals(codepairArray.getCode(a))) {
					currentChar = cFile.readBit();
					fullBit = fullBit + Character.toString(currentChar);
				}
				*/
				
				currentChar = cFile.readBit();
				fullBit = fullBit + Character.toString(currentChar);

			}
			
			// NOT WRITING ANYTHING
			
			
		}
	}
}
