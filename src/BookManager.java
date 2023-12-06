import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookManager {
    private Map<String, Book> books;  // 使用HashMap存储图书信息
    private String filePath; // 文件路径用于保存图书信息

    public BookManager(String filePath) {
        books = new HashMap<>();
        this.filePath = filePath;
        loadBooksFromFile(); // 加载书籍数据
    }
    // 从文件加载书籍数据
    private void loadBooksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    Book book = new Book(parts[0], parts[1], parts[2], parts[3]);
                    books.put(parts[0], book);
                }
            }
        } catch (IOException e) {
            System.err.println("读取书籍数据时发生错误: " + e.getMessage());
        }
    }

    // 获取所有书籍的方法
    public List<Book> getAllBooks() {
        return new ArrayList<>(books.values());
    }
    /**
     * 添加图书到图书管理系统
     *
     * @param book 要添加的图书对象
     */
    public void addBook(Book book) {
        books.put(book.getBookId(), book);
    }

    /**
     * 更新图书信息
     *
     * @param bookNumber   要更新的图书编号
     * @param updatedBook  更新后的图书对象
     */
    public void updateBook(String bookNumber, Book updatedBook) {
        if (books.containsKey(bookNumber)) {
            books.put(bookNumber, updatedBook);
        }
    }

    /**
     * 删除图书信息
     *
     * @param bookNumber 要删除的图书编号
     */
    public void deleteBook(String bookNumber) {
        books.remove(bookNumber);
    }

    /**
     * 保存图书信息到文件
     */
    public void saveBooksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books.values()) {
                writer.write(book.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 查找图书（根据ID）
    public Book findBookById(String bookId) {
        return books.get(bookId);
    }

    // 其他管理图书信息的方法（根据需要添加）
    // TODO: 添加更多管理图书的方法
}
