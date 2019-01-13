package actionListeners;

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ActionEvent;
import domain.WatchList;

public class selectserieforprofile implements ActionListener {


    private JTable serieTable;
    private WatchList mainForm;
    private JButton selectSerieBtn;
    private JTable seriekeuze;

    public selectserieforprofile(JTable serieTable,JTable seriekeuze, WatchList mainForm, JButton selectSerieBtn) {
        this.serieTable = serieTable;
        this.mainForm = mainForm;
        this.selectSerieBtn = selectSerieBtn;
        this.seriekeuze = seriekeuze;
    }
    //get all watched Series from the selected profile
    @Override
    public void actionPerformed(ActionEvent e) {
        //checks text of button
        if (selectSerieBtn.getText().equals("Terug")) {

            //if terug button is clicked, recreate table with series
            mainForm.populateEmptyEpisodeList();
            //change button to kies serie button
            selectSerieBtn.setText("Kies serie");
        } else if(seriekeuze.getSelectedRow()>-1) {
            int column = 0;
            //get selected row index
            int row = seriekeuze.getSelectedRow();
            //get value from the first column of the selected row.
            String value = seriekeuze.getModel().getValueAt(row, column).toString();
            //create table with all episodes and average watch time.
            mainForm.populateepisodeList(value);
            //change button to terug button.
            selectSerieBtn.setText("Terug");
        }

    }
}