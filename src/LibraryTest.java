import java.util.Scanner;

public class LibraryTest {

    public static void main(String[] args) {
        // 初始化管理器
        BookManager bookManager = new BookManager("books.txt");
        ReaderManager readerManager = new ReaderManager("readers.txt");
        BorrowManager borrowManager = new BorrowManager();
        LibraryManager libraryManager = new LibraryManager();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("欢迎来到图书馆管理系统");
            System.out.println("1. 添加书籍");
            System.out.println("2. 添加读者");
            System.out.println("3. 借书");
            System.out.println("4. 还书");
            System.out.println("5. 查看所有书籍");
            System.out.println("6. 查看所有读者");
            System.out.println("7. 退出");
            System.out.print("请选择一个选项: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符

            switch (choice) {
                case 1:
                    // 添加书籍逻辑
                    System.out.print("请输入书籍ID: ");
                    String bookId = scanner.nextLine();
                    System.out.print("请输入书名: ");
                    String title = scanner.nextLine();
                    System.out.print("请输入作者: ");
                    String author = scanner.nextLine();
                    System.out.print("请输入出版社: ");
                    String publisher = scanner.nextLine();

                    Book newBook = new Book(bookId, title, author, publisher);
                    bookManager.addBook(newBook);
                    System.out.println("书籍添加成功！");
                    break;
                case 2:
                    // 添加读者逻辑
                    System.out.print("请输入读者ID: ");
                    String readerId = scanner.nextLine();
                    System.out.print("请输入读者姓名: ");
                    String name = scanner.nextLine();

                    Reader newReader = new Reader(readerId, name);
                    readerManager.addReader(newReader);
                    System.out.println("读者添加成功！");
                    break;
                case 3:
                    // 借书逻辑
                    System.out.print("请输入读者ID: ");
                    String borrowerId = scanner.nextLine();
                    System.out.print("请输入书籍ID: ");
                    String borrowedBookId = scanner.nextLine();

                    if (libraryManager.borrowBook(borrowerId, borrowedBookId)) {
                        System.out.println("借书成功！");
                    } else {
                        System.out.println("借书失败，请检查读者ID和书籍ID是否正确，以及书籍是否可借。");
                    }
                    break;
                case 4:
                    // 还书逻辑
                    System.out.print("请输入读者ID: ");
                    String returnerId = scanner.nextLine();
                    System.out.print("请输入书籍ID: ");
                    String returnedBookId = scanner.nextLine();

                    if (libraryManager.returnBook(returnerId, returnedBookId)) {
                        System.out.println("还书成功！");
                    } else {
                        System.out.println("还书失败，请检查读者ID和书籍ID是否正确。");
                    }
                    break;
                case 5:
                    // 查看所有书籍
                    System.out.println("目前图书馆中的书籍：");
                    for (Book book : bookManager.getAllBooks()) {
                        System.out.println(book);
                    }
                    break;
                case 6:
                    // 查看所有读者
                    System.out.println("图书馆的注册读者：");
                    for (Reader reader : readerManager.getAllReaders()) {
                        System.out.println(reader);
                    }
                    break;
                case 7:
                    // 退出程序
                    System.out.println("退出图书馆管理系统");
                    scanner.close();
                    return;
                default:
                    System.out.println("无效选项，请重新选择");
                    break;
            }
        }
    }
}
