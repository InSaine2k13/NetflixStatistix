package domain;

import java.util.ArrayList;
import java.util.Objects;

public class Serie {
    private String title;
    private String genre;
    private String language;
    private int ageIndication;
    private ArrayList<Episode> episodes;

    public Serie(String title, String genre, String language, int ageIndication) {
        this(title, genre, language, ageIndication, null);
    }

    public Serie(String title, String genre, String language, int ageIndication, ArrayList<Episode> episodes) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.ageIndication = ageIndication;
        this.episodes = episodes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
        return ageIndication;
    }

    public void setAgeIndication(int ageIndication) {
        this.ageIndication = ageIndication;
    }

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    /**
     * Adds an episode to the serie.
     * @param episode
     */
    public void addEpisode(Episode episode) {
        if(episodes == null) {
            episodes = new ArrayList<>();
        }

        episodes.add(episode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return Objects.equals(title, serie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Serie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", ageIndication=" + ageIndication +
                ", episodes=" + episodes +
                '}';
    }
}
