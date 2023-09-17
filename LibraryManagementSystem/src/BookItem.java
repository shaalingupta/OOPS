import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class BookItem extends Book {
    private String barcode;
    private boolean isReferenceOnly;
    private boolean borrowed;
    private Date dueDate;
    private double price;
    private BookFormat format;
    private BookStatus status;
    private Date dateOfPurchase;
    private Date publicationDate;
    private Rack placedAt;

    public BookItem(String ISBN, String title, String subject, String publisher, String language, int numberOfPages,
                    String barcode, boolean isReferenceOnly, boolean borrowed, Date dueDate, double price,
                    BookFormat format, BookStatus status, Date dateOfPurchase, Date publicationDate,
                    Rack placedAt) {
        super(ISBN, title, subject, publisher, language, numberOfPages);
        this.barcode = barcode;
        this.isReferenceOnly = isReferenceOnly;
        this.borrowed = borrowed;
        this.dueDate = dueDate;
        this.price = price;
        this.format = format;
        this.status = status;
        this.dateOfPurchase = dateOfPurchase;
        this.publicationDate = publicationDate;
        this.placedAt = placedAt;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public boolean isReferenceOnly() {
        return isReferenceOnly;
    }

    public void setReferenceOnly(boolean referenceOnly) {
        isReferenceOnly = referenceOnly;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public BookFormat getFormat() {
        return format;
    }

    public void setFormat(BookFormat format) {
        this.format = format;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Rack getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Rack placedAt) {
        this.placedAt = placedAt;
    }

    public boolean checkout(String memberId) {
        if (isReferenceOnly) {
            System.out.println("This book is Reference only and can't be issued");
            return false;
        }
        if (!BookLending.lendBook(barcode, memberId)) {
            return false;
        }
        updateBookItemStatus(BookStatus.LOANED);
        return true;
    }

    public void updateBookItemStatus(BookStatus bookStatus) {
        this.setStatus(bookStatus);
    }

    public void updateDueDate(LocalDateTime localDateTime) {
        this.dueDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
