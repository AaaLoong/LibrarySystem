import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class LibraryManager {
    private List<Book> books;     // 存储图书信息的线性表
    private List<Reader> readers; // 存储读者信息的线性表
    private List<BorrowRecord> borrowRecords; // 存储借阅记录的线性表

    private static final String READERS_FILE = "readers.txt";
    private static final String BOOKS_FILE = "books.txt";

    public LibraryManager() {
        books = new ArrayList<>();    // 初始化图书列表
        readers = new ArrayList<>();  // 初始化读者列表
        borrowRecords = new ArrayList<>(); // 初始化借阅记录列表
    }

    // 省略其他不变的方法...

    // 借书操作
    public boolean borrowBook(String readerId, String bookId) {
        Reader reader = findReaderById(readerId);
        Book book = findBookById(bookId);

        if (reader != null && book != null && book.isAvailable()) {
            book.setAvailable(false); // 更新图书的借阅状态为不可用
            BorrowRecord borrowRecord = new BorrowRecord(readerId, bookId, new Date()); // 创建借书记录
            borrowRecords.add(borrowRecord); // 添加到记录列表中

            reader.incrementTotalBorrowed(); // 更新读者的借阅数量
            return true;
        } else {
            return false; // 借书失败，读者或图书不存在，或图书不可用
        }
    }

    // 还书操作
    public boolean returnBook(String readerId, String bookId) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getReaderId().equals(readerId) && record.getBookId().equals(bookId) && record.getReturnDate() == null) {
                record.setReturnDate(new Date()); // 设置还书日期
                findBookById(bookId).setAvailable(true); // 更新图书的借阅状态为可用
                return true;
            }
        }
        return false; // 还书失败，找不到对应的借阅记录
    }

    // 保存借书记录到文件
    public void saveBorrowRecordsToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(borrowRecords);
        }
    }

    // 从文件加载借书记录
    public void loadBorrowRecordsFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            borrowRecords = (List<BorrowRecord>) in.readObject();
        }
    }

    // 添加图书
    public void addBook(Book book) {
        books.add(book);
    }

    // 添加读者
    public void addReader(Reader reader) {
        readers.add(reader);
    }

    // 查找图书（顺序查找）
    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    // 查找读者（顺序查找）
    public Reader findReaderById(String readerId) {
        for (Reader reader : readers) {
            if (reader.getReaderId().equals(readerId)) {
                return reader;
            }
        }
        return null;
    }

    // 按读者姓名查询借书记录
    public List<BorrowRecord> findBorrowRecordsByName(String name) {
        List<BorrowRecord> records = new ArrayList<>();
        for (BorrowRecord record : borrowRecords) {
            if (findReaderById(record.getReaderId()).getName().equalsIgnoreCase(name)) {
                records.add(record);
            }
        }
        return records;
    }

    // 按借书证编号查询借书记录
    public List<BorrowRecord> findBorrowRecordsByReaderId(String readerId) {
        List<BorrowRecord> records = new ArrayList<>();
        for (BorrowRecord record : borrowRecords) {
            if (record.getReaderId().equals(readerId)) {
                records.add(record);
            }
        }
        return records;
    }

    // 统计特定读者借阅图书的总次数
    public int getTotalBorrowCountForReader(String readerId) {
        int count = 0;
        for (BorrowRecord record : borrowRecords) {
            if (record.getReaderId().equals(readerId)) {
                count++;
            }
        }
        return count;
    }

    // 图书冒泡排序（按书名）
    public void sortBooksByTitle() {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });
    }

    // 读者冒泡排序（按姓名）
    public void sortReadersByName() {
        Collections.sort(readers, new Comparator<Reader>() {
            @Override
            public int compare(Reader r1, Reader r2) {
                return r1.getName().compareTo(r2.getName());
            }
        });
    }

    // 统计最受欢迎的图书（按借阅次数）
    public void printTopBorrowedBooks(int topN) {
        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2) {
                return Integer.compare(b2.getBorrowCount(), b1.getBorrowCount());
            }
        });

        System.out.println("最受欢迎的图书：");
        for (int i = 0; i < Math.min(topN, books.size()); i++) {
            Book book = books.get(i);
            System.out.println(book.getTitle() + " - 借阅次数：" + book.getBorrowCount());
        }
    }

    // 打印特定读者的借阅情况
    public void printReaderBorrowStats(String readerId) {
        Reader reader = findReaderById(readerId);
        if (reader != null) {
            System.out.println(reader.getName() + " 总借阅数量：" + reader.getTotalBorrowed());
        } else {
            System.out.println("没有找到该读者的记录！");
        }
    }
}
