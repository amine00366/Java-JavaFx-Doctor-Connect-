/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;
import Entity.Post;
import Entity.Comment;
import Entity.PostLike;
import Entity.User;
import Util.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author khalil
 */
public class ServicePost {
     Connection cnx;

    public ServicePost() {
        cnx = MyDB.getInsatnce().getConnection();
    }
     public List<Post> getPostPopulairesParCommentair(int limit) {
        List<Post> posts = new ArrayList<>();

        try {
            String query = "SELECT DISTINCT post.* FROM post "
                    + "INNER JOIN postcomment ON post.id = postcomment.post_id "
                    + "ORDER BY (SELECT COUNT(*) FROM postcomment WHERE post_id = post.id) DESC "
                    + "LIMIT ?";
            PreparedStatement pstmt = cnx.prepareStatement(query);
            pstmt.setInt(1, limit);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setDescriptionP(rs.getString("description"));
                post.setHashtagP(rs.getString("title"));
              
 

                posts.add(post);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return posts;
    }
      public List<Post> recupererhashtag(String hashtag) {
        List<Post> posts = new ArrayList<>();
        try {
            String req = "select * from post where title = '" + hashtag+"' ORDER BY date_p DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setDescriptionP(rs.getString("description"));
                p.setHashtagP(rs.getString("title"));
                p.setDateP(rs.getString("date_p"));
                p.setImageP(rs.getString("photo"));
                p.setIdc(rs.getInt("creator"));
               posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
      public boolean ajouter(Post t) {
        boolean a=false;
        try {
            String req = "insert into post(creator,description,title,photo) values("+t.getIdc()+",' " + t.getDescriptionP()+ "','" + t.getHashtagP()+ "','" +t.getImageP()+"');";
          
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Post ajoutée");
            a=true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
    }
    public int comments (int id){
        List<Post> posts = new ArrayList<>();
        try {
            String req = "select * from postcomment where post_id = " +id+"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt("id"));
                p.setDescriptionP(rs.getString("creator"));
                p.setHashtagP(rs.getString("post_id"));
                p.setDateP(rs.getString("content"));
                p.setImageP(rs.getString("date_p"));
                p.setIdc(rs.getInt("user_id"));
               posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts.size(); 
    }
    public void modifier(Post t) {
        try {
            String req = "update post set description=?,title=?,photo=? where id= ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getDescriptionP());
            ps.setString(2, t.getHashtagP());
            ps.setInt(4, (int) t.getId());
            ps.setString(3, t.getImageP());
            ps.executeUpdate();
            System.out.println("Post modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }


    public void Supprimer(int id) {
    try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM post WHERE id = "+id+"";
                st.executeUpdate(req);      
      System.out.println("post supprimer avec succès...");
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());        
              }
    }
     public List<PostLike> likes (int id){
        List<PostLike> posts = new ArrayList<>();
        try {
            String req = "select * from post_like where post_id =" +id+";";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                PostLike p = new PostLike();
                p.setId(rs.getInt("id"));
                p.setIdpost(rs.getInt("post_id"));
                p.setIduser(rs.getInt("client_id"));
                posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts ; 
    }
     public List<Post> recuperer() {
        List<Post> posts = new ArrayList<>();
        try {
            String req = "select * from post ORDER BY date_p DESC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
                p.setDescriptionP(rs.getString("description"));
                p.setHashtagP(rs.getString("title"));
                p.setDateP(rs.getString("date_p"));
                p.setImageP(rs.getString("photo"));
                p.setIdc(rs.getInt("creator"));
               posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
       public List<PostLike> islikedbyuser(int idp,int idc) {
        
        List<PostLike> posts = new ArrayList<>();
        try {
            String req = "Select * from post_like where client_id= '"+idc+ "'and post_id ='"+idp+"';";
          
             Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                PostLike p = new PostLike();
                p.setId(rs.getInt("id"));
                p.setIdpost(rs.getInt("post_id"));
                p.setIduser(rs.getInt("client_id"));
                posts.add(p);
            }
            System.out.println(posts.size());
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       return posts;
    }
         public void Supprimerlike(int id, int idc ) {
    try
    { 
      Statement st = cnx.createStatement();
      String req = "DELETE FROM post_like WHERE post_id = '"+id+"' and client_id= '"+idc+"';";
                st.executeUpdate(req);      
      System.out.println("post supprimer avec succès...");
    } catch (SQLException ex) {
                System.out.println(ex.getMessage());        
              }
    }
    public boolean ajouterlike(int idp,int idc) {
        boolean a=false;
        try {
            String req = "insert into post_like(client_id,post_id) values("+idc+ "," +idp+")";
          
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            //System.out.println("like ajoutée");
            a=true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return a;
}
     public ObservableList<Post> getall() {
        ObservableList<Post> posts = FXCollections.observableArrayList();
        try {
            String req = "select * from post ORDER BY date_p ASC";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
            
                   p.setDescriptionP(rs.getString("description"));
                p.setHashtagP(rs.getString("title"));
                p.setDateP(rs.getString("date_p"));
                p.setImageP(rs.getString("photo"));
                p.setIdc(rs.getInt("creator"));
               posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return posts;
    }
    public boolean UserPost(int id ,int idc) {
        List<Post> posts = new ArrayList<>();
        try {
            String req = "select * from post where creator = " + idc +" and id = "+id+"";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {
                Post p = new Post();
                p.setId(rs.getInt(1));
              p.setDescriptionP(rs.getString("description"));
                p.setHashtagP(rs.getString("title"));
                p.setDateP(rs.getString("date_p"));
                p.setImageP(rs.getString("photo"));
                p.setIdc(rs.getInt("creator"));
               posts.add(p);
            }
            System.out.print(posts);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return !posts.isEmpty();
    }
    
    public User OneUser(int idu) {
           User u = new User();
        try {
            String req = "select * from user where id= "+idu;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                u.setCIN(rs.getInt("cin"));
                u.setAdresse(rs.getString("adresse"));
                u.setEmail(rs.getString("email"));
                u.setId(idu);
                u.setNumero(rs.getInt("numero"));
                u.setUserName(rs.getString("user_name"));
                System.out.println(u);
              
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return u ;
    }
}

