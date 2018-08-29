import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.io.File;

// GŁÓWNY INTERFEJS GRAFICZNY BAZY DANYCH: MainViewGUI
public class MainViewGUI extends JFrame
{
    private JLabel sortLabel;
    private JTextField fieldSearch;
    private JButton addButton ,deleteButton, editButton, searchButton, refreshButton;
    private JMenuBar menuBar;
    private JMenu menuDataBase,menuNewBase, menuDeleteBase,closeProgramMenu;
    private JMenuItem itemOpen, itemSave, itemCloseApp, itemNewBase, itemDeleteBase;
    private JComboBox<String> comboSort;
    private Object [] TableRow;
    private DefaultTableModel tabeleModel;
    private JTable tableView;
    private JScrollPane scrollPane;
    private File file;
    private boolean searchMode; // Wskazuje czy tabela jest w trybie wyszukowania
    private int sortMode; // Wskazuje w jakim trybie sortowania jest tabela

    // TWORZENIE ELEMENTÓW MainViewGUI - KONSTRUKTOR MainViewGUI
    public MainViewGUI()
    {
        // Etykieta: Sortowanie
        sortLabel = new JLabel("Sortowanie:");

        // Pole textowe: Szukaj
        fieldSearch = new JFormattedTextField();

        // Przyciski: Dodaj, Usuń, Edytuj, Szukaj, Odświerz
        addButton = new JButton("Dodaj pracownika");
        deleteButton = new JButton("Usuń pracownika");
        editButton = new JButton("Edycja pracownika");
        searchButton = new JButton("Szukaj");
        refreshButton = new JButton("Odświerz");

        // Pasek Menu i jego nagłówki: Baza Danych, Nowa Baza, Usuń Bazę, Zamknij Program
        menuBar = new JMenuBar();

        menuDataBase = new JMenu("Baza Danych");
        menuNewBase = new JMenu("Nowa Baza");
        menuDeleteBase = new JMenu("Usuń Bazę");
        closeProgramMenu = new JMenu("Zamknij Program");

        // Elementy do wyboru w Pasku Menu: Otwórz, Zapisz, Nowa Baza, Usuń Bazę, Zamknij Program
        itemOpen = new JMenuItem("Otwórz");
        itemSave = new JMenuItem("Zapisz");
        itemNewBase = new JMenuItem("Nowa Baza");
        itemDeleteBase =new JMenuItem("Usuń Bazę");
        itemCloseApp = new JMenuItem("Zaknij Program");

        // Skrolowana lista wyboru metod sortowania
        comboSort = new JComboBox<String>();

        // Tabela oraz jej elementy
        TableRow = new Object[6];
        tabeleModel = new DefaultTableModel(0,TableRow.length);
        tableView = new JTable(tabeleModel);
        scrollPane = new JScrollPane(tableView);

        setViewElements();
        setElementsLayout();
    }

    // DODAWANIE ELEMENTÓW DO MainViewGUI
    private void setViewElements()
    {
        // Etykieta Sortowanie
        add(sortLabel);

        // Pole txtowe Szukaj
        add(fieldSearch);

        // Przyciski: Dodaj, Usuń, Edycja, Szukaj, Odświerz
        add(addButton);
        add(deleteButton);
        add(editButton);
        add(searchButton);
        add(refreshButton);

        // Pasek Menu
        setJMenuBar(menuBar);

        // Nagłówki Paska Menu: Baza Danych, Nowa Baza, Usuń Bazę, Zamknij Program
        menuBar.add(menuDataBase);
        menuBar.add(menuNewBase);
        menuBar.add(menuDeleteBase);
        closeProgramMenu.add(itemCloseApp);

        // Elementy do wyboru w Pasku Menu: Otwórz, Zapisz, Nowa Baza, Usuń Bazę, Zamknij Program
        menuDataBase.add(itemOpen);
        menuDataBase.add(itemSave);
        menuNewBase.add(itemNewBase);
        menuDeleteBase.add(itemDeleteBase);
        menuBar.add(closeProgramMenu);
        itemCloseApp.setAccelerator(KeyStroke.getKeyStroke("ctrl X"));

        // Lista wyboru sortowania
        add(comboSort);
        comboSort.addItem("wg nazwiska");
        comboSort.addItem("wg wielkości konta - malejąco");
        comboSort.addItem("wg wielkosci konta - rosnąco");
        comboSort.addItem("wg nazwy banku");

        // Tabela Bazy Danych:
        String [] columNames = new String [] {"Lp.","Imię", "Nazwisko", "Nr Telefonu", "Stan konta", "Bank" };
        tabeleModel.setColumnIdentifiers(columNames);
        tableView.setEnabled(true);
        tableView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.addAncestorListener(null);
        add(scrollPane);
    }

    // UKLAD ELEMENTÓW MainViewGUI
    private void setElementsLayout()
    {
        // Okno Bazy Danych
        setBounds(320,0,755,725);
        setLayout(null);
        setTitle("Baza Danych");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Etykieta Sortuj
        sortLabel.setBounds(470,565,100,30);

        // Pole textowe Szukaj
        fieldSearch.setBounds(130,628,323,30);

        // Przyciski: Dodaj, Usuń, Edycja, Szukaj, Odświerz
        addButton.setToolTipText("Dodawanie nowego pracownika do bazy danych");
        addButton.setBounds(5,565,140,35);
        deleteButton.setToolTipText("Usuwanie pracownika z bazy danych");
        deleteButton.setBounds(160,565,135,35);
        editButton.setToolTipText("Edycja danych pracownika");
        editButton.setBounds(310,565,145,35);
        searchButton.setToolTipText("Wyszukiwanie pracowników");
        searchButton.setBounds(5,625,110,35);
        refreshButton.setToolTipText("Aktualizacja danych");
        refreshButton.setBounds(545,625,100,35);

        // Lista wyboru Sortowania
        comboSort.setBounds(545,565,190,30);

        // Skrolowany obraz JListy
        scrollPane.setBounds(0,0,735,500);
    }

    // LISTENERY PRZYCISKÓW I PÓL WYBORU
    public void setListeners(ActionListener listener)
    {
        fieldSearch.addActionListener(listener);
        addButton.addActionListener(listener);
        deleteButton.addActionListener(listener);
        editButton.addActionListener(listener);
        searchButton.addActionListener(listener);
        refreshButton.addActionListener(listener);
        itemOpen.addActionListener(listener);
        itemSave.addActionListener(listener);
        itemNewBase.addActionListener(listener);
        itemDeleteBase.addActionListener(listener);
        itemCloseApp.addActionListener(listener);
        comboSort.addActionListener(listener);
    }

    // GETTERY i SETTERY
    public JTextField getFieldSearch() {
        return fieldSearch;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public JButton getEditButton() {
        return editButton;
    }

    public JButton getSearchButton() {
        return searchButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public JMenuItem getItemOpen() {
        return itemOpen;
    }

    public JMenuItem getItemSave() {
        return itemSave;
    }

    public JMenuItem getItemCloseApp() {
        return itemCloseApp;
    }

    public JMenuItem getItemDeleteBase() {
        return itemDeleteBase;
    }

    public JMenuItem getItemNewBase() {
        return itemNewBase;
    }

    public JComboBox<String> getComboSort() {
        return comboSort;
    }

    public File getFile() {
        return file;
    }

    public boolean isSearchMode() {
        return this.searchMode;
    }

    public void setSearchMode(boolean searchMode) {
        this.searchMode = searchMode;
    }

    public Object[] getTableRow() {
        return TableRow;
    }

    public DefaultTableModel getTabeleModel() {
        return tabeleModel;
    }

        public JTable getTableView() {
        return this.tableView;
    }

    public int getSortMode() {
        return sortMode;
    }

    public void setSortMode(int sortMode) {
        this.sortMode = sortMode;
    }
}

