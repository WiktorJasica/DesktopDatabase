import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

// OTWIERANIE PLIKU Z ZAPISANĄ BAZĄ DANYCH, WYŚWIETLANIE POBRANEJ BAZY DANYCH W TABELI
public class OpenFileService extends Component
{
    public DataBaseDAO openDataBaseFile(DataBaseDAO dataBase,File file,DefaultTableModel tableModel,Object[] tableRow)
    {
        String path="";

        // Okno wyboru pliku do otwarcia
        JFileChooser fileChooser = new JFileChooser("C:\\");

        // Wyświetlenie okna wyboru, wybór i zapis pliku do zmiennej typu file
        if(fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            // Warunek wyboru pliku do otwarcia jesli baza danych nie jest pusta
            if(dataBase.EmployeeListSize()!=0)
            {
                // Wyswietlanie ostrzeżenia przed Otwarciem pliku
                int message = JOptionPane.showConfirmDialog(null,
                        "Otwarcie pliku usunie istniejącą bazę danych\n" +
                                "Niezapisane dane zostaną utracone\n" + "Czy napewno chcesz otworzyć ten plik?",
                        "Otwórz plik", JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if (message == JOptionPane.YES_OPTION)
                    {
                        file = fileChooser.getSelectedFile();

                    }
                else
                    {
                        fileChooser.cancelSelection();
                    }
            }
            // Warunek wyboru pliku do otwarcia jeśli baza danych jest pusta
            else
            {
                file = fileChooser.getSelectedFile();

            }
        }

        // Pobieranie ściezki pliku

           path = file.getPath();


        // Pobieranie Obiektu z wybranego pliku
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(path));

            // Usówanie wierszy z poprzedniej tabeli
            for(int i=dataBase.EmployeeListSize();i>0;i--)
            {
                tableModel.removeRow(i-1);
            }

            // Czyszczenie dataBaseList
            dataBase.listClear();

            // Zapis pobranej ArrayList do głównej ArrayList
            dataBase = (DataBaseDAO) inputStream.readObject();

            // Uzupełnianie Tabeli

            // Pobieranie danych z otwartej dataBaseList i uzupełnianie nimi tabeli
            for(int i=0; i<dataBase.EmployeeListSize();i++)
            {
                tableRow[0] = dataBase.getEmployee(i).getId();
                tableRow[1] = dataBase.getEmployee(i).getName();
                tableRow[2] = dataBase.getEmployee(i).getSurname();
                tableRow[3] = dataBase.getEmployee(i).getNrTel();
                tableRow[4] = dataBase.getEmployee(i).getAccountBallance();
                tableRow[5] = dataBase.getEmployee(i).getBank();
                tableModel.addRow(tableRow);
            }
        }
        catch (Exception e1)
        {
            e1.printStackTrace();
        }// Try - Catch ObjectInputStream

        return dataBase;
    }
}
