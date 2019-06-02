import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import simplelibrary.dao.BookDaoImpl;
import simplelibrary.model.Book;

public class BookDaoImplTest {

	@Autowired
	MongoTemplate mongotemplate;
	
	private static final String COLLECTION = "library";
//	private String dummy;
	
	//@Before // setup()
    public void before() throws Exception {
		BookDaoImpl daoImpl = new BookDaoImpl();
		Book mockBook = PowerMockito.mock(Book.class);
		PowerMockito.when(mockBook.getId()).thenReturn("1");
		PowerMockito.when(mockBook.getTitle()).thenReturn("title1");
		PowerMockito.when(mockBook.getAuthor()).thenReturn("author1");
		
		daoImpl.create(mockBook);
    }
	
	@Test
	public void findAllTest() throws Exception {
		BookDaoImpl daoImpl = new BookDaoImpl();
		Book mockBook1 = PowerMockito.mock(Book.class);
		PowerMockito.when(mockBook1.getTitle()).thenReturn("asd");
		PowerMockito.when(mockBook1.getAuthor()).thenReturn("asd");
		
		List<Book> listOfBooks = new ArrayList<Book>();
		listOfBooks.add(mockBook1);
		
		assertEquals(listOfBooks.get(0).getTitle(), daoImpl.findAll().get(0).getTitle());
		assertEquals(listOfBooks.get(0).getAuthor(), daoImpl.findAll().get(0).getAuthor());
		
	}
	
	@Test
	public void createTest() throws Exception {
		BookDaoImpl daoImpl = new BookDaoImpl();
		Book mockBook = PowerMockito.mock(Book.class);
		PowerMockito.when(mockBook.getId()).thenReturn("1");
		PowerMockito.when(mockBook.getTitle()).thenReturn("title1");
		PowerMockito.when(mockBook.getAuthor()).thenReturn("author1");
		
		Query query = new Query(Criteria.where("_id").is(mockBook.getId()));
		Book result = this.mongotemplate.findOne(query, Book.class, COLLECTION);
		
		assertNotNull(result);
	}
	
	@Test
	public void updateTest() throws Exception {
		BookDaoImpl daoImpl = new BookDaoImpl();
		Book mockBook = PowerMockito.mock(Book.class);
		PowerMockito.when(mockBook.getId()).thenReturn("1");
		PowerMockito.when(mockBook.getTitle()).thenReturn("title1");
		PowerMockito.when(mockBook.getAuthor()).thenReturn("author1");
		
	}
	
	@Test
	public void deleteByIdTest() throws Exception {
		BookDaoImpl daoImpl = new BookDaoImpl();
		Book mockBook = PowerMockito.mock(Book.class);
		PowerMockito.when(mockBook.getId()).thenReturn("1");
		PowerMockito.when(mockBook.getTitle()).thenReturn("title1");
		PowerMockito.when(mockBook.getAuthor()).thenReturn("author1");
		
		daoImpl.deleteById(mockBook.getId());
		
		Query query = new Query(Criteria.where("_id").is(mockBook.getId()));
		Book result = this.mongotemplate.findOne(query, Book.class, COLLECTION);
		assertNull(result);
	}
	
	@Test
	public void findByTitleTest() throws Exception {
		Book mockBook = PowerMockito.mock(Book.class);
		PowerMockito.when(mockBook.getTitle()).thenReturn("asd");
		PowerMockito.when(mockBook.getAuthor()).thenReturn("asd");
		BookDaoImpl daoImpl = new BookDaoImpl();
		
		assertEquals(mockBook.getTitle(), daoImpl.findByTitle("asd").getTitle());
		assertEquals(mockBook.getAuthor(), daoImpl.findByTitle("asd").getAuthor());
		
		assertNull(daoImpl.findByTitle(""));
	}
	
	
	
	//@After // tearDown()
    public void after() throws Exception {
		BookDaoImpl daoImpl = new BookDaoImpl();
		Book mockBook = PowerMockito.mock(Book.class);
		PowerMockito.when(mockBook.getId()).thenReturn("1");
		PowerMockito.when(mockBook.getTitle()).thenReturn("title1");
		PowerMockito.when(mockBook.getAuthor()).thenReturn("author1");
		
		daoImpl.deleteById(mockBook.getId());
    }
}
