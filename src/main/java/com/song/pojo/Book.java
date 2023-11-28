package com.song.pojo;

/**
 * @author Administrator
 */
public class Book {

    private  Integer id;
    private String bookName;
    private String author;
    private String publisher;
    private Double price;

    public Book() {
    }


    public Book(Integer id, String bookName, String author, String publisher, Double price) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return bookName
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 设置
     * @param bookName
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 获取
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取
     * @return publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * 设置
     * @param publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 获取
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    public String toString() {
        return "Book{id = " + id + ", bookName = " + bookName + ", author = " + author + ", publisher = " + publisher + ", price = " + price + "}";
    }
}
