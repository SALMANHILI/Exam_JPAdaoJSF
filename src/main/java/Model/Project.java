package Model;

import jakarta.persistence.*;

import java.io.Serializable;
import Model.Employee;

@Entity
@Table(name = "Projet")
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "projet_id")
    private Long id;

    @Column(name="project_name")
    private String name;

    @Column(name="budget")
    private double budget;

    @ManyToMany(mappedBy = "projects")
    private List<Employee> employees = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}