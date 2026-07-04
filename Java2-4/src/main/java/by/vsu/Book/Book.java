package by.vsu.Book;

import java.io.Serializable;

// Список книг различных авторов
// (шифр книги, автор, название, год издания, количество страниц, количество ссылок на книгу, индекс цитируемости книги).
// Последнее поле должно рассчитываться автоматически как отношение количества ссылок на книгу к количеству страниц книги.
// В отдельное текстовое поле выводить автора (или список авторов через запятую, если таких авторов несколько)
// с самым высоким индексом цитируемости (суммарный индекс цитируемости всех его книг).

public class Book implements Serializable {
    private Integer id;
    private String code;
    private String author;
    private String title;
    private Integer year;
    private Integer amountPages;
    private Integer amountLinks;
    private Float citationIndex;

    public Book(
            String code, String author, String title, Integer year,
            Integer amountPages, Integer amountLinks
        ) {
        setId(1);
        setCode(code);
        setAuthor(author);
        setTitle(title);
        setYear(year);
        setAmountPages(amountPages);
        setAmountLinks(amountLinks);
        recalcCitationIndex();
    }

    public Integer getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getAmountPages() {
        return amountPages;
    }

    public Integer getAmountLinks() {
        return amountLinks;
    }

    public Float getCitationIndex() {
        return citationIndex;
    }

    public boolean checkId(Integer id) {
        return id > 0;
    }

    public boolean checkCode(String code) {
        return code.length() > 0;
    }

    public boolean checkAuthor(String author) {
        return author.length() > 0;
    }

    public boolean checkTitle(String title) {
        return title.length() > 0;
    }

    public boolean checkYear(Integer year) {
        return year > 0 && year <= 2026;
    }

    public boolean checkAmountPages(Integer amountPages) {
        return amountPages > 0;
    }

    public boolean checkAmountLinks(Integer amountLinks) {
        return amountLinks >= 0;
    }

    public void setId(Integer id) {
        if (!checkId(id)) {
            throw new IllegalArgumentException("Идентификатор должен быть больше нуля!");
        }
        this.id = id;
    }

    public void setCode(String code) {
        if (!checkCode(code)) {
            throw new IllegalArgumentException("Шифр книги должен быть не пустым!");
        }
        this.code = code;
    }

    public void setAuthor(String author) {
        if (!checkAuthor(author)) {
            throw new IllegalArgumentException("Автор книги должен быть не пустым!");
        }
        this.author = author;
    }

    public void setTitle(String title) {
        if (!checkTitle(title)) {
            throw new IllegalArgumentException("Название книги должно быть не пустым!");
        }
        this.title = title;
    }

    public void setYear(int year) {
        if (!checkYear(year)) {
            throw new IllegalArgumentException("Год издания книги должен быть больше нуля и меньше 2027!");
        }
        this.year = year;
    }

    public void setAmountPages(int amountPages) {
        if (!checkAmountPages(amountPages)) {
            throw new IllegalArgumentException("Кол-во страниц должно быть больше нуля!");
        }
        this.amountPages = amountPages;
    }

    public void setAmountLinks(int amountLinks) {
        if (!checkAmountLinks(amountLinks)) {
            throw new IllegalArgumentException("Кол-во ссылок должно быть больше нуля!");
        }
        this.amountLinks = amountLinks;
    }

    private void recalcCitationIndex() {
        citationIndex = Math.round((amountLinks.floatValue() / amountPages.floatValue()) * 100) / 100.f;
    }
}