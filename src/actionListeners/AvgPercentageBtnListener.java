package actionListeners;

import applicationlayer.SerieController;
import domain.Episode;
import domain.MainForm;
import domain.Serie;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class AvgPercentageBtnListener implements ActionListener {
    private JTable CalcAVGSerie;
    private JLabel label;

    public AvgPercentageBtnListener(JTable calcAVGSerie, JLabel label) {
        this.CalcAVGSerie = calcAVGSerie;
        this.label = label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //loads the average watch time for the whole Serie
        Serie serie = SerieController.getInstance().readSerie(CalcAVGSerie.getValueAt(CalcAVGSerie.getSelectedRow(),0).toString());
        Map<Episode, Integer> episodes = SerieController.getInstance().readAverageWatchTime(serie);
        int percentage=0;
        double Count=0;
        double result=0;
        for(Map.Entry<Episode, Integer> entry : episodes.entrySet()) {
            percentage += entry.getValue();
            Count++;
        }
        if (percentage!=0||Count!=0){
            result=percentage/Count;

        }
        label.setText(result+"%");
    }
}
