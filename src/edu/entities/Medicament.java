    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */
    package edu.entities;

    import java.util.List;

    /**
     *
     * @author SAHLI
     */
    public class Medicament {
          private int id;
        private String nom,description,image;
       private float prix;
        private CategorieMedicament categorie;
            //private List<PrescriptionMedicament> prescriptionMedicaments;
        private List<Ordonnance> ordonnances;

        @Override
        public String toString() {
            return "Medicament{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", image=" + image + ", prix=" + prix + ", categorie=" + categorie + ", ordonnances=" + ordonnances + '}';
        }

        public Medicament(int id, String nom, String description, String image, float prix, CategorieMedicament categorie, List<Ordonnance> ordonnances) {
            this.id = id;
            this.nom = nom;
            this.description = description;
            this.image = image;
            this.prix = prix;
            this.categorie = categorie;
            this.ordonnances = ordonnances;
        }

        public Medicament() {
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setNom(String nom) {
            this.nom = nom;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public void setPrix(float prix) {
            this.prix = prix;
        }

        public void setCategorie(CategorieMedicament categorie) {
            this.categorie = categorie;
        }

        public void setOrdonnances(List<Ordonnance> ordonnances) {
            this.ordonnances = ordonnances;
        }

        public int getId() {
            return id;
        }

        public String getNom() {
            return nom;
        }

        public String getDescription() {
            return description;
        }

        public String getImage() {
            return image;
        }

        public float getPrix() {
            return prix;
        }

        public CategorieMedicament getCategorie() {
            return categorie;
        }

        public List<Ordonnance> getOrdonnances() {
            return ordonnances;
        }

    }
