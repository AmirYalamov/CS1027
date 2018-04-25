
public class ArrayCode {

	int size;
	CodePair[] codepairs;
	int numElements = 0;
	
	public ArrayCode(int size) {		
		this.size = size;
		codepairs = new CodePair[size]; // creates array of CodePair objects of length aSize
	}
	
	// expands capacity of array
	private void expandCapacity(char ex) {
		CodePair[] largercodepairs = new CodePair[codepairs.length];
		if (ex == 'a') {
			largercodepairs = new CodePair[codepairs.length * 2];
		}		
		// if array is bigger than 100
		if (ex == 'b') {
			largercodepairs = new CodePair[codepairs.length + 20];
		}
		
		for (int j = 0; j < codepairs.length; j++) {
			largercodepairs[j] = codepairs[j];
		}
		
		codepairs = largercodepairs;
	}
	
	// contracts size of array
	private void decreaseCapacity() {
		CodePair[] smallercodepairs = new CodePair[(codepairs.length / 2)];	// array half the length of codepairs
		
		for (int m = 0; m < numElements; m++) {
			smallercodepairs[m] = codepairs[m];
		}
		
		codepairs = smallercodepairs;
	}
	
	// adds a pair to the array of code pairs
	public void add(CodePair pair) {
		for (int i = 0; i < codepairs.length; i++) {
			if (codepairs[i] == null) {
				codepairs[i] = pair;
				numElements++;
				break;
			}
			else if (numElements == codepairs.length) {
				if (codepairs.length <= 100) {
					expandCapacity('a');
				}
				
				else if (codepairs.length > 100) {
					expandCapacity('b');
				}
			}
		}
	}
	
	
	// removes a pair from the array
	public void remove(CodePair pairToRemove) {
		int search = -1;
		
		// search the list for the specified pair
		for (int k = 0; k < numElements; k ++) {
			if (codepairs[k].equals(pairToRemove))
				search = k;
		}
		
		// target pair found, remove by replacing with last element in list
		if (search != -1) {
			codepairs[search] = codepairs[numElements - 1];
			codepairs[numElements - 1] = null;
			numElements--;
		}
		
		if (numElements < (codepairs.length / 4)) {
			decreaseCapacity();
		}
	}
	
	// looks for given code in array
	public int findCode(String code) {
		int index = -1;
		
		// searches through array to find a certain code
		for (int n = 0; n < numElements; n++) {
			if (codepairs[n].getCode().equals(code)) {
				index = n;
			}
		}
		
		return index;
	}
	
	// looks for given character in the array
	public int findCharacter(char c) {
		int index2 = -1;
		
		for (int p = 0; p < numElements; p++) {
			if (codepairs[p].getCharacter() == c) {
				index2 = p;
			}
		}
		
		return index2;
	}
	
	public String getCode(int i) {
		if (i >= 0) {
			return codepairs[i].getCode();
		}		
		else {
			return null;
		}
	}
	
	public char getCharacter(int i) {
		if (i >= 0) {
			return codepairs[i].getCharacter();
		}
		else {
			return 0;
		}
	}
	
	public int getSize() {
		return codepairs.length;
	}
	
	// returns amount of CodePair objects in array
	public int getNumPairs() {
		return numElements;
	}
}
