package application;

import javafx.application.Application; //Cho phép sử dụng lớp Application trong mã
import javafx.collections.FXCollections; //Dùng để tạo ObservableList
import javafx.collections.ObservableList;
import javafx.scene.Scene; //Dùng để sử dụng lớp Scene, nơi hiển thị
import javafx.scene.control.Button; //Sử dụng lớp Button (nút bấm)
import javafx.scene.control.TableColumn; //Sử dụng lớp TableColumn
import javafx.scene.control.TableView; //Sử dụng lớp TableView
import javafx.scene.control.cell.PropertyValueFactory; //Sử dụng lớp ...
import javafx.scene.layout.VBox; //Sử dụng lớp ...
import javafx.stage.FileChooser; //Sử dụng lớp ...
import javafx.stage.Stage; //Sử dụng lớp ...

import java.io.*; //Tất

public class SimpleDictionaryApp extends Application {

	private TableView<WordDefinition> table; // Giống List, khai báo

	public static void main(String[] args) {
		launch(args); // Khởi động môi trường JavaFX
	}

	@Override
	public void start(Stage primaryStage) { // Lớp Stage là 1 cửa sổ độc lập
		primaryStage.setTitle("Simple English Dictionary"); // Tên của cửa sổ

		// Tạo TableView cho từ điển
		table = new TableView<>(); // Khởi tạo

		// tạo 1 cột có tên là 'Word'
		// Sử dụng TableColumn để khởi tạo các cột trong bảng
		// WordDefinition là cột dữ liệu của các đối tượng mà bảng TableView sẽ chứa
		// String là kiểu dữ liệu mà các cột sẽ hiển thị
		// wordColumn là lên cột khởi tạo, tạo đối tượng TableColumn mới có tên là word
		// Trong javaFx có thể hiển thị một đối tượng dưới dạng một kiểu dữ liệu khác
		// bằng cellValueFactory
		TableColumn<WordDefinition, String> wordColumn = new TableColumn<>("Word");
		wordColumn.setMinWidth(200); // Chiều rộng tối thiểu của cột 200pixels
		wordColumn.setCellValueFactory(new PropertyValueFactory<>("word"));

//wordColumn.setCellValueFactory, đặt giá trị ô trong cột,liên kết các giá trị ô với
		// thuộc tính word của wordDefinition

		// Tạo một cột definition nữa tương tự như cột word
		TableColumn<WordDefinition, String> definitionColumn = new TableColumn<>("Definition");
		definitionColumn.setMinWidth(400);
		definitionColumn.setCellValueFactory(new PropertyValueFactory<>("definition"));

		// Add các cột vào bảng hiện thị, table là đối tượng của tableview
		/*
		 * Phương thức getColumns() trả về một
		 *  danh sách (ObservableList) chứa tất
		 *   cả các cột hiện tại của TableView
		 */
		//Dùng getColumns() để truy cập được vào addAll để thêm các cột vào tableview
		table.getColumns().addAll(wordColumn, definitionColumn);

		//getWord() của WordDefinition
		table.setItems(getWords());
		/*
		 * Phương thức setItems() được sử dụng để thiết lập danh sách dữ liệu mà bảng sẽ hiển thị.
setItems() nhận vào một đối tượng kiểu ObservableList<S>, trong đó S là kiểu dữ liệu của đối tượng mà bảng sẽ hiển thị trong mỗi hàng.
		 */

		// Tạo đối tượng Button cho nhập và xuất file
		Button importButton = new Button("Import from File");
		//Khi click chuột vào importButton thí setOnAction
		//e là tham số đại diện cho sự kiện được truyền vào phương thức
		importButton.setOnAction(e -> importFromFile(primaryStage));

		//Tương tự như importButton
		Button exportButton = new Button("Export to File");
		exportButton.setOnAction(e -> exportToFile(primaryStage));

		// Layout, Vertical Box, trong javaFx VBox là một lớp bố cục để sắp xếp các UI thep chiều dọc từ trên xuống dưới
		VBox vbox = new VBox(table, importButton, exportButton);

		// Scene, vbox sắp xếp các thành phần trong scene theo chiều dọc
		Scene scene = new Scene(vbox, 600, 400);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// Phương thức này để lấy dữ liệu (Thêm và xóa thủ công chứ không phải import file)
	public ObservableList<WordDefinition> getWords() {
		ObservableList<WordDefinition> words = FXCollections.observableArrayList();
		return words;
	}

	// Phương thức import data từ file
	private void importFromFile(Stage stage) {
		//Lớp FileChooser có sẵn trong javaFX
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Dictionary File");
		File file = fileChooser.showOpenDialog(stage);
		/* Dòng lệnh trên có chức năng hiển thị một hộp thoại để người dùng chọn một tệp
		 tin từ hệ thống tệp của họ, và sau khi người dùng chọn một tệp tin, nó sẽ trả
		 về đối tượng File đại diện cho tệp tin đó. stage AKA owner window
		*/ 

		if (file != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
				ObservableList<WordDefinition> words = FXCollections.observableArrayList(); //Giống ArrayList
				String line;
				while ((line = reader.readLine()) != null) {
					// Từ trong file được chia bằng tab
					String[] parts = line.split("\t");
					if (parts.length == 2) {
						//Tạo đối tượng WordDefinition mới nhận vào parts[0] và parts[1], trim() dùng để loại bỏ khoảng trắng thừa
						words.add(new WordDefinition(parts[0].trim(), parts[1].trim()));
					}
				}
				table.setItems(words);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Phương thức để export data ra một file
	private void exportToFile(Stage stage) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Dictionary File");
		File file = fileChooser.showSaveDialog(stage);
		//Giống bên trên nhưng showSaveDialog cho người dùng chọn tệp tin để lưu

		if (file != null) {
			try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
				for (WordDefinition wordDefinition : table.getItems()) {
					// làm đầu ra có các khoảng cách bằng nhau
					String formattedLine = String.format("%-20s %-20s", wordDefinition.getWord(),
							wordDefinition.getDefinition());
					/*Tạo một chuỗi String theo định dạng
					 *  cụ thể để đồng nhất,Định nghĩa một
					 *   chuỗi (String) với chiều rộng tối
					 *    thiểu là 20 ký tự, và căn lề
					 *     trái (dấu - chỉ căn lề trái). 
					 *     Nếu chuỗi ngắn hơn 20 ký tự, 
					 *     các ký tự trắng sẽ được thêm 
					 *     vào bên phải để lấp đầy khoảng 
					 *     trống.
					*/
					writer.write(formattedLine);
					writer.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// WordDefinition class
	public static class WordDefinition {
		private String word;
		private String definition;

		public WordDefinition(String word, String definition) {
			this.word = word;
			this.definition = definition;
		}

		public String getWord() {
			return word;
		}

		public void setWord(String word) {
			this.word = word;
		}

		public String getDefinition() {
			return definition;
		}

		public void setDefinition(String definition) {
			this.definition = definition;
		}
	}
}
