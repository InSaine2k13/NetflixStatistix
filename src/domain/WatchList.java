package domain;

import actionListeners.SaveWatchList;
import applicationlayer.AccountController;
import applicationlayer.EpisodeController;
import applicationlayer.FilmController;
import applicationlayer.SerieController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Set;

public class WatchList extends JFrame {
    private JTable filmAndSerielist;
    private JPanel panelWatchlist;
    private JTable episodeList;
    private JButton Save;
    private JButton episodesButton;

    private String type;
    private String account;


    public WatchList(String type, String account) {
        this.type = type;
        this.account = account;
        if (type.equals("Serie")){
            episodesButton.setVisible(true);
        }
        BuildForm();
        populatefilmAndSerielist();
        populateepisodeList();
    }

    private void BuildForm() {
        add(panelWatchlist);
        setTitle("Profile");
        setSize(600, 600);

        Save.addActionListener(new SaveWatchList(type, account,filmAndSerielist,episodeList));
    }

    public void populatefilmAndSerielist() {
        filmAndSerielist.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Titel"
                }
        ));

        //get all series
        if (type.equals("Serie")) {
            Set<Serie> series = SerieController.getInstance().readAllSeries();
            DefaultTableModel model = (DefaultTableModel) filmAndSerielist.getModel();
            Object rowData[] = new Object[1];

            for (Serie s : series) {
                rowData[0] = s.getTitle();
                model.addRow(rowData);
            }
        } else if (type.equals("Film")) {
            Set<Film> films = FilmController.getInstance().readAllFilms();
            DefaultTableModel model = (DefaultTableModel) filmAndSerielist.getModel();
            Object rowData[] = new Object[1];

            for (Film f : films) {
                rowData[0] = f.getTitle();
                model.addRow(rowData);
            }
        }
    }

    @Override
    public String toString() {
        return "WatchList{" +
                "type='" + type + '\'' +
                ", account='" + account +
                '}';
    }

    public void populateepisodeList() {
        if (type.equals("Serie")){
        episodeList.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Titel"
                }
        ));

        //get all series
        Set<Episode> episodes = EpisodeController.getInstance().readAllEpisodes();
        DefaultTableModel model = (DefaultTableModel) episodeList.getModel();
        Object rowData[] = new Object[1];

        for (Episode E : episodes) {
            rowData[0] = E.getTitle();
            model.addRow(rowData);
        }
    }
    }
}
