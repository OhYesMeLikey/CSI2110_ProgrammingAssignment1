import java.io.*;
import java.util.ArrayList;
public class Test{

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
            //System.out.println("Line in lower case = " + line);
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
            //System.out.println("This is str = " + str);

            if ( counter == 1 ) {
                if ( str.equals("construct") ) {
                    size = Integer.parseInt(numStr);
                    /*arrOfNum.ensureCapacity(size);
                    System.out.println( "The size is " + size );
                    System.out.println( "The size of arrOfNum is " + arrOfNum.size() );
                    */
                    //System.out.println("construct works");
                }
            }
            else if ( counter == 2 ) {
                //System.out.println("Got to counter 2");
                String [] arrOfStr = line.split(" ");
                /*System.out.println("The following is the array of string");
                for (String s1 : arrOfStr) {
                    System.out.print(s1 + " ");
                }*/
                //System.out.println( "The size of arrOfNum is " + arrOfNum.size() );

                for (int i = 0; i < size; i++) {
                    //System.out.println( "This is arrOfStr at " + i + " = " + arrOfStr[i] );
                    arrOfNum.add( Integer.parseInt(arrOfStr[i]) );
                    //System.out.println(arrOfNum.get(i));
                }

                System.out.println("The following is the ArrayList");
                for (int i = 0; i < arrOfNum.size(); i++) {
                    System.out.print(arrOfNum.get(i) + " ");
                }
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

    public static void main(String[] args) throws Exception{
        // We need to provide file path as the parameter:
        // double backquote is to avoid compiler interpret words
        // like \test as \t (ie. as a escape sequence)

        //File file = new File("input1.txt");
        readAndSave ("input1.txt");

    }
}
