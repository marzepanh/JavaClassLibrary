import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    Book book = new Book("HPMOR", "Eliezer Yudkowsky", null, null);
    @Test
    public void add() {
        Library library = new Library(new ArrayList<>());
        Book newBook = book;
        newBook.shelfCode = "A5";

        List<Book> books = new ArrayList<>();
        books.add(newBook);
        Library result = new Library(books);

        library.add(book, "A5");

        assertEquals(library, result);
    }
    @Test
    public void delete() {
        Library library = new Library(new ArrayList<>());
        Book newBook = book;
        newBook.shelfCode = "A5";
        library.delete(newBook);
        assertEquals(library, new Library(new ArrayList<>()));
    }


}
