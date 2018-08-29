import javax.swing.table.DefaultTableModel;

// DODAWANIE PRACOWNIKA DO KONTENERA I TABELI
public class AddEmployeeService
{
    public void  addEmployee (DataBaseDAO dataBase,Object[] tableRow ,DefaultTableModel tableModel,boolean addConfirm
                             ,Employee employee)
    {
        // Pobieranie indexu ostatniego pracownika w bazie danych
        int lastEmployee = dataBase.EmployeeListSize();

        // Jeśli w AddEmployeeGUI wcisniety zostanie przycisk OK
        if (addConfirm)
        {
            //Dodawanie pracownika do kontenera dataBaseList
            dataBase.addEmployee(employee);

            // Wstawianie danych pracownika z dataBaseList do rzędu tabeli

                tableRow[0] = lastEmployee+1;
                tableRow[1] = dataBase.getEmployee(lastEmployee).getName();
                tableRow[2] = dataBase.getEmployee(lastEmployee).getSurname();
                tableRow[3] = dataBase.getEmployee(lastEmployee).getNrTel();
                tableRow[4] = dataBase.getEmployee(lastEmployee).getStringAccountBallance();
                tableRow[5] = dataBase.getEmployee(lastEmployee).getBank();

            // Dodawanie rzedu do tabeli
            tableModel.addRow(tableRow);
        }
    }
}
