import java.io.FileNotFoundException;
import java.io.IOException;

public class ComputePath {
	Map cityMap;	// references object that represents city map where drone will be flying
	
	// constructor
	public ComputePath (String filename) {
			try {
				cityMap = new Map(filename);
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	private boolean interferance (MapCell cell) {
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
	
	private MapCell nextCell (MapCell cell) {
		MapCell bestCell = null;
		// goes through neighboring cells 
		for (int b = 0;  b < 6; b ++) {
			
			// if cell exists
			if (cell.getNeighbour(b) != null) {
				
				// if cell is NOT marked
				if (!cell.getNeighbour(b).isMarked()) {
					
					// if cell is free or is destination cell
					if (cell.getNeighbour(b).isFree() || cell.getNeighbour(b).isCustomer()) {
						// gets the free cell with lowest index
						if (cell.getNeighbour(b).isFree()) {
							bestCell = cell.getNeighbour(b);
							break;
						}
						bestCell = cell.getNeighbour(b);
					}
					
					else if (cell.getNeighbour(b).isHighAltitude()) {
						bestCell = cell.getNeighbour(b);
					}
					
					else if (cell.getNeighbour(b).isThief()) {
						bestCell = cell.getNeighbour(b);
					}
				}
			}
		}
			
		return bestCell;
	}
	
	public static void main (String[] args) {
		ComputePath path = new ComputePath(args[0]);
		
		MyStack stack = new MyStack();	// creates empty stack

		MapCell cell = path.cityMap.getStart();		// gets starting cell

		stack.push(cell);	// pushes starting cell to stack

		while (!stack.isEmpty() && !cell.isCustomer()) {
			MapCell currentCell = (MapCell) stack.peek();	// looks at cell at top of stack 
			
			// if the cell at the top of the stack is the destination cell, break out of the loop
			if (currentCell.isCustomer()) {
				break;
			}
			
			// if any of neighboring cells is a tower, remove top cell from stack
			if (path.interferance(currentCell)) {
				MapCell poppedCell = (MapCell) stack.pop();
				poppedCell.markOutStack();
				currentCell = (MapCell) stack.peek();
			}
			
			// checks for unmarked neighboring cells
			if (path.nextCell(currentCell) != null) {
				stack.push(path.nextCell(currentCell));
				path.nextCell(currentCell).markInStack();
			}
			else {
				stack.pop();
				currentCell.markOutStack();
			}
			
			cell = currentCell;
		}
		
		System.out.println("Destination reached.");
		System.out.println("Number of cells from initial cell to destination: " + stack.size());
	}
}
