import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Book {
    String name;
    String author;
    String genres;
    String shelfCode;

    public Book(String name, String author, String genres, String shelfCode) {
        this.name = name;
        this.author = author;
        this.genres = genres;
        this.shelfCode = shelfCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Book) {
            Book other = (Book) obj;
            return name != null && name.equals(other.name) &&
                    author != null && author.equals(other.author) &&
                    genres != null && genres.equals(other.genres) &&
                    shelfCode != null && shelfCode.equals(other.shelfCode);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author, genres);
    }

    @Override
    public String toString() {
        return "name: " + name + "\nauthor: " + author +
                "\ngenres: " + genres + "\nshelfCode: " + shelfCode;
    }
}
