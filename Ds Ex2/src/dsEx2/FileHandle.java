package dsEx2;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

public class FileHandle {

	public static void main(String[] args) throws IOException {
		// not used in this exercise
		/*
		try {
		      File myObj = new File("file1.txt");
		      Scanner myReader = new Scanner(myObj);
		      FileWriter writer = new FileWriter("file2.txt");
		      while (myReader.hasNextLine()) {
		    	String data = myReader.nextLine();
		    	writer.write(data);
		    	writer.write("\n");
		        System.out.println(data);
        }
        myReader.close();
        writer.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		*/
		List<Advertising> advertisings = ReadFile.readData("advertising.csv");
		ReadFile.writeData("advertising2.csv",advertisings);
	}

}
