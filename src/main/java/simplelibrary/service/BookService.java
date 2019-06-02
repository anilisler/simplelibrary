package simplelibrary.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import simplelibrary.dao.BookDao;
import simplelibrary.model.Book;


@Path("/books")
public class BookService {

	ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
	BookDao bookDao = (BookDao) context.getBean("BookDao");

	// Get all books
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public List<Book> getBooks_JSON() {
		List<Book> listOfBooks = bookDao.findAll();
		return listOfBooks;
	}

	// Create a new book
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void addBook(Book book) {
		bookDao.create(book);
	}

	// Update a book
	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public void updateBook(Book book) {
		bookDao.update(book);
	}
	
	// Delete a book by title
	@DELETE
    @Path("/{_id}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public int deleteBook(@PathParam("_id") String _id) {
        return bookDao.deleteById(_id);
    }

}
