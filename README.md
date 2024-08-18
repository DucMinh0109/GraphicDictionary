**#Graphic Dictionary** 

Mô tả
Đây là một ứng dụng từ điển đồ họa được phát triển bằng JavaFX. Ứng dụng cung cấp giao diện đồ họa đơn giản với hai cột: danh sách từ và giải nghĩa từ.

Tính năng
- Hiển thị danh sách các từ.
- Hiển thị định nghĩa của từng từ trong danh sách.
- Giao diện người dùng đơn giản và dễ sử dụng.
- Nhập từ từ file.
- Xuất từ ra file.

**Cài đặt**  
Để chạy ứng dụng, bạn cần có JDK 17 hoặc cao hơn và JavaFX SDK.

**Cấu trúc dự án**

Dưới đây là cấu trúc của dự án và mô tả chi tiết về các thành phần chính:

- **`src/main/java/application`**: Chứa mã nguồn Java của ứng dụng.
  - **`SimpleDictionaryApp.java`**: Lớp chính của ứng dụng JavaFX, quản lý giao diện người dùng và các hành động của ứng dụng. 
    - **`start(Stage primaryStage)`**: Phương thức khởi động ứng dụng, tạo và thiết lập giao diện người dùng.
    - **`getWords()`**: Phương thức trả về danh sách từ điển, hiện tại chưa có dữ liệu gốc.
    - **`importFromFile(Stage stage)`**: Phương thức nhập dữ liệu từ file.
    - **`exportToFile(Stage stage)`**: Phương thức xuất dữ liệu ra file.
    - **`WordDefinition`**: Lớp nội bộ đại diện cho một từ và định nghĩa của nó.
- **`src/main/resources`**: Thư mục chứa tài nguyên như hình ảnh hoặc tài liệu, nếu có.
- **`build.gradle`**: Tập tin cấu hình Gradle cho dự án.

### Mô Tả Mã Nguồn

- **`TableView<WordDefinition>`**: Đối tượng chính để hiển thị danh sách từ và định nghĩa.
- **`TableColumn<WordDefinition, String>`**: Cột hiển thị từ và định nghĩa trong bảng.
- **`Button`**: Các nút để nhập và xuất dữ liệu.
- **`VBox`**: Layout để sắp xếp các thành phần giao diện theo chiều dọc.
- **`FileChooser`**: Đối tượng cho phép chọn tệp tin để nhập hoặc xuất dữ liệu.

### Các Phương Thức Chính

- **`public static void main(String[] args)`**: Khởi động ứng dụng JavaFX.
- **`public void start(Stage primaryStage)`**: Tạo và thiết lập giao diện người dùng, bao gồm bảng từ điển và các nút chức năng.
- **`private ObservableList<WordDefinition> getWords()`**: Tạo danh sách từ điển rỗng.
- **`private void importFromFile(Stage stage)`**: Nhập dữ liệu từ file vào bảng từ điển.
- **`private void exportToFile(Stage stage)`**: Xuất dữ liệu từ bảng từ điển ra file.

### Lớp `WordDefinition`

- **`private String word`**: Thuộc tính lưu trữ từ.
- **`private String definition`**: Thuộc tính lưu trữ định nghĩa của từ.
- **`public WordDefinition(String word, String definition)`**: Constructor khởi tạo từ và định nghĩa.
- **`public String getWord()`**: Lấy từ.
- **`public void setWord(String word)`**: Đặt từ.
- **`public String getDefinition()`**: Lấy định nghĩa.
- **`public void setDefinition(String definition)`**: Đặt định nghĩa.

```markdown
### Ví Dụ

1. **Chạy Ứng Dụng**
   - Chạy lệnh `./gradlew run` để khởi động ứng dụng.

2. **Nhập Dữ Liệu**
   - Nhấp vào nút "Import from File" để chọn và nhập dữ liệu từ file.

3. **Xuất Dữ Liệu**
   - Nhấp vào nút "Export to File" để lưu dữ liệu hiện tại trong bảng vào file.

**Hướng dẫn sử dụng**
1. Mở ứng dụng.
2. Xem danh sách từ ở cột bên trái.
3. Chọn một từ để xem định nghĩa của nó trong cột bên phải.


