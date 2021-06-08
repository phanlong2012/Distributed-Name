package pk1;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Object2Doc {
	public static String convertDoc2XmlString(Document doc) {
		String output = "";
		
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			output = writer.getBuffer().toString();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		return output;
	}
	
	// convert from java object to document object
	public static String convertObject2Doc(ArrayList<Student> studentList) throws ParserConfigurationException  {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// create a document object
		Document doc = builder.newDocument();

		// build a tree
		Element root = doc.createElement("students");
		doc.appendChild(root);
		
		for(Student student : studentList) {
			Element userElement = doc.createElement("student");
			
			
			Element idElement = doc.createElement("id");
			idElement.appendChild(doc.createTextNode(student.getId()+""));
			
			Element nameElement = doc.createElement("name");
			nameElement.appendChild(doc.createTextNode(student.getName()));
			
			Element ageElement = doc.createElement("grade");
			ageElement.appendChild(doc.createTextNode(student.getGrade()+""));
			
			// for the user element
			userElement.appendChild(idElement);
			userElement.appendChild(nameElement);
			userElement.appendChild(ageElement);
			
			// for the root element
			root.appendChild(userElement);
		}
		
		// test
		String xmlString = convertDoc2XmlString(doc);
		return xmlString;
	}

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		//Customer user = new Customer(123, "John", 30);
		//convertObject2Doc(user);
	}
}
