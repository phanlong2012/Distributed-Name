package pk1;

import java.net.*;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;


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
				
				boolean login = false;
				while(!login) {
					String username = streamIn.readUTF();
					String pw = streamIn.readUTF();
					
					login = ChatServerThread.login(username, pw);
					DataOutputStream out = new DataOutputStream(this.getSocket().getOutputStream());
					out.flush();
                    if (login) {
                    	out.writeUTF("true");
                    }
                    else {
                    	out.writeUTF("false");
                    }
				}
				
				String message = "";
	            while (!message.equals("Bye")) {
	                message = streamIn.readUTF();
	                ChatServer.play(message+(this.name.charAt(6)+""));
	                int[][] tic = ChatServer.getArr();
                    
                    boolean isWin = false;
            		if ((tic[0][0] == tic[0][1] && tic[0][1] == tic[0][2] && tic[0][0] != 0) || 
            			(tic[1][0] == tic[1][1] && tic[1][1] == tic[1][2] && tic[1][0] != 0) || 	
            			(tic[2][0] == tic[2][1] && tic[2][1] == tic[2][2] && tic[2][0] != 0) || 
            			(tic[0][0] == tic[1][0] && tic[1][0] == tic[2][0] && tic[0][0] != 0) || 
            			(tic[0][1] == tic[1][1] && tic[1][1] == tic[1][2] && tic[0][1] != 0) || 
            			(tic[0][2] == tic[1][2] && tic[1][2] == tic[2][2] && tic[0][2] != 0) || 
            			(tic[0][0] == tic[1][1] && tic[1][1] == tic[2][2] && tic[0][0] != 0) || 
            			(tic[0][2] == tic[1][1] && tic[1][1] == tic[2][0] && tic[0][2] != 0)
            				) {
            			isWin = true;
            		}
            		
	                for (ChatServerThread clientSingle : ChatServer.client) {
	                    DataOutputStream out = new DataOutputStream(clientSingle.getSocket().getOutputStream());
	                    out.writeUTF(ChatServer.getTic());
	                    if (isWin) {
		                    out.writeUTF("Winner: "+this.name);
	                    }
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
	
	public static boolean login(String username, String password) throws RemoteException {
		//List<Media> list = new ArrayList<Media>();

		boolean valid = false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dsEx5";
			Connection con = DriverManager.getConnection(url, 
			                                             "root", 
														 "lan123");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from login");
			
			while (rs.next()) {
				//Media s = new Newspaper(rs.getString(1), rs.getString(2));
				//Media s = new Book(rs.getString(1), rs.getString(2));
				//list.add(s);
				if (rs.getString(1).equals(username) && rs.getString(2).equals(password)) {
					valid = true;
				}
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return valid;
	}

}
