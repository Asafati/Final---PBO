package entities;

public class Word {
    private Integer id;
    private String word;
    private String meaning;

    public Word() {
    }

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    // Konstruktor untuk inisialisasi dengan ID, kata, dan arti
    public Word(Integer id, String word, String meaning) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
    }

    // Getter dan Setter untuk ID
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter dan Setter untuk Kata
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    // Getter dan Setter untuk Arti
    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
