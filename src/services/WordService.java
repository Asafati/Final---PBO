package services;

import entities.Word;

import java.util.List;

public interface WordService {
    List<Word> getWordList();        // Mendapatkan daftar kata
    void addWord(String word, String meaning); // Menambahkan kata baru
    Boolean removeWord(String word); // Menghapus kata
    Boolean editWord(String word, String newMeaning); // Mengedit arti kata
}
