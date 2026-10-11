package main.java.edu.upc.eetac.dsa;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/** Implementación de la interfaz CompanyManager. */
public class CompanyManagerImpl implements CompanyManager {

    // Estructura obligatoria: HashMap para almacenar las empresas.
    private final HashMap<String, Company> companies = new HashMap<>();

    private static final Comparator<Employee> BY_NAME =
            Comparator.comparing(Employee::getName, String.CASE_INSENSITIVE_ORDER)
                      .thenComparing(Employee::getSurname, String.CASE_INSENSITIVE_ORDER);

    @Override
    public void addCompany(String companyName, String description) {
        // No sobrescribir una empresa existente: se perderían sus empleados.
        if (!companies.containsKey(companyName)) {
            companies.put(companyName, new Company(companyName, description));
        }
    }

    @Override
    public void addEmployee(String name, String surname, Date birthday,
                            double salary, String companyName) throws MyException {
        Company company = companies.get(companyName);
        if (company == null) {
            throw new MyException("La empresa '" + companyName + "' no existe.");
        }
        Employee employee = new Employee(name, surname, birthday, salary, companyName);
        company.addEmployee(employee);
    }

    @Override
    public List<Employee> findAllEmployeesOrderedByName() {
        List<Employee> result = allEmployees();
        result.sort(BY_NAME);
        return result;
    }

    @Override
    public List<Employee> findAllEmployeesOrderedBySalary() {
        List<Employee> result = allEmployees();
        // Se supone orden de menor a mayor salario.
        result.sort(Comparator.comparingDouble(Employee::getSalary).thenComparing(BY_NAME));
        return result;
    }

    @Override
    public List<Employee> employees(String companyName) {
        Company company = companies.get(companyName);
        if (company == null) {
            // Este método no declara excepciones; devolvemos una lista vacía.
            return new ArrayList<>();
        }
        return company.getEmployees();
    }

    @Override
    public List<Company> findAllCompanies() {
        List<Company> result = new ArrayList<>(companies.values());
        result.sort(Comparator.comparing(Company::getName, String.CASE_INSENSITIVE_ORDER));
        return result;
    }

    private List<Employee> allEmployees() {
        List<Employee> result = new ArrayList<>();
        for (Company company : companies.values()) {
            result.addAll(company.getEmployees());
        }
        return result;
    }
}
