package de.hdm.gruppe3.itprojekt.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ComponentPartDemo extends Showcase {
	
	  private VerticalPanel mainPanel = new VerticalPanel();
	  private FlexTable componentPartstocksFlexTable = new FlexTable();
	  private HorizontalPanel addPanel = new HorizontalPanel();
	  private TextBox newSymbolTextBox = new TextBox();
	  private Button addStockButton = new Button("Add");
	  private Label lastUpdatedLabel = new Label();
	/**
	   * Jeder Showcase besitzt eine einleitende �berschrift, die durch diese
	   * Methode zu erstellen ist.
	   * 
	   * @see Showcase#getHeadlineText()
	   */
	  @Override
	  protected String getHeadlineText() {
	    return "Componentpart";
	  }

	  /**
	   * Jeder Showcase muss die <code>run()</code>-Methode implementieren. Sie ist
	   * eine "Einschubmethode", die von einer Methode der Basisklasse
	   * <code>ShowCase</code> aufgerufen wird, wenn der Showcase aktiviert wird.
	   */
	  @Override
	  protected void run() {
	    this.append("Darstellen von Componentpart");
	  }
	  
	  	public void onModuleLoad() {
	  	      // Create table for stock data.
	  	      componentPartstocksFlexTable.setText(0, 0, "Name");
	  	      componentPartstocksFlexTable.setText(0, 1, "Nummer");
	  	      componentPartstocksFlexTable.setText(0, 2, "cpID");
	  	      componentPartstocksFlexTable.setText(0, 3, "Material");
	  	      componentPartstocksFlexTable.setText(0, 4, "Beschreibung");
	  	      componentPartstocksFlexTable.setText(0, 3, "l�schen");
	  	      componentPartstocksFlexTable.setText(0,4,"bearbeiten");

	  	      // Assemble Add Stock panel.
	  	      addPanel.add(newSymbolTextBox);
	  	      addPanel.add(addStockButton);

	  	      // Assemble Main panel.
	  	      mainPanel.add(componentPartstocksFlexTable);
	  	      mainPanel.add(addPanel);
	  	      mainPanel.add(lastUpdatedLabel);

	  	      // Associate the Main panel with the HTML host page.
	  	      RootPanel.get("StueckList").add(mainPanel);

	  	      // Move cursor focus to the input box.
	  	      newSymbolTextBox.setFocus(true);
	  	      
	  	      addStockButton.addClickHandler(new ClickHandler() {
	  		      @Override
	  			public void onClick(ClickEvent event) {
	  		        /*
	  		         * Showcase instantiieren.
	  		         */
	  		        Showcase showcase = new CreateComponentPart();

	  		        /*
	  		         * F�r die Ausgaben haben wir ein separates DIV-Element namens "Details"
	  		         * in die zugeh�rige HTML-Datei eingef�gt. Bevor wir den neuen Showcase
	  		         * dort einbetten, l�schen wir vorsichtshalber s�mtliche bisherigen
	  		         * Elemente dieses DIV.
	  		         */
	  		        RootPanel.get("Details").clear();
	  		        RootPanel.get("Details").add(showcase);
	  		      }
	  		    });
	  	    }

	  	  }