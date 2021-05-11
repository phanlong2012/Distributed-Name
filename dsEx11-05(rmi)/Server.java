import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Server implements MediaManagement {
    public Server() {}

    public List<Media> getBooks() throws RemoteException{
		List<Media> list = new ArrayList<Media>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dsEx5";
			Connection con = DriverManager.getConnection(url, 
			                                             "root", 
														 "lan123");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from book");
			
			while (rs.next()) {
				Media s = new Book(rs.getString(1), rs.getString(2));
				//Media s = new Book(rs.getString(1), rs.getString(2));
				list.add(s);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
        return list;
    }

    
    @Override
	public List<Media> getNews() throws RemoteException {
		List<Media> list = new ArrayList<Media>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/dsEx5";
			Connection con = DriverManager.getConnection(url, 
			                                             "root", 
														 "lan123");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from newspaper");
			
			while (rs.next()) {
				Media s = new Newspaper(rs.getString(1), rs.getString(2));
				//Media s = new Book(rs.getString(1), rs.getString(2));
				list.add(s);
			}
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
        return list;
	}
    
    public static void main(String args[]) {
        try {
            Server obj = new Server();
            MediaManagement stub = (MediaManagement) 
			                          UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("MediaManagement", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

	
}

