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
Нумерация: И3, где И - первая буква названия, 3 - номер полки
 */
package library;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public final class Library {
    private final List<Book> books = new ArrayList<>();

    public Library(Book ... newBooks) {
        for (Book book: newBooks) {
            if (book != null) {
                if (!book.getShelfCode().matches("[A-ZА-Я]\\d+"))
                    book.setShelfCode(generateShelfCode(book.getName()));
                books.add(book);
            }
        }
    }

    public List<Book> getBooks() {
        return new ArrayList<>(this.books);
    }

    private String generateShelfCode(String name) {
        Random random = new Random();
        int code = 1 + random.nextInt(12); //random int between [1; 12]
        char ch = name.charAt(0);
        if (Character.isLetter(ch)) return "" + Character.toUpperCase(ch) + code;
        return "undefined" + code;
    }

    private void shelfCodeFormatCheck(String shelfCode) throws IllegalShelfCodeException {
        if (!shelfCode.matches("[A-ZА-Я]\\d+"))
            throw new IllegalShelfCodeException("Wrong shelfCode");
    }

    private boolean isBookNotFound(int i) {
        if (i == -1) {
            System.out.println("Book not found");
            return true;
        } else return false;
    }

    public boolean add(Book book, String shelfCode) {
        if (shelfCode == null || book == null) return false;

        book.setShelfCode(shelfCode);
        books.add(book);
        return true;
    }

    public boolean delete(Book book) {
        if (book != null) { return books.remove(book); }
        return false;
    }

    public boolean edit(Book book, Book newBook) throws IllegalShelfCodeException {
        if (book == null || newBook == null) return false;
        shelfCodeFormatCheck(newBook.getShelfCode());
        int i = books.indexOf(book);
        if (isBookNotFound(i)) return false;
        books.set(i, newBook);
        return true;

    }

    public boolean move(Book book, String newShelfCode) throws IllegalShelfCodeException {
        if (book == null || newShelfCode == null) return false;
        shelfCodeFormatCheck(newShelfCode);
        int i = books.indexOf(book);
        if (isBookNotFound(i)) return false;
        book.setShelfCode(newShelfCode);
        return true;
    }

    //use null if parameter isn't important
    public List<Book> find(@NotNull Library this, @Nullable String name, @Nullable String author,
                           @Nullable List<String> genres, @Nullable String shelfCode) {
        return books.stream().filter(
                (book) -> (name == null || book.getName().contains(name)) &&
                        (author == null || book.getAuthor().contains(author)) &&
                        (genres == null || book.getGenres().containsAll(genres)) &&
                        (shelfCode == null || book.getShelfCode().equals(shelfCode))
                ).collect(Collectors.toList());
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

}

