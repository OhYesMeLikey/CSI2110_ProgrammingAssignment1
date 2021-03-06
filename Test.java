import java.io.*;
import java.util.ArrayList;
public class Test{

    private static ArrayList<Integer> max_heap = new ArrayList<Integer>();
    private static ArrayList<Integer> min_heap = new ArrayList<Integer>();
    private static Integer buffer = null;

    private static void storeIntoHeaps(String[] arrOfStr, int size){
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

    private static ArrayList<String> readEverything(String fileName) throws Exception{
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = "";
        ArrayList<String> cmds = new ArrayList<String>();

        while ((line = br.readLine()) != null){
            System.out.println(line);
            //System.out.println("This is line: " + line);
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
                cmds.add(str);
                cmds.add(numStr);
            }
        }
        return cmds;
    }

    private static void runTheCommands(ArrayList<String> listOfCmds){
        //System.out.println("It works");
        int cmd = 0;

        while ( cmd < listOfCmds.size() ) {
            if ( cmd >= 3 ) {
                //System.out.println("It works again");
                if ( listOfCmds.get(cmd).equals("insert") ) {
                    System.out.println("Call method insertItem and send the next item in the list as the number to be inserted by doing ++cmd");
                }
                else if ( listOfCmds.get(cmd).equals("removeMin") ) {
                    System.out.println("Call method removeMin");
                }
                else if ( listOfCmds.get(cmd).equals("removeMax") ) {
                    System.out.println("Call method removeMax");
                }
            }
            cmd++;
        }
    }

    private static void createFile(String out_path){
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

	private static String transformIntoString(ArrayList<Integer> heap, String heapName){
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

	// save the data in the required format
	private static void saveToFile(String out_path){
        try {
			FileWriter myWriter = new FileWriter(out_path);
			myWriter.write( transformIntoString(max_heap, "max-heap ") + "\n" );
			myWriter.write( transformIntoString(min_heap, "min-heap ") + "\n" );
			if ( buffer == null ) {
				myWriter.write( "buffer " + "\n" );
			}
			else {
				myWriter.write( "buffer " + buffer + "\n" );
			}
			myWriter.close();
			System.out.println("Successfully wrote to the file.");
		}
		catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

    private static void printEverything (){
        System.out.println("This is max heap: " + );

    }

    public static void main(String[] args) throws Exception{
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \test as \t (ie. as a escape sequence)

        //File file = new File("input1.txt");
        //readAndSave ("C:\\Users\\Leo Tan\\Desktop\\CSI 2110 [D]\\Assignments\\ProgrammingA1\\input1.txt");

        //String location = "C:\\Users\\Leo Tan\\Desktop\\CSI 2110 [D]\\Assignments\\ProgrammingA1\\input1.txt";

        /*String input1 = "input1.txt";
        String output1 = "output1.txt";

        runTheCommands (readEverything(input1));
        createFile(output1);
        saveToFile(output1);
        */

    }

}
