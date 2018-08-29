import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

// BAZA DANYCH PRACOWNIKÓW
public class DataBaseDAO implements Serializable
{
     private ArrayList<Employee> dataBaseList = new ArrayList<Employee>();

    // METODY GET POBIERAJĄCE DANE Z dataBaseList

    public Employee getEmployee(int i)
    {
        return this.dataBaseList.get(i);
    }

    public int EmployeeListSize()
    {
        return this.dataBaseList.size();
    }

    public boolean isListEmpty()
    {
        return this.dataBaseList.isEmpty();
    }


    // METODY MODYFIKUJĄCE dataBaseList

    public void remoweEmployee(int tableRow)
    {
        this.dataBaseList.remove(tableRow);
    }

    public void addEmployee(Employee employee)
    {
        this.dataBaseList.add(employee);
    }

    public void setElement(int editedTableRow,Employee employee)
    {
        this.dataBaseList.set(editedTableRow,employee);
    }

    public void listClear ()
    {
        this.dataBaseList.clear();
    }

    // METODY SORTUJĄCE dataBaseList wg: nazwiska, wielkości stanu konta rosnąco, malejąco, nazwy baku

    public void sortBySurname ()
    {
        this.dataBaseList.sort(Comparator.comparing(Employee::getSurname));
    }

    public void sortByAccountAsc()
    {
        dataBaseList.sort((employee1, employee2)
               -> Integer.valueOf((int) employee2.getAccountBallance())
                .compareTo((int) employee1.getAccountBallance()));}

    public void sortByAccountDes()
    {
        dataBaseList.sort((employee1, employee2)
            -> Integer.valueOf((int) employee1.getAccountBallance())
            .compareTo((int) employee2.getAccountBallance()));}

    public void sortByBankName ()
    {
        this.dataBaseList.sort(Comparator.comparing(Employee::getBank));
    }

}
