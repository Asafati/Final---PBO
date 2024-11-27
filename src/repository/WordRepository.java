package repository;

import entities.Word;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WordRepository {
    public void addWord(Word word) throws SQLException {
        String query = "INSERT INTO kamus (kata, arti) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, word.getWord());
            stmt.setString(2, word.getMeaning());
            stmt.executeUpdate();
        }
    }

    public void deleteWord(String word) throws SQLException {
        String query = "DELETE FROM kamus WHERE kata = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, word);
            stmt.executeUpdate();
        }
    }

    public List<Word> getAllWords() throws SQLException {
        String query = "SELECT * FROM kamus";
        List<Word> words = new ArrayList<>();
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                words.add(new Word(rs.getString("kata"), rs.getString("arti")));
            }
        }
        return words;
    }

    public Word getWord(String word) throws SQLException {
        String query = "SELECT * FROM kamus WHERE kata = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, word);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Word(rs.getString("kata"), rs.getString("arti"));
                }
            }
        }
        return null;
    }

    public void updateWord(Word word) throws SQLException {
        String query = "UPDATE kamus SET arti = ? WHERE kata = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, word.getMeaning());
            stmt.setString(2, word.getWord());
            stmt.executeUpdate();
        }
    }
}
