package miproyecto.core.driver_ports;

import miproyecto.core.domain.Book;
import miproyecto.core.driven_ports.BookRepository;

public class BookServiceImpl implements BookService {
	
	private BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public Book getBook(Long id) {
		return bookRepository.findById(id);
	}
}
