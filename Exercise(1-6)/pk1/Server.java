package pk1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.w3c.dom.Document;

public class Server {
	private Socket socket = null;
	private Server server = null;
	private static DataInputStream streamIn = null;

	
	public static void main(String[] args) {
		int port = 1999;
		Socket socket = null;
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			System.out.println("Waiting for a client ...");
			while (socket == null) {
				socket =  server.accept();
				streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				String xml = streamIn.readUTF();
				Document doc = Doc2Object.convertXmlString2Document(xml);

				int id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
				String name = doc.getElementsByTagName("name").item(0).getTextContent();
				int age = Integer.parseInt(doc.getElementsByTagName("age").item(0).getTextContent());

				Customer customer = new Customer(id, name, age);
				System.out.println(customer);
			}
			if (socket != null)
				socket.close();
			if (streamIn != null)
				streamIn.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
}
