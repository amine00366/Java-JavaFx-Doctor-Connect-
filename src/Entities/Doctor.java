/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author amine
 */
public class Doctor {

    public Doctor(int id) {
        this.id = id;
    }
    
    
    private int id ;
    private String name,username ;
    private String email ; 

    public Doctor(int id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        
    }

   
    public Doctor(String name, String username, String email) {
        this.name = name;
        this.username = username;
        this.email = email;
       
    }

    public Doctor(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Doctor() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
     @Override
    public String toString() {
        return  "id=" + id + ", email=" + email + ", name=" + name + ", username =" + username + '}';
    }
}
