package de.hdm.gruppe3.itprojekt.shared.report;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * Ist die Basisklasse aller Reports. Reports sind als <code>Serializable</code> 
 * deklariert. So können sie vom Server an den Clienten gesendet werden. Zugegriffen 
 * kann auf diese also nach deren Bereitstellung lokal auf de Client.
 * </p>
 * <p>
 * Report besitzt eine Reihe von Standartelementen. Diese werden mitels Attributen 
 * modelliert und dort dokumentiert.
 * </p>
 * @see Report
 * @author Aliye Gököz/ In Anlehnung an Herrn Prof. Thies
 */

public abstract class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	   * Ein kleines Impressum, das eine Art Briefkopf darstellt.
	   */
	  private Paragraph imprint = null;
	  
	/**
	 * Kopfdaten des Berichts.
	 */
	  private Paragraph headerData = null;
	  
	/**
	 * Jeder Aussgabe kann einen eigenen Titel besitzen.
	 */
	  private String title = "Report";
	  
	/**
	 * Datum der Erstellung der Aussgabe.
	 */
	  private Date created = new Date();
	  
	/**
	 * Auslesen des Impressums.
	 * 
	 * return Text des Impressums.
	 */
	  public Paragraph getImprint(){
		  return this.imprint;
	  }
	  
	/**
	 * Setzen des Impressums.
	 * 
	 * param imprint Text des Impressums.
	 */
	  public void setImprint(Paragraph imprint){
		  this.imprint = imprint;
	  }
	  
	  /**
	   * Auslesen der Kopfdaten.
	   * 
	   * return Text der Kopfdaten.
	   */
	   public Paragraph getHeaderDate(){
		   return this.headerData;
	   }
	   
	  /**
	   * Setzen der Kopfdaten.
	   */
	   public void setHeaderData(Paragraph headerData){
		   this.headerData = headerData;
	   }
	   
	   /**
	    * Auslesen des Berichtstitels.
	    * 
	    * return Titeltext.
	    */
	   public String getTitle(){
		   return this.title;
	   }
	   
	   /**
	    * Setzen des Berichtstitels.
	    * 
	    * title Titeltext
	    */
	   public void setTitle(String title){
		   this.title = title;
	   }
	   
	   /**
	    * Auslesen des Ertstellungsdatums.
	    * 
	    * return Datum der Erstellung.
	    */
	   public Date getCreated(){
		   return this.created;
	   }
	   
	   /**
	    * Setzen des Erstellungsdatums.
	    * Aufruf dieser Methode ist aber nicht unbedingt erforderlich.
	    * Da die Zeit automatisch gesetzt wird.
	    */
	   public void setCreated(Date created){
		   this.created = created;
	   }
}