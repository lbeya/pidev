/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealtroc;

import entities.commentaire;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CRUDcommentaire;
import utils.MyConnection;

/**
 *
 * @author Eya
 */
public class FXMLDocumentController implements Initializable {
    commentaire c;
    public int cid;
    public String cidS;
    Statement ste;
    Connection conn = MyConnection.getInstance().getConnection();

    @FXML
    private Label label;
    
    @FXML
    private TextField texte;

    @FXML
    private TableView<commentaire> tablecommentaire;
    
    @FXML
    private TableColumn<commentaire,Integer> id;
     
    @FXML
    private TableColumn<commentaire,String> commentaire;
    
    @FXML
    private TableColumn<commentaire,Integer> iduser;
    
        @FXML
    private TableColumn<commentaire,LocalDateTime> date;
    
    public ObservableList<commentaire> data = FXCollections.observableArrayList();
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
        @FXML
    void Ajouter_commentaire(ActionEvent event) throws SQLException {
        System.out.println(texte.getText());
        
       // c=new commentaire();
        c = new commentaire(0, texte.getText(),1, LocalDateTime.now());
//        c.setCommentaire(texte.getText());
//        c.setId(1);
//        c.setDate(LocalDateTime.now());
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Ajouter_commentaire(c);
        ////////////////////////////// récuperer id from base 
try{
    Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT id FROM commentaire WHERE commentaire ='"+texte.getText()+"'");
if (rs.next()) {
    cid = rs.getInt("id");
    System.out.println(cid);
    
    c.setId(cid);
    System.out.println("id du commentaire est devenu="+c.getId());

}
}catch(SQLException ex) {
         System.out.println(ex);
                 }


         
    }
    
    @FXML
    void Supprimer_commentaire(ActionEvent event) {
        try{
    Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT id FROM commentaire WHERE commentaire ='"+texte.getText()+"'");
if (rs.next()) {
    cid = rs.getInt("id");
    System.out.println(cid);
    
    c.setId(cid);
    System.out.println("id du commentaire est devenu="+c.getId());

}
}catch(SQLException ex) {
         System.out.println(ex);
                 }
        
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Supprimer_commentaire(c.getId());

    }
    
    
    @FXML
    void Modifier_commentaire(ActionEvent event) {
        commentaire c1 = new commentaire(c.getId(), texte.getText(), 1, LocalDateTime.now());
        c1.toString();
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Modifier_commentaire(c1);
    }
    
    
    
    @FXML
    void Consulter_commentaires(ActionEvent event) {
//        CRUDcommentaire cc = new CRUDcommentaire();
//        System.out.println(cc.Consulter_commentaires());
try{
    String sql = "SELECT * FROM commentaire";

Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
    //commentaire resultCommentaire = new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime());
    System.out.println("commentaire{"+rs.getInt(1)+","+rs.getNString(2)+","+rs.getInt(3)+","+rs.getDate(4)+"}");
    

}
    }catch(SQLException ex) {
         System.out.println(ex);
                 }
    
}
    
   @FXML
   void view_commentaires(ActionEvent event){
       try{
           Statement stmt = conn.createStatement();
    String sql = "SELECT * FROM commentaire";

ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
 //commentaire resultCommentaire = new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime());
//data.add(new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime()));
data.add(new commentaire(rs.getInt(1),rs.getNString(2),rs.getInt(3),rs.getTimestamp("Date").toLocalDateTime()));
} 
    }catch(SQLException ex) { 
         System.out.println(ex);
                 }
    id.setCellValueFactory(new PropertyValueFactory<commentaire,Integer>("id") );
    commentaire.setCellValueFactory(new PropertyValueFactory<commentaire,String>("commentaire") );
    iduser.setCellValueFactory(new PropertyValueFactory<commentaire,Integer>("id_utilisateur") );
    date.setCellValueFactory(new PropertyValueFactory<commentaire,LocalDateTime>("Date") );
    tablecommentaire.setItems(data);

}
   
   } 


