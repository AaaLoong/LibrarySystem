import java.util.*;

public class StatisticManager {
    private BorrowManager borrowManager;
    private BookManager bookManager; // 添加对BookManager的引用

    public StatisticManager(BorrowManager borrowManager, BookManager bookManager) {
        this.borrowManager = borrowManager;
        this.bookManager = bookManager; // 初始化bookManager
    }

    /**
     * 统计借阅次数前十的图书
     *
     * @return 借阅次数前十的图书列表
     */
    public List<Book> topBorrowedBooks() {
        Map<String, Integer> borrowCount = new HashMap<>();

        // 统计每本书的借阅次数
        List<BorrowRecord> borrowRecords = borrowManager.getBorrowRecords();
        for (BorrowRecord record : borrowRecords) {
            String bookId = record.getBookId();
            borrowCount.put(bookId, borrowCount.getOrDefault(bookId, 0) + 1);
        }

        // 对借阅次数进行排序
        List<Map.Entry<String, Integer>> list = new ArrayList<>(borrowCount.entrySet());
        Collections.sort(list, (o1, o2) -> o2.getValue().compareTo(o1.getValue())); // 降序排序

        // 获取借阅量前十的图书
        List<Book> topBooks = new ArrayList<>();
        for (int i = 0; i < 10 && i < list.size(); i++) {
            String bookId = list.get(i).getKey();
            Book book = bookManager.findBookById(bookId); // 根据ID从BookManager获取Book对象
            if (book != null) {
                topBooks.add(book);
            }
        }
        return topBooks;
    }

    // 统计某读者借阅图书的总次数和总册数的方法可以在这里实现
    // TODO: 实现更多的统计方法
}
