import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

// ZAPISYWANIE DO PLIKU NA DYSKU UTWORZONEJ BAZY DANYCH
public class SaveDataFileService
{
    public void saveDataBaseFile(DataBaseDAO dataBase, File file)
    {
        // Okno zapisu pliku
        JFileChooser fc = new JFileChooser("C:\\");

        // Zapisujemy wybrany plik do zmiennej typu file
        if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            file = fc.getSelectedFile();
        }

        // Pobieranie ściezki wybranego pliku
        String path = file.getPath();

        // Zapisywanie Obiektu typu DataBaseDAO do pliku na dysku
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
            out.writeObject(dataBase);
            out.close(); // Zamknięcie otwartego pliku w którym zapisywane były dane
            }
        catch (IOException e1)
            {
            e1.printStackTrace();
            }
    }
}
