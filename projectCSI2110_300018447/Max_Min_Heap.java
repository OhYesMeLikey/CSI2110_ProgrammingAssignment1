/*
Student Name: Leo Tan
Student Number: 300018447
*/
import java.util.ArrayList;
import java.lang.Math;
import java.io.*;

public class Max_Min_Heap {
	private ArrayList<Integer> max_heap;
	private ArrayList<Integer> min_heap;
	private Integer buffer;
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	Returns the position of the parent element
	*/
	private int parent (int j){
		return ((j-1)/2);
	}

	/*
	Returns the position of the left element
	*/
	private int left (int j){
		return ((2*j) + 1);
	}

	/*
	Returns the position of the right element
	*/
	private int right (int j){
		return ((2*j) + 2);
	}

	/*
	Returns true if a left element does exist
	*/
	private boolean hasLeft (int j, ArrayList<Integer> heap){
		return (left(j) < heap.size());
	}

	/*
	Returns true if a right element does exist
	*/
	private boolean hasRight (int j, ArrayList<Integer> heap){
		return (right(j) < heap.size());
	}

	/*
	Swaps the element at i with the element at j
	*/
	private void swap (int i, int j, ArrayList<Integer> heap){
		Integer temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	/*
	Returns the difference of a - b
	*/
	private int compare (Integer a, Integer b){
		return a.compareTo(b);
	}

	/*
	Performs up heap operation for min heap
	*/
	private void upHeapForMinHeap (int j, int endPos, ArrayList<Integer> heap){
		while (j > endPos) {
			int p = parent(j);
			if ( compare( heap.get(j), heap.get(p) ) >= 0 ) {
				break;
			}

			swap(j, p, heap);
			j = p;
		}
	}

	/*
	Performs up heap operation for max heap
	*/
	private void upHeapForMaxHeap (int j, int endPos, ArrayList<Integer> heap){
		while (j > endPos) {
			int p = parent(j);
			if ( compare( heap.get(p), heap.get(j) ) >= 0 ) {
				break;
			}

			swap(j, p, heap);
			j = p;
		}
	}

	/*
	Performs down heap operation for min heap
	*/
	private void downHeapForMinHeap (int j, ArrayList<Integer> heap){
		while ( hasLeft(j, heap) ) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;

			if ( hasRight(j, heap) ) {
				int rightIndex = right(j);
				if ( compare(heap.get(leftIndex), heap.get(rightIndex)) > 0 ) {
					smallChildIndex = rightIndex;
				}
			}

			if ( compare(heap.get(smallChildIndex), heap.get(j)) >= 0 ) {
				break;
			}

			swap(j, smallChildIndex, heap);
			j = smallChildIndex;
		}
	}

	/*
	Performs down heap operation for max heap
	*/
	private void downHeapForMaxHeap (int j, ArrayList<Integer> heap){
		while ( hasLeft(j, heap) ) {
			int leftIndex = left(j);
			int bigChildIndex = leftIndex;

			if ( hasRight(j, heap) ) {
				int rightIndex = right(j);
				if ( compare(heap.get(rightIndex), heap.get(leftIndex)) > 0 ) {
					bigChildIndex = rightIndex;
				}
			}

			if ( compare(heap.get(j), heap.get(bigChildIndex)) >= 0 ) {
				break;
			}

			swap(j, bigChildIndex, heap);
			j = bigChildIndex;
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	public Max_Min_Heap (){
		//initialize max_heap, min_heap, and buffer
		max_heap = new ArrayList<Integer>();
		min_heap = new ArrayList<Integer>();
		buffer = null;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	From an array arrOfStr that contains all of the initial numbers to construct the min and max heaps, first half of arrOfStr will be added to the min_heap and the second half will be added to max_heap.
	*/
	private void storeIntoHeaps (String[] arrOfStr, int size){
		ArrayList<Integer> arrOfNum = new ArrayList<Integer>();

		for (int i = 0; i < size; i++) {
			arrOfNum.add( Integer.parseInt(arrOfStr[i]) );
		}

		if ( size % 2 == 1 ) {
			buffer = arrOfNum.remove( arrOfNum.size() - 1 );
		}
		for (int i = 0; i < arrOfNum.size(); i++) {
			if ( i < arrOfNum.size()/2 ) {
				min_heap.add( arrOfNum.get(i) );
			}
			else {
				max_heap.add( arrOfNum.get(i) );
			}
		}
	}

	/*
	Reads each line by line and Returns an ArrayList that contains all of the commands and its corresponding number (i.e. insert method and the number that needs to be inserted) in sequential order from the given fileName.
	*/
	private ArrayList<String> readEverything (String fileName) throws Exception{
		File file = new File(fileName);
		BufferedReader br = new BufferedReader(new FileReader(file));

		String line = "";
		ArrayList<String> cmds = new ArrayList<String>();

		while ((line = br.readLine()) != null){
			System.out.println(line);
			if ( line.length() > 0 && Character.isDigit(line.charAt(0)) ) {
				String[] nums = line.split(" ");
				storeIntoHeaps(nums, nums.length);
				cmds.add(line);
			}
			else {
				//System.out.println(line);
				line.toLowerCase();
				String str = "";
				String numStr = "";

				for (int i = 0; i < line.length(); i++) {
					char ch = line.charAt(i);
					if ( Character.isLetter(line.charAt(i)) ) {
						str += ch;
					}
					else if ( Character.isDigit(line.charAt(i)) ) {
						numStr += ch;
					}
				}
				if ( !str.isEmpty() ) {
					cmds.add(str);
				}
				if ( !numStr.isEmpty() ) {
					cmds.add(numStr);
				}
			}
		}
		return cmds;
	}

	/*
	Create a text file that contains the contents max_heap, min_heap, and buffer to the given output path
	*/
	private void createFile (String out_path){
		try {
			File myObj = new File(out_path);
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
			} else {
				System.out.println("File already exists.");
			}
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/*
	Return a single string object that contains all of the numbers in the given heap
	*/
	private String transformIntoString (ArrayList<Integer> heap, String heapName){
		String output = heapName;
		for (int i = 0; i < heap.size(); i++) {
			if ( i == heap.size() - 1 ) {
				output += Integer.toString(heap.get(i));
			}
			else {
				output += Integer.toString(heap.get(i)) + " ";
			}
		}
		return output;
	}

	/*
	save the data in the required format
	*/
	private void saveToFile (String out_path, String message){
		try {
			FileWriter myWriter = new FileWriter(out_path, true);
			myWriter.write( message + "\n");
			myWriter.write( transformIntoString(max_heap, "max-heap ") + "\n" );
			myWriter.write( transformIntoString(min_heap, "min-heap ") + "\n" );
			if ( buffer == null ) {
				myWriter.write( "buffer " + "\n" );
			}
			else {
				myWriter.write( "buffer " + buffer + "\n" );
			}
			myWriter.write( "\n" );
			myWriter.close();
			//System.out.println("Successfully wrote to the file.");
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	/*
	Runs each command in the given listOfCmds which contains the sequential order of all of the commands from an input text file.
	*/
	private void runTheCommands (ArrayList<String> listOfCmds, String out_path){
		int cmd = 0;
		System.out.println("\n");
		int removed = 0;
		String message = "";

		while ( cmd < listOfCmds.size() ) {
			if ( cmd >= 3 ) {
				if ( listOfCmds.get(cmd).equals("insert") ) {
					message = listOfCmds.get(cmd) + " " + listOfCmds.get(cmd+1);
					System.out.println(listOfCmds.get(cmd) + " " + listOfCmds.get(cmd+1));
					insertItem( Integer.parseInt( listOfCmds.get(++cmd) ) ) ;
				}
				else if ( listOfCmds.get(cmd).equals("removeMin") ) {
					System.out.println(listOfCmds.get(cmd));
					removed = removeMin();
					System.out.println("Removed num is: " + removed);
					message = listOfCmds.get(cmd) + "\nRemoved num is: " + removed;
				}
				else if ( listOfCmds.get(cmd).equals("removeMax") ) {
					System.out.println(listOfCmds.get(cmd));
					removed = removeMax();
					System.out.println("Removed num is: " + removed);
					message = listOfCmds.get(cmd) + "\nRemoved num is: " + removed;
				}
				printEverything();
				saveToFile(out_path, message);
			}
			cmd++;
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	1) read the data and save into max_heap, min_heap, and buffer;
	2) call heapConstruction() to arrange the max_heap, min_heap, and buffer to create max-min heap
	3) record the current state of the heap
	4) read the operations from the file.
	5) according to each operation, call removeMin(), removeMax(), or insertItem(ele) respectively.
	Record the heap's state of each operation
	6) save the states after each operation together by calling save(out_path) function
	*/
	public void execution (String in_path, String out_path) throws Exception{
		ArrayList<String> commands = readEverything(in_path);

		System.out.println("\nThis is the initial state of everything = ");
		printEverything();

		saveToFile(out_path, "This is the initial state of everything = ");
		saveToFile(out_path, commands.get(0) + " " + commands.get(1));
		saveToFile(out_path, commands.get(2));

		heapConstruction();
		System.out.println("The following shows their respective heaps after construction");
		printEverything();

		saveToFile(out_path, "This is current state after constructing the heaps");

		runTheCommands(commands, out_path);
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	// execution of bottom-up heap construction according to the algorithm

	Bottom up heap construction for min-max-heap is done as follows:
	1. Swap external elements on last level in the min-heap with associate elements in the max-heap.
	2. For ( current_level = last level - 1; current_level >= 1; current_level -= 1) {
	toMaxHeapify elements on current_level in min-heap to level current_level+1 in max-heap toMinHeapify elements on current_level in max-heap to level current_level in min-heap}
	*/
	private void heapConstruction (){
		swapAssociates();

		//System.out.println("After initial swaps");
		//printEverything();

		// For this for loop, the height of the root is considered 0, and the next layer is considered 1, and the next next layer is 2, and etc...
		for (int currentLevel = height(min_heap) - 1; currentLevel >= 0; currentLevel--) {

			ArrayList<Integer> listOfNumInLevelInMinHeap = new ArrayList<Integer>();
			ArrayList<Integer> listOfNumInLevelInMaxHeap = new ArrayList<Integer>();

			int startingPosInLevel = posInHeap(min_heap, currentLevel);
			int sizeOfCurrentLevel = posInHeap(min_heap, currentLevel) + (int) Math.pow(2, currentLevel);
			for (int i = startingPosInLevel; i < sizeOfCurrentLevel; i++) {
				listOfNumInLevelInMinHeap.add(min_heap.get(i));
			}

			startingPosInLevel = posInHeap(max_heap, currentLevel);
			sizeOfCurrentLevel = posInHeap(max_heap, currentLevel) + (int) Math.pow(2, currentLevel);
			for (int i = startingPosInLevel; i < sizeOfCurrentLevel; i++) {
				listOfNumInLevelInMaxHeap.add(max_heap.get(i));
			}
			//int elemForMinHeap = min_heap.get(posInHeap(min_heap, currentLevel));
			//int elemForMaxHeap = max_heap.get(posInHeap(max_heap, currentLevel));

			//System.out.println("elemForMaxHeap: " + elemForMaxHeap);
			//System.out.println("elemForMinHeap: " + elemForMinHeap);

			for (int i = listOfNumInLevelInMinHeap.size() - 1; i >= 0; i--) {
				//System.out.println("The current level is " + currentLevel);

				toMaxHeapify(listOfNumInLevelInMinHeap.get(i), currentLevel);

				//System.out.println("Result after performing toMaxHeapify on " + listOfNumInLevelInMinHeap.get(i));
				//printEverything();
			}
			for (int i = listOfNumInLevelInMaxHeap.size() - 1; i >= 0; i--) {
				//System.out.println("The current level is " + currentLevel);

				toMinHeapify(listOfNumInLevelInMaxHeap.get(i), currentLevel);

				//System.out.println("Result after performing toMinHeapify on " + listOfNumInLevelInMaxHeap.get(i));
				//printEverything();
			}
			//heapify(elemForMinHeap, elemForMaxHeap, currentLevel);
		}
	}

	/*
	We apply downheap operation in min-heap for x and it is connected to upheap operation in the max-heap up to a level i through possible external element swap using swapExternal operation. The final level for upheap in max-heap is level i . The default level i is the level of the root node in the max-heap.
	*/
	private void toMaxHeapify (int elem, int level){
		int currentPos = posOfElem(elem, min_heap);

		// While loop performs down heap on min_heap
		while ( hasLeft(currentPos, min_heap) ) {
			int leftIndex = left(currentPos);
			int smallChildIndex = leftIndex;

			if ( hasRight(currentPos, min_heap) ) {
				int rightIndex = right(currentPos);
				if ( compare(min_heap.get(leftIndex), min_heap.get(rightIndex)) > 0 ) {
					smallChildIndex = rightIndex;
				}
			}

			if ( compare(min_heap.get(smallChildIndex), min_heap.get(currentPos)) >= 0 ) {
				break;
			}

			swap(currentPos, smallChildIndex, min_heap);
			currentPos = smallChildIndex;
		}

		if ( min_heap.get(currentPos).compareTo( max_heap.get(currentPos) ) > 0 ) {
			//System.out.println("Before swap");
			//printEverything();
			swapExternal(min_heap, max_heap, currentPos);
			//System.out.println("After swap");
			//printEverything();

			int currentLevel = height(max_heap) - level;
			//While loop performs up heap on max_heap
			while (currentLevel > level) {
				int p = parent(currentPos);
				if ( compare( max_heap.get(p), max_heap.get(currentPos) ) >= 0 ) {
					break;
				}
				swap(currentPos, p, max_heap);
				currentPos = p;

				currentLevel--;
			}
		}

	}

	/*
	We apply downheap operation in max-heap for x and it is connected to upheap operation in the min-heap up to a level i through possible external element swap using swapExternal operation. The final level for upheap in min-heap is level i . The default level i is the level of the root node in the min-heap.
	*/
	private void toMinHeapify (int elem, int level){
		int currentPos = posOfElem(elem, max_heap);

		// While loop performs down heap on max_heap
		while ( hasLeft(currentPos, max_heap) ) {
			int leftIndex = left(currentPos);
			int bigChildIndex = leftIndex;

			if ( hasRight(currentPos, max_heap) ) {
				int rightIndex = right(currentPos);
				if ( compare(max_heap.get(rightIndex), max_heap.get(leftIndex)) > 0 ) {
					bigChildIndex = rightIndex;
				}
			}

			if ( compare(max_heap.get(currentPos), max_heap.get(bigChildIndex)) >= 0 ) {
				break;
			}

			swap(currentPos, bigChildIndex, max_heap);
			currentPos = bigChildIndex;
		}

		if ( min_heap.get(currentPos).compareTo( max_heap.get(currentPos) ) > 0 ) {
			swapExternal(min_heap, max_heap, currentPos);

			int currentLevel = height(max_heap) - level;
			// While loop performs up heap on min_heap
			while (currentLevel >= level) {
				int p = parent(currentPos);
				if ( compare( min_heap.get(currentPos), min_heap.get(p) ) >= 0 ) {
					break;
				}

				swap(currentPos, p, min_heap);
				currentPos = p;

				currentLevel--;
			}
		}
	}

	/*
	Calls the methods toMinHeapify and toMaxHeapify to fix the structures of how a min heap and max heap should be like
	*/
	private void heapify (Integer minHeapElem, Integer maxHeapElem, int level){
		//System.out.println("Calling toMaxHeapify");
		toMaxHeapify(minHeapElem, level);
		//System.out.println("Current state of everything after calling toMaxHeapify");
		//printEverything();

		//System.out.println("Calling toMinHeapify");
		toMinHeapify(maxHeapElem, level);
		//System.out.println("Current state of everything after calling toMinHeapify");
		//printEverything();
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	If an external element in a min-heap and an external element in a max-heap share the same index in the array representation, they are associated.
	*/
	private boolean associated (ArrayList<Integer> heap1, ArrayList<Integer> heap2, int pos){
		if ( heap1.get(pos) != null && heap2.get(pos) != null ) {
			return true;
		}
		else {
			return false;
		}
	}

	/*
	If the external element in the min-heap is bigger than its associate element in the max-heap, swap them.
	*/
	private void swapExternal (ArrayList<Integer> smallHeap, ArrayList<Integer> bigHeap, int pos){
		if ( smallHeap.get(pos).compareTo( bigHeap.get(pos) ) > 0 ) {
			Integer copy = smallHeap.get(pos);
			smallHeap.set(pos, bigHeap.get(pos));
			bigHeap.set(pos, copy);
		}
	}

	/*
	For each number in min_heap that are associated with each number in max_heap, swap them
	*/
	private void swapAssociates (){
		for (int i = 0; i < min_heap.size(); i++) {
			if ( !hasLeft(i, min_heap) &&
			!hasRight(i, min_heap) &&
			associated(min_heap, max_heap, i) ) {
				swapExternal(min_heap, max_heap, i);
			}
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	Returns the height of the given heap
	*/
	private int height (ArrayList<Integer> heap){
		return (int) ( Math.log ( heap.size() ) / Math.log(2) );
	}

	/*
	Returns the position in the given heap for the specified level
	e.g. 10 elements in a heap and want to access the bottom or last level
	therefore, position should be index 7
	*/
	private int posInHeap (ArrayList<Integer> heap, int level){
		int height = height(heap);
		int depth = 0;
		int pos = 0;
		if ( level >= 0 && level <= height ) {
			while ( depth < level ) {
				pos += Math.pow(2, depth);
				depth++;
			}
		}
		return pos;
	}

	/*
	Returns the position of the given element from the given heap
	*/
	private int posOfElem (int elem, ArrayList<Integer> heap){
		for (int i = 0; i < heap.size(); i++) {
			if ( heap.get(i) == elem ) {
				return i;
			}
		}
		return -1;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	Remove element in the root position and move the last element to root position then downheap the element following a path toward an external node until swapping is not required.

	Removing the min element is done as follows:
	1. (Case 1) If buffer is not empty and the element in the buffer is smaller than the element at the root of min-heap, then simply return the element in the buffer and empty the buffer.
	2. Else you remove the element at the root of the min-heap and then
	2.1. (Case 2) If the buffer is not empty, then
	2.1.1. move the element in the buffer to the root of min-heap.
	2.1.2. Empty buffer.
	2.2. (Case 3) Else if the buffer is empty, then
	2.2.1. Move a last element of of min-heap to its root.
	2.2.2. Move a last element of of max-heap to the buffer.
	2.3. (Case 2 and 3) Apply toMaxHeapify to the element in the root of the min-heap.
	*/
	public Integer removeMin (){

		Integer copy;
		if ( buffer != null && ( buffer.compareTo( min_heap.get(0) ) < 0 ) ) {
			copy = buffer;
			buffer = null;
			return copy;
		}
		else {
			copy = min_heap.get(0);
			min_heap.set(0, null);

			if ( buffer != null ) {
				min_heap.set(0, buffer);
				buffer = null;
			}
			else if ( buffer == null ) {
				min_heap.set( 0, min_heap.remove( min_heap.size() - 1 ) );
				buffer = max_heap.remove( max_heap.size() - 1 );
			}

			toMaxHeapify(min_heap.get(0), 0);
		}
		return copy;
	}

	/*
	Remove element in the root position and move the last element to root position then downheap the element following a path toward an external node until swapping is not required.

	Removing the max element is done by following the same procedure as for the removal of the min element as explained above, except that it now operates from the max-heap.
	1. If buffer is not empty and the element in the buffer is bigger than the element at the root of max-heap, then simply return the element in the buffer and empty the buffer.
	2. Else you remove the element at the root of the max-heap and then
	2.1. If the buffer is empty, then
	2.1.1. Move a last element of of max-heap to its root.
	2.1.2. Move a last element of of min-heap to the buffer.
	2.2. Else if the buffer is not empty, then
	2.2.1. move the element in the buffer to the root of max-heap.
	2.2.2. Empty buffer.
	2.3. Apply toMinHeapify to the element in the root of the max-heap.
	*/
	public Integer removeMax (){
		Integer copy;
		if ( buffer != null && ( buffer.compareTo( max_heap.get(0) ) < 0 ) ) {
			copy = buffer;
			buffer = null;
			return copy;
		}
		else {
			copy = max_heap.get(0);
			max_heap.set(0, null);

			if ( buffer != null ) {
				max_heap.set( 0, buffer );
				buffer = null;
			}
			else if ( buffer == null ) {
				max_heap.set( 0, max_heap.remove( max_heap.size() - 1 ) );
				buffer = min_heap.remove( min_heap.size() - 1 );
			}

			toMinHeapify (max_heap.get(0), 0);
		}
		return copy;
	}

	/*
	Adding a new element is done as follows:
	If the buffer is empty, then insert the new element in it.
	Else if the buffer contains already an element, we have two elements to insert to the min-max heap.
	1. Compare the new element and the element in the buffer. Insert a smaller element into the min-heap as a last element and insert a bigger element into the max-heap as a last element. Empty buffer.
	2. Heapify
	2.1. For the last element in min-heap, run toMinHeapify, i.e. upheap in the min-heap.
	2.2. For the last element in max-heap, run toMinHeapify (see Figure 5(b))
	2.3. For a last element in max-heap, run toMaxHeapify, i.e. upheap in the max-heap
	2.4. For a last element in min-heap, run toMaxHeapify (see Figure 5(c)
	*/
	public void insertItem (int elem){
		if ( buffer == null ) {
			buffer = elem;
		}
		else {
			if ( elem < buffer ) {
				min_heap.add(elem);
				max_heap.add(buffer);
			}
			else {
				min_heap.add(buffer);
				max_heap.add(elem);
			}
			buffer = null;

			heapConstruction();
			//heapify(min_heap.get(min_heap.size() - 1), max_heap.get(max_heap.size() - 1), height(min_heap) - 1);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	Prints out the numbers of a heap
	*/
	private void printHeap (ArrayList<Integer> arrList){
		System.out.println("\nThis is the following Array List:");
		String result = "";
		for (int i = 0; i < arrList.size(); i++) {
			if ( i == arrList.size() - 1 ) {
				result += arrList.get(i);
			}
			else {
				result += arrList.get(i) + " ";
			}
		}
		System.out.println(result);
	}

	/*
	Prints out all of the stored commands in the list of commands
	*/
	private void printCmds (ArrayList<String> arrList){
		String result = "";
		for (int i = 0; i < arrList.size(); i++) {
			if ( i == arrList.size() - 1 ) {
				result += arrList.get(i);
			}
			else {
				result += arrList.get(i) + "\n";
			}
		}
		System.out.println(result + "\n");
	}

	/*
	Returns a single line of string from the given list
	*/
	private String sendHeap (ArrayList<Integer> arrList){
		String result = "";
		for (int i = 0; i < arrList.size(); i++) {
			if ( i == arrList.size() - 1 ) {
				result += String.valueOf( arrList.get(i) );
			}
			else {
				result += String.valueOf( arrList.get(i) ) + " ";
			}
		}
		return result;
	}

	/*
	Prints the max_heap, min_heap, and the buffer
	*/
	private void printEverything (){
		System.out.println("This is the current state of everything:");
		System.out.println("max heap: " + sendHeap(max_heap));
		System.out.println("min heap: " + sendHeap(min_heap));
		System.out.println("the buffer: " + buffer + "\n");
	}
	////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main (String args[]) throws Exception{
		Max_Min_Heap heap = new Max_Min_Heap();
		//String input_path = "input1.txt";
		//String output_path = "output1.txt";
		String output_path = "";
		String input_path = args[0];
		if ( input_path.equals("input1.txt") ) {
			output_path = "output1.txt";
		}
		else if ( input_path.equals("input2.txt") ) {
			output_path = "output2.txt";
		}
		else {
			output_path = "output.txt";
		}
		heap.execution(input_path, output_path);
	}
}
