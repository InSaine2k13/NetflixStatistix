package domain;


import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import static org.junit.Assert.*;


public class WatchListTest {
    private JTable episodeList= new JTable();
    @Test
    public void populateEmptyEpisodeList() {
        //Arrange
        int Rowcount=0;
        episodeList.setModel(new DefaultTableModel(
                new Object[][]{

                },
                new String[]{
                        "Titel"
                }
        ));
        ArrayList<String> Tests = new ArrayList<String>();
        Tests.add("a");
        Tests.add("b");
        Tests.add("c");
        Tests.add("d");
        Tests.add("e");
        DefaultTableModel model = (DefaultTableModel) episodeList.getModel();
        Object rowData[] = new Object[1];

        for (String t : Tests) {
            rowData[0] = t;
            model.addRow(rowData);
        }

        //ACT
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }

        //Assert
        assertEquals(model.getRowCount(), Rowcount);
    }
}
