package de.hdm.gruppe3.itprojekt.shared.report;

import java.util.Vector;

/**
 * Ein <code>ReportWriter</code>, der mit Reports Plain Text formatiert. 
 * In der Variable wird das im Zielformat vorliegende Ergebnis abgelegt,
 * in Folge dessen kann sie mit der entsprechenden Prozessierungsmethode mit 
 * <code>getReportText</code> ausgelesen werden. 
 * 
 * @author Gököz
 */

public class PlainTextReportWriter extends ReportWriter{

	/**
	 * Diese Variable wird mit dem Ergebnis einer Umwandlung (vgl.
	 * <code>process...</code>-Methoden) belegt. Format: Text
	 */
	private String reportText = "";
	
	/**
	 * Zurücksetzen der Variable <code>reportText</code> 
	 */
	
	public void resetReportText(){
		this.reportText = "";
	}
	
	/**
	 * Header-Text produzieren
	 * 
	 * return Text
	 */
	public String getHeader(){
		return "";
	}
	
	/**
	 * Trailer-Text produzieren.
	 * 
	 * return Text
	 */
	public String getTrailer(){
		//einfache Trennlinie markiert Report-Ende
		return "___________________________________________";
	}
	
	/**
	 * Prozessieren des Ã¼bergebenen Reports und Ablage im Zielformat. Ein Auslesen
	 * des Ergebnisses kann spÃ¤ter mittels <code>getReportText()</code> erfolgen.
	 * 
	 * @param r der zu prozessierende Report
	 */
	public void process(BillOfMaterialReport r){
		
		//vorerst werden die Ergebnisse vorhergehenden Prozessierungen gelöscht
		this.resetReportText();
		
		/*
		 * in diesen Buffer werden während der Prozessierung unsere Ergebnisse rein-
		 * geschrieben
		 */
		StringBuffer result = new StringBuffer();
		
		/*
		 * Schritt für Schritt werden die einzelnen Bestandteile des Reportes ausgelesen
		 * und in Text-Form übersetzt.
		 */
		result.append("*** " + r.getTitle() + " ***\n\n");
	    result.append(r.getHeaderDate() + "\n");
	    result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");
	    Vector<Row> rows = r.getRows();

	    for (Row row : rows) {
	      for (int k = 0; k < row.getNumColumns(); k++) {
	        result.append(row.getColumnAt(k) + "\t ; \t");
	      }

	      result.append("\n");
	    }

	    result.append("\n");
	    result.append(r.getImprint() + "\n");
	    /*
	     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
	     * reportText-Variable zugewiesen. Dadurch wird es mÃ¶glich, anschlieÃŸend das
	     * Ergebnis mittels getReportText() auszulesen.
	     */
	    this.reportText = result.toString();
	  }
	
	/**
	   * Prozessieren des Ã¼bergebenen Reports und Ablage im Zielformat. Ein Auslesen
	   * des Ergebnisses kann spÃ¤ter mittels <code>getReportText()</code> erfolgen.
	   * 
	   * @param r der zu prozessierende Report
	   */
	  public void process(BillsOfMaterialReport r) {
		// ZunÃ¤chst lÃ¶schen wir das Ergebnis vorhergehender Prozessierungen.
		 this.resetReportText();

		 /*
		  * In diesen Buffer schreiben wir wÃ¤hrend der Prozessierung sukzessive
		  * unsere Ergebnisse.
		  */
		  StringBuffer result = new StringBuffer();

		 /*
		  * Nun werden Schritt fÃ¼r Schritt die einzelnen Bestandteile des Reports
		  * ausgelesen und in Text-Form Ã¼bersetzt.
		  */
		  result.append("*** " + r.getTitle() + " ***\n\n");

		  if (r.getHeaderDate() != null)
		  result.append(r.getHeaderDate() + "\n");

		  result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");

		  /*
		   * Da BillsOfMaterialReport ein CompositeReport ist, enthält r
		   * eine Menge von Teil-Reports des Typs BillOfMaterialReport. Für
		   * jeden dieser Teil-Reports rufen wir processBillofMaterialReport
		   * auf. Das Ergebnis des jew. Aufrufs fügen wir dem Buffer hinzu.
		   */
		  for (int i = 0; i < r.getNumSubReports(); i++) {
		      /*
		       * BillOfMaterialReport wird als Typ der SubReports vorausgesetzt.
		       * Sollte dies in einer erweiterten Form des Projekts nicht mehr gelten,
		       * so müsste hier eine detailliertere Implementierung erfolgen.
		       */
		      BillOfMaterialReport subReport = (BillOfMaterialReport) r
		          .getSubReportAt(i);

		      this.process(subReport);

		      // Ein Form Feed wäre hier statt der 5 Leerzeilen auch denkbar...
		      result.append(this.reportText + "\n\n\n\n\n");

		      /*
		       * Nach jeder Ãœbersetzung eines Teilreports und anschlieÃŸendem Auslesen
		       * sollte die Ergebnisvariable zurÃ¼ckgesetzt werden.
		       */
		      this.resetReportText();
		    }
	  }
		public void process(MaterialRequirementsReport r) {

			    // ZunÃ¤chst löschen wir das Ergebnis vorhergehender Prozessierungen.
			    this.resetReportText();

			    /*
			     * In diesen Buffer schreiben wir während der Prozessierung sukzessive
			     * unsere Ergebnisse.
			     */
			    StringBuffer result = new StringBuffer();

			    /*
			     * Nun werden Schritt für Schritt die einzelnen Bestandteile des Reports
			     * ausgelesen und in Text-Form übersetzt.
			     */
			    result.append("*** " + r.getTitle() + " ***\n\n");
			    result.append(r.getHeaderDate() + "\n");
			    result.append("Erstellt am: " + r.getCreated().toString() + "\n\n");
			    Vector<Row> rows = r.getRows();

			    for (Row row : rows) {
			      for (int k = 0; k < row.getNumColumns(); k++) {
			        result.append(row.getColumnAt(k) + "\t ; \t");
			      }

			      result.append("\n");
			    }

			    result.append("\n");
			    result.append(r.getImprint() + "\n");

			    /*
			     * Zum Schluss wird unser Arbeits-Buffer in einen String umgewandelt und der
			     * reportText-Variable zugewiesen. Dadurch wird es möglich, anschlie?end das
			     * Ergebnis mittels getReportText() auszulesen.
			     */
			    this.reportText = result.toString();
			  }
		
		/**
		   * Auslesen des Ergebnisses der zuletzt aufgerufenen Prozessierungsmethode.
		   * 
		   * @return ein String bestehend aus einfachem Text
		   */
		  public String getReportText() {
		    return this.getHeader() + this.reportText + this.getTrailer();
		  }
		
}
