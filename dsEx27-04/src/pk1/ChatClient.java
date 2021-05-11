package pk1;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class ChatClient {
	public static void main(String args[]) {
		Socket socket = null;
		BufferedReader console = null;
		DataOutputStream streamOut = null;
		String serverName = "localhost";
		int serverPort = 1999;
		
		try {
			System.out.println("Establishing connection. Please wait ...");
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);

			console = new BufferedReader(new InputStreamReader(System.in));
			DataInputStream streamIn = new DataInputStream(socket.getInputStream());
			streamOut = new DataOutputStream(socket.getOutputStream());
			

			Thread output = new Thread(new Runnable(){
	            @Override
	            public void run() {
	                try {
	                	String res = "";
	                	while (!res.equals(".bye")) {
	                		res = streamIn.readUTF();
	                		System.out.println(res);
	                	}
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	        output.start();
			
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
		
		
		String line = "";
		while (!line.equals(".bye")) {
			try {
				line = console.readLine();
				streamOut.writeUTF(line);
				streamOut.flush();	
			} catch (IOException ioe) {
				System.out.println("Sending error: " + ioe.getMessage());
			}
		}
		
		try {
			console.close();
			streamOut.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}