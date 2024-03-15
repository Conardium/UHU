package miproyecto.core.driver_ports;

import miproyecto.core.domain.Book;

public interface BookService {
	Book getBook(Long id);
}
