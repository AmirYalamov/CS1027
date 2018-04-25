// implements stack using array

public class MyStack<T> implements MyStackADT<T>{

	private T[] arrayStack;
	private int numItems;
	private int maximumCapacity = 1000;
	
	// creates an empty stack of a default value
	public MyStack () {
		
		numItems = 0;
		arrayStack = (T[])(new Object[10]);	// creates empty stack of initial capacity 10
	}
	
	// creates an empty stack of specified value
	public MyStack (int initialCapacity, int maxCap) {		
		maximumCapacity = maxCap;
		arrayStack = (T[])(new Object[initialCapacity]);	// creates empty stack of initial capacity specified in method arguments
	}
	
	// adds element on top of stack
	public void push (T dataItem) throws OverflowException{
		T[] newStack;
		
		if (arrayStack.length > maximumCapacity) {
			throw new OverflowException("Overflow");
		}
		
		// if stack capacity less than 60
		if (arrayStack.length < 60 && (arrayStack[arrayStack.length-1] != null)) {
			newStack = (T[])(new Object[arrayStack.length*3]);	// creates new stack with triple length of old stack
			
			// assigns old elements in array to new array in same spots
			for (int a = 0; a < arrayStack.length; a ++) {
				newStack[a] = arrayStack[a];
			}
			arrayStack = newStack;	// arrayStack will point to the new, bigger stack created
		}	
		
		// if stack capacity greater than 60
		if (arrayStack.length >= 60 && (arrayStack[arrayStack.length-1] != null)) {
			newStack = (T[])(new Object[arrayStack.length + 100]);	// creates new stack with length 100 indices greater than old stack
			
			for (int b = 0; b < arrayStack.length; b ++) {
				newStack[b] = arrayStack[b];
			}
			arrayStack = newStack;	// arrayStack will point to the new, bigger stack created
		}
		
		// checks to see if amount of elements in stack is less that the stack capacity
		if (numItems < arrayStack.length) {
			arrayStack[numItems] = dataItem;		// adds the element dataItem on top of stack
			numItems++;
		}
		
	}
	
	// removes and returns the element on top of the stack
	public T pop () throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error: empty stack.");
		}
		
		numItems--;
		T element = arrayStack[numItems];
		arrayStack[numItems] = null;
		return element;
	}
	
	// looks at the element at the top of the stack
	public T peek () throws EmptyStackException {
		if (isEmpty()) {
			throw new EmptyStackException("Error: empty stack.");
		}
		return arrayStack[numItems - 1];
	}
	
	// checks if stack is empty
	public boolean isEmpty() {
		return (arrayStack[0] == null);	// if stack is empty, returns true, else returns false
	}
	
	// returns number of elements in stack
	public int size () {
		return numItems;
	}
	
	public String toString () {
		String toString = null;
		
		// converts all the elements in the stack to a string
		for (int d = 0; d < arrayStack.length; d ++) {
			toString = toString + arrayStack[d] + " ";
		}
		
		return toString;
	}
}

