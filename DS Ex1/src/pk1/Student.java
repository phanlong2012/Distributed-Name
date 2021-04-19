package pk1;

import java.io.Serializable;

public class Student implements Serializable{
	private int id;
	private String name;
	private int year;
	
	public Student(int id, String name, int year){
		this.id = id;
		this.name = name;
		this.year = year;
	}
	
	public int getID() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getYear() {
		return year;
	}
	
	public void display() {
		System.out.println("Student: ID: "+id+" - Name: "+name+" - Year: "+year);
	}
}
