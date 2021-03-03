package library;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LibraryTest {
    Book book = new Book("HPMOR", "Eliezer Yudkowsky", new ArrayList<>(), "");
    Book newBook = new Book("HPMOR", "Eliezer Yudkowsky", new ArrayList<>(), "A5");

    @Test
    public void getBooks() {
        List<Book> expected = new ArrayList<>();
        expected.add(book);
        expected.add(newBook);
        assertEquals(new Library(book, newBook).getBooks(), expected);
    }

    @Test
    public void add() {
        Library result = new Library();
        assertTrue(result.add(book, "A5"));
        assertFalse(result.add(book, null));

        Library library = new Library(newBook);
        assertEquals(library, result);
    }

    @Test
    public void delete() {
        Library library = new Library(newBook);

        assertTrue(library.delete(newBook));
        assertFalse(library.delete(null));
        assertEquals(library, new Library());
    }

    @Test
    public void edit() throws IllegalShelfCodeException {
        Library library = new Library(newBook);
        Book editedBook = new Book("HP and the Methods of Rationality",
                "Eliezer Yudkowsky", List.of("hard fantasy", "adventure"), "A5");
        assertTrue(library.edit(newBook, editedBook));
        assertFalse(library.edit(book, editedBook));

        Library result = new Library(editedBook);
        assertEquals(library, result);
    }

    @Test
    public void move() throws IllegalShelfCodeException {
        Library library = new Library(newBook);
        assertTrue(library.move(newBook, "B2"));
        assertFalse(library.move(book, "B5"));

        Book book = newBook;
        book.setShelfCode("B2");
        Library result = new Library(book);
        assertEquals(library, result);
    }

    @Test
    public void find() {
        Library library = new Library(newBook);
        Book fantasyBook = new Book("random fantasy", "unknown",
                List.of("fantasy", "adventure"), "A5");
        library.add(fantasyBook, "A5");
        List<Book> expected = new ArrayList<>();
        expected.add(fantasyBook);

        List<Book> result = library.find(null,null, List.of("fantasy"), null);
        assertEquals(expected, result);
    }

    @Test
    public void findByAuthor() {
        Book anyBook = new Book("name", "Eliezer Yudkowsky", new ArrayList<>(), "A5");
        Library library = new Library(book, newBook, anyBook);
        assertEquals(List.of(newBook, anyBook),
                library.find(null, "Eliezer Yudkowsky", null, "A5"));
    }

    Book testBook = new Book("Mr Mercedes", "Stephen King", List.of("thriller"), "B8");
    @Test
    public void getName() {
        assertEquals("Mr Mercedes", testBook.getName());
    }

    @Test
    public void setName() {
        Book book = testBook;
        book.setName("book");
        assertEquals("book", book.getName());
    }

    @Test
    public void getAuthor() {
        assertEquals("Stephen King", testBook.getAuthor());
    }

    @Test
    public void setAuthor() {
        Book book = testBook;
        book.setAuthor("author");
        assertEquals("author", book.getAuthor());
    }

    @Test
    public void getGenres() {
        assertEquals(List.of("thriller"), testBook.getGenres());
    }

    @Test
    public void setGenres() {
        Book book = testBook;
        book.setGenres(List.of("drama"));
        assertEquals(List.of("drama"), book.getGenres());
    }

    @Test
    public void getShelfCode() {
        assertEquals("B8", testBook.getShelfCode());
    }

    @Test
    public void setShelfCode() {
        Book book = testBook;
        book.setShelfCode("A5");
        assertEquals("A5", book.getShelfCode());
    }

    @Test(expected = IllegalShelfCodeException.class)
    public void ShelfCodeException() throws IllegalShelfCodeException {
        Library library = new Library(book, newBook);
        library.move(book, "shelfCode");
    }

}
