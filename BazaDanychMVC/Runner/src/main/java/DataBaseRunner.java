public class DataBaseRunner
{
    // URUCHOMIENIE APLIKACJI
    public static void main (String args[])
    {
        // Model
        DataBaseDAO dataBase =  new DataBaseDAO();

        // Widok
        MainViewGUI mainViewGUI = new MainViewGUI();

        // Kontroler
        MainController mainController = new MainController(mainViewGUI,dataBase);

        mainViewGUI.setVisible(true); // Interfejs graficzny MainViewGUI jest widoczny
    }
}
