package pk1;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class ChatServer {
	int i = 1;
	private ServerSocket server = null;
	public static List<ChatServerThread> client = new ArrayList<ChatServerThread>();

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
		String clientString = ChatServer.createString(client);
		for (int counter = 0; counter < client.size(); counter++) {
			DataOutputStream out = new DataOutputStream(client.get(counter).getSocket().getOutputStream());
            out.writeUTF(clientString);
		}
	}
	
	public static String createString(List<ChatServerThread> client) {
		String res = "List of Client: ";
		for (int i = 0; i < client.size(); i++) {
			res+=(client.get(i).getThreadName());
			res+= " ";
		}
		return res;
	}
	
	public static void main(String args[]) {
		ChatServer server = new ChatServer(1999);
	}
}
