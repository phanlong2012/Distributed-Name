import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
	private static String name = "";
	
    private Client() {}

    public static void main(String[] args) {
        String host = "localhost";
        try {
			BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
			
            Registry registry = LocateRegistry.getRegistry(host);
            MediaManagement stub = (MediaManagement) registry.lookup("MediaManagement");
			
			while (true) {
				String line = console.readLine();
				
				boolean login = false;
				while (!login) {
					Scanner sc= new Scanner(System.in);
					String username = sc.nextLine();
					System.out.println("Username: " + username);
					String password = sc.nextLine();
					System.out.println("Password: " + password);
					login = stub.login(username, password);
					System.out.println("Login: " + login);

					Client.name = username;
				}
				
				Thread output = new Thread(new Runnable(){
		            @Override
		            public void run() {
		                try {
		                	int slottt = 0;
		                	while(true) {
		              
			                	if (slottt != stub.getSlot()) {
			                		int[][] arr = stub.getTic();
			                		slottt = stub.getSlot();
			                		for (int i = 0; i<3; i++) {
			    						for (int j=0; j<3; j++) {
			    							System.out.print(arr[i][j]+" ");
			    						}
			    						System.out.println();
			    					}
			                		System.out.println();
			                	}
		                	}
		                	
		                } catch (IOException e) {
		                    e.printStackTrace();
		                }
		            }
		        });
		        output.start();
				
				while(stub.getWinner().equals("")) {
					Scanner sc= new Scanner(System.in);
					System.out.println("Pos1: ");
					int pos1 = sc.nextInt();
					System.out.println("Pos2: ");
					int pos2 = sc.nextInt();
					System.out.println("Character: ");
					int character = sc.nextInt();
					stub.play(pos1, pos2, character);
					
					
				}
				
				System.out.println("Winner: " + stub.getWinner());
				
				/*
				List<Media> rs1 = stub.getBooks();
				List<Media> rs2 = stub.getNews();
				int BookNumber = rs1.size();	
				int NewsNumber = rs2.size();
				System.out.println("Number of Books: " + BookNumber);
				System.out.println("Number of Books: " + NewsNumber);*/
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

