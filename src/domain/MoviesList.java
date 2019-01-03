package domain;

import applicationlayer.AccountController;
import applicationlayer.FilmController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Set;

public class MoviesList extends JFrame {
    private JButton selectMovieBtn;
    private JList movieList;
    private JPanel panel1;
    private JTable movieListTable;

    public MoviesList(){
        buildForm();
    }

    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
    }

    public void populateMoviesList(){
        movieListTable.setModel(new DefaultTableModel(
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
        Set<Film> films = FilmController.getInstance().readAllFilms();
        DefaultTableModel model = (DefaultTableModel) movieListTable.getModel();
        Object rowData[] = new Object[5];

        for(Film f : films){
           // rowData[0] = f.getName();
           // rowData[1] = f.getStreet();
         //   rowData[2] = f.getHouseNumber();
          //  rowData[3] = f.getHouseNumberAddition();
         //   rowData[4] = f.getResidence();
            model.addRow(rowData);
        }
    }
}
