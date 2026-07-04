package by.vsu;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static void main() throws IOException, URISyntaxException {
        HttpURLConnection connection = getHttpURLConnection();
        List<Book> books = getBooks();

        try (OutputStream os = connection.getOutputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            byte[] input = mapper.writeValueAsBytes(books);
            os.write(input, 0, input.length);
            os.flush();
        }

        System.out.println("Код ответа: " + connection.getResponseCode());

        StringBuffer content = new StringBuffer();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
        }

        System.out.println("Результат запроса:");
        System.out.println(content);

        connection.disconnect();
    }

    private static HttpURLConnection getHttpURLConnection() throws URISyntaxException, IOException {
        String spec = "https://reqbin.com/echo/post/json";
        URL url = new URI(spec).toURL();
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");

        connection.setDoOutput(true);
        return connection;
    }

    private static List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("978-0134685991", "Joshua Bloch", "Effective Java", 2018, 416));
        books.add(new Book("978-0132350884", "Robert Martin", "Clean Code", 2008, 464));
        books.add(new Book("978-1260440232", "Herbert Schildt", "Java: The Complete Reference", 2019, 1248));
        books.add(new Book("978-1491910771", "Kathy Sierra", "Head First Java", 2014, 752));
        books.add(new Book("978-0131872486", "Bruce Eckel", "Thinking in Java", 2006, 1150));
        books.add(new Book("978-0321349606", "Brian Goetz", "Java Concurrency in Practice", 2006, 432));
        books.add(new Book("978-0201633610", "Erich Gamma", "Design Patterns", 1994, 416));
        books.add(new Book("978-1617294945", "Craig Walls", "Spring in Action", 2018, 520));
        books.add(new Book("978-0262033848", "Thomas Cormen", "Introduction to Algorithms", 2009, 1312));
        books.add(new Book("978-0321146533", "Kent Beck", "Test Driven Development", 2002, 240));
        return books;
    }
}