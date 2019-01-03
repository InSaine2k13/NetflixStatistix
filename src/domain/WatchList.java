package domain;

import actionListeners.SaveWatchList;
import actionListeners.selectserieforprofile;
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
    private JTextField Watchpercentage;

    private String type;
    private String Profile;
    private String AccountID;


    public WatchList(String type, String Profile, String AccountID) {
        this.Profile = Profile;
        this.type= type;
        if (type.equals("Serie")) {
            episodesButton.setVisible(true);
        } else {
            episodesButton.setVisible(false);
        }
        this.AccountID=AccountID;
        episodesButton.setText("Kies serie");
        BuildForm();
        populatefilmAndSerielist();
    }

    private void BuildForm() {
        add(panelWatchlist);
        setTitle("Watch");
        setSize(600, 600);

        Save.addActionListener(new SaveWatchList(type, Profile, filmAndSerielist, episodeList,AccountID, Watchpercentage));
        episodesButton.addActionListener(new selectserieforprofile(episodeList,filmAndSerielist,this , episodesButton));
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
            filmAndSerielist.setModel(new DefaultTableModel(
                    new Object[][] {

                    },
                    new String [] {
                            "Title",
                            "Language"
                    }
            ));
            Set<Serie> series = SerieController.getInstance().readAllSeries();
            DefaultTableModel model = (DefaultTableModel) filmAndSerielist.getModel();
            Object rowData[] = new Object[2];

            for (Serie s : series) {
                rowData[0] = s.getTitle();
                rowData[1] = s.getLanguage();
                model.addRow(rowData);
            }
        } else if (type.equals("Film")) {
            filmAndSerielist.setModel(new DefaultTableModel(
                    new Object[][] {

                    },
                    new String [] {
                            "Title",
                            "Language"
                    }
            ));
            Set<Film> films = FilmController.getInstance().readAllFilms();
            DefaultTableModel model = (DefaultTableModel) filmAndSerielist.getModel();
            Object rowData[] = new Object[2];

            for (Film f : films) {
                rowData[0] = f.getTitle();
                rowData[1] = f.getLanguage();
                model.addRow(rowData);
            }
        }
    }

    @Override
    public String toString() {
        return "WatchList{" +
                "type='" + type + '\'' +
                ", account='" + Profile +
                '}';
    }

    public void populateepisodeList(String serie) {
        if (type.equals("Serie")) {
            episodeList.setModel(new DefaultTableModel(
                    new Object[][]{

                    },
                    new String[]{
                            "Titel"
                    }
            ));

            //get all series
            Set<Episode> episodes = EpisodeController.getInstance().readAllEpisodes(serie);
            DefaultTableModel model = (DefaultTableModel) episodeList.getModel();
            Object rowData[] = new Object[1];

            for (Episode E : episodes) {
                rowData[0] = E.getTitle();
                model.addRow(rowData);
            }
    }
    }
    public void populateEmptyEpisodeList() {
        if (type.equals("Serie")) {
            DefaultTableModel model = (DefaultTableModel) episodeList.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }
        }
}
}
