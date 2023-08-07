package Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping
    public  ResponseEntity<Book> getBookById(@PathVariable String ID){
        Book book = bookService.getBookById(ID);
        if (book != null){
            return new ResponseEntity<>(book, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        Book addedBook = bookService.addbook(book);
        return new ResponseEntity<>(addedBook, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Book> updateBook(@PathVariable String ID, @RequestBody Book book){
        Book updeatedBook = bookService.updateBook(ID, book);
        if ( updeatedBook != null){
            return new ResponseEntity<>(updeatedBook, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable String ID){
        bookService.deleteBook(ID);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
