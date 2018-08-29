import javax.swing.table.DefaultTableModel;

// EDYCJA DANYCH PRACOWNIKA W KONTENERZE I TABELI
public class EditEmployeeService
{
    /* Zmienne do Konstruktora Okna Edycji Pracownika AddEmployeeGUI.
       Zienne wypełniają pola textowe wskazanego rzędu do edycji
    */
    private String nameToSend;
    private String surnameToSend;
    private String nrTelToSend;
    private double AccountBallanceToSend;
    private String bankNameToSend;
    private int employeeIdToSend;
    private Employee employee;

    // Pobieranie danych wybranego pracownika
    public void getDataFromTableRow (DataBaseDAO dataBase,int editedRowNumber)
    {
        this.employee = dataBase.getEmployee(editedRowNumber);
        this.employeeIdToSend = dataBase.getEmployee(editedRowNumber).getId();
        this.nameToSend = dataBase.getEmployee(editedRowNumber).getName();
        this.surnameToSend = dataBase.getEmployee(editedRowNumber).getSurname();
        this.nrTelToSend = dataBase.getEmployee(editedRowNumber).getNrTel();
        this.AccountBallanceToSend = dataBase.getEmployee(editedRowNumber).getAccountBallance();
        this.bankNameToSend = dataBase.getEmployee(editedRowNumber).getBank();
    }

    // Edycja danych pracownika w kontenerze i tabeli
    public void editEmployee (boolean editConfirm,DataBaseDAO dataBase,DefaultTableModel tableModel,Object[] tableRow
            ,int editedRowNumber, Employee employee)
    {
        // Jesli w EditEmployeeGUI zostanie wciśnięty przycisk Ok
        if (editConfirm)
        {
            // Wstawianie do kontenera dataBaseList zedytowaniego pracownika
            dataBase.setElement(editedRowNumber, employee);

            // Wstawianie danych z dataBaseList do rzędu tabeli
            tableRow[0] = editedRowNumber+1;
            tableRow[1] = dataBase.getEmployee(editedRowNumber).getName();
            tableRow[2] = dataBase.getEmployee(editedRowNumber).getSurname();
            tableRow[3] = dataBase.getEmployee(editedRowNumber).getNrTel();
            tableRow[4] = dataBase.getEmployee(editedRowNumber).getStringAccountBallance();
            tableRow[5] = dataBase.getEmployee(editedRowNumber).getBank();

            // Wstawienie zedytowanego rzędu do tabeli
            for (int i = 0; i < tableRow.length; i++) {
                tableModel.setValueAt(tableRow[i], editedRowNumber, i);
            }
        }
    }

    // GETTERY I SETTERY
    public String getNameToSend() {return nameToSend;}

    public String getSurnameToSend() {return surnameToSend;}

    public String getNrTelToSend() {return nrTelToSend;}

    public double getAccountBallanceToSend() {return AccountBallanceToSend;}

    public String getBankNameToSend() {return bankNameToSend;}

    public int getEmployeeIdToSend() {return employeeIdToSend;}

    public Employee getEmployee() {
        return employee;
    }
}
