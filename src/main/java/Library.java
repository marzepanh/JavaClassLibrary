/*
Вариант 21 -- библиотека [Java]
Хранит информацию о книгах и позволяет их искать. Для каждой книги хранится
название, автор и жанр, а также код полки, на которой она лежит (например, А3 или
Г4. Вы можете сами придумать реалистичную систему кодирования полок).
Операции:
1. конструктор
2. добавить/удалить книгу
3. изменить существующую книгу
4. переместить книгу на другую полку
5. поиск книг по разным признакам (по автору, по
   названию, по словам из названия, по жанру, по коду полки, по комбинации этих
   признаков)
Нумерация: И3, где A - номер отдела(и - история), 3 - номер полки
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class Library {
    private final List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public boolean add(Book book, String shelfCode) {
        if (shelfCode == null || book == null) return false;
        book.shelfCode = shelfCode;
        books.add(book);
        return true;
    }

    public boolean delete(Book book) {
        if (book != null) { return books.remove(book); }
        return false;
    }

    public boolean edit(Book book, Book newBook) {
        if (book == null || newBook == null) return false;
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).equals(book)) {
                books.set(i, newBook);
                return true;
            }
        }
        return false;
    }

    public boolean move(Book book, String newShelfCode) {
        if (book == null || newShelfCode == null) return false;
        for (Book cBook: books) {
            if (cBook.equals(book)) {
                cBook.shelfCode = newShelfCode;
                return true;
            }
        }
        return false;
    }

    //use null if parameter isn't important
    public List<Book> find(String name, String author,
                           String genres, String shelfCode) {
        List<Book> result = new ArrayList<>();

        for (Book book: books) {
            if (name != null)
                if (!book.name.contains(name)) continue;
                
            if (author != null) 
                if (!book.author.equals(author)) continue;
                
            boolean g = true;
            List<String> genresList = Arrays.asList(genres.split(",\s*"));
            if (!genresList.isEmpty()) {
                for (String genre: genresList) {
                    if (!book.genres.contains(genre)) {
                        g = false;
                        break;
                    }
                }
                if (!g) continue;
            }
            if (shelfCode != null)
                if (!book.shelfCode.equals(shelfCode)) continue;
            result.add(book);
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Library) {
            Library other = (Library) obj;
            return books.equals(other.books);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(books);
    }

    @Override
    public String toString() {
        return "" + books;
    }

    public static void main(String[] args) {
        //System.out.println("adventure".contains("adventure"));
    }
}
