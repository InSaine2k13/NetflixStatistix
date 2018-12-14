package actionListeners;

import domain.MainForm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class KiesSerieButtonListener implements ActionListener {

    private MainForm mainForm;
    private JTable serieAccountTable;
    private JTable accountSerieTable;
    private JButton kiesSerieEnAccountButton;

    public KiesSerieButtonListener(MainForm mainForm, JTable serieAccountTable, JTable accountSerieTable, JButton kiesSerieEnAccountButton) {
        this.mainForm = mainForm;
        this.serieAccountTable = serieAccountTable;
        this.accountSerieTable = accountSerieTable;
        this.kiesSerieEnAccountButton = kiesSerieEnAccountButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = kiesSerieEnAccountButton.getText();

        if(buttonText.equals("Terug")) {
            //reset the table to original state.
            mainForm.populateSerieAccountTable();
            accountSerieTable.setEnabled(true);
            kiesSerieEnAccountButton.setText("Kies serie en account");
        } else {
            int column = 0;
            //get selected row index in account table
            int row = serieAccountTable.getSelectedRow();
            //get value from the first column of the selected row.
            String selectedAccount = serieAccountTable.getModel().getValueAt(row, column).toString();
            //get selected row index in series table
            int serieRow = accountSerieTable.getSelectedRow();
            //get value from first column of the selected row.
            String selctedSerie = accountSerieTable.getModel().getValueAt(serieRow, column).toString();

            if(selctedSerie == null || selectedAccount == null || selctedSerie.isEmpty() || selectedAccount.isEmpty()) {
                return;
            }

            accountSerieTable.setEnabled(false);
            mainForm.populateSerieAccountTableWithAverageWatchTimePerEpisode(selctedSerie, selectedAccount);
            kiesSerieEnAccountButton.setText("Terug");
        }
    }
}
