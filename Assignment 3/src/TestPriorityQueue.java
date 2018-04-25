
public class TestPriorityQueue {

	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DLPriorityQueue<String> prioQueue = new DLPriorityQueue<String>();
		boolean testPassed;
		String s;

		// Test 1: numItems, isEmpty, removeMin
		// --------------------------------
		testPassed = true;
		try {
			if (!prioQueue.isEmpty())
				testPassed = false;
			if (prioQueue.numItems() != 0)
				testPassed = false;
			s = prioQueue.getSmallest();
			System.out.println("Test 1 failed");
		} catch (EmptyPriorityQueueException e) {
			if (testPassed)
				System.out.println("Test 1 passed");
			else
				System.out.println("Test 1 failed");
		} catch (Exception e) {
			System.out.println("Test 1 failed");
		}
			
		
		
		// Test 2: enqueue, removeMin, updateMin.
		// -----------------------------------
		testPassed = true;
		prioQueue.enqueue("data1", 1.0);
		try {
			prioQueue.changePriority("data1", 2.0);
			s = prioQueue.getSmallest();
			if (!s.equals("data1"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		try {
			prioQueue.changePriority("data0", 1.0);	// for some reason program fails here
			testPassed = false;
		} catch (InvalidDataItemException e) {
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 2 passed");
		else
			System.out.println("Test 2 failed");

		// Test 3: enqueue removeMin, numItems, dequeue
		// ---------------------------
		prioQueue = new DLPriorityQueue<String>();
		testPassed = true;
		try {
			for (int i = 0; i < 1000; ++i)
				prioQueue.enqueue("data" + i, (double) ((i + 1) % 10) + (double) i / 1000.0);

			for (int i = 0; i < 20; ++i) {
				s = prioQueue.getSmallest();
				if (!s.equals("data" + (9 + i * 10)))
					testPassed = false;
			}

			if (prioQueue.numItems() != 980)
				testPassed = false;
			
			s = prioQueue.dequeue();
			if (!s.equals("data0")) testPassed = false;
			
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 3 passed");
		else
			System.out.println("Test 3 failed");
		
		// Test 4: enqueue, updateMin
		// ----------------------
		prioQueue = new DLPriorityQueue<String>();
		testPassed = true;
		try {
			for (int i = 1000; i > 0; --i)
				prioQueue.enqueue("data" + i, (double) ((i + 1) % 10) + (double) i / 1000.0);

			for (int i = 0; i < 20; ++i) {
				prioQueue.changePriority("data" + (9 + i * 10), 1.0 + i);
			}

			s = prioQueue.getSmallest();
			if (!s.equals("data209"))
				testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		try {
			prioQueue.changePriority("data0", 0.0);
			testPassed = false;
		} catch (InvalidDataItemException e) {
		}

		if (testPassed)
			System.out.println("Test 4 passed");
		else
			System.out.println("Test 4 failed");

		// Test 5: toString
		// ----------------
		testPassed = true;
		try {
			s = prioQueue.toString();
			for (int i = 1000; i > 0; --i)
				if (i != 209 && !s.contains("data" + i))
					testPassed = false;
		} catch (Exception e) {
			testPassed = false;
		}

		if (testPassed)
			System.out.println("Test 5 passed");
		else
			System.out.println("Test 5 failed");
			
	}

}
