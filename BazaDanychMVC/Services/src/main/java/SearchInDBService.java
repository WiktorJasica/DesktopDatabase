import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

// PRZESZUKIWANIE TABELI WG WYBRANEGO KLUCZA
public class SearchInDBService
{
    // Sortowanie tabeli
    public void dataBaseSearch(String key, JTable tableView, DefaultTableModel tableModel)
    {
        TableRowSorter sorter;
        sorter = new TableRowSorter(tableModel);
        sorter.setRowFilter(RowFilter.regexFilter(key));
        tableView.setRowSorter(sorter);
    }
}
