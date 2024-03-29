package Beans;

import Model.Employee;
import Services.EmployeService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class EmployeBean implements Serializable {

    private List<Employee> employes;
    private Map<Integer, Boolean> editModes;
    private Employee employeAdd = new Employee();

    private final EmployeService employeService = new EmployeService();
    private String emailExistError;




    @PostConstruct
    public void init() {
        refreshData();
    }

    public List<Employee> getEmployes() {
        return employes;
    }

    public Employee getEmployeAdd() {
        return employeAdd;
    }

    public void setEmployeAdd(Employee employeAdd) {
        this.employeAdd = employeAdd;
    }

    public void addEmploye() {
        employeService.addEmployeService(employeAdd);
        refreshData();

    }

    public void updateEmploye(Employee employe) {
        employeService.updateEmployeService(employe);
        refreshData();
    }

    public void deleteEmploye(int id) {
        employeService.deleteEmployeService(id);
        refreshData();
    }

    public Map<Integer, Boolean> getEditModes() {
        return editModes;
    }

    public void toggleEditMode(Employee employe) {
        long id = employe.getId();
        boolean currentMode = editModes.get(id);
        editModes.put(id,!currentMode);
    }

    public void refreshData() {
        employes = employeService.getEmployesListService();
        editModes = new HashMap<>();
        for (Employee employe : employes) editModes.put(employe.getId(), false);

        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("EmployeTableForm:EmployeTable");

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Table refreshed successfully.", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


}
