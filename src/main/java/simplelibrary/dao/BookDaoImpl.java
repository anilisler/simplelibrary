package simplelibrary.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.mongodb.WriteResult;
import simplelibrary.model.Book;

public class BookDaoImpl implements BookDao {

	private static final String COLLECTION = "library";

	@Autowired
	MongoTemplate mongoTemplate;

	public Book create(Book book) {
		if (book != null) {
			this.mongoTemplate.insert(book, COLLECTION);
			return book;
		}
		return null;
	}

	public Book update(Book book) {
		if (book != null) {
			this.mongoTemplate.save(book, COLLECTION);
			return book;
		}
		return null;
	}

	public int deleteById(String _id) {
		Query query = new Query(Criteria.where("_id").is(_id));
		WriteResult result = this.mongoTemplate.remove(query, Book.class, COLLECTION);
		return result.getN();
	}

	public Book findByTitle(String title) {
		Query query = new Query(Criteria.where("title").is(title));
		return this.mongoTemplate.findOne(query, Book.class, COLLECTION);
	}

	public List<Book> findAll() {
		return (List<Book>) mongoTemplate.findAll(Book.class, COLLECTION);
	}
	
	public Book findById(String _id) {
		Query query = new Query(Criteria.where("_id").is(_id));
		return this.mongoTemplate.findOne(query, Book.class, COLLECTION);
	}
	

}
