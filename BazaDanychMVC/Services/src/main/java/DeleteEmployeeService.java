import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// USUWANIE PRACOWNIKA Z KONTENERA I TABELI
public class DeleteEmployeeService
{
    public int deleteEmployee (DataBaseDAO dataBase,Object[] tableRow,DefaultTableModel tableModel,JTable tableView
                                ,int idCounter)
    {
        // Licznik służący do aktualizacji Id pracowników
        int idUpdater = 1;

        // Liczba prządkowa tabeli
        int ordinalNr = 1;

        // Warunek jeśli kontener jest pusty lub nie wybrałęś pracownika
        if(dataBase.isListEmpty()||tableView.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(null,
                    "Nie możesz usunąć pracownika !\nBaza danych jest pusta \nlub nie wybrałeś pracownika z listy ",
                    "Warning",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Usuwanie pracowniaka z kontenera dataBaseList i tabeli
            int rowNumberToDelete = tableView.getSelectedRow(); // Pobieranie rzędu z tabeli do usunięcia
            tableModel.removeRow(rowNumberToDelete);
            dataBase.remoweEmployee(rowNumberToDelete);
            idCounter--;

            // Po usunięciu Pracownka z bazy uaktualniamy id pracowników
            for(int i=0; i<dataBase.EmployeeListSize();i++)
            {
                dataBase.getEmployee(i).setId(idUpdater);
                idUpdater++;
            }

            // Usuwanie wierszy z poprzedniej tabeli
            for(int i=dataBase.EmployeeListSize();i>0;i--)
            {
                tableModel.removeRow(i-1);
            }

            // Uzupełnianie Tabeli

            // Wstawianie danych z kontenera dataBaseList do rzędów tabeli
            for(int i=0; i<dataBase.EmployeeListSize();i++ )
            {
                tableRow[0] = ordinalNr;
                tableRow[1] = dataBase.getEmployee(i).getName();
                tableRow[2] = dataBase.getEmployee(i).getSurname();
                tableRow[3] = dataBase.getEmployee(i).getNrTel();
                tableRow[4] = dataBase.getEmployee(i).getAccountBallance();
                tableRow[5] = dataBase.getEmployee(i).getBank();
                tableModel.addRow(tableRow);
                ordinalNr++;
            }
        }
        return idCounter;
    }
}
