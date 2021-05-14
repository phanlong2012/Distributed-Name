import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Server implements MediaManagement {
	public static int[][] arr = new int[3][3];
	public static String winner = "";
	public static int slot = 0;
	
    public Server() {
    	System.err.println(arr[0][0]);
    }
    
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
    /*
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
    }*/
    
    
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
    

	@Override
	public boolean login(String username, String password) throws RemoteException {
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

	@Override
	public void play(int pos1, int pos2, int character) throws RemoteException {
		
		/*
		if(character.equals("name1")){
			Server.arr[pos1][pos2] = 1;
		}
		else {
			Server.arr[pos1][pos2] = 1;
		}*/
		
		Server.arr[pos1][pos2] = character;
		Server.slot++; 
		boolean isWin = Server.check();
		if (isWin) {
			Server.winner = "Player"+character;
		}
	}

	public static boolean check() {
		int[][] tic = Server.arr;
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
		return isWin;
	}

	@Override
	public String getWinner() throws RemoteException {
		// TODO Auto-generated method stub
		return Server.winner;
	}

	@Override
	public int[][] getTic() throws RemoteException {
		// TODO Auto-generated method stub
		return Server.arr;
	}

	@Override
	public int getSlot() throws RemoteException {
		// TODO Auto-generated method stub
		return Server.slot;
	}
}

