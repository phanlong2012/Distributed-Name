package pk1;

import java.net.*;
import java.util.List;
import java.util.ResourceBundle;

import application.OpenChatBox;
import application.SampleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.*;

public class ChatServerThread extends Thread{
	private Socket socket = null;
	private ChatServer server = null;
	private int ID;
	private String name;
	private DataInputStream streamIn = null;
	public boolean changeMessageList;
	public boolean changeClientList;

	public ChatServerThread(ChatServer server, Socket socket, String name) throws IOException {
		this.server = server;
		this.socket = socket;
		this.ID = socket.getPort();
		this.name = name;
		
	}
	
	public void run() {
		System.out.println("Server Thread " + ID + " running.");
		while (true) {
			try {
				streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			
				String message = "";
	            while (!message.equals("Bye")) {
	                message = streamIn.readUTF();
	                System.out.println("Chat: "+ChatServer.client);
	                for (ChatServerThread clientSingle : ChatServer.client) {
	                    DataOutputStream out = new DataOutputStream(clientSingle.getSocket().getOutputStream());
	                    out.writeUTF(name + ": " + message);
	                }
	            }
				
			} catch (IOException ioe) {
			
			}
		}
	}

	public void open() throws IOException {
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
	}

	public void close() throws IOException {
		if (socket != null)
			socket.close();
		if (streamIn != null)
			streamIn.close();
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public String getThreadName() {
		return name;
	}
	
	public void updateMessageChatBox(List<String> clientMessageList) {
		System.out.println("Signal1: "+clientMessageList.size());
		new SampleController().updateContent(clientMessageList);
	}

}
