package de.hdm.gruppe3.itprojekt.shared.report;

/**
	 * <p>
	 * Klasse wird benötigt, damit die <code>Report</code>-Objekte 
	 * welche über den Server an den Clienten zur Verfügung gestellt werden. 
	 * In ein menschenlesbares Format überführt werden. 
	 * </p>
	<p>
	 * Methoden zum Auslesen der in das Zielformat überführten ist den Subklassen überlassen. 
	 * Hier werden die Signaturen der Methoden deklariert, 
	 * welche für die Prozessierung der Quellinfos zuständig ist.
	 * </p>
	 * 
	 * @author Aliye Gököz/ In Anlehnung an Herrn Prof.Thies
	*/ 
public abstract class ReportWriter {
	
	 /**
	   * Übersetzen eines <code>BillOfMaterialReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu Übersetzende Report
	   */
	  public abstract void process(BillOfMaterialReport r);

	  /**
	   * Übersetzen eines <code>BillsOfMaterialReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu Übersetzende Report
	   */
	  public abstract void process(BillsOfMaterialReport r);
	  
	  /**
	   * Übersetzen eines <code>MaterialRequirementsReport</code> in das
	   * Zielformat.
	   * 
	   * @param r der zu Übersetzende Report
	   */
	  public abstract void process(MaterialRequirementsReport r);

	}

