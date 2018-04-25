
public class CodePair {

	char c;
	String code;
	
	public CodePair(char c, String code) {
		this.c = c;
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}
	
	public char getCharacter() {
		return c;
	}
	
	public void setCharacter(char c) {
		this.c = c;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public boolean equals(CodePair anotherPair) {
		boolean bool = false;
		if (anotherPair.c == c) {
			bool = true;
		}
		return bool;
	}
	
	
}
