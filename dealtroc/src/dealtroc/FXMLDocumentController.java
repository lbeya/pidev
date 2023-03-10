/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dealtroc;

import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentClass;
import edu.stanford.nlp.util.CoreMap;
import entities.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Properties;
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
import static services.CRUDcommentaire.estUneChaineSansChiffres;
import utils.MyConnection;
import javax.mail.*;
/**
 *
 * @author Eya
 */
public class FXMLDocumentController implements Initializable {
    commentaire c;
    public String mail;
    public String cidS;
    Statement ste;
    int x;
    Connection conn = MyConnection.getInstance().getConnection();
    
    commentaire selectedCommentaire;

    @FXML
    private Label label;
    
    @FXML
    private TextField texte;
    
    
    @FXML
    private TextField textM;


    @FXML
    private TableView<commentaire> tablecommentaire;
    
    
    @FXML
    private TableColumn<commentaire,Integer> id;
     
    @FXML
    private TableColumn<commentaire,String> commentaire;
    
    @FXML
    private TableColumn<commentaire,Integer> iduser;
    
  
    @FXML
    private TableColumn<commentaire,Integer> idproduit;
        
    @FXML
    private TableColumn<commentaire,LocalDateTime> date;
    
        @FXML
    private TableColumn<commentaire, String> type;

    
    public ObservableList<commentaire> data = FXCollections.observableArrayList();
    
    @FXML
    private Label labelerror;
    
    @FXML
    private Label labelvalide;
    
    
    @FXML
    private Label AnalyseComm;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
    } 
    public void NbrDeCommParP(int idP) throws SQLException{
        try{
    Statement stmt = conn.createStatement();
    String req = "SELECT COUNT(*)  FROM `commentaire` WHERE IdProduit = " + idP;
    ResultSet rs = stmt.executeQuery(req);
    System.out.println(req);
// R??cup??ration du r??sultat de la requ??te
rs.next();
int nombreDeLignes = rs.getInt(1);
// Affichage du nombre de lignes
System.out.println("Le nombre de lignes dans la table est : " + nombreDeLignes);
        }catch(Exception ex){
    System.out.println(ex);
}
}
    
        @FXML
    void Ajouter_commentaire(ActionEvent event) throws SQLException, MessagingException {

        System.out.println(texte.getText());
        if (estUneChaineSansChiffres(texte.getText())==false){
            labelvalide.setText("");
            labelerror.setText("invalide format: le commentaire ne doit pas ??tre vide ou contenir des nombres >=8");
       
        }else{
                    //Analyse du commentaire
Properties props = new Properties();
props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
Annotation document = new Annotation(texte.getText());
pipeline.annotate(document);
CoreMap sentence = document.get(SentencesAnnotation.class).get(0);
String sentiment = sentence.get(SentimentClass.class);
System.out.println(sentiment);

            labelvalide.setText(texte.getText()+" a ??t?? ajout??");
            labelerror.setText("");
                   // c=new commentaire();
       c = new commentaire(0, texte.getText(),1, LocalDateTime.now(),sentiment,2);
       //c = new commentaire(0, texte.getText(),1, LocalDateTime.now(),sentiment,1);

        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Ajouter_commentaire(c);
        
        //nombre des commentaires par produit
        x=c.getIdProduit();
       System.out.println(x);
     NbrDeCommParP(x);
        try{
    Statement stmt = conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT email FROM utilisateur WHERE Id_utilisateur  ='"+0+"'");
//ResultSet rs = stmt.executeQuery("SELECT email FROM utilisateur WHERE Id_utilisateur  = (SELECT iduser FROM  produit WHERE IdProduit ='"+1+"')");
ResultSet rs = stmt.executeQuery("SELECT email FROM utilisateur WHERE Id_utilisateur  = (SELECT iduser FROM  produit WHERE IdProduit ='"+2+"')");

if (rs.next()) {
    mail = rs.getString("email");
    System.out.println(mail);
}
}catch(SQLException ex) {
         System.out.println(ex);
                 }
        }        

               
        ////////////////////////////// r??cuperer id from base 
//try{
//    Statement stmt = conn.createStatement();
//ResultSet rs = stmt.executeQuery("SELECT id FROM commentaire WHERE commentaire ='"+texte.getText()+"'");
//if (rs.next()) {
//    cid = rs.getInt("id");
//    System.out.println(cid);
//    
//    //c.setId(cid);
//    System.out.println("id du commentaire est devenu="+c.getId());
//
//}
//}catch(SQLException ex) {
//         System.out.println(ex);
//                 }

   //String to = "gharnougui.ismail19@gmail.com";
//   String to = mail;
//   String from ="lbeya99@gmail.com";
//   String subject = "Deal Troc";
//   String body = "Someone commented your article";
//   String passwd="ltusslpyminftuvk";
//   MailSender.sendEmail(to,from,passwd,subject,body);
   
//  SMSsender.sendSMS();

    }
    ////supprimer en ecrivant le chaine de caract??re
//    @FXML
//    void Supprimer_commentaire(ActionEvent event) {
//
////        try{
////    Statement stmt = conn.createStatement();
    ////recuperer id from base
////ResultSet rs = stmt.executeQuery("SELECT id FROM commentaire WHERE commentaire ='"+texte.getText()+"'");
////if (rs.next()) {
////    cid = rs.getInt("id");
////    System.out.println(cid);
////    
////c.setId(cid);
////    System.out.println("id du commentaire est devenu="+c.getId());
////
////}
////}catch(SQLException ex) {
////         System.out.println(ex);
////                 }
////        
//        CRUDcommentaire cc = new CRUDcommentaire();
//        cc.Supprimer_commentaire(c.getId());
//        labelvalide.setText(texte.getText()+" a ??t?? supprim??");
//
//    }
    
        @FXML
    void Supprimer_commentaire(ActionEvent event) {
         labelerror.setText("");
selectedCommentaire = tablecommentaire.getSelectionModel(). getSelectedItem();
            System.out.println(selectedCommentaire.getId());      
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Supprimer_commentaire(selectedCommentaire.getId());
        labelvalide.setText(selectedCommentaire.getCommentaire()+" a ??t?? supprim??");

    }
    
    
//    @FXML
//    void Modifier_commentaire(ActionEvent event) {
//       // iduser.setEditable(true);
//            if (estUneChaineSansChiffres(texte.getText())){
//            labelvalide.setText("votre commentaire a ??t?? modifi??");
//        }
//        commentaire c1 = new commentaire(c.getId(), texte.getText(), 1, LocalDateTime.now());
//        c1.toString();
//        CRUDcommentaire cc = new CRUDcommentaire();
//        cc.Modifier_commentaire(c1);
//    }
    
        @FXML
        void Modifier_commentaire(ActionEvent event) {
            Properties props = new Properties();
props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
Annotation document = new Annotation(textM.getText());
pipeline.annotate(document);
CoreMap sentence = document.get(SentencesAnnotation.class).get(0);
String sentiment = sentence.get(SentimentClass.class);
System.out.println(sentiment);

              if (estUneChaineSansChiffres(textM.getText())==false){
            labelerror.setText("invalide format: le commentaire ne doit pas contenir des nombres >8");
            labelvalide.setText("");
       
        }else{
            labelvalide.setText("valide format");
            labelerror.setText("");
        } 
        selectedCommentaire = tablecommentaire.getSelectionModel(). getSelectedItem();

        commentaire c1 = new commentaire(selectedCommentaire.getId(), textM.getText(), 1, LocalDateTime.now(),sentiment);
        c1.toString();
        CRUDcommentaire cc = new CRUDcommentaire();
        cc.Modifier_commentaire(c1);
                    if (estUneChaineSansChiffres(textM.getText())){
            labelvalide.setText(selectedCommentaire.getCommentaire() +"a ??t?? modifi?? ?? "+ textM.getText());
        }
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
    System.out.println("commentaire{"+rs.getInt(1)+","+rs.getNString(2)+","+rs.getInt(3)+","+rs.getDate(4)+rs.getNString(5)+"}");
    

}
    }catch(SQLException ex) {
         System.out.println(ex);
                 }
    
}
    
   @FXML
   void view_commentaires(ActionEvent event){
       data.clear();
       tablecommentaire.setItems(data);
       
       try{
           Statement stmt = conn.createStatement();
    String sql = "SELECT * FROM commentaire";

ResultSet rs = stmt.executeQuery(sql);
while (rs.next()) {
 //commentaire resultCommentaire = new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime());
//data.add(new commentaire(rs.getInt("id"), rs.getString("commentaire"), rs.getInt("id_utilisateur"), rs.getTimestamp("Date").toLocalDateTime()));
data.add(new commentaire(rs.getInt(1),rs.getNString(2),rs.getInt(3),rs.getTimestamp("Date").toLocalDateTime(),rs.getNString(5)));
} 
    }catch(SQLException ex) { 
         System.out.println(ex);
                 }
    id.setCellValueFactory(new PropertyValueFactory<commentaire,Integer>("id") );
    commentaire.setCellValueFactory(new PropertyValueFactory<commentaire,String>("commentaire") );
    iduser.setCellValueFactory(new PropertyValueFactory<commentaire,Integer>("id_utilisateur") );
    date.setCellValueFactory(new PropertyValueFactory<commentaire,LocalDateTime>("Date") );
    type.setCellValueFactory(new PropertyValueFactory<commentaire,String>("type") );
    idproduit.setCellValueFactory(new PropertyValueFactory<commentaire,Integer>("IdProduit") );

    tablecommentaire.setItems(data);

}

   
   
   } 


