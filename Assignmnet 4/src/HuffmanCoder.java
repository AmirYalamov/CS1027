
// this class uses a Huffman tree  for encoding a character and decoding a code string
public class HuffmanCoder {

	private HuffmanTree huffTree;
	private ArrayUnorderedList<EncodingData> encodingList = new ArrayUnorderedList();
	
	/**
	 * creates huffTree using 4th constructor
	 * @param pairsList
	 */
	public HuffmanCoder(ArrayOrderedList<HuffmanPair> pairsList) {
		huffTree = new HuffmanTree(pairsList);
		
		buildEncodingList(huffTree.getRoot(), "");
	}
	
	/**
	 * finds original character that was coded
	 * @param code
	 * @return
	 */
	public char decode (String code) {
		BinaryTreeNode<HuffmanPair> treeNode = huffTree.getRoot();
		
		// HuffmanTree method
		/*
		// gets leaf node containing corresponding symbol to the code
		for (int e = 0; e < code.length(); e ++) {
			if (code.charAt(e) == '0' && treeNode != null){
				treeNode = treeNode.getLeft();
			}
			
			if (code.charAt(e) == '1' && treeNode != null) {
				treeNode = treeNode.getRight();
			}
		}
		
		// if binary code doesn't lead to a leaf node
		if (treeNode == null) {
			return (char) 0;
		}
		else {
			return treeNode.getElement().getCharacter();
		}
		*/
		
		// list traversal method
		
		char blah = 0;
		
		// traverses list and finds the code's corresponding character
		for (int f = 0; f < encodingList.size(); f ++) {
			if (code.equals(encodingList.getElement(f).getEncoding()) && encodingList.getElement(f).getEncoding() != null) {
				blah = encodingList.getElement(f).getSymbol();
			}
		}
		
		return blah;
		
	}
	
	/**
	 * takes specified character and returns string representation of binary
	 * Huffman encoding of that character
	 * @param c
	 * @return
	 * @throws ElementNotFoundException
	 */
	public String encode (char c) throws ElementNotFoundException {
		String code = "";
		Boolean flag = true;
		
		// if the element exists in encodingList, flag will be false
		for (int d = 0; d < encodingList.size(); d ++) {
			if (c == encodingList.getElement(d).getSymbol()) {
				flag = false;
			}
		}
		
		if (flag == true) {
			throw new ElementNotFoundException ("Element not found.");
		}
		
		else {
			for (int b = 0; b < encodingList.size(); b ++) {
				if (c == encodingList.getElement(b).getSymbol()) {
					code = encodingList.getElement(b).getEncoding();
				}
			}
		}
		
		return code;
	}
	
	/**
	 * string representation of encoding list
	 */
	public String toString () {
		String s = "";
		
		for (int c = 0; c < encodingList.size(); c ++) {
			s += encodingList.getElement(c) + " ";
		}
		
		return s;
	}
	
	/**
	 * build unordered list 
	 * @param node
	 * @param encoding
	 */
	private void buildEncodingList (BinaryTreeNode<HuffmanPair> node, String encoding) {
		if (node.isLeaf()) {
			EncodingData newData = new EncodingData(node.getElement().getCharacter(), encoding);
			encodingList.addToRear(newData);
		}
		
		else {
			buildEncodingList (node.getLeft(), encoding + "0");
			buildEncodingList (node.getRight(), encoding + "1");
		}
	}
}
