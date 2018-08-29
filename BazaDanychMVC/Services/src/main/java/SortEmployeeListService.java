import javax.swing.table.DefaultTableModel;

// SORTOWANIE KONTENERA WG WYBRANEGO KLUCZA I WYŚWIETLANIE WYNIKU W TABELI
public class SortEmployeeListService
{
    public int sortEmployeeList(int choice,DataBaseDAO dataBase,DefaultTableModel tableModel,Object[] tableRow)
    {
        // Tryb sortowania
        int sortMode=-1;

        switch (choice)
        {
            case 0: //Sortowanie wg Nazwiska
            {
                sortMode=0;

                // Liczba porządkowa "Lp." w tabeli
                int ordinalNumber = 1;

                // Usówanie Wierszy z poprzedniej tabeli
                for(int i=dataBase.EmployeeListSize();i>0;i--)
                {
                    tableModel.removeRow(i-1);
                }
                // Sortowanie dataBaseList
                dataBase.sortBySurname();

                // Uzupełnianie Tabeli

                // Wstawianie danych pracowników z posortowanej dataBaseList do rzędów tabeli
                for(int i=0; i<dataBase.EmployeeListSize();i++)
                {
                    tableRow[0] = ordinalNumber ;
                    tableRow[1] = dataBase.getEmployee(i).getName();
                    tableRow[2] = dataBase.getEmployee(i).getSurname();
                    tableRow[3] = dataBase.getEmployee(i).getNrTel();
                    tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                    tableRow[5] = dataBase.getEmployee(i).getBank();
                    tableModel.addRow(tableRow);
                    ordinalNumber++;
                }
            }break;

            case 1: // Sortowanie wg Stanu Konta rosnąco
            {
                sortMode=1;

                // Liczba porządkowa "Lp" w tabeli
                int ordinalNumber =1;

                // Usówanie Wierszy z poprzedniej tabeli
                for(int i=dataBase.EmployeeListSize();i>0;i--)
                {
                    tableModel.removeRow(i-1);
                }

                // Sortowanie dataBaseList
                dataBase.sortByAccountAsc();

                // Uzupełnianie Tabeli

                // Wstawianie danych pracowników z posortowanej dataBaseList do rzędów tabeli
                for(int i=0; i<dataBase.EmployeeListSize();i++)
                {
                    tableRow[0] = ordinalNumber ;
                    tableRow[1] = dataBase.getEmployee(i).getName();
                    tableRow[2] = dataBase.getEmployee(i).getSurname();
                    tableRow[3] = dataBase.getEmployee(i).getNrTel();
                    tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                    tableRow[5] = dataBase.getEmployee(i).getBank();
                    tableModel.addRow(tableRow);
                    ordinalNumber++;
                }
            }break;

            case 2: // Sortowanie wg Stanu Konta malejąco
            {
                sortMode=2;

                // Liczba porządkowa "Lp." w tabeli
                int ordinalNumber = 1;
                
                // Usówanie Wierszy z poprzedniej tabeli
                for(int i=dataBase.EmployeeListSize();i>0;i--)
                {
                    tableModel.removeRow(i-1);
                }

                // Sortowanie
                dataBase.sortByAccountDes();

                // Uzupełnianie Tabeli

                // Wstawianie danych pracowników z posortowanej dataBaseList do rzędów tabeli
                for(int i=0; i<dataBase.EmployeeListSize();i++)
                {
                    tableRow[0] = ordinalNumber ;
                    tableRow[1] = dataBase.getEmployee(i).getName();
                    tableRow[2] = dataBase.getEmployee(i).getSurname();
                    tableRow[3] = dataBase.getEmployee(i).getNrTel();
                    tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                    tableRow[5] = dataBase.getEmployee(i).getBank();
                    tableModel.addRow(tableRow);
                    ordinalNumber ++;
                }
            }break;

            case 3: // Sortowanie wg nazwy Banku
            {
                sortMode=3;

                // Liczba porządkowa "Lp." w tabeli
                int ordinalNumber = 1;

                // Usówanie Wierszy z poprzedniej tabeli
                for(int i=dataBase.EmployeeListSize();i>0;i--)
                {
                    tableModel.removeRow(i-1);
                }

                dataBase.sortByBankName();

                // Uzupełnianie Tabeli

                // Wstawianie danych pracowników z posortowanej dataBaseList do rzędów tabeli
                for(int i=0; i<dataBase.EmployeeListSize();i++)
                {
                    tableRow[0] = ordinalNumber ;
                    tableRow[1] = dataBase.getEmployee(i).getName();
                    tableRow[2] = dataBase.getEmployee(i).getSurname();
                    tableRow[3] = dataBase.getEmployee(i).getNrTel();
                    tableRow[4] = dataBase.getEmployee(i).getStringAccountBallance();
                    tableRow[5] = dataBase.getEmployee(i).getBank();
                    tableModel.addRow(tableRow);
                    ordinalNumber++;
                }
            }break;
        }//switch/case
        return sortMode;
    }
}
