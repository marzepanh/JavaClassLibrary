import java.util.ArrayList;
import java.util.List;

public final class Book {
    String name;
    String author;
    List<String> genres;
    String shelfCode;

    public Book(String name, String author, List<String> genres, String shelfCode) {
        this.name = name;
        this.author = author;
        this.genres = genres;
        this.shelfCode = shelfCode;
    }
}
