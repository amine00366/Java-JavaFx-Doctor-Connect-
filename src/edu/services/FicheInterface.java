/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;
import javafx.collections.ObservableList;
import java.util.List;
import static javafx.scene.input.KeyCode.T;

/**
 *
 * @author SAHLI
 */
public interface FicheInterface <T> {
     public void Ajouter(T f);
    public void supprimer(int id);
    public void modifier(T o);
    public List<T> getAll();
}
