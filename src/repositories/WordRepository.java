package repositories;

import entities.Word;

import java.util.List;

public interface WordRepository {
    List<Word> getAll();           // Mengambil semua kata
    void add(Word word);           // Menambahkan kata baru
    Boolean remove(String word);   // Menghapus kata berdasarkan nama
    Boolean edit(Word word);       // Mengubah arti kata
}
