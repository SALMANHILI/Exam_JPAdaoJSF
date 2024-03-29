package Dao;

import Model.Employee;

import java.util.List;

public interface EmployeDao {
    public List<Employee> getEmployeListfromDB();
    public Employee getEmployeById(int id);
    public  void InsertEmployeInDB(Employee employe);
    public void updateEmploye(Employee employe);
    public  boolean deleteEmploye(int id);
    public String testEmail(Employee employe);
}
