package pk1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;


public class Machine1 {
	
	public static void main(String args[]) throws IOException, ParserConfigurationException {
		System.out.println("Establishing connection. Please wait ...");
		String serverName = "localhost";
		int serverPort = 1999;
		Socket socket = new Socket(serverName, serverPort);
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream streamIn = new DataInputStream(socket.getInputStream());
		DataOutputStream  streamOut = new DataOutputStream(socket.getOutputStream());
		
		
		streamOut.writeUTF(Object2Doc.convertObject2Doc(DAO.getStudents("ds")));
		
		
    	while (true) {
    		String xml = streamIn.readUTF();
			ArrayList<Student> studentList2 = Doc2Object.convertXmlString2Document(xml);
			
			for (Student s:studentList2) {
				System.out.println(s);
			}
			
			DAO.insertStudents("ds", studentList2);
			
    		break;
    	}
    
    	System.out.println("Client ended");
    	
		console.close();
		streamOut.close();
		socket.close();
	}
}