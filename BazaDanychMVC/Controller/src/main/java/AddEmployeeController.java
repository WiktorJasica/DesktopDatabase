import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//KONTROLER DODAWANIA PRACOWNIKA
public class AddEmployeeController
{
    private AddEmployeeGUI addEmployeeGUI;
    private Employee employee;

    public AddEmployeeController(AddEmployeeGUI addEmployeeGUI)
    {
        this.addEmployeeGUI = addEmployeeGUI;

        addEmployeeGUI.setListeners(new Listeners());
    }

    // Listenery przycisków OK i Cancel - AddEmployeeGUI
    private class Listeners implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            // "Nasłuchiwanie źródła akcji" wykonywanej przez użytkownika aplikacji
            Object source = e.getSource();

            // WCIŚNIĘCIE PRZYCISKU OK
            if(source== addEmployeeGUI.getAddButtonOK())
            {
                // Flaga poprawności wartości wpisanej w pole "Stan konta"
                boolean isAccountBallanceDouble=false;

                // Pobieranie Danych z Pól textowych

                //Pobieranie danych pracownika: imienia, nazwiska, nr telefonu, stanu konta, nazwy banku
                String addName = addEmployeeGUI.getNameDataField().getText();
                String addSurname = addEmployeeGUI.getSurnameDataField().getText();
                String addNrTel = addEmployeeGUI.getNrTelDataField().getText();
                String addBank = addEmployeeGUI.getBankDataField().getText();

                // Pobieranie Stanu Konta Pracownika
                double addAcountBallance=0;

                try
                {
                    addAcountBallance = Double.parseDouble(addEmployeeGUI.getAccountBallanceDataField().getText());
                    isAccountBallanceDouble = true;
                }
                catch (Exception e2)
                {
                    JOptionPane.showMessageDialog(addEmployeeGUI,
                            "Podany stan konta nie jest liczbą. \n Spróbuj jeszcze raz ",
                            "Warning", JOptionPane.ERROR_MESSAGE);
                }

                // Dodawanie nowego pracownika

                // Jeśli wartość wpisana w pole textowe "Stan konta" jest poprawna
                if (isAccountBallanceDouble)
                {
                    //Tworzenie nowego pracownika
                    int tempIdCounter = MainController.getIdCounter(); // Pobieranie aktualnego licznika Id
                    employee = Employee.New()
                            .id(tempIdCounter)
                            .name(addName)
                            .surname(addSurname)
                            .nrTel(addNrTel)
                            .accountBallance(addAcountBallance)
                            .bank(addBank)
                            .build();

                    // Inkrementacja licznika id
                    tempIdCounter++;
                    MainController.setIdCounter(tempIdCounter);

                    //Flaga dodania Pracownika do Listy
                    addEmployeeGUI.setAddConfirm(true);

                    System.out.println(employee);
                    // Zamknięcie Okna AddEmployeeGUI
                    addEmployeeGUI.dispose();
                }
            }

            // WCISNIECIE PRZYCISKU CANCEL
            if(source== addEmployeeGUI.getAddButtonCancel())
            {
                //Flaga dodania Pracownika do Listy
                addEmployeeGUI.setAddConfirm(false);

                // Zamknięcie Okna AddEmployeeGUI
                addEmployeeGUI.dispose();
            }
        }
    }

    //GETTERY I SETTERY
    public Employee getEmployee() {
        return employee;
    }

}
