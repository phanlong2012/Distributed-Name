package dsEx2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws SQLException {
		int serverPort = 1999;
		ServerSocket serverSocket = null;
		ObjectOutputStream toClient = null;
		ObjectInputStream fromClient = null;
		
		try {
			serverSocket = new ServerSocket(serverPort);
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("Just connected to " + socket.getRemoteSocketAddress());
				
				toClient = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
				fromClient = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				List<Advertising> msgRequest = (List<Advertising>) fromClient.readObject();
				//dao.addOrder(msgRequest.getID(), msgRequest.getName(), msgRequest.getYear());
				toClient.writeObject(Advertising.getTotalSales(msgRequest));
				toClient.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}