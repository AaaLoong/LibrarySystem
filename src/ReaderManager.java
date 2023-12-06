import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderManager {
    private List<Reader> readers; // 存储读者信息的列表
    private String filePath; // 用于保存读者信息的文件路径

    public ReaderManager(String filePath) {
        this.readers = new ArrayList<>();
        this.filePath = "Reader.txt";
        loadReadersFromFile(); // 加载读者数据
    }
    // 从文件加载读者数据
    private void loadReadersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    Reader newReader = new Reader(parts[0], parts[1]);
                    readers.add(newReader);
                }
            }
        } catch (IOException e) {
            System.err.println("读取读者数据时发生错误: " + e.getMessage());
        }
    }

    // 获取所有读者的方法
    public List<Reader> getAllReaders() {
        return new ArrayList<>(readers);
    }
    public void addReader(Reader reader) {
        readers.add(reader);
    }

    // 保存读者信息到文件
    public void saveReadersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Reader reader : readers) {
                writer.write(reader.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 更新读者信息
    public void updateReader(String id, Reader updatedReader) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getReaderId().equals(id)) {
                readers.set(i, updatedReader);
                return;
            }
        }
    }

    // 删除读者
    public void deleteReader(String id) {
        readers.removeIf(reader -> reader.getReaderId().equals(id));
    }

    // 查找读者
    public Reader findReaderById(String id) {
        for (Reader reader : readers) {
            if (reader.getReaderId().equals(id)) {
                return reader;
            }
        }
        return null; // 如果未找到，则返回null
    }

    // 其他管理读者信息的方法（如添加更多的查询功能）
    // TODO: 实现更多的读者管理功能
}
