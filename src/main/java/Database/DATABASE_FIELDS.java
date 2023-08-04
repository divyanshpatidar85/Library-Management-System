package Database;

import javax.swing.border.TitledBorder;

public class DATABASE_FIELDS {
    private String Title;

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setNumBooks(int numBooks) {
        this.numBooks = numBooks;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setPublication(String publication) {
        Publication = publication;
    }

    private String ISBN;
    private String Publication;
    private String Author;
    private int numBooks;

    DATABASE_FIELDS(String Title, String ISBN, String Publication, String Author, int numBooks){

        this.Title = Title;
        this.Author = Author;
        this.Publication = Publication;
        this.ISBN = ISBN;
        this.numBooks = numBooks;
//        validator++;
    }

    DATABASE_FIELDS(String Title, String ISBN, String Publication, String Author){
        this.Title = Title;
        this.Author = Author;
        this.Publication = Publication;
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return Author;
    }

    public String getPublication() {
        return Publication;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public int getNumBooks() {
        return numBooks;
    }
}
