package pk1;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class Doc2Object {
	static ArrayList<Student> convertXmlString2Document(String xmlStr) {
		ArrayList<Student> studentList = new ArrayList<Student>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
			Element e = (Element) doc.getElementsByTagName("students").item(0);
			NodeList list = e.getChildNodes();
			for (int i=0; i<list.getLength(); i++) {
				String id = ((Element) list.item(i)).getElementsByTagName("id").item(0).getTextContent();
				String name = ((Element) list.item(i)).getElementsByTagName("name").item(0).getTextContent();
				String grade = ((Element) list.item(i)).getElementsByTagName("grade").item(0).getTextContent();
				
				studentList.add(new Student(Integer.parseInt(id), name, Double.parseDouble(grade)));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentList;
	}
	
	
	/*
	public static void main(String[] args) {
		final String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
				+ "<users><user><id>123</id><name>John</name><age>30</age></user></users>";
		Document doc = convertXmlString2Document(xmlStr);

		int id = Integer.parseInt(doc.getElementsByTagName("id").item(0).getTextContent());
		String name = doc.getElementsByTagName("name").item(0).getTextContent();
		int age = Integer.parseInt(doc.getElementsByTagName("age").item(0).getTextContent());

		Customer user = new Customer(id, name, age);
		System.out.println(user);
	}
	*/
}
