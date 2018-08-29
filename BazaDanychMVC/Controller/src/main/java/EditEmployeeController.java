import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// KONTROLER EDYCJI PRACOWNIKA
public class EditEmployeeController
{
    private EditEmployeeGUI editEmployeeGUI;
    private Employee employee;

    public EditEmployeeController(EditEmployeeGUI editEmployeeGUI,Employee employee)
    {
        this.editEmployeeGUI = editEmployeeGUI;
        this.employee = employee;

        editEmployeeGUI.setListeners(new Listeners());
    }

    // Listenery przycisków OK i Cancel - EditEmployeeGUI
    private class Listeners implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {
            // "Nasłuchiwanie źródła akcji" wykonywanej przez użytkownika aplikacji
            Object source = e.getSource();

            // WCIŚNIĘCIE PRZYCISKU OK
            if(source==editEmployeeGUI.getEditButtonOK())
            {
                double editAccountBallance=0;

                // Flaga poprawności wartości wpisanej w pole "Stan konta"
                boolean isAccountBallanceDouble=false;

                //Pobieranie z pól texstowych imienia, nazwiska, nr telefonu, stanu konta, nazwy banku
                String editName = editEmployeeGUI.getNameDataField().getText();
                String editSurname = editEmployeeGUI.getSurnameDataField().getText();
                String editNrTel = editEmployeeGUI.getNrTelDataField().getText();
                String editBank = editEmployeeGUI.getBankDataField().getText();
                try {
                    editAccountBallance = Double.parseDouble(editEmployeeGUI.getAccountBallanceDataField().getText());
                    isAccountBallanceDouble=true;
                }
                catch(Exception e2){
                    JOptionPane.showMessageDialog(editEmployeeGUI,
                            "Podany stan konta nie jest liczbą. \n Spróbuj jeszcze raz ",
                            "Warning",JOptionPane.ERROR_MESSAGE);
                }

                // EDYCJA PRACOWNIKA

                // Jeśli wartość wpisana w pole textowe "Stan konta" jest poprawna
                if(isAccountBallanceDouble)
                {
                    // Zapisywanie nowych danych
                    employee = Employee.New().id(employee.getId())
                                             .name(editName)
                                             .surname(editSurname)
                                             .nrTel(editNrTel)
                                             .accountBallance(editAccountBallance)
                                             .bank(editBank)
                                             .build();

                    // Flaga zedytowania pracownika
                    editEmployeeGUI.setEditConfirm(true);

                    // Zamknięcie Okna EditEmployeeGUI
                    editEmployeeGUI.dispose();
                }
            }

            // WCISNIECIE PRZYCISKU CANCEL
            if(source==editEmployeeGUI.getEditButtonCancel())
            {
                // Flaga zedytowania pracownika
                editEmployeeGUI.setEditConfirm(false);

                // Zamknięcie Okna EditEmployeeGUI
                editEmployeeGUI.dispose();
            }
        }
    }

    // GETTERY SETTERY
    public Employee getEmployee() {
        return employee;
    }
}
