package actionListeners;

import domain.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Actionlistener for Kies serie button
 */
public class SelectSerieBtnListener implements ActionListener {

    private JTable serieTable;
    private MainForm mainForm;
    private JButton selectSerieBtn;

    public SelectSerieBtnListener(JTable serieTable, MainForm mainForm, JButton selectSerieBtn) {
        this.serieTable = serieTable;
        this.mainForm = mainForm;
        this.selectSerieBtn = selectSerieBtn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //checks text of button
        if (serieTable.getSelectedRow() > -1) {
            if (selectSerieBtn.getText().equals("Terug")) {
                //if terug button is clicked, recreate table with series
                mainForm.populateSerieTable();
                //change button to kies serie button
                selectSerieBtn.setText("Kies serie");
            } else {
                int column = 0;
                //get selected row index
                int row = serieTable.getSelectedRow();
                //get value from the first column of the selected row.
                String value = serieTable.getModel().getValueAt(row, column).toString();
                //create table with all episodes and average watch time.
                mainForm.populateSeriesTableWithAverageWatchTimePerEpisode(value);
                //change button to terug button.
                selectSerieBtn.setText("Terug");
            }
        }
    }
}
