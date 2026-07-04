package by.vsu;

public class Book {
    private String code;
    private String author;
    private String title;
    private int year;
    private int pageAmounts;

    public Book(String code, String author, String title, int year, int pageAmounts) {
        this.code = code;
        this.author = author;
        this.title = title;
        this.year = year;
        this.pageAmounts = pageAmounts;
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

    public int getYear() {
        return year;
    }

    public int getPageAmounts() {
        return pageAmounts;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPageAmounts(int pageAmounts) {
        this.pageAmounts = pageAmounts;
    }
}
