package pk1;

import java.io.File;
import java.io.StringWriter;

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

public class Object2Doc2 {
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
	public static void convertObject2Doc(Book book) throws ParserConfigurationException  {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		
		// create a document object
		Document doc = builder.newDocument();

		// build a tree
		
		
		Element bookElement = doc.createElement("book");
		
		
		Element titleElement = doc.createElement("title");
		titleElement.appendChild(doc.createTextNode(book.getTitle()+""));
		
		Element publisherElement = doc.createElement("publisher");
		publisherElement.appendChild(doc.createTextNode(book.getPublisher()));
		
		Element authorElement = doc.createElement("author");
		
		Element nameAuthor = doc.createElement("name");
		nameAuthor.appendChild(doc.createTextNode(book.getAuthor().getName()));
		Element ageAuthor = doc.createElement("age");
		ageAuthor.appendChild(doc.createTextNode(book.getAuthor().getAge()+""));
		
		authorElement.appendChild(nameAuthor);
		authorElement.appendChild(ageAuthor);
		
		// for the user element
		bookElement.appendChild(titleElement);
		bookElement.appendChild(publisherElement);
		bookElement.appendChild(authorElement);
		
		// for the root element
		doc.appendChild(bookElement);

		// test
		String xmlString = convertDoc2XmlString(doc);
		System.out.println(xmlString);
	}

	public static void main(String[] args) throws ParserConfigurationException, TransformerException {
		convertObject2Doc(new Book("java", "20-12", "Nha", 40));
	}
}
