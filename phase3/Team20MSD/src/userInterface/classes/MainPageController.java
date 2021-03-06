package userInterface.classes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
//import main.java.classes.Author;
//import main.java.classes.SearchQuery;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.classes.QueryEngine;
import main.classes.ConcreteUserInterface;
import main.interfaces.UserInterface;

public class MainPageController implements Initializable {

	// declaration

	@FXML
	private TextField keyword;
	@FXML
	private ChoiceBox conferencename;
	@FXML
	private ChoiceBox pubdate;
	@FXML
	private TextField numofpub;
	@FXML
	private ChoiceBox numofcom;

	@FXML
	private Label label123;

	@FXML
	private Button searchforresult;

	@FXML
	private Button myfavouriteauthor;

	@FXML
	private void setSearch(Event event) throws Exception {
		// get the criteria parameters from the search page
		
		String conferencename1 = null;
//		System.out.println("isNotSelected0... " + !conferencename.getSelectionModel().isSelected(0));
//		System.out.println("isNotEmpty... " + !conferencename.getSelectionModel().isEmpty());
		if (!conferencename.getSelectionModel().isSelected(0) && !conferencename.getSelectionModel().isSelected(-1)) {
//			System.out.println("index... " + conferencename.getSelectionModel().getSelectedIndex());
			conferencename1 = (conferencename.getSelectionModel().getSelectedItem().toString().equals("") || 
					conferencename.getSelectionModel().getSelectedItem().toString().equals(" ")) ? ""
					: conferencename.getSelectionModel().getSelectedItem().toString().trim();
		}

		String pubdate1 = null;
//		System.out.println("..isNotEmpty... " + !pubdate.getSelectionModel().isEmpty());
		if (!pubdate.getSelectionModel().isEmpty()) {
			pubdate1 = (pubdate.getSelectionModel().getSelectedItem().toString().equals("") || 
					pubdate.getSelectionModel().getSelectedItem().toString().equals(" ")) ? ""
					: pubdate.getSelectionModel().getSelectedItem().toString().trim();
		}

		String numofcom1 = null;
		if (!numofcom.getSelectionModel().isEmpty()) {
			numofcom1 = numofcom.getSelectionModel().getSelectedItem().toString().equals("") ? "0"
					: numofcom.getSelectionModel().getSelectedItem().toString().trim();
		}

		String keyword1 = keyword.getText().equals("") ? "" : keyword.getText();

		String numofpub1 = numofpub.getText().equals("") ? "0" : numofpub.getText();

		// int[] intArray = new int[1];
		// intArray[0] = (Integer.parseInt(pubdate1));
		// List<Author> authorList =
		// SearchQuery.populateListOfAuthors(conferencename1, keyword1,
		// intArray, Integer.parseInt(numofpub1));
		// TableViewSample tableViewSample = new TableViewSample();

		SearchResultPage tableViewSample = new SearchResultPage();

		// pass the criteria variables to query engine
		tableViewSample.setCurrentInfo(keyword1, conferencename1, pubdate1, numofpub1, numofcom1,ui,qe);
		
		ui.displayNewWindow(tableViewSample.getScene(),"Result Page");
	}
	
	@FXML
	private void setAllclear(){
		keyword.clear();
		numofpub.clear();
		conferencename.getSelectionModel().clearSelection();
		numofcom.getSelectionModel().selectFirst();
		pubdate.getSelectionModel().clearSelection();
	}
	
	@FXML
	private void searchclear(Event event) throws Exception {
	   setAllclear();
	}

	@FXML
	private void candidateson(Event event) throws Exception {
		CandidateListPage page = new CandidateListPage(ui);
		ui.displayNewWindow(page.getScene());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		qe = new QueryEngine();
		ui = new ConcreteUserInterface(qe);
		
		List<String> list = qe.fetchJournalNames();
		List<Integer> plist = qe.fetchYearsAvailable();
		ObservableList confList = (ObservableList) FXCollections.observableList(list);
		ObservableList pubdateList = (ObservableList) FXCollections.observableList(plist);

		conferencename.getItems().clear();
		conferencename.setItems(confList);

		pubdate.getItems().clear();
		pubdate.setItems(pubdateList);
		
		System.out.println("Dynamic Loading successful..");
	}
	
	private UserInterface ui;
	private QueryEngine qe;

}
