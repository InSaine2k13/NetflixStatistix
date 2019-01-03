package domain;

import applicationlayer.AccountController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Set;

public class AccountsList extends JFrame {
    private JPanel panel1;
    private JButton selectBtn;
    private JTable accountsListTable;
    private JTable watchedMoviesTable;

    public AccountsList(){
        buildForm();
        populateAccountsListTable();
    }

    public AccountsList(JTable watchedMoviesTable){
        buildForm();
        populateAccountsListTable();
    }

    public void buildForm(){
        add(panel1);

        setTitle("Account");
        setSize(600,600);
    }

    /**
     * Fills the table on the Account page with all the Accounts.
     */
    public void populateAccountsListTable(){
        accountsListTable.setModel(new DefaultTableModel(
                new Object[][] {

                },
                new String [] {
                        "Naam",
                        "Straat",
                        "Housenummer",
                        "Toevoeging huisnummer",
                        "Woonplaats"
                }
        ));

        //get all accounts
        Set<Account> accounts = AccountController.getInstance().readAllAccounts();
        DefaultTableModel model = (DefaultTableModel) accountsListTable.getModel();
        Object rowData[] = new Object[5];

        for(Account a : accounts){
            rowData[0] = a.getName();
            rowData[1] = a.getStreet();
            rowData[2] = a.getHouseNumber();
            rowData[3] = a.getHouseNumberAddition();
            rowData[4] = a.getResidence();
            model.addRow(rowData);
        }
    }
}
