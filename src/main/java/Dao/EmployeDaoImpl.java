package Dao;

import Model.Employee;
import Utils.ConnectionDb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeDaoImpl implements EmployeDao {
    public static String req;
    public static Connection conn = ConnectionDb.connectionBD();
    public static ResultSet res;
    public static PreparedStatement preparedStatement;
    @Override
    public List<Employee> getEmployeListfromDB() {
        List<Employee> employeBeans = new ArrayList<>();
        try{
            req="select * from donnees";
            preparedStatement = conn.prepareStatement(req);
            res=preparedStatement.executeQuery();
            while(res.next()){
                int id=res.getInt("id");
                String nom=res.getString("nom");
                String prenom=res.getString("prenom");
                String email=res.getString("email");
                String motdepasse=res.getString("motdepasse");
                int salaire= Integer.parseInt(res.getString("salaire"));
                employeBeans.add(new Employee(id,nom,prenom,email,motdepasse,salaire));
            }

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return employeBeans;
    }

    @Override
    public Employee getEmployeById(int id) {
        Employee employeBean =null;
        try{
            req="select * from donnees where id=?";
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            res = preparedStatement.executeQuery();
            while (res.next()) {
                String nom=res.getString("nom");
                String prenom=res.getString("prenom");
                String email=res.getString("email");
                String motdepasse=res.getString("motdepasse");
                int salaire= Integer.parseInt(res.getString("salaire"));
                employeBean = new Employee(id,nom,prenom,email,motdepasse,salaire);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return employeBean;
    }

    @Override
    public void InsertEmployeInDB(Employee employeBean) {
        try{
            req="insert into donnees (nom,prenom,email,motdepasse,salaire) values (?,?,?,?,?)";
            preparedStatement=conn.prepareStatement(req);
            preparedStatement.setString(1, employeBean.getNom());
            preparedStatement.setString(2, employeBean.getPrenom());
            preparedStatement.setString(3, employeBean.getEmail());
            preparedStatement.setString(4, employeBean.getMotdepasse());
            preparedStatement.setInt(5, employeBean.getSalaire());
            preparedStatement.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateEmploye(Employee employeBean) {
        try{
            req="update donnees set nom=?,prenom=?,email=?, motdepasse=?, salaire=? where id=?";
            preparedStatement =conn.prepareStatement(req);
            preparedStatement.setString(1, employeBean.getNom());
            preparedStatement.setString(2, employeBean.getPrenom());
            preparedStatement.setString(3, employeBean.getEmail());
            preparedStatement.setString(4, employeBean.getMotdepasse());
            preparedStatement.setInt(5, employeBean.getSalaire());
            preparedStatement.setInt(6, employeBean.getId());
            int rowsAffected= preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Un Employe a été mis à jour avec succès.");
            }

        }
        catch(SQLException e){
         System.out.println("Erreur lors de la mise à jour de l'Employe : " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public boolean deleteEmploye(int id) {
        boolean rowDeleted = false;
        try{
            req="delete from donnees where id=?";
            preparedStatement=conn.prepareStatement(req);
            preparedStatement.setInt(1,id);
            rowDeleted=preparedStatement.executeUpdate()>0;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return rowDeleted;
    }
    @Override
    public String testEmail(Employee employe) {
        try {
            String req = "SELECT email FROM donnees WHERE id != ? AND email = ?";
            preparedStatement = conn.prepareStatement(req);
            preparedStatement.setInt(1, employe.getId());
            preparedStatement.setString(2, employe.getEmail());
            res = preparedStatement.executeQuery();
            if (res.next()) {
                return "Error: Email already exists.";
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: Database connection error.";
        }
    }


}
