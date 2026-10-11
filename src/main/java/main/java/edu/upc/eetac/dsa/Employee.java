package main.java.edu.upc.eetac.dsa;

import java.util.Date;

/** Datos de un empleado. */
public class Employee {
    private final String name;
    private final String surname;
    private final Date birthday;
    private final double salary;
    private final String companyName;

    public Employee(String name, String surname, Date birthday, double salary, String companyName) {
        this.name = name;
        this.surname = surname;
        this.birthday = (birthday == null) ? null : new Date(birthday.getTime());
        this.salary = salary;
        this.companyName = companyName;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthday() {
        return (birthday == null) ? null : new Date(birthday.getTime());
    }

    public double getSalary() {
        return salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return name + " " + surname + " | Salario: " + salary + " | Empresa: " + companyName;
    }
}
