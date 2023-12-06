public class Book {
    private String bookId;   // 书籍ID
    private String title;    // 书名
    private String author;   // 作者
    private String publisher; // 出版社
    private int borrowCount; // 借阅次数属性
    private boolean available; // 图书是否可借

    // 构造函数
    public Book(String bookId, String title, String author, String publisher) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.borrowCount = 0; // 初始化借阅次数为0
        this.available = true; // 初始时设为可借
    }

    // Getter和Setter方法
    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // 新增方法：增加借阅次数
    public void incrementBorrowCount() {
        this.borrowCount++;
    }

    // 获取借阅次数
    public int getBorrowCount() {
        return borrowCount;
    }

    // 重写toString方法，方便打印图书信息
    @Override
    public String toString() {
        return "Book{" +
                "bookId='" + bookId + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", borrowCount=" + borrowCount +
                ", available=" + available +
                '}';
    }
}
