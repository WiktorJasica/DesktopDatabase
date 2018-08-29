import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GŁÓWNY KONTROLER APLIKACJI
public class MainController
{
    private MainViewGUI mainView;
    private DataBaseDAO dataBase;
    private static int idCounter = 1;

    public  MainController (MainViewGUI mainView, DataBaseDAO dataBase)
    {
        this.dataBase = dataBase;
        this.mainView = mainView;

        // Ustawianie Listenerów
        mainView.setListeners(new Listeners());
    }

    // Listenery przycisków i pól wyboru MainViewGUI
    private class Listeners implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            // "Nasłuchiwanie źródła akcji" wykonywanej przez użytkownika aplikacji (wciśniecie przycisku, pola wyboru)
            Object s = e.getSource();

            // DODAWANIE PRACOWNIKA
            if(s==mainView.getAddButton())
            {
                // Interfejs graficzny dodawania pracownika AddEmployeeGUI
                AddEmployeeGUI addEmployeeGUI = new AddEmployeeGUI(mainView);

                // Kontroller dodawania pracownika
                AddEmployeeController addEmployeeController = new AddEmployeeController(addEmployeeGUI);
                addEmployeeGUI.setVisible(true); // Interfejs graficzny AddEmployeeGUI jest widoczny

                // Usługa dodawania pracownika do Kontenera i tabeli
                AddEmployeeService addEmployeeService = new AddEmployeeService();
                addEmployeeService.addEmployee(dataBase,mainView.getTableRow(),mainView.getTabeleModel()
                        ,addEmployeeGUI.getAddConfirm(),addEmployeeController.getEmployee());
            }

            // USUWANIE PRACOWNIKA
            if(s==mainView.getDeleteButton())
            {
                // Usługa usuwanie pracownika z Kontenera i tabeli
                DeleteEmployeeService deleteEmployeeService = new DeleteEmployeeService();
                idCounter = deleteEmployeeService.deleteEmployee(dataBase,mainView.getTableRow(),mainView.getTabeleModel()
                        ,mainView.getTableView(),idCounter);

                System.out.println(idCounter);

            }

            // EDYCJA PRACOWNIKA
            if(s==mainView.getEditButton())
            {
                // Usługa edycji pracownika w Kontenerze i tabeli oraz pobierania danych do pól text. EditEmployeeGUI
                EditEmployeeService editEmployeeService = new EditEmployeeService();

                // Warunek jeśli kontener z pracownikami jest pusty lub nie wybralismy pracownika do edycji
                if(dataBase.isListEmpty()||mainView.getTableView().getSelectedRow()==-1)
                {
                    JOptionPane.showMessageDialog(null,
                            "Nie możesz edytować pracownika !\n" +
                                    "Baza danych jest pusta \n lub nie wybrałeś pracownika z listy "
                            ,"Warning",JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    /* Warunek jeśli kontener z pracownikami nie jest pusty, wybralismy pracownika do edycji oraz
                       nie jesteśmy w trybie wyszukiwania wg klucza
                     */
                    if(mainView.isSearchMode()==false)
                    {
                        // Pobranie indexu wybranego rzędu
                        int editedRowNumber = mainView.getTableView().getSelectedRow();

                        // Pobieranie danych wybranego pracownika
                        editEmployeeService.getDataFromTableRow(dataBase,editedRowNumber);

                        // Interfejs graficznny edycji pracownika EditEmployeeGUI
                        EditEmployeeGUI editEmployeeGUI = new EditEmployeeGUI(mainView
                                ,editEmployeeService.getNameToSend(),editEmployeeService.getSurnameToSend()
                                ,editEmployeeService.getNrTelToSend(),editEmployeeService.getAccountBallanceToSend()
                                ,editEmployeeService.getBankNameToSend());

                        // Kontroler edycji pracownika
                        EditEmployeeController editEmployeeController = new EditEmployeeController(editEmployeeGUI
                                ,editEmployeeService.getEmployee());

                        editEmployeeGUI.setVisible(true);// Interfejs graficzny EditEmployeeGUI jest widoczny

                        // Edycja wybranego pracownika
                        editEmployeeService.editEmployee(editEmployeeGUI.isEditConfirm()
                                ,dataBase,mainView.getTabeleModel(),mainView.getTableRow()
                                ,editedRowNumber,editEmployeeController.getEmployee());

                    }// if tryb szukaj false

                    /* Warunek jeśli kontener z pracownikami nie jest pusty, wybralismy pracownika do edycj
                       i jesteśmy w trybie wyszukiwania wg klucza
                     */
                    else if(mainView.isSearchMode()==true)
                    {
                        // Pobieranie nr Id pracownika we wskazanym rzędzie
                        int editRowNumber = mainView.getTableView().getSelectedRow();
                        int columnNumber = 0;
                        Object selectedObject = mainView.getTableView().getValueAt(editRowNumber,columnNumber);
                        int selectedEmployeeId = (Integer)selectedObject - 1;

                        // Pobieranie danych wybranego pracownika
                        editEmployeeService.getDataFromTableRow(dataBase,selectedEmployeeId);

                        // Interfejs graficznny edycji pracownika EditEmployeeGUI
                        EditEmployeeGUI editEmployeeGUI = new EditEmployeeGUI(mainView
                                ,editEmployeeService.getNameToSend(),editEmployeeService.getSurnameToSend()
                                ,editEmployeeService.getNrTelToSend(),editEmployeeService.getAccountBallanceToSend()
                                ,editEmployeeService.getBankNameToSend());

                        // Kontroler edycji pracownika
                        EditEmployeeController editEmployeeController = new EditEmployeeController(editEmployeeGUI
                                ,editEmployeeService.getEmployee());

                        editEmployeeGUI.setVisible(true); // Interfejs graficzny EditEmployeeGUI jest widoczny

                        // Edycja wybranego pracownika
                        editEmployeeService.editEmployee(editEmployeeGUI.isEditConfirm(),dataBase
                                ,mainView.getTabeleModel(),mainView.getTableRow(),selectedEmployeeId
                                ,editEmployeeController.getEmployee());
                    }//if tryb szukaj true
                }
            }

            // OTWIERANIA PLIKU Z ZAPISANYMI DANYMI
            if(s==mainView.getItemOpen())
            {
                // Usługa otwierania pliku zzapisanymi danymi
                OpenFileService openFileService = new OpenFileService();
                dataBase = openFileService.openDataBaseFile(dataBase,mainView.getFile()
                                                        ,mainView.getTabeleModel()
                                                        ,mainView.getTableRow());

                // Aktuallizacja idCountera po wczytaniu pliku
                int lastId = dataBase.EmployeeListSize();
                System.out.println(lastId);
                MainController.setIdCounter(lastId+1);
            }

            // ZAPISYWANIE PLIKU NA DYSKU
            if(s==mainView.getItemSave())
            {
                // Usługa zapisu pliku na dysku
                SaveDataFileService saveDataFileService = new SaveDataFileService();
                saveDataFileService.saveDataBaseFile(dataBase,mainView.getFile());
            }

            // SORTOWANIE LISTY WG KLUCZA
            if(s==mainView.getComboSort())
            {
                int tempSortMode; // Pomocniczy tryb sortowania

                // Wybór metody sortowania w ComboBox
                int choice = mainView.getComboSort().getSelectedIndex();

                // Usługa sortowania wg klucza
                SortEmployeeListService sortEmployeeListService = new SortEmployeeListService();
               tempSortMode = sortEmployeeListService.sortEmployeeList(choice,dataBase,mainView.getTabeleModel()
                                                                       ,mainView.getTableRow());
               mainView.setSortMode(tempSortMode);
            }

            // WYSZUKIWANIE ELEMENTÓW TABELI
            if(s==mainView.getSearchButton())
            {
                // Szukanie w Bazie Danych wg klucza z pola textowego

                //Pobieranie wpisanego klucza wyszukiwania
                String searchKey = mainView.getFieldSearch().getText();

                // Warunek jesli nie wpisaliśmy nic w pole txt. wyszukiwania
                if((searchKey.isEmpty())&&(s==mainView.getSearchButton()))
                {
                    JOptionPane.showMessageDialog(mainView,
                            "Brak klucza wyszukiwania !",
                            "Warning", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    //Usługa wyszukiwania w tabeli wg wpisanego klucza
                    SearchInDBService searchInDBService = new SearchInDBService();
                    searchInDBService.dataBaseSearch(searchKey,mainView.getTableView(),mainView.getTabeleModel());

                    //Ustawienie tabeli w tryb wyszukiwania
                    mainView.setSearchMode(true);
                }
            }

            // ODSWIEŻANIE TABELI
            if(s==mainView.getRefreshButton())
            {
                // Usługa odswieżania widoku tabeli
                RefreshTableService refreshTableService = new RefreshTableService();
                refreshTableService.refreshTable(mainView.getTableView(),dataBase,mainView.getTableRow()
                                                 ,mainView.getTabeleModel(),mainView.getSortMode());

                //Wyjście z trybu wyszukiwania w tabeli
                mainView.setSearchMode(false);
            }

            // TWORZENIE NOWEJ BAZY DANYCH
            if(s==mainView.getItemNewBase())
            {
                // Nowy Model
                DataBaseDAO dataBase =  new DataBaseDAO();

                // Nowy Widok Gówny
                MainViewGUI mainViewGUI = new MainViewGUI();

                // Nowy Kontroler
                MainController mainController = new MainController(mainViewGUI,dataBase);
                mainViewGUI.setVisible(true);// Interfejs graficzny MainViewGUI jest widoczny
            }

            // USUWANIE ISTNIEJĄCEJ BAZY DANYCH
            if(s==mainView.getItemDeleteBase())
            {
                {
                    // Usługa usuwająca istniejącą bazę danych
                    DeleteDataBaseService deleteDataBaseService = new DeleteDataBaseService();
                    deleteDataBaseService.deleteDataBase(dataBase,mainView.getTabeleModel());

                    // Aktualizacja IdCountera
                    MainController.setIdCounter(1);
                }
            }

            // ZAMYKANIE APLIKACJI
            if(s==mainView.getItemCloseApp())
            {
                // Zamykanie aplikacji
                if(dataBase.EmployeeListSize()!=0)
                {
                    // Ostrzeżenie o utracie danych w istniejącej już bazie danych
                    int answer = JOptionPane.showConfirmDialog(null,
                            "Czy napewno chcesz zamknąć Program?\n"
                                    +"Niezapisane dane zostaną utracone", "Zamknij program",
                            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

                    if (answer == JOptionPane.YES_OPTION) {
                        System.exit(-1);
                    }
                }
                else
                {
                    System.exit(-1);
                }

            }// if Zaknij Program
        }
    }
    // GETERY I SETERY

    public static int getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(int idCounter) {
        MainController.idCounter = idCounter;
    }
}
