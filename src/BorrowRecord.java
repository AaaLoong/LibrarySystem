import java.util.Date;

public class BorrowRecord {
    private String readerId; // 读者ID
    private String bookId;   // 书籍ID
    private Date borrowDate; // 借书日期
    private Date returnDate; // 还书日期

    public BorrowRecord(String readerId, String bookId, Date borrowDate) {
        this.readerId = readerId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = null; // 初始时设置还书日期为null，表示未归还
    }

    // Getter和Setter方法
    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }
}
