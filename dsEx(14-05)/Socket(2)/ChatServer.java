package pk1;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ChatServer {
	int i = 1;
	private ServerSocket server = null;
	public static List<ChatServerThread> client = new ArrayList<ChatServerThread>();
	public static int[][] arr = new int[3][3];

	public ChatServer(int port) {
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);

			// listening coming connections
			while (true) {
				System.out.println("Waiting for a client ...");
				addThread(server.accept());
			}
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public void addThread(Socket socket) throws IOException {
		System.out.println("Client accepted: " + socket);
		ChatServerThread newThread = new ChatServerThread(this, socket, "client"+i); 
		client.add(newThread);
		i++;
		System.out.println("Total: "+client.size());
		newThread.open();
		newThread.start();
		
	}

	
	public static void main(String args[]) {
		ChatServer server = new ChatServer(1999);
	}

	public static void play(String message) {
		// TODO Auto-generated method stub
		arr[Integer.parseInt(message.charAt(0)+"")][Integer.parseInt(message.charAt(1)+"")] = Integer.parseInt(message.charAt(2)+"");
	}

	public static String getTic() {
		String res = "Tic:\n";
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) {
				res+=(arr[i][j]+" ");
			}
			res+="\n";
		}
		return res;
	}
	
	public static int[][] getArr() {
		return arr;
	}
}
