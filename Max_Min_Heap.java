import java.util.ArrayList;
import java.lang.Math;
import java.io.*;

public class Max_Min_Heap {
	private ArrayList<Integer> max_heap;
	private ArrayList<Integer> min_heap;
	private Integer buffer;

	public Max_Min_Heap(){
		//initialize max_heap, min_heap, and buffer
		max_heap = new ArrayList<Integer>();
		min_heap = new ArrayList<Integer>();
		buffer = null;
	}

	/*
	Takes in the name of file.
	Reads character by character by appending them to determine whether the string is a word or a number.
	If the string is a word, then it will call a specific method.
	Else the number will just be used as the parameters to the corresponding method.
	*/
	private void readAndSave (String fileName) throws Exception{
        File file = new File(fileName);

		BufferedReader br = new BufferedReader(new FileReader(file));

		String line;
		ArrayList<Integer> arrOfNum = new ArrayList<Integer>();
		int counter = 0;
		int size = 0;

		while ((line = br.readLine()) != null){
			counter++;

			System.out.println(line);
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

			if ( counter == 1 ) {
				if ( str.equals("construct") ) {
					size = Integer.parseInt(numStr);
				}
			}
			else if ( counter == 2 ) {
				String [] arrOfStr = line.split(" ");

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

				//printHeap(min_heap);
                //System.out.println();
				//printHeap(max_heap);
			}
			else {
				if ( str.equals("insert") ) {
					//call insert method with the integer representation of numStr
					//System.out.println("insert works");
				}
				else if ( str.equals("removeMin") || str.equals("removemin") ) {
					//call removeMin method
					//System.out.println("removeMin works");
				}
				else if ( str.equals("removeMax") || str.equals("removemax") ) {
					//call removeMax method
					//System.out.println("removeMax works");
				}
			}
		}
    }

	private void printHeap (ArrayList<Integer> arrList){
		System.out.println("This is the following Array List");
		for (int num : arrList) {
			System.out.print(num + " ");
		}
	}

	public void execution(String in_path, String out_path){
		/* 1) read the data and save into max_heap, min_heap, and buffer;
		 * 2) call heapConstruction() to arrange the max_heap, min_heap, and buffer to create max-min heap
		 * 3) record the current state of the heap
		 * 4) read the operations from the file.
		 * 5) according to each operation, call removeMin(), removeMax(), or insertItem(ele) respectively.
		 *	 Record the heap's state of each operation
		 * 6) save the states after each operation together by calling save(out_path) function
		 */
	}

	/*
	// execution of bottom-up heap construction according to the algorithm

	Bottom up heap construction for min-max-heap is done as follows:
	1. Swap external elements on last level in the min-heap with associate elements in the max-heap.
	2. For ( current_level = last level - 1; current_level >= 1; current_level -= 1) {
	toMaxHeapify elements on current_level in min-heap to level current_level+1 in max-heap toMinHeapify elements on current_level in max-heap to level current_level in min-heap
 	}
	*/
	private void heapConstruction(){
		swapAssociates();
	}

	private void swapAssociates(){
		/*
		int biggerSize;
		if ( max_heap.size() > min_heap.size() ) {
			biggerSize = max_heap.size();
			for (int i = posInHeap(max_heap, height(max_heap)); i > 0; i--) {
				if ( associated(max_heap, min_heap, i) ) {
					swapExternal(max_heap, min_heap, i);
				}
			}
		}
		else {
			biggerSize = min_heap.size();
			for (int i = posInHeap(min_heap, height(min_heap)); i > 0; i--) {
				if ( associated(max_heap, min_heap, i) ) {
					swapExternal(max_heap, min_heap, i);
				}
			}
		}
		*/


	}

	/*
	If an external element in a min-heap and an external element in a max-heap share the same index in the array representation, they are associated.
	*/
	private boolean associated(ArrayList<Integer> heap1, ArrayList<Integer> heap2, int pos){
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
	private void swapExternal(ArrayList<Integer> bigHeap, ArrayList<Integer> smallHeap, int pos){
		if ( smallHeap.get(pos).compareTo( bigHeap.get(pos) ) > 0 ) {
			Integer copy = smallHeap.get(pos);
			smallHeap.set(pos, bigHeap.get(pos));
			bigHeap.set(pos, copy);
		}
	}

	/*
	Returns the height of the given heap
	*/
	private int height(ArrayList<Integer> heap){
		return (int) ( Math.log (heap.size()) / Math.log(2) );
	}

	/*
	Returns the position in the given heap for the specified level
	e.g. 10 elements in a heap and want to access the bottom or last level
	therefore, position should be index 7
	*/
	private int posInHeap(ArrayList<Integer> heap, int level){
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

	// execute the toMaxHeapify operation according to the algorithm
	private void toMaxHeapify(Integer elem, int level){
	}

	// execute the toMinHeapify operation according to the algorithm
	private void toMinHeapify(Integer elem, int level){
	}

	/*
	// execution of remove min operation

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
	public Integer removeMin(){

		Integer copy;
		if ( buffer != null && ( buffer.compareTo( min_heap.get(0) ) < 0 ) ) {
			copy = buffer;
			buffer = null;
			return copy;
		}
		else {
			copy = min_heap.get(0);
			min_heap.remove ( min_heap.size() - 1 );

			if ( buffer != null ) {
				min_heap.set( 0, buffer );
				buffer = null;
			}
			else if ( buffer == null ) {
				min_heap.set( 0, min_heap.get( min_heap.size() - 1 ) );
				buffer = max_heap.remove( max_heap.size() - 1 );
			}
			else {
				toMaxHeapify( min_heap.get(0), 0 );
			}
		}
		return copy;
	}

	/*
	// execution of remove max operation

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
	public Integer removeMax(){
		Integer copy;
		if ( buffer != null && ( buffer.compareTo( max_heap.get(0) ) < 0 ) ) {
			copy = buffer;
			buffer = null;
			return copy;
		}
		else {
			copy = max_heap.get(0);
			max_heap.remove ( max_heap.size() - 1 );

			if ( buffer != null ) {
				max_heap.set( 0, buffer );
				buffer = null;
			}
			else if ( buffer == null ) {
				max_heap.set( 0, max_heap.get( max_heap.size() - 1 ) );
				buffer = min_heap.remove( min_heap.size() - 1 );
			}
			else {
				toMinHeapify( max_heap.get(0), 0 );
			}
		}
		return copy;
	}

	/*
	// execution of insert a new element operation
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
	public void insertItem(int elem){
		if ( buffer == null ) {
			buffer = elem;
		}
		else {

		}
	}

	// save the data in the required format
	private void save(String out_path){

	}

	public static void main(String args[]) throws Exception{
		Max_Min_Heap heap = new Max_Min_Heap();
		String input_path = "";
		String output_path = "";
		heap.execution(input_path, output_path);
	}
}
