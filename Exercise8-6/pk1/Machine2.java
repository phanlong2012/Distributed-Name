package pk1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;


public class Machine2 {
	private Socket socket = null;
	private Machine2 server = null;
	private static DataInputStream streamIn = null;

	
	public static void main(String[] args) throws ParserConfigurationException {
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
				DataOutputStream streamOut = new DataOutputStream(socket.getOutputStream());
				String xml = streamIn.readUTF();
				ArrayList<Student> studentList1 = Doc2Object.convertXmlString2Document(xml);
				ArrayList<Student> studentList2 = DAO.getStudents("dsEx5");
				ArrayList<Student> mergeStudentList = Machine2.mergeList(studentList1, studentList2);
				streamOut.writeUTF(Object2Doc.convertObject2Doc(mergeStudentList));
				DAO.insertStudents("dsEx5", mergeStudentList);
				
				for (Student s:mergeStudentList) {
					System.out.println(s);
				}
			}
			if (socket != null)
				socket.close();
			if (streamIn != null)
				streamIn.close();
		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	public static ArrayList<Student> mergeList(ArrayList<Student> studentList1, ArrayList<Student> studentList2){
		Set<Student> set = new LinkedHashSet<>(studentList1);
        set.addAll(studentList2);
        ArrayList<Student> combinedList = new ArrayList<Student>(set);
		return combinedList;
	}
}
