package domain;

import javax.swing.*;
//not used because of WatchList
public class SerieList extends JFrame{
    private JList list1;
    private JPanel panel1;
    private JButton selectSerieBtn;
    private JButton episodesBtn;

    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
    }
}
