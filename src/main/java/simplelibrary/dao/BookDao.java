package simplelibrary.dao;

import java.util.List;

import simplelibrary.model.Book;

public interface BookDao {

	 public void create(Book book);
	 
	 public void update(Book book);
	 
	 public int deleteById(String _id);
	 
	 public Book findByTitle(String title);
	 
	 public List<Book> findAll();
}
