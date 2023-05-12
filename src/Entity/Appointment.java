package Entity;

import Entity.User;
import java.sql.Date;
import java.time.LocalDateTime;
//import java.time.LocalDate;
//import java.time.LocalDateTime;

public class Appointment {
    private int id;
    private User user;
    private Doctor doctor;
    private Typeappoinment type;
   // private Date appointment_date;
    private Date datefin;
    private String categorie;
    private boolean approved;
    private LocalDateTime appointment_date;

    public Appointment(int id, User user, Doctor doctor, Typeappoinment type, LocalDateTime appointment_date, Date datefin, String categorie, boolean approved) {
        this.id = id;
        this.user = user;
        this.doctor = doctor;
        this.type = type;
        this.appointment_date = appointment_date;
        this.datefin = datefin;
        this.categorie = categorie;
        this.approved = approved;
    }

    public Appointment(int id, User user, Doctor doctor, Typeappoinment type, String categorie, boolean approved) {
        this.id = id;
        this.user = user;
        this.doctor = doctor;
        this.type = type;
        this.categorie = categorie;
        this.approved = approved;
    }

     public Appointment(int id, User user, Doctor doctor,LocalDateTime appointment_date, Typeappoinment type, String categorie, boolean approved ) {
        this.id = id;
        this.user = user;
        this.doctor = doctor;
        this.type = type;
        this.categorie = categorie;
        this.approved = approved;
        this.appointment_date = appointment_date;
    }
     
    public Appointment(int id, Typeappoinment type, LocalDateTime appointment_date, Date datefin, String categorie) {
        this.id = id;
        this.type = type;
        this.appointment_date = appointment_date;
        this.datefin = datefin;
        this.categorie = categorie;
    }

    public Appointment() {
    }

    public Appointment(Typeappoinment type, String categorie, Date dateDebut, Date dateFin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Appointment(int a, User b, Doctor c, Date selectedStartDate, Date selectedEndDate, Typeappoinment selectedType, String selectedMode, boolean e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Appointment(int a, User b, Doctor c, Typeappoinment selectedType, LocalDateTime startDateTime, String selectedMode, boolean b0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Typeappoinment getType() {
        return type;
    }

    public void setType(Typeappoinment type) {
        this.type = type;
    }

    public LocalDateTime getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(LocalDateTime appointment_date) {
        this.appointment_date = appointment_date;
    }

    public Date getDatefin() {
        return datefin;
    }


    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    public String toString() {
    return "Appointment [id=" + id + ", user=" + user + ", doctor=" + doctor + ", type=" + type + ", appointment_date=" + appointment_date + ", datefin=" + datefin + ", categorie=" + categorie + ", approved=" + approved + "]";
}
}






