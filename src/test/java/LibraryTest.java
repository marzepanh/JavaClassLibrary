import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    Book book = new Book("HPMOR", "Eliezer Yudkowsky", "adventure", "");
    Book newBook = new Book("HPMOR", "Eliezer Yudkowsky", "", "A5");

    private Library createLibrary(Book book) {
        List<Book> books = new ArrayList<>();
        books.add(book);
        return new Library(books);
    }

    @Test
    public void add() {
        Library result = new Library(new ArrayList<>());
        assertTrue(result.add(book, "A5"));
        assertFalse(result.add(book, null));

        Library library = createLibrary(newBook);
        assertEquals(library, result);
    }

    @Test
    public void delete() {
        Library library = createLibrary(newBook);

        assertTrue(library.delete(newBook));
        assertEquals(library, new Library(new ArrayList<>()));
    }

    @Test
    public void edit() {
        Library library = createLibrary(newBook);
        Book editedBook = new Book("HP and the Methods of Rationality", "Eliezer Yudkowsky", "Hard fantasy, adventure", "A5");
        assertTrue(library.edit(newBook, editedBook));

        Library result = createLibrary(editedBook);
        assertEquals(library, result);
    }

    @Test
    public void move() {
        Library library = createLibrary(newBook);
        assertTrue(library.move(newBook, "B2"));

        Book book = newBook;
        book.shelfCode = "B2";
        Library result = createLibrary(book);
        assertEquals(library, result);
    }

    @Test
    public void find() {
        Library library = createLibrary(newBook);
        Book fantasyBook = new Book("random fantasy", "unknown", "fantasy, adventure", "A5");
        library.add(fantasyBook, "A5");
        List<Book> expected = new ArrayList<>();
        expected.add(fantasyBook);

        List<Book> result = library.find(null,null, "fantasy", null);
        assertEquals(expected, result);
    }
}
