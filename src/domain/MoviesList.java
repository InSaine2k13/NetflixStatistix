package domain;

import javax.swing.*;

public class MoviesList extends JFrame {
    private JButton selectMovieBtn;
    private JList movieList;
    private JPanel panel1;

    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
    }
}
