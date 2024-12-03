package views;

import entities.Word;
import services.WordService;

import java.util.List;
import java.util.Scanner;

public class WordTerminalViewImpl implements TodoListView {
    private static final Scanner scanner = new Scanner(System.in);
    private final WordService wordService;

    // Konstruktor untuk menginisialisasi WordService
    public WordTerminalViewImpl(WordService wordService) {
        this.wordService = wordService;
    }

    // Method untuk menerima input dari pengguna
    public String input(String info) {
        System.out.print(info + " : ");
        return scanner.nextLine();
    }

    // Menampilkan menu utama
    public void showMainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showWordList(); // Menampilkan daftar kata
            System.out.println("\nMENU:");
            System.out.println("1. Tambah Kata");
            System.out.println("2. Hapus Kata");
            System.out.println("3. Edit Kata");
            System.out.println("4. Keluar");

            String selectedMenu = input("Pilih menu");

            switch (selectedMenu) {
                case "1":
                    showMenuAddWord();
                    break;
                case "2":
                    showMenuRemoveWord();
                    break;
                case "3":
                    showMenuEditWord();
                    break;
                case "4":
                    isRunning = false;
                    System.out.println("Keluar dari aplikasi...");
                    break;
                default:
                    System.out.println("Pilih menu dengan benar.");
            }
        }
    }

    // Menampilkan daftar kata
    public void showWordList() {
        System.out.println("\nDAFTAR KATA:");
        List<Word> wordList = wordService.getWordList();
        if (wordList.isEmpty()) {
            System.out.println("Tidak ada kata.");
        } else {
            for (int i = 0; i < wordList.size(); i++) {
                Word word = wordList.get(i);
                System.out.println((i + 1) + ". " + word.getWord() + " : " + word.getMeaning());
            }
        }
    }

    // Menampilkan menu untuk menambahkan kata
    public void showMenuAddWord() {
        System.out.println("\nMENAMBAH KATA");
        String word = input("Masukkan kata (x untuk batal)");
        if (word.equals("x")) return;

        String meaning = input("Masukkan arti kata (x untuk batal)");
        if (meaning.equals("x")) return;

        wordService.addWord(word, meaning);
        System.out.println("Kata berhasil ditambahkan.");
    }

    // Menampilkan menu untuk menghapus kata
    public void showMenuRemoveWord() {
        System.out.println("\nMENGHAPUS KATA");
        String word = input("Masukkan kata yang ingin dihapus (x untuk batal)");
        if (word.equals("x")) return;

        boolean success = wordService.removeWord(word);
        if (success) {
            System.out.println("Kata berhasil dihapus.");
        } else {
            System.out.println("Gagal menghapus kata: " + word);
        }
    }

    // Menampilkan menu untuk mengedit arti kata
    public void showMenuEditWord() {
        System.out.println("\nMENGEDIT ARTI KATA");
        String word = input("Masukkan kata yang ingin diedit (x untuk batal)");
        if (word.equals("x")) return;

        String newMeaning = input("Masukkan arti kata yang baru (x untuk batal)");
        if (newMeaning.equals("x")) return;

        boolean success = wordService.editWord(word, newMeaning);
        if (success) {
            System.out.println("Arti kata berhasil diubah.");
        } else {
            System.out.println("Gagal mengedit arti kata.");
        }
    }

    // Menjalankan aplikasi dengan tampilan utama
    @Override
    public void run() {
        showMainMenu();
    }
}
