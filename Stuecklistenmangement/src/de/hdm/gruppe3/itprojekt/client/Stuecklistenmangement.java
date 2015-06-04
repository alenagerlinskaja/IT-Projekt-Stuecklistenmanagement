package de.hdm.gruppe3.itprojekt.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry-Point-Klasse des Projekts <b>Stuecklistenmanagement</b>.
 */
public class Stuecklistenmangement implements EntryPoint {
	 
	 public void onModuleLoad() {
		 
		 VerticalPanel naviPanel = new VerticalPanel();
		 
		 RootPanel.get("Navigator").add(naviPanel);
		 
		 final Button component = new Button ("Component");
		 final Button componentPart = new Button("Componentpart");
		 final Button finishedProduct = new Button("Finishedproduct");
		 final Button user = new Button("User");
		 
		 /*
		  * Name unter der die Datei in der CSS-datei formatiert werden kann.
		  */
		 component.setStylePrimaryName("menubutton");
		 componentPart.setStylePrimaryName("menubutton");
		 finishedProduct.setStylePrimaryName("menubutton");
		 user.setStylePrimaryName("menubutton");
		 
		 /*
		  * Button wird zur Panel hinzugefügt
		  */
		 naviPanel.add(component);
		 naviPanel.add(componentPart);
		 naviPanel.add(finishedProduct);
		 naviPanel.add(user);
		 
		 /*
		     * Natürlich benötigt der Button auch ein Verhalten, wenn man mit der Maus
		     * auf ihn klickt. Hierzu registrieren wir einen ClickHandler, dessen
		     * onClick()-Methode beim Mausklick auf den zugehörigen Button aufgerufen
		     * wird.
		     */
		    component.addClickHandler(new ClickHandler() {
		      @Override
			public void onClick(ClickEvent event) {
		        /*
		         * Showcase instantiieren.
		         */
		        Showcase showcase = new ComponentDemo();

		        /*
		         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
		         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
		         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
		         * Elemente dieses DIV.
		         */
		        RootPanel.get("Details").clear();
		        RootPanel.get("Details").add(showcase);
		      }
		    });
		    
		    componentPart.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new ComponentPartDemo();

			        /*
			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
			         * Elemente dieses DIV.
			         */
			        RootPanel.get("Details").clear();
			        RootPanel.get("Details").add(showcase);
			      }
			    });
		    
			
		    finishedProduct.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new finishedProductDemo();

			        /*
			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
			         * Elemente dieses DIV.
			         */
			        RootPanel.get("Details").clear();
			        RootPanel.get("Details").add(showcase);
			      }
			    });
			  
		    user.addClickHandler(new ClickHandler() {
			      @Override
				public void onClick(ClickEvent event) {
			        /*
			         * Showcase instantiieren.
			         */
			        Showcase showcase = new userDemo();

			        /*
			         * Für die Ausgaben haben wir ein separates DIV-Element namens "Details"
			         * in die zugehörige HTML-Datei eingefügt. Bevor wir den neuen Showcase
			         * dort einbetten, löschen wir vorsichtshalber sämtliche bisherigen
			         * Elemente dieses DIV.
			         */
			        RootPanel.get("Details").clear();
			        RootPanel.get("Details").add(showcase);
			      }
			    });	 
	 }
}