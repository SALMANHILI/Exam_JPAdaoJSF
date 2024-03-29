package Model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "Emlpoye")
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Employee_id")
    private Long id;

    @Column(name="employe_name")
    private String employeeName;

    @Column(name="email")
    private String email;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Employee_Project",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "project_id") }
    )
    private List<Project> projects = new ArrayList<>();

    public Employee() {

    }

    public Employee(Long id, String employeeName, String email, List<Project> projects) {
        this.id = id;
        this.employeeName = employeeName;
        this.email = email;
        this.projects = projects;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}