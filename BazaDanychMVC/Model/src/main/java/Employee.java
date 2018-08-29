import java.io.Serializable;

// DEFINIOWANIE PRACOWNIKA
public class Employee implements Serializable
{
    // Dane Pracownika: id, imię, nazwisko, nr telefonu, bank, stan konta
    private int id;
    private String name, surname, nrTel, bank;
    private double accountBallance;

    // Tworzenie nowego pracownika sposobem Builder
    public static EmployeeBuilder New()
    {
        return new EmployeeBuilder();
    }

    // Reprezentacja obiektu w postaci String
    @Override
    public String toString()
    {
        return "Employee nr: " + id + ", name=" + name +", surname=" + surname + ", nrTel=" + nrTel
                +", bank=" + bank + ", accountBallance=" + accountBallance;
    }

    // Klasa wewnętrzna wykorzystywana do tworzenia nowego pracownika w metodzie "New"
    public static class EmployeeBuilder
    {
        private Employee employee;

        private EmployeeBuilder()
        {
            employee = new Employee();
        }

        // Metody przypisujące dane do pracownika

        public EmployeeBuilder id (int id)
        {
            employee.id = id;
            return this;
        }

        public EmployeeBuilder name (String name)
        {
           employee.name = name;
           return this;
        }

        public EmployeeBuilder surname (String surname)
        {
            employee.surname = surname;
            return this;
        }

        public EmployeeBuilder nrTel (String nrTel)
        {
            employee.nrTel = nrTel;
            return this;
        }

        public EmployeeBuilder bank (String bank)
        {
            employee.bank = bank;
            return this;
        }

        public EmployeeBuilder accountBallance (double accountBallance)
        {
            employee.accountBallance = accountBallance;
            return this;
        }

        public Employee build()
        {
            return this.employee;
        }
    }

    // GETTERY i SETTERY DANYCH PRACOWNIKA
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNrTel() {
        return nrTel;
    }

    public String getBank() {
        return bank;
    }

    public double getAccountBallance() {
        return accountBallance;
    }

    public String getStringAccountBallance ()
    {
        String stringAccountBalance =String.valueOf(accountBallance);
        return stringAccountBalance;
    }
}

