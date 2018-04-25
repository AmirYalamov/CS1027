import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author ayala
 *
 */
public class FindShortestPath {
	
	/**
	 * @param cell
	 * @return
	 */
	private static boolean interferance (MapCell cell) {
		Boolean bool = false;
		for (int a = 0; a < 6; a ++) {
			// if cell exists
			if (cell.getNeighbour(a) != null) {
				if (cell.getNeighbour(a).isTower()) {
					bool = true;
				}
			}
			
		} 
		return bool;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		// make sure program catches any exceptions that might be thrown
		
		// create empty priority queue
		DLPriorityQueue<MapCell> queue = new DLPriorityQueue<MapCell>();
		
		// get initial cell where the UWO store is
		Map map = new Map(args[0]);
		
		// UWO store map cell
		MapCell firstCell = map.getUWOstore();
		
		// add initial cell to priority queue with priority of 0
		queue.enqueue(firstCell, 0);
		
		firstCell.markEnqueued();
		
		int distance = 0;
		
		try {
			// while queue not empty AND customer cell not reached yet
			while (!queue.isEmpty() && !firstCell.isCustomer()) {
				MapCell currentCell;

				MapCell smallestCell = queue.getSmallest();	// finds smallest cell	// gets stuck on infinite loop on this step

				smallestCell.markDequeued();	// marks smallest cell as dequeued
				double totalDistance = 0;

				// if S is customer cell, algorithm exits while loop
				if (smallestCell.isCustomer()) {
					distance += smallestCell.getDistanceToStart() + 1;
					break;
				}
				
				// goes through neighboring cells 
				for (int a = 0; a < 6; a ++) {
					currentCell = smallestCell.getNeighbour(a);	// gets one of neighboring cells
					distance = 0;
					try {
						if (currentCell != null) {
							if (interferance(smallestCell)) {	// problem line
								continue;
							}

							if (!currentCell.isNoFlying() && !currentCell.isMarkedDequeued()) {
								distance = 1 + smallestCell.getDistanceToStart();

								// if distance from initial cell to current cell is larger than whole distance
								if (currentCell.getDistanceToStart() > distance) {
									currentCell.setDistanceToStart(distance);
									currentCell.setPredecessor(smallestCell);
								}

								totalDistance = currentCell.getDistanceToStart() + currentCell.euclideanDistToDest(map);

								if (currentCell.isMarkedEnqueued() && totalDistance < queue.getPriority(currentCell)) {
									queue.changePriority(currentCell, totalDistance);	// updates priority of the current cell to new priority
								}

								if (!currentCell.isMarkedEnqueued()) {
									queue.enqueue(currentCell, currentCell.getDistanceToStart() + currentCell.euclideanDistToDest(map));
									currentCell.markEnqueued();
								}
							}

						}
					}
					catch (NullPointerException e){
						System.out.println("cell not in queue.");
					}
				}
			}
		}
		catch(EmptyPriorityQueueException e){
			System.out.println("Empty queue.");
		}
		catch(InvalidDataItemException e){
			System.out.println("Invalid data item access atempt.");
		}
		catch(InvalidNeighbourIndexException e){
			System.out.println("Invalid neighbour index.");
		}
		catch(InvalidMapException e){
			System.out.println("Invalid map entered.");
		}
		
		// if there is no solution to map
		if (distance == 0) {
			System.out.println("Error: cannot reach home cell.");
		}
		// prints out how many cells there are in shortest path
		else {
			System.out.println("Number of cells: " + distance);
		}
	}
}
