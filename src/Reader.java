public class Reader {
    private String readerId; // 读者ID
    private String name;     // 读者姓名
    private int totalBorrowed; // 总借阅数量

    // 构造函数
    public Reader(String readerId, String name) {
        this.readerId = readerId;
        this.name = name;
        this.totalBorrowed = 0; // 初始化总借阅数量为0
    }

    // Getter和Setter方法
    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 新增方法：增加借阅数量
    public void incrementTotalBorrowed() {
        this.totalBorrowed++;
    }

    // 获取总借阅数量
    public int getTotalBorrowed() {
        return totalBorrowed;
    }

    // 重写toString方法，方便打印读者信息
    @Override
    public String toString() {
        return "Reader{" +
                "readerId='" + readerId + '\'' +
                ", name='" + name + '\'' +
                ", totalBorrowed=" + totalBorrowed +
                '}';
    }
}
