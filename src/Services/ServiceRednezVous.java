
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Appointment;
import Entity.Typeappoinment;
import Entity.Doctor;
import Entity.User;
import Utils.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author amine
 */
public class ServiceRednezVous implements IServiceRendezVous<Appointment>{
 
    Connection con=DataSource.getInstance().getConnection();
    
    private Statement ste;
    private Appointment p;

    public ServiceRednezVous() {
        
        try {
            ste=con.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
  
    
    
    // ajouter un rendez vous 
    public boolean ajouter(Appointment c) throws SQLException {
    boolean verif = true;

    try {
        String req = "INSERT INTO `Appointment`(`id`, `user_id`, `doctor_id`, `appointment_date`, `datefin`, `type_id`, `categorie`, `approved`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, c.getId());
        pre.setInt(2, c.getUser().getId());
        pre.setInt(3, c.getDoctor().getId());
        
      //  pre.setDate(4,c.getAppointment_date());
         Timestamp timestamp = Timestamp.valueOf(c.getAppointment_date());
            pre.setTimestamp(4, timestamp);
       LocalDateTime datefin = c.getAppointment_date().plusMinutes(30);
Timestamp datefinTimestamp = Timestamp.valueOf(datefin);

// Utiliser la méthode setTimestamp() pour la colonne 4 et 5
pre.setTimestamp(5, datefinTimestamp);
      
        pre.setInt(6, c.getType().getId());
        pre.setString(7, c.getCategorie());
        pre.setBoolean(8, c.isApproved());

        int rowsInserted = pre.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Appointment ajouté avec succès !");
        }
    } catch (SQLException e) {
        System.out.println(e);
        verif = false;
    }

    return verif;
}
    
    
//
public User OneUser(int idu) {
           User u = new User();
        try {
            String req = "select * from user where id= "+idu;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                u.setCIN(rs.getInt("cin"));
                u.setAdresse(rs.getString("adresse"));
                u.setEmail(rs.getString("email"));
                u.setId(idu);
                u.setNumero(rs.getInt("numero"));
               // u.setRoles(rs.getString("roles"));
                u.setUserName(rs.getString("user_name"));
                System.out.println(u);
              
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u ;
    }
    ////
    //// afficher la liste de tous les rendez vous 
    public List<Appointment> readAll() throws SQLException {
    List<Appointment> appointmentList = new ArrayList<>();
    String req = "SELECT * FROM Appointment";
    ResultSet res = ste.executeQuery(req);
    while (res.next()) {
        Appointment appointment = new Appointment();
      appointment.setId(res.getInt("id"));
  //   appointment.setAppointment_date(res.getDate("appointment_date"));
  Timestamp timestamp = res.getTimestamp("appointment_date");
LocalDateTime dateTime = timestamp.toLocalDateTime();
appointment.setAppointment_date(dateTime);


        appointment.setDatefin(res.getDate("datefin"));
        appointment.setCategorie(res.getString("categorie"));
        appointment.setApproved(false);
       
        int userId = res.getInt("user_id");
        int doctorId = res.getInt("doctor_id");
        int typeId = res.getInt("type_id");
    

        ServiceDoctor coursServices = new ServiceDoctor();
            Doctor cours = coursServices.GetDoctorById(doctorId);
            ServicetypeRDV typeServices = new ServicetypeRDV();
            Typeappoinment typee = typeServices.GetTypeById(typeId);
            ServiceUser userServices = new ServiceUser();
            User userr = userServices.GetUserById(userId);
        //Appointment appointment = new Appointment(id, new User(userId), new Doctor(doctorId), new Typeappoinment(typeId), appointmentDate, dateFin, category, approved);
       appointment.setUser(userr);
       appointment.setDoctor(cours);
       appointment.setType(typee);
        appointmentList.add(appointment);
    }
    return appointmentList;
}
    
    
    
    
    
    
    
    
    
    
////// update tekhdem baad jointure 
public void update(Appointment c) throws SQLException {
    String req="UPDATE `Appointment` SET `user_id`=?,`doctor_id`=?,`appointment_date`=?,`datefin`=?,`type_id`=?,`categorie`=? ,`approved`=? where `id` ="+c.getId()+"";

    PreparedStatement pre=con.prepareStatement(req);

    pre.setInt(1, c.getUser().getId());
    pre.setInt(2, c.getDoctor().getId());
    Timestamp timestamp = Timestamp.valueOf(c.getAppointment_date());
            pre.setTimestamp(3, timestamp);
   // pre.setLocalDateTime(3,c.getAppointment_date());
pre.setTimestamp(3, timestamp);
Timestamp time = Timestamp.valueOf(c.getAppointment_date());
            pre.setTimestamp(4, time);
    pre.setInt(5, c.getType().getId());
    pre.setString(6, c.getCategorie());
    pre.setBoolean(7, c.isApproved());

    pre.executeUpdate();
    System.out.println("rendez vous modifié !");
}
    
    
    public boolean supprimer(int id) throws SQLException {
    String req = "DELETE FROM `Appointment` WHERE id = ?;";
    PreparedStatement pre = con.prepareStatement(req);
    pre.setInt(1, id);

    int rowsDeleted = pre.executeUpdate();
    return rowsDeleted > 0;
}
    
    
    
    
    




  








public List<Appointment> getAppointmentsByDoctorAndDate(int doctorId, LocalDateTime date) {
    List<Appointment> appointments = new ArrayList<>();


    try {
      
  
      
        String query = "SELECT * FROM appointment WHERE doctor_id = ? AND appointment_date = ?";
        PreparedStatement pre=con.prepareStatement(query);
        pre.setInt(1, doctorId);
        pre.setTimestamp(2, Timestamp.valueOf(date));
        ResultSet rs = pre.executeQuery();

        while (rs.next()) {
            Appointment appointment = new Appointment();
            appointment.setId(rs.getInt("id"));
            appointment.setAppointment_date(rs.getTimestamp("appointment_date").toLocalDateTime());
            appointment.setApproved(rs.getBoolean("approved"));
            // Set other properties here...

            appointments.add(appointment);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return appointments;
}


   /* @Override
    public List<Appointment> findById(int id) throws SQLException {
        ArrayList<Appointment> listper = new ArrayList<>();
        String req = "select * FROM Appointment where user_id = " + id + ";";

        ResultSet res = ste.executeQuery(req);
    while (res.next()) {
      int idr = res.getInt(1);
            int user_id = res.getInt(2);
            int doctor_id = res.getInt(3);
           LocalDateTime appointment_date = res.getTimestamp(4).toLocalDateTime();
            LocalDateTime datefin = res.getTimestamp(5).toLocalDateTime();
            int type_id = res.getInt(6);
            String categorie = res.getString(7);
            Boolean approved = res.getBoolean(8);
       Appointment c = new Appointment(idr, user_id, doctor_id , appointment_date, datefin ,type_id , categorie , approved );
  listper.add(c);
    }
        
        return listper;
    }*/
    

  public List<Appointment> UserAppointment(int idc) throws SQLException {
    List<Appointment> appointmentList = new ArrayList<>();
    String req = "select * from Appointment where user_id = " + idc ;
    ResultSet res = ste.executeQuery(req);
    while (res.next()) {
        Appointment appointment = new Appointment();
      appointment.setId(res.getInt("id"));
  //   appointment.setAppointment_date(res.getDate("appointment_date"));
  Timestamp timestamp = res.getTimestamp("appointment_date");
LocalDateTime dateTime = timestamp.toLocalDateTime();
appointment.setAppointment_date(dateTime);


        appointment.setDatefin(res.getDate("datefin"));
        appointment.setCategorie(res.getString("categorie"));
        appointment.setApproved(false);
       
      //  int userId = res.getInt("user_id");
        int doctorId = res.getInt("doctor_id");
        int typeId = res.getInt("type_id");
    

        ServiceDoctor coursServices = new ServiceDoctor();
            Doctor cours = coursServices.GetDoctorById(doctorId);
            ServicetypeRDV typeServices = new ServicetypeRDV();
            Typeappoinment typee = typeServices.GetTypeById(typeId);
            ServiceUser userServices = new ServiceUser();
           User userr = userServices.GetUserById(idc);
        //Appointment appointment = new Appointment(id, new User(userId), new Doctor(doctorId), new Typeappoinment(typeId), appointmentDate, dateFin, category, approved);
       appointment.setUser(userr);
       appointment.setDoctor(cours);
       appointment.setType(typee);
        appointmentList.add(appointment);
    }
    return appointmentList;
}

  
   public List<Appointment> DoctorAppointment(int idc) throws SQLException {
    List<Appointment> appointmentList = new ArrayList<>();
    String req = "select * from Appointment where doctor_id = " + idc ;
    ResultSet res = ste.executeQuery(req);
    while (res.next()) {
        Appointment appointment = new Appointment();
      appointment.setId(res.getInt("id"));
  //   appointment.setAppointment_date(res.getDate("appointment_date"));
  Timestamp timestamp = res.getTimestamp("appointment_date");
LocalDateTime dateTime = timestamp.toLocalDateTime();
appointment.setAppointment_date(dateTime);


        appointment.setDatefin(res.getDate("datefin"));
        appointment.setCategorie(res.getString("categorie"));
        appointment.setApproved(false);
       
        int userId = res.getInt("user_id");
        int doctorId = res.getInt("doctor_id");
        int typeId = res.getInt("type_id");
    

        ServiceDoctor coursServices = new ServiceDoctor();
            Doctor cours = coursServices.GetDoctorById(doctorId);
            ServicetypeRDV typeServices = new ServicetypeRDV();
            Typeappoinment typee = typeServices.GetTypeById(typeId);
            ServiceUser userServices = new ServiceUser();
           User userr = userServices.GetUserById(userId);
        //Appointment appointment = new Appointment(id, new User(userId), new Doctor(doctorId), new Typeappoinment(typeId), appointmentDate, dateFin, category, approved);
       appointment.setUser(userr);
       appointment.setDoctor(cours);
       appointment.setType(typee);
        appointmentList.add(appointment);
    }
    return appointmentList;
}

/* public List<Appointment> readByUser(int userid) throws SQLException {
    List<Appointment> appointmentList = new ArrayList<>();
    String req = "SELECT * FROM Appointment  WHERE user_id = ?";
    ResultSet res = ste.executeQuery(req);
     PreparedStatement pre=con.prepareStatement(req);
        pre.setInt(1, userid);
    while (res.next()) {
        Appointment appointment = new Appointment();
      appointment.setId(res.getInt("id"));
  Timestamp timestamp = res.getTimestamp("appointment_date");
LocalDateTime dateTime = timestamp.toLocalDateTime();
appointment.setAppointment_date(dateTime);


        appointment.setDatefin(res.getDate("datefin"));
        appointment.setCategorie(res.getString("categorie"));
        appointment.setApproved(false);
       
        int userId = res.getInt("user_id");
        int doctorId = res.getInt("doctor_id");
        int typeId = res.getInt("type_id");
    

        ServiceDoctor coursServices = new ServiceDoctor();
            Doctor cours = coursServices.GetDoctorById(doctorId);
            ServicetypeRDV typeServices = new ServicetypeRDV();
            Typeappoinment typee = typeServices.GetTypeById(typeId);
            ServiceUser userServices = new ServiceUser();
            User userr = userServices.GetUserById(userId);
        //Appointment appointment = new Appointment(id, new User(userId), new Doctor(doctorId), new Typeappoinment(typeId), appointmentDate, dateFin, category, approved);
       appointment.setUser(userr);
       appointment.setDoctor(cours);
       appointment.setType(typee);
        appointmentList.add(appointment);
    }
    return appointmentList;
}*/
    
    /*  public List<Appointment> sortbydate() throws SQLException {
        ArrayList<Appointment> listper = new ArrayList<>();

        String req = "select * from Appointment ORDER by appointment_date";

        ResultSet res = ste.executeQuery(req);

        while (res.next()) {
          int idr = res.getInt(1);
            int user_id = res.getInt(2);
            int doctor_id = res.getInt(3);
            LocalDateTime appointment_date = res.getTimestamp(4).toLocalDateTime();
            LocalDateTime datefin = res.getTimestamp(5).toLocalDateTime();
            int type_id = res.getInt(6);
            String categorie = res.getString(7);
            Boolean approved = res.getBoolean(8);
             Appointment c = new Appointment(idr, user_id, doctor_id , appointment_date, datefin ,type_id , categorie , approved );
  listper.add(c);
        }
        return listper;
    }*/

   
    
}

