import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import simplelibrary.dao.BookDao;
import simplelibrary.model.Book;

@RunWith(MockitoJUnitRunner.class)
public class BookDaoImplTest {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
	BookDao bookDao = (BookDao) context.getBean("BookDao");

	@Before
	public void setup() throws Exception {
		
		// create test data in db
		Book book = new Book();
		book.setId("111111");
		book.setTitle("title1");
		book.setAuthor("author1");
		bookDao.create(book);
	}

	@Test
	public void findAllTest() throws Exception {
		
		Book mockBook = Mockito.mock(Book.class);
		Mockito.when(mockBook.getId()).thenReturn("111111");
		Mockito.when(mockBook.getTitle()).thenReturn("title1");
		Mockito.when(mockBook.getAuthor()).thenReturn("author1");

		List<Book> listOfBooks = bookDao.findAll();
		
		assertEquals(mockBook.getId(), listOfBooks.get(listOfBooks.size() - 1).getId());
		assertEquals(mockBook.getTitle(), listOfBooks.get(listOfBooks.size() - 1).getTitle());
		assertEquals(mockBook.getAuthor(), listOfBooks.get(listOfBooks.size() - 1).getAuthor());
		assertNotEquals(0, listOfBooks.size());
	}

	@Test
	public void createTest() throws Exception {
		
		Book mockBook = Mockito.mock(Book.class);
		Mockito.when(mockBook.getId()).thenReturn("111111");
		Mockito.when(mockBook.getTitle()).thenReturn("title1");
		Mockito.when(mockBook.getAuthor()).thenReturn("author1");
		
		Book book = bookDao.findById(mockBook.getId());
		
		assertNotNull(book);
		assertEquals(mockBook.getId(),book.getId());
		assertEquals(mockBook.getTitle(),book.getTitle());
		assertEquals(mockBook.getAuthor(),book.getAuthor());
	}

	@Test
	public void updateTest() throws Exception {
		
		Book mockBook = Mockito.mock(Book.class);
		Mockito.when(mockBook.getId()).thenReturn("111111");
		Mockito.when(mockBook.getTitle()).thenReturn("title2");
		Mockito.when(mockBook.getAuthor()).thenReturn("author2");
		
		Book book = new Book();
		book.setId("111111");
		book.setTitle("title2");
		book.setAuthor("author2");
		bookDao.update(book);
		
		Book bookUpdated = bookDao.findById(mockBook.getId());

		assertEquals(mockBook.getId(),bookUpdated.getId());
		assertEquals(mockBook.getTitle(),bookUpdated.getTitle());
		assertEquals(mockBook.getAuthor(),bookUpdated.getAuthor());
	}

	@Test
	public void deleteByIdTest() throws Exception {

		bookDao.deleteById("111111");
		
		Book book = bookDao.findById("111111");
		assertNull(book);
	}

	@Test
	public void findByTitleTest() throws Exception {
		Book mockBook = Mockito.mock(Book.class);
		Mockito.when(mockBook.getId()).thenReturn("111111");
		Mockito.when(mockBook.getTitle()).thenReturn("title1");
		Mockito.when(mockBook.getAuthor()).thenReturn("author1");

		Book book = bookDao.findByTitle("title1");
		
		assertEquals(mockBook.getId(),book.getId());
		assertEquals(mockBook.getTitle(),book.getTitle());
		assertEquals(mockBook.getAuthor(),book.getAuthor());
	}

	@After
	public void tearDown() throws Exception {
		// delete test data in db
		bookDao.deleteById("111111");
	}
}
