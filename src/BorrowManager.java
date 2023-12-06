import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowManager {
    private List<BorrowRecord> borrowRecords = new ArrayList<>(); // 使用线性表存储借还记录

    /**
     * 借书操作
     *
     * @param reader 读者对象
     * @param book   图书对象
     */
    public void borrowBook(Reader reader, Book book) {
        // 确保书籍可借
        if (!book.isAvailable()) {
            System.out.println("图书不可借。");
            return;
        }

        String readerId = reader.getReaderId();
        String bookId = book.getBookId();
        Date borrowDate = new Date();

        // 使用提取的信息创建 BorrowRecord 对象并添加到列表
        BorrowRecord record = new BorrowRecord(readerId, bookId, borrowDate);
        borrowRecords.add(record);

        // 更新图书和读者的状态
        book.setAvailable(false);
        book.incrementBorrowCount();
        reader.incrementTotalBorrowed();
    }

    /**
     * 还书操作
     *
     * @param reader 读者对象
     * @param book   图书对象
     */
    public void returnBook(Reader reader, Book book) {
        // 查找借阅记录，并更新还书日期
        for (BorrowRecord record : borrowRecords) {
            if (record.getReaderId().equals(reader.getReaderId()) && record.getBookId().equals(book.getBookId()) && record.getReturnDate() == null) {
                record.setReturnDate(new Date());

                // 更新图书状态为可借
                book.setAvailable(true);
                return;
            }
        }
        // 如果没有找到匹配的借阅记录，表示还书失败
        System.out.println("还书失败，未找到匹配的借阅记录。");
    }

    // 其他借还书的方法（根据需要添加）

    // 获取所有借还记录
    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }
}
