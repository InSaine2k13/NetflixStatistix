package domain;

public class Film extends Program {
    private String genre;
    private String language;
    private int AgeIndication;

    public Film(String title, int duration, String genre, String language, int ageIndication) {
        super(title, duration);
        this.genre = genre;
        this.language = language;
        AgeIndication = ageIndication;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getAgeIndication() {
        return AgeIndication;
    }

    public void setAgeIndication(int ageIndication) {
        AgeIndication = ageIndication;
    }

    @Override
    public String toString() {
        return "Film{" +
                "genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", AgeIndication=" + AgeIndication +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
