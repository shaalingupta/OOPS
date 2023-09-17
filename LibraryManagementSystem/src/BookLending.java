import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class BookLending {
    private Date creationDate;
    private Date dueDate;
    private Date returnDate;
    private String bookItemBarcode;

    private String memberId;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookItemBarcode() {
        return bookItemBarcode;
    }

    public void setBookItemBarcode(String bookItemBarcode) {
        this.bookItemBarcode = bookItemBarcode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public BookLending(Date creationDate, Date dueDate, String bookItemBarcode, String memberId) {
        this.creationDate = creationDate;
        this.dueDate = dueDate;
        this.bookItemBarcode = bookItemBarcode;
        this.memberId = memberId;
    }

    public static boolean lendBook(String barcode, String memberId) {
        Date today_date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date due_date =  Date.from(LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS).atStartOfDay(ZoneId.systemDefault()).toInstant());
        // Implementation goes here
        BookLending bookLending = new BookLending(today_date, due_date, barcode, memberId);

        try {
            Member member = Constants.getLibraryMember(memberId);
            member.addLoanedBooked(bookLending);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static BookLending fetchLendingDetails(String barcode) {
        return null;
    }
}