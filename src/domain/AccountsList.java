package domain;

import applicationlayer.AccountController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Set;

public class AccountsList extends JFrame {
    private JPanel panel1;
    private JButton selectBtn;
    private JTable table1;
    private JTable Accounts;

    public AccountsList(){

        fillAccounts();
        buildForm();
    }

    private void fillAccounts() {
        Accounts.setModel(new DefaultTableModel(
                new Object[][] {

                },
                new String [] {
                        "Naam"
                }
        ));

        //get all accounts
        Set<Account> accounts = AccountController.getInstance().readAllAccounts();
        DefaultTableModel model = (DefaultTableModel) Accounts.getModel();
        Object rowData[] = new Object[1];

        for (Account a : accounts){
            rowData[0]=a.getName();
            model.addRow(rowData);
        }
    }


    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
    }
}
