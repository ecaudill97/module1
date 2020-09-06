//Eric Caudill July 27th, 2020 OOP Valencia Project 8

package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.sql.*;


import javafx.stage.*;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		
		// JAVA fx creating GUI
		
		try {
			
			// create title
			
			primaryStage.setTitle("Persons database interface!");
			
			// create buttons
			
			Button insertThree = new Button("Insert");
			Button showAllbutton = new Button("Show All");
			Button insertButton = new Button("Insert");
			Button selectButton = new Button("Select");
			Button deleteButton = new Button("Delete");
			
			// create labels
			
			Label ThreeLabel = new Label("Insert three test persons into db.  (use once)");
			Label showAlllabel = new Label("Show all persons in database");
			Label newInsert = new Label("Fill out all information to insert new person: ");
			Label id = new Label("ID*: ");
			Label firstName = new Label("First Name*: ");
			Label lastName = new Label("Last Name*: ");
			Label age = new Label("Age*: ");
			Label ss = new Label("Social Security*: ");
			Label cc = new Label("Credit Card*: ");
			Label selectLabel = new Label("Select by ID: ");
			Label idDelete = new Label("Delete by First and Last: ");
			
			// text fields
			
			TextField idT = new TextField();
			TextField firstT = new TextField();
			TextField lastT = new TextField();
			TextField ageT = new TextField();
			TextField ssT = new TextField();
			TextField ccT = new TextField();
			TextField selectT = new TextField();
			TextField deleteFirst = new TextField();
			TextField deleteLast = new TextField();
			
			
			
			// Button action on click
			
			insertThree.setOnAction(actionEvent ->  {
				System.out.println("Inserting 3 people into db...");
				
	        	Person eric = new Person(1,"Eric","Caudill",22,100000000,200000000);
	        	Person sponge = new Person(2,"Sponge","Bob",30,100000000,200000000);
	        	Person patrick = new Person(3,"Patrick","Star",30,100000000,200000000);
	        	
	        	Person.insertPerson(eric);
	        	Person.insertPerson(sponge);
	        	Person.insertPerson(patrick);
	        	
			});
			
			showAllbutton.setOnAction(actionEvent ->  {
				
				System.out.println("Showing all people in records...");
				Person.showAll();
			    
			});
			

			insertButton.setOnAction(actionEvent -> {

				System.out.println("Inserting " + firstT.getText() + " " + lastT.getText() + " into db...");
				Person newPerson = new Person(Integer.parseInt(idT.getText()), firstT.getText(), lastT.getText(),
						Integer.parseInt(ageT.getText()), Long.parseLong(ssT.getText()), Long.parseLong(ccT.getText()));
				Person.insertPerson(newPerson);

			});
			
			selectButton.setOnAction(actionEvent ->  {
				
				System.out.println("Showing person based on id "+selectT.getText()+"...");
				Person.selectPerson(Integer.parseInt(selectT.getText()));
			    
			});
			
			deleteButton.setOnAction(actionEvent ->  {
				
				System.out.println("Deleting " + deleteFirst.getText() + " " + deleteLast.getText() + "...");
				Person.deletePerson(deleteFirst.getText(),deleteLast.getText());
				System.out.println("Showing updated list...");
				Person.showAll();
		
			    
			});

			// hbox

			HBox hboxTest = new HBox(10, ThreeLabel, insertThree);
			HBox hboxShow = new HBox(10, showAlllabel, showAllbutton);
			HBox hboxid = new HBox(10,id,idT);
			HBox hboxfirst = new HBox(10,firstName,firstT);
			HBox hboxlast = new HBox(10,lastName,lastT);
			HBox hboxage = new HBox(10,age,ageT);
			HBox hboxss = new HBox(10,ss,ssT);
			HBox hboxcc = new HBox(10,cc,ccT);
			HBox hboxselect = new HBox(10,selectLabel,selectT,selectButton);
			HBox hboxdelete = new HBox(10,idDelete,deleteFirst,deleteLast,deleteButton);
			
			//vbox
			
	        VBox vbox = new VBox(15,hboxTest,newInsert,hboxid,hboxfirst,hboxlast,hboxage,hboxss,hboxcc,insertButton,hboxselect,hboxdelete,hboxShow);
	        
	        // set padding on vbox to 30
	        
	        vbox.setPadding(new Insets(30,30,30,30));
			
			
			BorderPane root = new BorderPane(vbox);
			Scene scene = new Scene(root, 600, 650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Creates database and a table for persons

		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");

			stmt = c.createStatement();
			String sql = "CREATE TABLE PERSONS " + "(ID INT PRIMARY KEY     NOT NULL,"
					+ " FIRST     CHAR(30)    NOT NULL, " + " LAST      CHAR(30), "
					+ " AGE            INT(3)      NOT NULL, " + " SSN            LONG, " + " CC    LONG)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
		}
		

	}



	public static void main(String[] args) {
		launch(args);
	}
}
