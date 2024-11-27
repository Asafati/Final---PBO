package service;

import entities.Word;
import repository.WordRepository;
import java.sql.SQLException;
import java.util.List;

public class WordService {
    private final WordRepository wordRepository = new WordRepository();

    public void addWord(String word, String meaning) throws SQLException {
        wordRepository.addWord(new Word(word, meaning));
    }

    public void deleteWord(String word) throws SQLException {
        wordRepository.deleteWord(word);
    }

    public List<Word> getAllWords() throws SQLException {
        return wordRepository.getAllWords();
    }

    public Word getWord(String word) throws SQLException {
        return wordRepository.getWord(word);
    }

    public void updateWordMeaning(String word, String newMeaning) throws SQLException {
        Word wordToUpdate = getWord(word);
        if (wordToUpdate != null) {
            wordToUpdate.setMeaning(newMeaning);
            wordRepository.updateWord(wordToUpdate);
        } else {
            throw new SQLException("Kata tidak ditemukan.");
        }
    }

    public void saveWordsToFile() {
        // Implementasi untuk menyimpan kata-kata ke file jika diperlukan.
    }
}
