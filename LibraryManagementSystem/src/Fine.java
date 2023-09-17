import java.util.Date;

public class Fine {
    private Date creationDate;
    private String bookItemBarcode;
    private String memberId;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    public Fine(Date creationDate, String bookItemBarcode, String memberId) {
        this.creationDate = creationDate;
        this.bookItemBarcode = bookItemBarcode;
        this.memberId = memberId;
    }

    public static void collectFine(String memberId, int days) {
        // Implementation goes here
    }
}
