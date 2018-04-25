/* represents a single pair of data used to encode a text 
 * message into its binary Huffman code.
 * Class will be used to encode a text file */

public class EncodingData {

	private char symbol;
	private String encoding;
	
	/**
	 * 
	 * @param symbol
	 * @param encoding
	 */
	public EncodingData (char symbol, String encoding) {
		this.symbol = symbol;
		this.encoding = encoding;
	}
	
	
	/**
	 * gets symbol
	 * @return
	 */
	public char getSymbol (){
		return this.symbol;
	}
	
	/**
	 * gets encoding
	 * @return
	 */
	public String getEncoding () {
		return this.encoding;
	}
	
	/**
	 * sets symbol
	 * @param s
	 */
	public void setSymbol (char s) {
		this.symbol = s;
	}
	
	/**
	 * sets encoding
	 * @param enc
	 */
	public void setEncoding (String enc) {
		this.encoding = enc;
	}
	
	/**
	 * determines of two EncodingData objects are equal based on symbol attribute
	 */
	public boolean equals (Object obj) {
		EncodingData other = (EncodingData) obj;
		
		// compares the two objects
		if (symbol != other.getSymbol()) {
			return false;
		}
		return true;
	}
	
	/**
	 * gives string representation of symbol and Huffman code
	 */
	public String toString () {
		String s = "";
		String e = "";
		
		s = getSymbol() + "";
		e = getEncoding() + "";
		
		return s + " " + e;
	}
}
