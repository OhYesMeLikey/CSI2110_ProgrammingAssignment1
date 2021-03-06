import java.io.*;
import java.util.ArrayList;
public class Test{

    private static ArrayList<Integer> max_heap = new ArrayList<Integer>();
    private static ArrayList<Integer> min_heap = new ArrayList<Integer>();
    private static Integer buffer = null;

    private static void readAndSave (String fileName) throws Exception{
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

    private static void printHeap (ArrayList<Integer> arrList){
		System.out.println("This is the following Array List");
		for (int num : arrList) {
			System.out.print(num + " ");
		}
	}

    public static void main(String[] args) throws Exception{
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \test as \t (ie. as a escape sequence)

        //File file = new File("input1.txt");
        readAndSave ("C:\\Users\\Leo Tan\\Desktop\\CSI 2110 [D]\\Assignments\\ProgrammingA1\\input1.txt");

    }
}
