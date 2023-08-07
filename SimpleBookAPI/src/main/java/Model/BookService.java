package Model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookService {
    Book addbook(Book book);
    Book getBookById(String ID);
    List<Book> getAllBooks();
    Book updateBook(String ID, Book book);
    void deleteBook(String ID);

}
