import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// INTERFEJS GRAFICZNY OKNA DODAWANIA PRACOWNIKA -  AddEmployeeGUI
public class AddEmployeeGUI extends JDialog
{
    private JLabel nameLabel, surnameLabel,nrTelLabel, accountBallanceLabel, bankLabel;
    private JTextField nameDataField, surnameDataField, nrTelDataField, accountBallanceDataField, bankDataField;
    private JButton addButtonOK, addButtonCancel;
    private boolean addConfirm;

    // TWORZENIE ELEMENTÓW AddEmployeeGUI - KONSTRUKTOR AddEmployeeGUI
    public AddEmployeeGUI(JFrame MainViewGUI)
    {
        super (MainViewGUI,"Dodaj Pracownika",true);

        //Etykiety Imię, Nazwisko, Nr Telefonu, Stan Konta, Nazwa Banku
        nameLabel = new JLabel("Imię:");
        surnameLabel = new JLabel("Nazwisko:");
        nrTelLabel = new JLabel("Nr telefonu:");
        accountBallanceLabel = new JLabel("Stan konta:");
        bankLabel = new JLabel("Nazwa banku:");

        // Pola textowe: Imię, Nazwisko, Nr Telefonu, Stan Konta, Nazwa Banku
        nameDataField = new JTextField();
        surnameDataField = new JTextField();
        nrTelDataField = new JTextField();
        accountBallanceDataField = new JTextField();
        bankDataField = new JTextField();

        // Przyciski: OK, Cancel
        addButtonOK = new JButton("Ok");
        addButtonCancel = new JButton("Cancel");

        setViewElements();
        setElementsLayout();
    }

    // DODAWANIE ELEMENTÓW DO AddEmployeeGUI
    private void setViewElements()
    {
        // Etykiety: Imię, Nazwisko, Nr Telefonu, Stan Konta, Nazwa Banku
        add(nameLabel);
        add(surnameLabel);
        add(nrTelLabel);
        add(accountBallanceLabel);
        add(bankLabel);

        // Pola textowe: ImImię, Nazwisko, Nr Telefonu, Stan Konta, Nazwa Banku
        add(nameDataField);
        add(surnameDataField);
        add(nrTelDataField);
        add(accountBallanceDataField);
        add(bankDataField);

        // Przyciski: OK, Cancel
        add(addButtonOK);
        add(addButtonCancel);
    }

    // UKLAD ELEMENTÓW AddEmployeeGUI
    private void setElementsLayout()
    {
        // Układ ramki  AddGUI
        setBounds(500,100,320,320);
        setTitle("Podaj Dane Pracownika");
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Etykiety: Imię, Nazwisko, Nr Telefonu, Stan Konta, Nazwa Banku
        nameLabel.setBounds(10,10,95,30);
        nameLabel.setFont(new Font("Arial",Font.BOLD,14));
        surnameLabel.setBounds(10,50,95,30);
        surnameLabel.setFont(new Font("Arial",Font.BOLD,14));
        nrTelLabel.setBounds(10,90,95,30);
        nrTelLabel.setFont(new Font("Arial",Font.BOLD,14));
        accountBallanceLabel.setBounds(10,130,95,30);
        accountBallanceLabel.setFont(new Font("Arial",Font.BOLD,14));
        bankLabel.setBounds(10,170,95,30);
        bankLabel.setFont(new Font("Arial",Font.BOLD,14));

        // Pola textowe: Imię, Nazwisko, Nr Telefonu, Stan Konta, Nazwa Banku
        nameDataField.setBounds(120,13,170,25);
        surnameDataField.setBounds(120,53,170,25);
        nrTelDataField.setBounds(120,93,170,25);
        accountBallanceDataField.setBounds(120,133,170,25);
        bankDataField.setBounds(120,173,170,25);

        // Przyciski: OK, Cancel
        addButtonOK.setBounds(53,225,70,30);
        addButtonCancel.setBounds(153,225,100,30);

    }
    // LISTENERY PRZYCISKÓW
    public void setListeners(ActionListener listener)
    {
        addButtonOK.addActionListener(listener);
        addButtonCancel.addActionListener(listener);
    }

    // GETTERY I SETTERY
    public JTextField getNameDataField() {
        return nameDataField;
    }

    public JTextField getSurnameDataField() {
        return surnameDataField;
    }

    public JTextField getNrTelDataField() {
        return nrTelDataField;
    }

    public JTextField getAccountBallanceDataField() {
        return accountBallanceDataField;
    }

    public JTextField getBankDataField() {
        return bankDataField;
    }

    public JButton getAddButtonOK() {
        return addButtonOK;
    }

    public JButton getAddButtonCancel() {
        return addButtonCancel;
    }

    public boolean getAddConfirm() {
        return addConfirm;
    }

    public void setAddConfirm(boolean addConfirm) {
        this.addConfirm = addConfirm;
    }

}
