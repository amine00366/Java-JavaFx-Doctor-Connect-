/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import Service.ServicePost;

/**
 *
 * @author khalil
 */

   public class Post implements Comparable<Post> {
   private int id , likes ,idc;
   private String HashtagP,DescriptionP,ImageP;
   private String DateP , country; 

    public Post(String titreb, String auteurb, int Idc) {
        this.id = Idc;
        this.HashtagP = auteurb;
        this.DescriptionP = titreb;
       }

    public int getIdc() {
        return idc;
    }

    public void setIdc(int idc) {
        this.idc = idc;
    }
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Post(int id, String HashtagP, String DescriptionP, String DateP) {
        this.id = id;
        this.HashtagP = HashtagP;
        this.DescriptionP = DescriptionP;
        this.DateP = DateP;
    }

    public Post(int id, int likes, String HashtagP, String DescriptionP,String ImageP, String DateP) {
        this.id = id;
        this.likes = likes;
        this.HashtagP = HashtagP;
        this.DescriptionP = DescriptionP;
        this.ImageP = ImageP;
        this.DateP = DateP;
    }
    
    public Post() {
    }

    public Post(String HashtagP, String DescriptionP,String DateP) {
        this.HashtagP = HashtagP;
        this.DescriptionP = DescriptionP;
        this.DateP = DateP;
    }

    public Post(int id, String HashtagP, String DescriptionP,String ImageP, String DateP) {
        this.id = id;
        this.HashtagP = HashtagP;
        this.DescriptionP = DescriptionP;
        this.ImageP = ImageP;
        this.DateP = DateP;
    }

    public Post(String HashtagP, String DescriptionP) {
        this.HashtagP = HashtagP;
        this.DescriptionP = DescriptionP;
    }
    
    public Post(String HashtagP, String DescriptionP, String ImageP, String DateP) {
        this.HashtagP = HashtagP;
        this.DescriptionP = DescriptionP;
        this.ImageP = ImageP;
        this.DateP = DateP;
    }
    
    public String getImageP() {
        return ImageP;
    }

    public void setImageP(String ImageP) {
        this.ImageP = ImageP;
    }
    
   
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHashtagP() {
        return HashtagP;
    }

    public void setHashtagP(String HashtagP) {
        this.HashtagP = HashtagP;
    }

    public String getDescriptionP() {
        return DescriptionP;
    }

    public void setDescriptionP(String DescriptionP) {
        this.DescriptionP = DescriptionP;
    }

  

    public String getDateP() {
        return DateP;
    }

    public void setDateP(String DateP) {
        this.DateP = DateP;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Post{" + "id=" + id + ", likes=" + likes + ", HashtagP=" + HashtagP + ", DescriptionP=" + DescriptionP + ", ImageP=" + ImageP + ", DateP=" + DateP + '}';
    }

    @Override
    public int compareTo(Post o) {
        ServicePost serv = new ServicePost();
        int l1 = serv.likes(this.id).size();
        int l2 = serv.likes(o.id).size();
        return l1-l2;
    }
  
    
    
}

