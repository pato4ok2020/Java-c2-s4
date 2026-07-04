package by.vsu.Book;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class Storage {
    private static Map<Integer, Book> books;
    private static String storage_path;
    public static void init(String storage) {
        storage_path = storage;
        books = load();
    }

    public static Collection<Book> readAll() {
        return books.values();
    }

    public static Book readById(Integer id) {
        return books.get(id);
    }

    public static void create(Book book) {
        if (existsByCode(book.getCode())) {
            throw new IllegalArgumentException("Шифр книги должен быть уникальным!");
        }

        Integer id = 1;
        Set<Integer> ids = books.keySet();
        if (!ids.isEmpty()) {
            id += Collections.max(ids);
        }
        book.setId(id);
        books.put(id, book);
        save();
    }

    public static void update(Book book) {
        Book old = books.get(book.getId());
        if (old == null) {
            throw new IllegalArgumentException("Книга не найдена!");
        } else {
            if (old.getCode().equals(book.getCode()) || !existsByCode(book.getCode())) {
                books.put(book.getId(), book);
            } else {
                throw new IllegalArgumentException("Шифр книги должен быть уникальным!");
            }
        }
        
        save();
    }

    public static void delete(Integer id) {
        books.remove(id);
        save();
    }

    public static boolean existsByCode(String code) {
        for (Book book : books.values()) {
            if (book.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public static Collection<String> getBestAuthors(Collection<Book> books) {
        Map<String, Float> citationIndexOfAuthors = new HashMap<>();

        for (Book book : books) {
            Float citationIndex = book.getCitationIndex();
            String author = book.getAuthor();
            Float citationIndexCurrAuthor = citationIndexOfAuthors.get(author);
            if (citationIndexCurrAuthor != null) {
                citationIndexOfAuthors.put(author, citationIndexCurrAuthor + citationIndex);
            } else {
                citationIndexOfAuthors.put(author, citationIndex);
            }
        }

        Float maxCitationIndex = Collections.max(citationIndexOfAuthors.values());
        Double ACCURACY = 1e-2;
        Collection<String> bestAuthors = new ArrayList<>();
        for (String author : citationIndexOfAuthors.keySet()) {
            if (Math.abs(citationIndexOfAuthors.get(author) - maxCitationIndex) <= ACCURACY) {
                bestAuthors.add(author);
            }
        }

        return bestAuthors;
    }

    private static void save() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(storage_path))) {
            oos.writeObject(books);
        } catch(Exception e){ 
            e.printStackTrace();
        } 
    }

    private static Map<Integer, Book> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(storage_path))) {
            return (Map<Integer, Book>) ois.readObject();
        } catch (Exception ex) {
            return new HashMap<>();
        }
    }
}