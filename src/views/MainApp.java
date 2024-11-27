package views;

import service.WordService;
import java.util.Scanner;

public class MainApp {
    private static final WordService wordService = new WordService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Kata");
            System.out.println("2. Hapus Kata");
            System.out.println("3. Tampilkan Semua Kata");
            System.out.println("4. Cari Kata");
            System.out.println("5. Tampilkan Kata dengan Prefix");
            System.out.println("6. Tampilkan Kata dengan Suffix");
            System.out.println("7. Edit Arti Kata");
            System.out.println("8. Hitung Total Kata");
            System.out.println("9. Cek Apakah Kata Ada");
            System.out.println("10. Tampilkan Daftar Kata dalam Urutan Alphabet");
            System.out.println("11. Tampilkan Daftar Kata dengan Panjang Tertentu");
            System.out.println("12. Simpan Data ke File");
            System.out.println("13. Keluar");
            System.out.print("Pilih opsi: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Masukkan kata: ");
                        String word = scanner.nextLine();
                        System.out.print("Masukkan arti: ");
                        String meaning = scanner.nextLine();
                        wordService.addWord(word, meaning);
                        System.out.println("Kata berhasil ditambahkan.");
                        break;
                    case 2:
                        System.out.print("Masukkan kata yang ingin dihapus: ");
                        String wordToDelete = scanner.nextLine();
                        wordService.deleteWord(wordToDelete);
                        System.out.println("Kata berhasil dihapus.");
                        break;
                    case 3:
                        wordService.getAllWords().forEach(w -> System.out.println(w.getWord() + ": " + w.getMeaning()));
                        break;
                    case 4:
                        System.out.print("Masukkan kata yang ingin dicari: ");
                        String wordToFind = scanner.nextLine();
                        var foundWord = wordService.getWord(wordToFind);
                        if (foundWord != null) {
                            System.out.println(foundWord.getWord() + ": " + foundWord.getMeaning());
                        } else {
                            System.out.println("Kata tidak ditemukan.");
                        }
                        break;
                    case 5:
                        System.out.print("Masukkan prefix: ");
                        String prefix = scanner.nextLine();
                        wordService.getAllWords().stream()
                                .filter(w -> w.getWord().startsWith(prefix))
                                .forEach(w -> System.out.println(w.getWord() + ": " + w.getMeaning()));
                        break;
                    case 6:
                        System.out.print("Masukkan suffix: ");
                        String suffix = scanner.nextLine();
                        wordService.getAllWords().stream()
                                .filter(w -> w.getWord().endsWith(suffix))
                                .forEach(w -> System.out.println(w.getWord() + ": " + w.getMeaning()));
                        break;
                    case 7:
                        System.out.print("Masukkan kata yang ingin diubah: ");
                        String wordToEdit = scanner.nextLine();
                        System.out.print("Masukkan arti baru: ");
                        String newMeaning = scanner.nextLine();
                        wordService.updateWordMeaning(wordToEdit, newMeaning);
                        System.out.println("Arti kata berhasil diubah.");
                        break;
                    case 8:
                        int totalWords = wordService.getAllWords().size();
                        System.out.println("Total kata dalam kamus: " + totalWords);
                        break;
                    case 9:
                        System.out.print("Masukkan kata yang ingin dicek: ");
                        String wordToCheck = scanner.nextLine();
                        boolean exists = wordService.getWord(wordToCheck) != null;
                        System.out.println("Apakah kata ada? " + (exists ? "Ya" : "Tidak"));
                        break;
                    case 10:
                        wordService.getAllWords().stream()
                                .sorted((w1, w2) -> w1.getWord().compareTo(w2.getWord()))
                                .forEach(w -> System.out.println(w.getWord() + ": " + w.getMeaning()));
                        break;
                    case 11:
                        System.out.print("Masukkan panjang kata: ");
                        int length = scanner.nextInt();
                        scanner.nextLine();
                        wordService.getAllWords().stream()
                                .filter(w -> w.getWord().length() == length)
                                .forEach(w -> System.out.println(w.getWord() + ": " + w.getMeaning()));
                        break;
                    case 12:
                        wordService.saveWordsToFile();
                        System.out.println("Data berhasil disimpan ke file.");
                        break;
                    case 13:
                        running = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
    }
}
