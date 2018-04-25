import java.util.*;

public class HuffmanTree extends LinkedBinaryTree<HuffmanPair> implements Comparable<HuffmanTree> {

	/**
	 * creates empty Huffman tree
	 */
	public HuffmanTree () {
		super();
	}
	
	/**
	 * creates Huffman tree with 1 Huffman pair at the root
	 * @param element
	 */
	public HuffmanTree (HuffmanPair element) {
		super(element);
	}
	
	// creates Huffman tree rooted at a node containing element.
	// roots of left subtree and right subtree are left and right child, resp.
	/**
	 * creates Huffman tree rooted at a node containing element
	 * roots of left subtree and right subtree are left and right child, resp.
	 * @param element
	 * @param leftSubtree
	 * @param rightSubtree
	 */
	public HuffmanTree (HuffmanPair element, HuffmanTree leftSubtree, HuffmanTree rightSubtree) {
		super(element);
		
		this.getRoot().setLeft(leftSubtree.getRoot());	// sets left child as root
		this.getRoot().setRight(rightSubtree.getRoot()); // sets right child as root
	}
	
	/**
	 * creates Huffman tree with list
	 * @param pairsList
	 */
	public HuffmanTree (ArrayOrderedList<HuffmanPair> pairsList) {
		ArrayOrderedList<HuffmanTree> buildList = new ArrayOrderedList<HuffmanTree>();
		
		// adds each element as a tree in a temporary ordered list
		for (int i = 0; i < pairsList.size(); i ++) {
			//makes all the elements into HuffmanTree types
			HuffmanTree tempTree = new HuffmanTree (pairsList.getElement(i));
			
			buildList.add(tempTree);
		}
		
		//System.out.println(buildList);
		
		// while there is more than one item in buildList
		while (buildList.size() > 1) {
			HuffmanTree leftTree;
			HuffmanTree rightTree;
			
			HuffmanTree first = buildList.first();	// first element to be removed
			leftTree = buildList.removeFirst();	// second element to be removed
			
			HuffmanTree second = buildList.first();
			rightTree = buildList.removeFirst();
			
			int totalFreq = first.getRoot().getElement().getFrequency() + second.getRoot().getElement().getFrequency();
			HuffmanPair root = new HuffmanPair(totalFreq);
			
			// adds newly formed tree to buildList
			HuffmanTree finalTree = new HuffmanTree (root, leftTree, rightTree);
			buildList.add(finalTree);
		}
		
		this.setRoot(buildList.removeFirst().getRoot());
		
		// accounts for if there is only 1 element in pairsList
		if (pairsList.size() == 1) {
			HuffmanTree onlyTree = (HuffmanTree) buildList.removeFirst();
			buildList.add(onlyTree);	// only element in list is the final tree
		}
	}
	
	/**
	 * compares frequencies in the root node of the trees, so that add method in
	 * ArrayOrderedList puts node in correct place in buildList
	 */
	public int compareTo (HuffmanTree otherTree) {
		
		if (otherTree.getRoot().getElement().getFrequency() > getRoot().getElement().getFrequency()) {
			return -1;
		}
		
		else if (otherTree.getRoot().getElement().getFrequency() == getRoot().getElement().getFrequency()) {
			return 0;
		}
		
		else {
			return 1;
		}
	}
	
	/**
	 * creates string representation of a Huffman tree
	 */
	public String toString () {
		String s = "";
		
		Iterator<HuffmanPair> list;
		
		list = iteratorPreOrder();
		
		while (list.hasNext()) {
			s += list.next().toString();
		}
		return s;
	}
}
