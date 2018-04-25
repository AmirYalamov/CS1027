
/**
 * @author ayala
 *
 * @param <T>
 */
public class PriorityNode<T> {

	private T dataItem;
	private PriorityNode<T> next;
	private PriorityNode<T> previous;
	private double priority;
	
	// creates node storing given data and priority
	/**
	 * @param data
	 * @param prio
	 */
	public PriorityNode (T data, double prio) {
		this.dataItem = data;
		this.priority = prio;
	}
	
	// creates empty node, with null data and priority zero
	/**
	 * 
	 */
	public PriorityNode () {
		this.dataItem = null;
		this.priority = 0;
	}
	
	/**
	 * @return
	 */
	public double getPriority() {
		return this.priority;
	}
	
	/**
	 * @return
	 */
	public T getDataItem (){
		return this.dataItem;
	}
	
	/**
	 * @return
	 */
	public PriorityNode<T> getNext (){
		return this.next;	
	}
	
	/**
	 * @return
	 */
	public PriorityNode<T> getPrevious () {
		return this.previous;
	}
	
	/**
	 * @param dt
	 */
	public void setDataItem (T dt) {
		this.dataItem = dt;
	}
	
	/**
	 * @param n
	 */
	public void setNext (PriorityNode<T> n) {
		this.next = n;
		}
	
	/**
	 * @param p
	 */
	public void setPrevious (PriorityNode<T> p) {
		this.previous = p;
	}
	
	/**
	 * @param prio
	 */
	public void setPriority (double prio) {
		this.priority = prio;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString () {
		String dt = getDataItem() + "";
		String pr = getPriority() + "";
		return dt + " " + pr;
	}
}
