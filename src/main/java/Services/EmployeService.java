package Services;

import Dao.EmployeDao;
import Dao.EmployeDaoImpl;
import Model.Employee;

import java.util.List;

public class EmployeService {

    private final EmployeDao EmployeDao = new EmployeDaoImpl();

    public List<Employee> getEmployesListService() {
        return EmployeDao.getEmployeListfromDB();
    }

    public Employee getEmployeByIdService(int id) {
        return EmployeDao.getEmployeById(id);
    }

    public void addEmployeService(Employee Employe) {
        String errorMessage = EmployeDao.testEmail(Employe);
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
        EmployeDao.InsertEmployeInDB(Employe);
    }

    public void updateEmployeService(Employee Employe) {
        String errorMessage = EmployeDao.testEmail(Employe);
        if (errorMessage != null) {
            throw new IllegalArgumentException(errorMessage);
        }
        EmployeDao.updateEmploye(Employe);
    }

    public void deleteEmployeService(int id) {
        EmployeDao.deleteEmploye(id);
    }

}
