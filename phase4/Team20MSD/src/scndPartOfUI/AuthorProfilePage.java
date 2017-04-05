package scndPartOfUI;
import main.classes.Author;
import main.classes.Publication;
import main.interfaces.*;
import main.search.Result;

import java.util.ArrayList;

import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AuthorProfilePage implements CandidateListListener {
	
	public AuthorProfilePage(Author a, UserInterface ui){
		atr = a;
		this.ui = ui;
		ui.addListenerToCandList(this);
		setName();
		setButtonArea();
		setBlgrphyTable();
		setCanvas();
	}
	
	private Author atr;
	private UserInterface ui;
	private Label name;
	private Button addBut;
	private Button remBut;
	private Button simAuthBut;
	private TableView<Publication> blgrphyTable;
	private HBox buttonArea;
	private VBox canvas;
	
	private void setName(){
		name = new Label(atr.getName());
		name.setFont(new Font("Arial",30));
	}
	
	private void setButtonArea(){
		int buttonAreaSpacing = 50;
		setAddBut();
		setRemBut();
		setSimAuthBut();
		buttonArea = new HBox(buttonAreaSpacing,ui.hasCand(atr) ? remBut:addBut,simAuthBut);
	}
	
	private int sizeOfAddRemBut = 200;
	private Font butTextFont = new Font("Black",12);
	private String addButText = "Add to candidate list";
	private String remButText = "Remove from candidate list";
	
	private void setAddBut(){
		addBut = new Button(addButText);
		fixElementWidth(addBut,sizeOfAddRemBut);
		addBut.setFont(butTextFont);
		addBut.setOnAction((ActionEvent ae) -> {
			ui.addCand(atr);
		});
	}

	public void refresh(){
		buttonArea.getChildren().set(0,ui.hasCand(atr) ? remBut : addBut);
	}
	private void fixElementWidth(Region r,double width) {
		r.setMinWidth(width);
		r.setPrefWidth(width);
		r.setMaxWidth(width);
	}
	
	private void setRemBut(){
		remBut = new Button(remButText);
		fixElementWidth(remBut,sizeOfAddRemBut);
		remBut.setFont(butTextFont);
		remBut.setOnAction((ActionEvent ae) -> {
			ui.remCand(atr);
		});
	}

	private void setSimAuthBut(){
		simAuthBut = new Button("Find similar authors");
		simAuthBut.setFont(butTextFont);
		simAuthBut.setOnAction((ActionEvent ae) -> {
			Result page = new Result();
			ui.displayNewWindow(page.getScene());
		});
	}
	
	private void setBlgrphyTable(){
		SortedList<Publication> origList = new SortedList<>(ui.getAuthorPubs(atr));
		blgrphyTable = new TableView<Publication>(origList);
		origList.comparatorProperty().bind(blgrphyTable.comparatorProperty());
		blgrphyTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		setTableColumns();
	}
	
	private void setTableColumns(){
		addStringColumnToTable("Title","title");
		addStringColumnToTable("Publisher","publisher");
		addIntegerColumnToTable("Year","year");
	}
	
	private void addStringColumnToTable(String header,String propertyName){
		TableColumn<Publication,String> newColumn = new TableColumn<>(header);
		newColumn.setCellValueFactory(new PropertyValueFactory<Publication,String>(propertyName));
		blgrphyTable.getColumns().add(newColumn);
	}
	
	private void addIntegerColumnToTable(String header,String propertyName){
		TableColumn<Publication,Integer> newColumn = new TableColumn<>(header);
		newColumn.setCellValueFactory(new PropertyValueFactory<Publication,Integer>(propertyName));
		blgrphyTable.getColumns().add(newColumn);
	}
	
	private void setCanvas(){
		canvas = new VBox(20,name,buttonArea,blgrphyTable);
		canvas.setAlignment(Pos.CENTER);
		canvas.setPadding(new Insets(20,40,20,40));
		canvas.setPrefSize(600, VBox.USE_COMPUTED_SIZE);
	}
	public Scene getScene(){
		return new Scene(canvas);
	}
	
	Button getAddBut(){
		return addBut;
	}
	
	Button getRemBut(){
		return remBut;
	}
	
	HBox getButtonArea(){
		return buttonArea;
	}
	
	Button getSimAuthBut(){
		return simAuthBut;
	}
	
}