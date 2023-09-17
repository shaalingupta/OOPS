import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Member extends Account {
    private Date dateOfMembership;
    private int totalBooksCheckedOut;

    private List<BookLending> listOfLoanedBooks;

    public Member(String id, String password, Person person, AccountStatus status) {
        super(id, password, person, status);
        this.dateOfMembership = new Date();
        this.totalBooksCheckedOut = 0;
        this.listOfLoanedBooks = new ArrayList<>();
    }

    public int getTotalBooksCheckedOut() {
        return this.totalBooksCheckedOut;
    }

    public void reserveBookItem(BookItem bookItem) {
        // implementation
    }

    public void incrementTotalBooksCheckedOut() {
        this.totalBooksCheckedOut = this.totalBooksCheckedOut+1;
    }

    public void addLoanedBooked(BookLending bookLending){
        this.listOfLoanedBooks.add(bookLending);
    }

    public boolean checkout_book_item(BookItem book_item) {
        if (getTotalBooksCheckedOut() >= Constants.MAX_BOOKS_ISSUED_TO_A_USER) {
            System.out.println("The user has already checked-out maximum number of books");
            return false;
        }
        BookReservation book_reservation = BookReservation.fetchReservationDetails(book_item.getBarcode());
        if (book_reservation != null && book_reservation.getMemberId() != this.getId()) {
            System.out.println("self book is reserved by another member");
            return false;
        } else if (book_reservation != null) {
            book_reservation.setStatus(ReservationStatus.COMPLETED);
        }
        if (!book_item.checkout(this.getId())) {
            return false;
        }
        incrementTotalBooksCheckedOut();
        return true;
    }

    public void checkForFine(String book_item_barcode) {
        BookLending book_lending = BookLending.fetchLendingDetails(book_item_barcode);
        Date due_date = book_lending.getDueDate();
        LocalDate today = LocalDate.now();

        if (today.isAfter(due_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate())) {
            long diff = ChronoUnit.DAYS.between(due_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), today);
            int diff_days = (int) diff;
            Fine.collectFine(this.getId(), diff_days);
        }
    }

    public void returnBookItem(BookItem bookItem) {
        checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null) {
            bookItem.updateBookItemStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
            bookItem.updateBookItemStatus(BookStatus.AVAILABLE);
        }
    }

    public boolean renewBookItem(BookItem bookItem) {
        checkForFine(bookItem.getBarcode());
        BookReservation bookReservation = BookReservation.fetchReservationDetails(bookItem.getBarcode());
        if (bookReservation != null && bookReservation.getMemberId() != getMemberId()) {
            System.out.println("self book is reserved by another member");
            decrementTotalBooksCheckedOut();
            bookItem.updateBookItemStatus(BookStatus.RESERVED);
            bookReservation.sendBookAvailableNotification();
            return false;
        } else if (bookReservation != null) {
            bookReservation.setStatus(ReservationStatus.COMPLETED);
        }
        BookLending.lendBook(bookItem.getBarcode(), getMemberId());
        bookItem.updateDueDate(LocalDateTime.now().plusDays(Constants.MAX_LENDING_DAYS));
        return true;
    }

    private void decrementTotalBooksCheckedOut() {
        this.totalBooksCheckedOut = this.totalBooksCheckedOut-1;
    }

    private String getMemberId() {
        return this.getId();
    }
}