import java.io.Serializable;

public class Book extends Media  implements Serializable{
	String author;
	
	public Book(String name, String author){
		super(name);
		this.author = author;
	}
	
	public void display() {
		System.out.println("Newspaper: (Name) "+name+", (Author) "+author);
	}
}
