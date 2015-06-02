package de.hdm.gruppe3.itprojekt.shared;



import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.gruppe3.itprojekt.shared.bo.BillOfMaterial;
import de.hdm.gruppe3.itprojekt.shared.report.BillsOfMaterialReport;
import de.hdm.gruppe3.itprojekt.shared.report.BillOfMaterialReport;



/**

 * <p>

 * Synchrone Schnittstelle für eine RPC-fähige Klasse zur Erstellung von

 * Reports. Diese Schnittstelle benutzt die gleiche Realisierungsgrundlage wie

 * das Paar {@link Administration} und {@AdministrationImpl}. Zu

 * technischen Erläuterung etwa bzgl. GWT RPC bzw. {@link RemoteServiceServlet}

 * siehe {@link Administration} und AdministrationImpl}.

 * </p>

 * <p>

 * Ein ReportGenerator bietet die Möglichkeit, eine Menge von Berichten

 * (Reports) zu erstellen, die Menge von Daten bzgl. bestimmter Sachverhalte des

 * Systems zweckspezifisch darstellen.

 * </p>

 * <p>

 * Die Klasse bietet eine Reihe von <code>create...</code>-Methoden, mit deren

 * Hilfe die Reports erstellt werden können. Jede dieser Methoden besitzt eine

 * dem Anwendungsfall entsprechende Parameterliste. Diese Parameter benötigt der

 * der Generator, um den Report erstellen zu können.

 * </p>

 * <p> 

 * Bei neu hinzukommenden Bedarfen an Berichten, kann diese Klasse auf einfache

 * Weise erweitert werden. Hierzu können zusätzliche <code>create...</code>

 * -Methoden implementiert werden. Die bestehenden Methoden bleiben davon

 * unbeeinflusst, so dass bestehende Programmlogik nicht verändert werden muss.

 * </p>

 * 

 * @author thies

 */

@RemoteServiceRelativePath("reportgenerator")

public interface ReportGenerator extends RemoteService {



  /**

   * Initialisierung des Objekts. Diese Methode ist vor dem Hintergrund von GWT

   * RPC zusätzlich zum No Argument Constructor der implementierenden Klasse

   *AdministrationImpl} notwendig. Bitte diese Methode direkt nach der

   * Instantiierung aufrufen.

   * 

   * @throws IllegalArgumentException

   */

  public void init() throws IllegalArgumentException;



  /**

   * Erstellen eines <code>BillOfMaterialReport</code>-Reports. Dieser

   * Report-Typ stellt sämtliche Baugruppen einer Stueckliste dar.

   * 

   * @param b eine Referenz auf die Stueckliste bzgl. dessen der Report

   * erstellt werden soll.

   * @return das fertige Reportobjekt

   * @throws IllegalArgumentException

   * @see BillOfMaterialReport

   */

  public abstract BillOfMaterialReport createBillOfMaterialReport(

     BillOfMaterial b) throws IllegalArgumentException;



  /**

   * Erstellen eines <code>BillsOfMaterialReport</code>-Reports.

   * Dieser Report-Typ stellt sämtliche Baugruppen aller Stuecklisten dar.

   * 

   * @return das fertige Reportobjekt

   * @throws IllegalArgumentException

   * @see BillsOfMaterialReport

   */

  public abstract BillsOfMaterialReport createBillsOfMaterialReport()

      throws IllegalArgumentException;

}
