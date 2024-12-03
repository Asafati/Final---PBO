import config.Database;
import repositories.WordRepository;
import repositories.WordRepositoryDbImpl;
import services.WordService;
import services.WordServiceImpl;
import views.WordTerminalViewImpl;
import views.TodoListView;

public class Main {
    public static void main(String[] args) {

        // Setup koneksi ke database
        Database database = new Database("final_pbo", "root", "", "localhost", "3306");
        database.setup(); // Memastikan koneksi ke database berhasil

        // Inisialisasi WordRepository untuk mengakses data kata
        WordRepository wordRepository = new WordRepositoryDbImpl(database);

        // Inisialisasi WordService yang akan menangani logika bisnis terkait kata dan artinya
        WordService wordService = new WordServiceImpl(wordRepository);

        // Inisialisasi dan menjalankan WordTerminalViewImpl untuk interaksi dengan pengguna
        TodoListView wordView = new WordTerminalViewImpl(wordService);
        wordView.run(); // Menjalankan aplikasi
    }
}
