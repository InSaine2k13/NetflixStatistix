package domain;

import actionListeners.*;
import applicationlayer.AccountController;
import applicationlayer.FilmController;
import applicationlayer.ProfileController;
import applicationlayer.SerieController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ComponentAdapter;
import java.util.Map;
import java.util.Set;
//test
public class MainForm extends JFrame {
    private JPanel panel1;
    private JTabbedPane tabbedPane;
    private JPanel homeTab;
    private JButton newAccountBtn;
    private JButton deleteAccountBtn;
    private JButton editAccountBtn;
    private JButton newProfileBtn;
    private JButton deleteProfileBtn;
    private JButton watchMovieBtn;
    private JPanel accountsTab;
    private JPanel profilesTab;
    private JPanel serieWatchLengthTab;
    private JPanel serieWatchLengthAccountTab;
    private JPanel watchedMoviesTab;
    private JPanel longestWatchedKidMovieTab;
    private JPanel singleProfileAccountsTab;
    private JPanel audienceRatingsTab;
    private JButton selectMovieButton;
    private JLabel amountOfCompletedViewersLbl;
    private JButton selectSerieButton;
    private JLabel kidMovieNameLbl;
    private JLabel kidMovieLengthLbl;
    private JList watchedMoviesList;
    private JButton selectAccountButton;
    private JButton watchEpisodeBtn;
    private JButton createProfileBtn;
    private JButton selectSerieBtn;
    private JButton kiesSerieEnAccountButton;
    private JTable serieTable;
    private JTable serieAccountTable;
    private JTable AccountSerieTable;
    private JTable accountsTable;
    private JTable profileTable;
    private JButton refreshBtn;
    private JTable singleProfileAccountsTable;
    private JTextField EditprofielNameTxt1;
    private JTextField EditIDNR;
    private JButton terugButton;

    /**
     * Main form setup, run on application start.
     */
    public MainForm(){
        add(panel1);

        setTitle("Netflix Statistix");
        setSize(800,800);

        disableEditingTables();

        populateSerieTable();
        populateAccountSerieTable();
        populateSerieAccountTable();
        populateAccountTable();
        populateProfileTable();
        populateLongestDurationChildFilm();
        populateSingleProfileAccountsTable();

        selectSerieBtn.addActionListener(new SelectSerieBtnListener(serieTable, this, selectSerieBtn));
        kiesSerieEnAccountButton.addActionListener(new KiesSerieButtonListener(this, serieAccountTable, AccountSerieTable, kiesSerieEnAccountButton));
        newAccountBtn.addActionListener(new NewAccountBtnListener());
        editAccountBtn.addActionListener(new EditAccountBtnListener(accountsTable));
        deleteAccountBtn.addActionListener(new DeleteAccountBtnListener(accountsTable));
        createProfileBtn.addActionListener(new AddProfileBtnListener(accountsTable));
        newProfileBtn.addActionListener(new EditProfilBtnListener(profileTable, EditprofielNameTxt1, EditIDNR));
        deleteProfileBtn.addActionListener(new DeleteProfileBtnListener(profileTable));
        watchEpisodeBtn.addActionListener(new profileWatchlist(profileTable,"Serie"));
        watchMovieBtn.addActionListener(new profileWatchlist(profileTable, "Film"));
        selectAccountButton.addActionListener(new SelectAccountBtnListener());

        serieWatchLengthTab.addComponentListener(new ComponentAdapter() {
        });
        refreshBtn.addActionListener(new RefreshAccountsBtnListener(accountsTable, this));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Fills the table on the Serie kijksduur account page with all series.
     */
    public void populateAccountSerieTable() {
            AccountSerieTable.setModel(new DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                            "Titel"
                    }
            ));

            //get all series
            Set<Serie> series = SerieController.getInstance().readAllSeries();
            DefaultTableModel model = (DefaultTableModel) AccountSerieTable.getModel();
            Object rowData[] = new Object[1];

        for(Serie s : series) {
            rowData[0] = s.getTitle();
            model.addRow(rowData);
        }
    }

    /**
     * Fills the table on the Serie kijksduur account page with all accounts.
     */
    public void populateSerieAccountTable() {
        serieAccountTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Titel"
                }
        ));

        //get all accounts
        Set<Account> accounts = AccountController.getInstance().readAllAccounts();
        DefaultTableModel model = (DefaultTableModel) serieAccountTable.getModel();
        Object rowData[] = new Object[1];

        for(Account a : accounts) {
            rowData[0] = a.getName();
            model.addRow(rowData);
        }
    }

    /**
     * Fills the table on the Serie kijksduur page with all series.
     */
    public void populateSerieTable() {
        serieTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Titel"
                }
        ));

        //get all series
        Set<Serie> series = SerieController.getInstance().readAllSeries();
        DefaultTableModel model = (DefaultTableModel) serieTable.getModel();
        Object rowData[] = new Object[1];

        for(Serie s : series) {
            rowData[0] = s.getTitle();
            model.addRow(rowData);
        }
    }
    /**
     * populates the serieTable with the average watch time per episode for the given serie.
     * @param serieTitle
     */
    public void populateSeriesTableWithAverageWatchTimePerEpisode(String serieTitle) {
        serieTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "EpisodeNr",
                        "Titel",
                        "Gemiddeld bekeken percentage"
                }
        ));

        Serie serie = SerieController.getInstance().readSerie(serieTitle);
        Map<Episode, Integer> episodes = SerieController.getInstance().readAverageWatchTime(serie);
        DefaultTableModel model = (DefaultTableModel) serieTable.getModel();
        Object rowData[] = new Object[3];

        for(Map.Entry<Episode, Integer> entry : episodes.entrySet()) {
            rowData[0] = entry.getKey().getEpisodeNr();
            rowData[1] = entry.getKey().getTitle();
            rowData[2] = entry.getValue() + "%";
            model.addRow(rowData);
        }
    }

    /**
     * Populates the serieAccountTable with average watch time per episode for the given serie and account.
     * @param serieTitle
     * @param accountName
     */
    public void populateSerieAccountTableWithAverageWatchTimePerEpisode(String serieTitle, String accountName) {
        serieAccountTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "EpisodeNr",
                        "Titel",
                        "Gemiddeld bekeken percentage"
                }
        ));

        Serie serie = SerieController.getInstance().readSerie(serieTitle);
        Account account = AccountController.getInstance().read(accountName);
        Map<Episode, Integer> episodes = SerieController.getInstance().readAverageWatchTimeForAccount(account, serie);
        DefaultTableModel model = (DefaultTableModel) serieAccountTable.getModel();
        Object rowData[] = new Object[3];

        for(Map.Entry<Episode, Integer> entry : episodes.entrySet()) {
            rowData[0] = entry.getKey().getEpisodeNr();
            rowData[1] = entry.getKey().getTitle();
            rowData[2] = entry.getValue() + "%";
            model.addRow(rowData);
        }

    }
    /**
     * Fills the table on the Account page with all the Accounts.
     */
    public void populateAccountTable(){
        accountsTable.setModel(new DefaultTableModel(
                new Object[][] {

                },
                new String [] {
                        "Naam",
                        "Straat",
                        "Housenummer",
                        "Toevoeging huisnummer",
                        "Woonplaats"
                }
        ));

        //get all accounts
        Set<Account> accounts = AccountController.getInstance().readAllAccounts();
        DefaultTableModel model = (DefaultTableModel) accountsTable.getModel();
        Object rowData[] = new Object[5];

        for(Account a : accounts){
            rowData[0] = a.getName();
            rowData[1] = a.getStreet();
            rowData[2] = a.getHouseNumber();
            rowData[3] = a.getHouseNumberAddition();
            rowData[4] = a.getResidence();
            model.addRow(rowData);
        }
    }
    /**
     * Fills the table on the Profile page with all the Profiles.
     */
    public void populateProfileTable(){
        profileTable.setModel(new DefaultTableModel(
                new Object[][] {

                },
                new String [] {
                        "Profiel naam",
                        "Account"
                }
        ));

        //get all profiles
        Set<Profile> profiles = ProfileController.getInstance().readAllProfiles();
        DefaultTableModel model = (DefaultTableModel) profileTable.getModel();
        Object rowData [] = new Object[1];

        for(Profile p : profiles){
            rowData[0] = p.getName();
            model.addRow(rowData);
        }
    }

    public void populateLongestDurationChildFilm(){
        Film longestDurationChildFilm = FilmController.getInstance().readLongestDurationChildrenFilm();
        kidMovieNameLbl.setText(longestDurationChildFilm.title);
        kidMovieLengthLbl.setText(Integer.toString(longestDurationChildFilm.duration));
    }

    public void populateSingleProfileAccountsTable(){
        singleProfileAccountsTable.setModel(new DefaultTableModel(
                new Object[][] {

                },
                new String [] {
                        "Naam",
                        "Straat",
                        "Housenummer",
                        "Toevoeging huisnummer",
                        "Woonplaats"
                }
        ));

        //get all single profile accounts
        Set<Account> singleProfileAccounts = AccountController.getInstance().readAllSingleProfileAccounts();
        DefaultTableModel model = (DefaultTableModel) singleProfileAccountsTable.getModel();
        Object rowData[] = new Object[5];


        for(Account a : singleProfileAccounts){
            rowData[0] = a.getName();
            rowData[1] = a.getStreet();
            rowData[2] = a.getHouseNumber();
            rowData[3] = a.getHouseNumberAddition();
            rowData[4] = a.getResidence();
            model.addRow(rowData);
        }
    }

    public void disableEditingTables(){
        serieTable.setDefaultEditor(Object.class, null);
        profileTable.setDefaultEditor(Object.class, null);
        accountsTable.setDefaultEditor(Object.class, null);
        serieAccountTable.setDefaultEditor(Object.class, null);
        AccountSerieTable.setDefaultEditor(Object.class, null);
        singleProfileAccountsTable.setDefaultEditor(Object.class, null);
    }
}
