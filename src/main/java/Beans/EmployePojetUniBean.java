package Beans;

import Model.Employee;
import Model.Project;
import jakarta.persistence.*;

public class EmployePojetUniBean {

     public static void main(String[] args) {
            EntityManagerFactory emf = null;
            EntityManager em = null;

            try {
                emf = Persistence.createEntityManagerFactory("Eclipselink_Exam");
                em = emf.createEntityManager();
                em.getTransaction().begin();

                Project p = new Project();
                p.setName("Salesforce");
                p.setBudget(20000);

                Employee e = new Employee();
                e.setEmployeName("Salma");
                e.setEmail("0salma.nhili0@gmail.com");
                e.setSkills("Java");

                em.persist(e);
                em.getTransaction().commit();
            } catch (Exception e) {
                if (em != null && em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                if (em != null) {
                    em.close();
                }
                if (emf != null) {
                    emf.close();
                }
            }
        }
    }

}
