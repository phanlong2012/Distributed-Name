package pk1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Staff3 {
	public static void main(String[] args) {
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("src/pk1/staff3.xml"));
			NodeList nl = doc.getElementsByTagName("company");
			System.out.println("Root Element: "+nl.item(0).getNodeName());
			NodeList nl2 = doc.getElementsByTagName("staff");
			System.out.println("# Staffs: "+nl2.getLength());
			for (int i = 0; i < nl2.getLength(); i++) {
				Element e = (Element) nl2.item(i);
				System.out.println("StaffID: "+e.getAttribute("id"));
				System.out.println("FirstName: "+e.getElementsByTagName("firstname").item(0).getTextContent());
				System.out.println("LastName: "+e.getElementsByTagName("lastname").item(0).getTextContent());
				System.out.println("NickName: "+e.getElementsByTagName("nickname").item(0).getTextContent());
				System.out.println("Salary: "+e.getElementsByTagName("salary").item(0).getTextContent());
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
	}
}