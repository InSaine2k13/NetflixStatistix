package domain;

public class Episode extends Program {
    private int episodeNr;

    public Episode(String title, int duration, int episodeNr) {
        super(title, duration);
        this.episodeNr = episodeNr;
    }

    public int getEpisodeNr() {
        return episodeNr;
    }

    public void setEpisodeNr(int episodeNr) {
        this.episodeNr = episodeNr;
    }

    @Override
    public String toString() {
        return "Episode{" +
                "episodeNr=" + episodeNr +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}
