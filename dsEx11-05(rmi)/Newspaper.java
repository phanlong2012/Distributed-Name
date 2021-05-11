import java.io.Serializable;

public class Newspaper extends Media  implements Serializable{
	String type;
	
	public Newspaper(String name, String type){
		super(name);
		this.type = type;
	}
	
	public void display() {
		System.out.println("Newspaper: (Name) "+name+", (Type) "+type);
	}
}
