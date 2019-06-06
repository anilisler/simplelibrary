package simplelibrary.dao;

import java.util.List;

import simplelibrary.model.Book;

public interface BookDao {

	 public Book create(Book book);
	 
	 public Book update(Book book);
	 
	 public int deleteById(String _id);
	 
	 public Book findByTitle(String title);
	 
	 public List<Book> findAll();
	 
	 public Book findById(String _id);
}
