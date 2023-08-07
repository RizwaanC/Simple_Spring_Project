package Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImp(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addbook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book getBookById(String ID) {
        return bookRepository.findById(ID).orElse(null);

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(String ID, Book book) {
        Book existingBook = bookRepository.findById(ID).orElse(null);
        if (existingBook != null){
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setISBN(book.getISBN());
            return bookRepository.save(existingBook);
        }
        return null;
    }

    @Override
    public void deleteBook(String ID) {
        bookRepository.deleteById(ID);

    }
}

