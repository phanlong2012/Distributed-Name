import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Client {
    private Client() {}

    public static void main(String[] args) {
        String host = "localhost";
        try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			
            Registry registry = LocateRegistry.getRegistry(host);
            MediaManagement stub = (MediaManagement) registry.lookup("MediaManagement");
			
			while (true) {
				String line = console.readLine();
				
				List<Media> rs1 = stub.getBooks();
				List<Media> rs2 = stub.getNews();
				int BookNumber = rs1.size();	
				int NewsNumber = rs2.size();
				System.out.println("Number of Books: " + BookNumber);
				System.out.println("Number of Books: " + NewsNumber);
			}
            
        } catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}		
		catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}

