import java.io.*;
import java.util.ArrayList;
public class Test{

    private static ArrayList<Integer> max_heap = new ArrayList<Integer>();
    private static ArrayList<Integer> min_heap = new ArrayList<Integer>();
    private static Integer buffer = null;

    private static void readAndSave(String fileName) throws Exception{
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

                printHeap(min_heap);
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

    private static void printHeap (ArrayList<Integer> arrList){
        System.out.println("\nThis is the following Array List:");
        for (int num : arrList) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception{
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \test as \t (ie. as a escape sequence)

        //File file = new File("input1.txt");
        //readAndSave ("C:\\Users\\Leo Tan\\Desktop\\CSI 2110 [D]\\Assignments\\ProgrammingA1\\input1.txt");

        String location = "C:\\Users\\Leo Tan\\Desktop\\CSI 2110 [D]\\Assignments\\ProgrammingA1\\input1.txt";

        runTheCommands (readEverything(location));
    }

}
