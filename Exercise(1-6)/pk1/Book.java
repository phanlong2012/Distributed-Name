package pk1;

public class Book {
	private String title;
	private String publisher;
	private Author author;
	
	public Book(String title, String publisher, String name, int age) {
		this.setTitle(title);
		this.setPublisher(publisher);
		this.setAuthor(new Author(name, age));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
