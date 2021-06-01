package pk1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;

/*
 * Client represents the client, communicates with server via socket
 * Client can input 2 numbers and get the sum of those from server by socket
*/
public class Client {
	
	public static void main(String args[]) throws IOException, ParserConfigurationException {
		System.out.println("Establishing connection. Please wait ...");
		String serverName = "localhost";
		int serverPort = 1999;
		Socket socket = new Socket(serverName, serverPort);
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream streamIn = new DataInputStream(socket.getInputStream());
		DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
		
		
		Customer user = new Customer(14742, "Long", 20);
		streamOut.writeUTF(Object2Doc.convertObject2Doc(user));
		
    	System.out.println("Client ended");
    	
		console.close();
		streamOut.close();
		socket.close();
	}
}