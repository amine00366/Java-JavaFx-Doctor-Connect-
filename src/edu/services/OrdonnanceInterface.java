/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.services;

import javafx.collections.ObservableList;

/**
 *
 * @author SAHLI
 * @param <T>
 */
public interface OrdonnanceInterface <T>{
   // public void AjouterOrdonnance(Ordonnance O);
  //  public  ObservableList<Ordonnance > AfficherOrdonnance ();
   // public void supprimer(Ordonnance O);
   // public void modifier(Ordonnance O);

    public void AjouterOrdonnance(T o);
    public void supprimer(int id);
    public void modifier(T o);
    public ObservableList<T> getAll();
    
}
