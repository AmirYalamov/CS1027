
public class tester {

	public static void main(String[] args) {
		ArrayCode list = null;
		boolean test = true;
		list = new ArrayCode(20);
		
		for (int i = 1; i <= 20; ++i)
			list.add(new CodePair((char)i,Integer.toBinaryString(i)));
		
		System.out.println("Length of array: " + list.getSize());
		
	    if(list.getSize() != 20) test = false;
	    
	    System.out.println("Value: " + test);
	}

}
