import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// USUWANIE TABELI I CZYSZCZENIE KONTENERA
public class DeleteDataBaseService
{
    // Usuwanie tabeli i czyszczenie kontenera
    public void deleteDataBase (DataBaseDAO dataBase, DefaultTableModel tableModel)
    {
        // Warunek jeśli baza danych jest pusta
        if(dataBase.isListEmpty())
        {
            JOptionPane.showMessageDialog(null,
                    "Nie możesz usunąć nieistniejącej bazy danych "
                    ,"Warning",JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            // Ostrzeżenie o utracie danych w istniejącej już bazie danych
            int answer = JOptionPane.showConfirmDialog(null,
                    "\"Czy napewno chcesz usunąć Bazę ? "+"\n"+ "Niezapisane dane zostaną utracone"
                       ,"Usuń Bazę",
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if(answer==JOptionPane.YES_OPTION)
            {
                // Usuwanie tabeli
                for (int i = dataBase.EmployeeListSize(); i > 0; i--)
                {
                        tableModel.removeRow(i - 1);
                }
                // Czyszczenie kontenera dataBaseList
                dataBase.listClear();
            }
        }
    }
}
