package de.hdm.gruppe3.itprojekt.client;

<<<<<<< HEAD
=======
import de.hdm.gruppe3.itprojekt.shared.Administration;
import de.hdm.gruppe3.itprojekt.shared.AdministrationAsync;
import de.hdm.gruppe3.itprojekt.shared.FieldVerifier;

>>>>>>> refs/remotes/origin/Roxana
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
<<<<<<< HEAD
	 
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
=======
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final AdministrationAsync administrationAsync = GWT
			.create(Administration.class);

	public AdministrationAsync stuecklistenmanagement;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Send");
		final TextBox nameField = new TextBox();
		nameField.setText("GWT User");
		final Label errorLabel = new Label();

		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();

		// Create the popup dialog box
		final DialogBox dialogBox = new DialogBox();
		dialogBox.setText("Remote Procedure Call");
		dialogBox.setAnimationEnabled(true);
		final Button closeButton = new Button("Close");
		// We can set the id of a widget by accessing its Element
		closeButton.getElement().setId("closeButton");
		final Label textToServerLabel = new Label();
		final HTML serverResponseLabel = new HTML();
		VerticalPanel dialogVPanel = new VerticalPanel();
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Sending name to the server:</b>"));
		dialogVPanel.add(textToServerLabel);
		dialogVPanel.add(new HTML("<br><b>Server replies:</b>"));
		dialogVPanel.add(serverResponseLabel);
		dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		dialogVPanel.add(closeButton);
		dialogBox.setWidget(dialogVPanel);

		// Add a handler to close the DialogBox
		closeButton.addClickHandler(new ClickHandler() {
>>>>>>> refs/remotes/origin/Roxana
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

<<<<<<< HEAD
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
=======
				// Then, we send the input to the server.
				sendButton.setEnabled(false);
				textToServerLabel.setText(textToServer);
				serverResponseLabel.setText("");
				stuecklistenmanagement.greetServer(textToServer,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								dialogBox
										.setText("Remote Procedure Call - Failure");
								serverResponseLabel
										.addStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(SERVER_ERROR);
								dialogBox.center();
								closeButton.setFocus(true);
							}

							public void onSuccess(String result) {
								dialogBox.setText("Remote Procedure Call");
								serverResponseLabel
										.removeStyleName("serverResponseLabelError");
								serverResponseLabel.setHTML(result);
								dialogBox.center();
								closeButton.setFocus(true);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
	}
}
>>>>>>> refs/remotes/origin/Roxana
