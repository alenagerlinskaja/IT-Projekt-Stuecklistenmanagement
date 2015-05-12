package de.hdm.gruppe3.itprojekt.shared.report;

/**
	 * <p>
	 * Klasse wird ben�tigt, damit die <code>Report</code>-Objekte 
	 * welche �ber den Server an den Clienten zur Verf�gung gestellt werden. 
	 * In ein menschenlesbares Format �berf�hrt werden. 
	 * </p>
	<p>
	 * Methoden zum Auslesen der in das Zielformat �berf�hrten ist den Subklassen �berlassen. 
	 * Hier werden die Signaturen der Methoden deklariert, 
	 * welche f�r die Prozessierung der Quellinfos zust�ndig ist.
	 * </p>
	 * 
	 * @author Aliye G�k�z/ In Anlehnung an Herrn Prof.Thies
	*/ 
public abstract class ReportWriter {
	
	 /**
	   * �bersetzen eines <code>BillOfMaterialReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   */
	  public abstract void process(BillOfMaterialReport r);

	  /**
	   * �bersetzen eines <code>BillsOfMaterialReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   */
	  public abstract void process(BillsOfMaterialReport r);
	  
	  /**
	   * �bersetzen eines <code>MaterialRequirementsReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu �bersetzende Report
	   */
	  public abstract void process(MaterialRequirementsReport r);

	}

