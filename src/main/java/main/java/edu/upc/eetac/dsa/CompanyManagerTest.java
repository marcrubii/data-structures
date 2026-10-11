package main.java.edu.upc.eetac.dsa;

import java.util.Date;
import java.util.List;

/** Pruebas sin dependencias externas: ejecutar con java y sin activar assertions. */
public class CompanyManagerTest {
    private static int checks = 0;

    private static void verify(boolean condition, String message) {
        checks++;
        if (!condition) {
            throw new AssertionError(message);
        }
    }

    public static void main(String[] args) throws MyException {
        CompanyManager manager = new CompanyManagerImpl();
        verify(manager.findAllCompanies().isEmpty(), "Inicialmente no hay empresas");
        verify(manager.findAllEmployeesOrderedByName().isEmpty(), "Inicialmente no hay empleados");

        manager.addCompany("Zeta", "Primera");
        manager.addCompany("Alfa", "Segunda");
        manager.addCompany("Zeta", "Duplicada");
        verify(manager.findAllCompanies().size() == 2, "No se duplican empresas");
        verify(manager.findAllCompanies().get(0).getName().equals("Alfa"), "Empresas ordenadas");

        Date birthday = new Date(0L);
        manager.addEmployee("Carlos", "Cano", birthday, 3000, "Zeta");
        manager.addEmployee("Ana", "Zurita", birthday, 2000, "Alfa");
        manager.addEmployee("Ana", "Bravo", birthday, 2500, "Zeta");
        verify(manager.employees("Zeta").size() == 2, "Empleados por empresa");
        verify(manager.employees("Inexistente").isEmpty(), "Empresa desconocida devuelve lista vacía");

        List<Employee> byName = manager.findAllEmployeesOrderedByName();
        verify(byName.get(0).getSurname().equals("Bravo"), "Empate de nombres se ordena por apellido");
        verify(byName.get(1).getSurname().equals("Zurita"), "Segundo apellido correcto");
        verify(byName.get(2).getName().equals("Carlos"), "Tercer nombre correcto");

        List<Employee> bySalary = manager.findAllEmployeesOrderedBySalary();
        verify(bySalary.get(0).getSalary() == 2000, "Primer salario correcto");
        verify(bySalary.get(2).getSalary() == 3000, "Último salario correcto");

        boolean threw = false;
        try {
            manager.addEmployee("X", "Y", birthday, 1000, "NoExiste");
        } catch (MyException expected) {
            threw = true;
        }
        verify(threw, "Añadir a empresa inexistente lanza MyException");

        manager.employees("Zeta").clear();
        verify(manager.employees("Zeta").size() == 2, "La copia de la lista no modifica la empresa");
        System.out.println("OK: " + checks + " comprobaciones superadas.");
    }
}
