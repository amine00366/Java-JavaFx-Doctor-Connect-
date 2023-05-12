/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;
import Entity.User;
import Entity.Doctor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Util.MyDB;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author HP
 */
public class Crud {
     Connection cnx;
     
      public  Crud(){
        cnx =MyDB.getInsatnce().getConnection();
    }
    
  /*  public void ajouter(User t) {
    try {
        String qry = "INSERT INTO `user`( `FirstName`, `LastName`, `Email`, `Adresse`, `Age`, `Cin`, `Password`, `Roleperm`) VALUES (?,?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getAdresse());
        ps.setInt(5, t.getAge());
        ps.setInt(6, t.getCin());
        ps.setString(7, t.getPassword());
        ps.setString(8, t.getRoleperm());

        ps.executeUpdate();
        System.out.println("créé avec succès ");
    } catch (SQLException ex) {
        System.out.println("merci de vérifier et valider vos coordonnées ");

        System.out.println(ex.getMessage());
    }
}

     public User getUser(String email) {
        User stu = new User();
        try {
            String query = "select * from user where email =?";
           PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, email);
         ResultSet rs = pst.executeQuery();

            //tant que rs has next get matiere and add it to the list
            while (rs.next()) {

                stu.setId(rs.getInt("id")); //set id from req result
                stu.setFirstName(rs.getString("nom"));
                stu.setLastName(rs.getString("prenom"));
                stu.setEmail(rs.getString("email"));
                stu.setPassword(rs.getString("password"));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return stu;
    }
    public void updatemdp(User stu) {
    String req = "update user set password=?  WHERE email=?";
    try {
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, stu.getPassword());
        pst.setString(2, stu.getEmail());
        pst.executeUpdate();
        System.out.println("Password updated");
        System.out.println(stu.getPassword());
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}*/
/*public boolean login(String password, String email){
    
         try {
            String requete = "SELECT count(*) FROM user where password=? AND email=? ";
            
            PreparedStatement preparedStatement =MyDB.getInsatnce().getConnection().prepareStatement(requete);
            preparedStatement.setString(1, password);
            preparedStatement.setString(2, email);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (rs.getInt(1) == 1) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
       
    }
public ResultSet getUserInfo(String username) {
        ResultSet rs = null;
        try {
            Connection cnx = Myconn.getInstance().getcnx();
            String req = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, username);
            rs = pst.executeQuery();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return rs;
    }
   public List<User> afficher() {
    List<User> users = new ArrayList<>();
    try {
        String req = "SELECT * FROM `user` ";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            User u = new User();
            u.setId(rs.getInt(1));
            u.setFirstName(rs.getString(2));
            u.setLastName(rs.getString(3));
            u.setEmail(rs.getString(4));
            u.setAdresse(rs.getString(5));
            u.setAge(rs.getInt(6));
            u.setCin(rs.getInt(7));
            u.setRoleperm(rs.getString(8));
            users.add(u);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return users;
}






    
     public void modifier(User t) {
    try {
        String qry = "UPDATE `user` SET `FirstName`=?,`LastName`=?,`Email`=?,`Address`=?,`Age`=?,`CIN`=?,`Password`=?,`RolePerm`=? WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setString(1, t.getFirstName());
        ps.setString(2, t.getLastName());
        ps.setString(3, t.getEmail());
        ps.setString(4, t.getAdresse());
        ps.setInt(5, t.getAge());
        ps.setInt(6, t.getCin());
        ps.setString(7, t.getPassword());
       
        ps.setString(8, t.getRoleperm());
        ps.setInt(9, t.getId());

        ps.executeUpdate();
        System.out.println("modifié avec succès ");
    } catch (SQLException ex) {
        System.out.println("merci de vérifier et valider vos coordonnées ");
        System.out.println(ex.getMessage());
    }
}
   
    public void supprimer(int id) {
        try {
            String qry = "DELETE FROM `user` WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setInt(1, id);

            ps.executeUpdate();
            System.out.println("supprimé avec succès ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/
     public void ajouterd(Doctor d) {
         
        try {
            String qry = "INSERT INTO `doctor`(`nom`, `prenom`, `Email`, `adresse`, `Age`, `Cin`, `Password`, `RolePerm`, `Diplome`, `Specialite`) VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getEmail());
            ps.setString(4, d.getAddress());
            ps.setInt(5, d.getAge());
            ps.setInt(6, d.getCin());
            
            ps.setString(7, hashPassword(d.getPassword()));
           
            ps.setString(8, d.getRoleperm());
            ps.setString(9, d.getDiplome());
            ps.setString(10, d.getSpecialite());

            ps.executeUpdate();
            System.out.println("Doctor ajouté avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'ajout du doctor: " + ex.getMessage());
        }
    }
     

    public List<Doctor> afficherd() {
        List<Doctor> doctors = new ArrayList<>();

        try {
            String req ="SELECT * FROM `doctor` ";

            Statement st = cnx.createStatement();
            ResultSet rs= st.executeQuery(req);
            while (rs.next()){
                Doctor d = new Doctor();
                d.setId(rs.getInt(1));
                d.setFirstName(rs.getString(2));
                d.setLastName(rs.getString(3));
                d.setEmail(rs.getString(4));
                d.setAddress(rs.getString(5));
                d.setAge(rs.getInt(6));
                d.setCin(rs.getInt(7));
                d.setPassword(rs.getString(8));
                d.setRoleperm(rs.getString(9));
                d.setDiplome(rs.getString(10));
                d.setSpecialite(rs.getString(11));

                doctors.add(d);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur lors de l'affichage des doctors: " + ex.getMessage());
        }

        return doctors;
    }

    public void modifierd(Doctor d) {
        try {
            String qry = "UPDATE `doctor` SET `nom`=?,`prenom`=?,`Email`=?,`adresse`=?,`Age`=?,`Cin`=?,`Password`=?,`RolePerm`=?,`Diplome`=?,`Specialite`=? WHERE `id`=?";
            PreparedStatement ps = cnx.prepareStatement(qry);
            ps.setString(1, d.getFirstName());
            ps.setString(2, d.getLastName());
            ps.setString(3, d.getEmail());
            ps.setString(4, d.getAddress());
            ps.setInt(5, d.getAge());
            ps.setInt(6, d.getCin());
            ps.setString(7, d.getPassword());
            ps.setString(8, d.getRoleperm());
            ps.setString(9, d.getDiplome());
            ps.setString(10, d.getSpecialite());
            ps.setInt(11, d.getId());

            ps.executeUpdate();
            System.out.println("Doctor modifié avec succès");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification du doctor: " + ex.getMessage());
        }
    }
    public static String hashPassword(String password) {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return passwordEncoder.encode(password);
}
public void supprimerDoctor(int id) {
    try {
        String qry = "DELETE FROM `doctor` WHERE `id`=?";
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setInt(1, id);

        ps.executeUpdate();
        System.out.println("Doctor supprimé avec succès ");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
}








}
