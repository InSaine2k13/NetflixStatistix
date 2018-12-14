package domain;

import javax.swing.*;

public class AccountsList extends JFrame {
    private JPanel panel1;
    private JButton selectBtn;
    private JList Accounts;

    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
    }
}
