package repositories;

import config.Database;
import entities.Word;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordRepositoryDbImpl implements WordRepository {
    private final Database database;

    // Konstruktor untuk menginisialisasi objek Database
    public WordRepositoryDbImpl(Database database) {
        this.database = database;
    }

    // Mendapatkan semua kata dari database
    @Override
    public List<Word> getAll() {
        List<Word> wordList = new ArrayList<>();
        String sql = "SELECT * FROM kamus"; // Query untuk mengambil semua kata
        try (Connection connection = database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String word = resultSet.getString("word");
                String meaning = resultSet.getString("meaning");
                wordList.add(new Word(id, word, meaning)); // Menambahkan objek Word ke list
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return wordList;
    }

    // Menambahkan kata baru ke database
    @Override
    public void add(Word word) {
        String sql = "INSERT INTO words (word, meaning) VALUES (?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word.getWord());
            preparedStatement.setString(2, word.getMeaning());
            preparedStatement.executeUpdate();  // Menjalankan query
            System.out.println("Kata berhasil ditambahkan.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Menghapus kata dari database
    @Override
    public Boolean remove(String word) {
        String sql = "DELETE FROM words WHERE word = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Kata berhasil dihapus.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    // Mengedit arti kata dalam database
    @Override
    public Boolean edit(Word word) {
        String sql = "UPDATE words SET meaning = ? WHERE word = ?";
        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, word.getMeaning());
            preparedStatement.setString(2, word.getWord());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Arti kata berhasil diperbarui.");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }
}
