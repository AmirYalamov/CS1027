
/**
 * @author ayala
 *
 * @param <T>
 */
public class DLPriorityQueue<T> implements PriorityQueueADT<T>{

	private PriorityNode<T> front;	// reference to 1st node of doubly linked list
	private PriorityNode<T> rear;	// reference to last node of doubly linked list
	private int count;	// number of data items in the priority queue

	// creates empty priority queue
	/**
	 * 
	 */
	public DLPriorityQueue () {
		front = rear = null;
		count = 0;
	}
	
	// adds to priority queue the given dataItem, added to rear of doubly linked list
	/* (non-Javadoc)
	 * @see PriorityQueueADT#enqueue(java.lang.Object, double)
	 */
	public void enqueue (T dataItem, double priority) {
		PriorityNode<T> node = new PriorityNode<T> ();
		node.setDataItem(dataItem);
		node.setPriority(priority);
		
		if (isEmpty()) {
			front = rear = node;
		}
		else {
			//node.setPrevious(rear);
			rear.setNext(node);	
		}
		
		rear = node;
		count ++;
	}
	
	// removes and returns data item at front of queue
	/* (non-Javadoc)
	 * @see PriorityQueueADT#dequeue()
	 */
	public T dequeue () throws EmptyPriorityQueueException { 
		if (isEmpty()) {
			throw new EmptyPriorityQueueException("Empty queue.");
		}
		
		T result = front.getDataItem();
		front = front.getNext();
		count--;
		
		return result;
	}
	
	// returns priority of specified dataItem
	/**
	 * @param dataItem
	 * @return
	 * @throws InvalidDataItemException
	 */
	public double getPriority (T dataItem) throws InvalidDataItemException {
		PriorityNode<T> node = front;
		T di = dataItem;
		
		// linear search through linked list
		while (!node.getDataItem().equals(di)) {
			node = node.getNext();
			
			// breaks out of while loop if loop has gone through whole list
			if (node.getNext().getDataItem().equals(null)) {
				break;
			}
		}
		
		// if dataItem not it list
		if (node.getDataItem().equals(null)) {
			throw new InvalidDataItemException ("datatItem not in list.");
		}
		
		else {
			return node.getPriority();
		}
	}
	
	// changes priority of given element to new value
	/* (non-Javadoc)
	 * @see PriorityQueueADT#changePriority(java.lang.Object, double)
	 */
	public void changePriority (T dataItem, double newPriority) throws InvalidDataItemException {
		PriorityNode<T> theNode = new PriorityNode<T>(dataItem, newPriority);
		PriorityNode<T> currentNode = front;
		PriorityNode<T> nextNode = new PriorityNode<T>();
		Boolean flag = true;
		
		for (int i = 0; i < numItems(); i ++) {
			if ((currentNode.getDataItem()).equals(dataItem)) {
				flag = false;
			}
			nextNode = currentNode.getNext();
			currentNode = nextNode;
		}
		
		if (flag == true) {
			throw new InvalidDataItemException("Not in queue.");
		}
		
		if (isEmpty()) {
			throw new InvalidDataItemException("dataItem not in list.");
		}
		// throw exception for element that doesnt exist AKA "data0"
	
		PriorityNode<T> node = front;
		int counter = 0;
		
		while (node != (null) && !node.getDataItem().equals(theNode.getDataItem())) {	// not going into while loop, check conditions
			PriorityNode<T> temp = node.getNext();	
			node = temp;
			counter ++;
			if (counter == numItems()) {
				throw new InvalidDataItemException ("dataItem not in list.");
			}
		}
		node.setPriority(newPriority);	// for some reason throws NullPointerException
	}
	
	// removes and returns item with smallest priority 
	/* (non-Javadoc)
	 * @see PriorityQueueADT#getSmallest()
	 */
	public T getSmallest () throws EmptyPriorityQueueException {
		PriorityNode<T> node = front;
		PriorityNode<T> lowestNode = node;
		
		// if queue is empty
		if (isEmpty()) {
			throw new EmptyPriorityQueueException ("Queue is empty.");
		}
		
		PriorityNode<T> temp = front;
		
		if (numItems() == 1) {
			return dequeue();
		}
		
		for (int b = 0; b < numItems(); b ++) {
			
			if (numItems() == 1) {
				break;
			}
			
			if (temp.getPriority() < lowestNode.getPriority()) {
				lowestNode = temp;
			}
			else {
				temp.setNext(temp.getNext());
			}
		}
		
		 //if smallest node is at front
        if (lowestNode == front) {
            //if smallest node only node in queue
            if (lowestNode == rear) {
                front = null;
                rear = null;
                //if smallest node is only at front but there are other nodes in queue
            } else {
                front = lowestNode.getNext();
                lowestNode.getNext().setPrevious(null);
            }
            //if not is not at front
        } else if (lowestNode != front) {
            //if not is not at front and rear meaning it is middle then remove by pointing to nodes before and after
            if (lowestNode != rear) {
                lowestNode.getNext().setPrevious(lowestNode.getPrevious());
                lowestNode.getPrevious().setNext(lowestNode.getNext());
                //if node is at rear and there are items in queue
            } else {
                rear = lowestNode.getPrevious();
                rear.getNext().setNext(null);
            }
        }
        count--;

		/*
			// if lowestNode is first in queue
			if (lowestNode == front) {
				
				front.setNext(lowestNode.getNext()); 
				lowestNode.getNext().setPrevious(null);	
				
				//PriorityNode<T> smallest = (PriorityNode<T>) dequeue();
			}
			// if lowestNode is last in queue
			else if (lowestNode == rear) {
				rear.setPrevious(lowestNode.getPrevious());
				lowestNode.getPrevious().setNext(null);
			}
			
			else {
				//lowestNode's next node has lowestNode's previous node set at its previous node
				lowestNode.getNext().setPrevious(lowestNode.getPrevious());
				//lowestNode's previous node has lowestNode's next node set at its next node
				lowestNode.getPrevious().setNext(lowestNode.getNext());
			}
			*/
		//return (T) smallest.getPriority();
		return lowestNode.getDataItem();
	}
	
	// returns true if priority queue is empty, returns false otherwise
	/* (non-Javadoc)
	 * @see PriorityQueueADT#isEmpty()
	 */
	public boolean isEmpty () {
		Boolean bool = false;
		
		// check if queue empty
		if (numItems() == 0) {
			bool = true;
		}
		
		return bool;
	}
	
	// returns number of items in priority queue
	/* (non-Javadoc)
	 * @see PriorityQueueADT#numItems()
	 */
	public int numItems () {
		return count;
	}
	
	// returns a string representation of priority queue
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		String result = "";
		PriorityNode<T> node = front;
		
		while (node != null) {
			result = node.toString() + result;
			node = node.getNext();
		}
		
		return result;
	}
}
