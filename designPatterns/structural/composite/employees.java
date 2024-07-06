/**
 * Composite pattern is used in cases where we have a tree-like hierarchy of similar elements.
 * In this example, there are multiple instances of folders and files in a tree-like directory.
 * So we implement them from same interface and same methods are available to them.
 * Composite elements are elements with children, while leaf elements have no children.
 * We store a link of children/parent element in each element so that we are able to navigate through the tree.
 */


import java.util.*;

abstract class EmployeeComponent {
    public abstract void displayInfo();
    public abstract double calculateSalary();
}

// Leaf node
class Employee extends EmployeeComponent {
    private String name;
    private double salary;

    public Employee(String empName, double empSalary) {
        name = empName;
        salary = empSalary;
    }

    @Override
    public void displayInfo() {
        System.out.println("Employee: " + name + " Salary: Rs." + salary);
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}

// Composite class
class Department extends EmployeeComponent {
    private String name;
    private List<EmployeeComponent> members;

    public Department(String deptName) {
        name = deptName;
        members = new ArrayList<>();
    }

    public void addMember(EmployeeComponent member) {
        members.add(member);
    }

    @Override
    public void displayInfo() {
        System.out.println("Department: " + name);
        for (EmployeeComponent member : members) {
            member.displayInfo();
        }
    }

    @Override
    public double calculateSalary() {
        double totalSalary = 0.0;
        for (EmployeeComponent member : members) {
            totalSalary += member.calculateSalary();
        }
        return totalSalary;
    }
}

// Composite class
class Team extends EmployeeComponent {
    private String name;
    private List<EmployeeComponent> members;

    public Team(String teamName) {
        name = teamName;
        members = new ArrayList<>();
    }

    public void addMember(EmployeeComponent member) {
        members.add(member);
    }

    @Override
    public void displayInfo() {
        System.out.println("Team: " + name);
        for (EmployeeComponent member : members) {
            member.displayInfo();
        }
    }

    @Override
    public double calculateSalary() {
        double totalSalary = 0.0;
        for (EmployeeComponent member : members) {
            totalSalary += member.calculateSalary();
        }
        return totalSalary;
    }
}

public class EmployeesDemo {
    public static void main(String[] args) {
        EmployeeComponent keerti = new Employee("Keerti", 100.0);
        EmployeeComponent amit = new Employee("Amit", 200.0);

        Team sales = new Team("Sales");
        sales.addMember(keerti);
        sales.addMember(amit);

        EmployeeComponent bob = new Employee("Bob", 50.0);

        Team marketing = new Team("Marketing");
        marketing.addMember(bob);

        Department headOffice = new Department("Head Office");
        headOffice.addMember(sales);
        headOffice.addMember(marketing);

        // Display and calculate total salary for the organization hierarchy
        headOffice.displayInfo();
        double totalSalary = headOffice.calculateSalary();
        System.out.println("Total Salary for the Organization: Rs." + totalSalary);
    }
}