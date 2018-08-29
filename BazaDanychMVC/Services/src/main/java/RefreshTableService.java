import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

// ODŚWIEŻENIE WIDOKU TABELI
public class RefreshTableService
{

    // Odsiweżenie widoku tabeli
    public void refreshTable(JTable tableView, DataBaseDAO dataBase, Object[] tableRow ,DefaultTableModel tableModel
                            ,int sortMode) {

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);

        // Liczba porządkowa "Lp." w tabeli
        int ordinalNumber = 1;

        // Odświeżanie w zalezności od trybu sortowania
        if (sortMode == 0)
        {
            // Sortowanie wg nazwiska
            dataBase.sortBySurname();

            for (int i = dataBase.EmployeeListSize(); i > 0; i--) {
                tableModel.removeRow(i - 1);
            }

            // Uzupełnianie tabeli wg stanu przed sortowaniem i wyszukiwaniem
            for (int i = 0; i < dataBase.EmployeeListSize(); i++) {
                tableRow[0] = ordinalNumber;
                tableRow[1] = dataBase.getEmployee(i).getName();
                tableRow[2] = dataBase.getEmployee(i).getSurname();
                tableRow[3] = dataBase.getEmployee(i).getNrTel();
                tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                tableRow[5] = dataBase.getEmployee(i).getBank();
                tableModel.addRow(tableRow);
                ordinalNumber++;
            }
            sorter.setModel(tableModel);
            tableView.setRowSorter(sorter);
        }
        else if(sortMode==1)
        {
            // Sortowanie wg stanu konta rosnąco
            dataBase.sortByAccountAsc();

            for (int i = dataBase.EmployeeListSize(); i > 0; i--) {
                tableModel.removeRow(i - 1);
            }

            // Uzupełnianie tabeli wg stanu przed sortowaniem i wyszukiwaniem
            for (int i = 0; i < dataBase.EmployeeListSize(); i++) {
                tableRow[0] = ordinalNumber;
                tableRow[1] = dataBase.getEmployee(i).getName();
                tableRow[2] = dataBase.getEmployee(i).getSurname();
                tableRow[3] = dataBase.getEmployee(i).getNrTel();
                tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                tableRow[5] = dataBase.getEmployee(i).getBank();
                tableModel.addRow(tableRow);
                ordinalNumber++;
            }
            sorter.setModel(tableModel);
            tableView.setRowSorter(sorter);
        }
        else if(sortMode==2)
        {
            // Sortowanie wg stanu konta malejaco
            dataBase.sortByAccountDes();

            for (int i = dataBase.EmployeeListSize(); i > 0; i--) {
                tableModel.removeRow(i - 1);
            }

            // Uzupełnianie tabeli wg stanu przed sortowaniem i wyszukiwaniem
            for (int i = 0; i < dataBase.EmployeeListSize(); i++) {
                tableRow[0] = ordinalNumber;
                tableRow[1] = dataBase.getEmployee(i).getName();
                tableRow[2] = dataBase.getEmployee(i).getSurname();
                tableRow[3] = dataBase.getEmployee(i).getNrTel();
                tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                tableRow[5] = dataBase.getEmployee(i).getBank();
                tableModel.addRow(tableRow);
                ordinalNumber++;
            }
            sorter.setModel(tableModel);
            tableView.setRowSorter(sorter);
        }
        else if(sortMode==3)
        {
            // Sortowanie wg nazwy banku
            dataBase.sortByBankName();

            for (int i = dataBase.EmployeeListSize(); i > 0; i--) {
                tableModel.removeRow(i - 1);
            }

            // Uzupełnianie tabeli wg stanu przed sortowaniem i wyszukiwaniem
            for (int i = 0; i < dataBase.EmployeeListSize(); i++) {
                tableRow[0] = ordinalNumber;
                tableRow[1] = dataBase.getEmployee(i).getName();
                tableRow[2] = dataBase.getEmployee(i).getSurname();
                tableRow[3] = dataBase.getEmployee(i).getNrTel();
                tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                tableRow[5] = dataBase.getEmployee(i).getBank();
                tableModel.addRow(tableRow);
                ordinalNumber++;
            }
            sorter.setModel(tableModel);
            tableView.setRowSorter(sorter);
        }
    }
}
