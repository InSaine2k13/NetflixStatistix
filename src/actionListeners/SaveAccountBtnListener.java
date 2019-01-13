package actionListeners;

import datalayer.DAOAccount;
import domain.Account;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveAccountBtnListener implements ActionListener {

    private boolean editing = false;
    private JTextField name;
    private JTextField street;
    private JTextField houseNumber;
    private JTextField houseNumberAddition;
    private JTextField residence;


    public SaveAccountBtnListener(boolean editing, JTextField name, JTextField street, JTextField houseNumber, JTextField houseNumberAddition, JTextField residence){
        this.editing = editing;
        this.name = name;
        this.street = street;
        this.houseNumber = houseNumber;
        this.houseNumberAddition = houseNumberAddition;
        this.residence = residence;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //make it possible to save the Account in the database
        if ((houseNumberAddition.getText()).length() > 1) {
            JOptionPane.showMessageDialog(null, "House number addition can only be one character max.", "House number addition", JOptionPane.INFORMATION_MESSAGE);
        }else{
            if(editing) {
                DAOAccount.getInstance().saveAccount(name.getText(), street.getText(), houseNumber.getText(), houseNumberAddition.getText(), residence.getText());
            } else {
                DAOAccount.getInstance().newAccount(name.getText(), street.getText(), houseNumber.getText(), houseNumberAddition.getText(), residence.getText());
            }
        }
    }
}
