package main.java.edu.upc.eetac.dsa;

import java.util.ArrayList;
import java.util.List;

/** Una empresa y su lista de empleados. */
public class Company {
    private final String name;
    private final String description;
    private final List<Employee> employees;

    public Company(String name, String description) {
        this.name = name;
        this.description = description;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public String toString() {
        return name + " (" + description + ") - Empleados: " + employees.size();
    }
}
