/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 * This class represents a doctor.
 */
public class Doctor {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String adresse;
    private int age;
    private int cin;
    private String password;
    private String roleperm;
    private String diplome;
    private String specialite;
     public static Doctor Current_doctor;

    public Doctor(int id, String nom, String prenom, int cin, String email, String adresse, int age, String password, String roleperm, String diplome, String specialite) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.cin = cin;
        this.adresse = adresse;
        this.age = age;
        this.password = password;
        this.roleperm = roleperm;
        this.diplome = diplome;
        this.specialite = specialite;
    }

    public Doctor() {

    }
  public Doctor(int id) {
        this.id = id;
    }
    public Doctor(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }
      public static Doctor getCurrent_doctor() {
        return Current_doctor;
    }

    public static void setCurrent_doctor(Doctor Current_doctor) {
        Doctor.Current_doctor = Current_doctor;
    }

    public Doctor(int a, String nomtype, String description) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return nom;
    }

    public void setFirstName(String nom) {
        this.nom = nom;
    }

    public String getLastName() {
        return prenom;
    }

    public void setLastName(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getAddress() {
        return adresse;
    }

    public void setAddress(String adresse) {
        this.adresse = adresse;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleperm() {
        return roleperm;
    }

    public void setRoleperm(String roleperm) {
        this.roleperm = roleperm;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    @Override
    public String toString() {
        return "doctor{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", email=" + email + ", adresse=" + adresse + ", age=" + age + ", password=" + password + ", roleperm=" + roleperm + ", diplome=" + diplome + ", specialite=" + specialite + '}';
    }
}