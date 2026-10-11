package main.java.edu.upc.eetac.dsa;

import java.util.Date;

/** Ejemplo de uso para comprobar manualmente la práctica. */
public class Main {
    public static void main(String[] args) {
        CompanyManager manager = new CompanyManagerImpl();
        manager.addCompany("Tech", "Desarrollo de software");
        manager.addCompany("Design", "Diseño gráfico");

        try {
            manager.addEmployee("Marc", "García", new Date(), 2400, "Tech");
            manager.addEmployee("Ana", "López", new Date(), 3100, "Tech");
            manager.addEmployee("Bruno", "Martín", new Date(), 1800, "Design");
        } catch (MyException e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("EMPRESAS: " + manager.findAllCompanies());
        System.out.println("EMPLEADOS DE TECH: " + manager.employees("Tech"));
        System.out.println("ORDEN POR NOMBRE: " + manager.findAllEmployeesOrderedByName());
        System.out.println("ORDEN POR SALARIO: " + manager.findAllEmployeesOrderedBySalary());
    }
}
