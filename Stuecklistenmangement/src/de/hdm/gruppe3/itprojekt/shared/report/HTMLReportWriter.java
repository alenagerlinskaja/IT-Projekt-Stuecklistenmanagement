package de.hdm.gruppe3.itprojekt.shared.report;

import java.util.Vector;

import src.de.hdm.thies.bankProjekt.shared.report.AllAccountsOfAllCustomersReport;
import src.de.hdm.thies.bankProjekt.shared.report.AllAccountsOfCustomerReport;
import src.de.hdm.thies.bankProjekt.shared.report.CompositeParagraph;
import src.de.hdm.thies.bankProjekt.shared.report.Paragraph;
import src.de.hdm.thies.bankProjekt.shared.report.ReportWriter;
import src.de.hdm.thies.bankProjekt.shared.report.Row;
import src.de.hdm.thies.bankProjekt.shared.report.SimpleParagraph;


public class HTMLReportWriter extends ReportWriter {


  private String reportText = "";

 
  public void resetReportText() {
    this.reportText = "";
  }

  
  public String paragraph2HTML(Paragraph p) {
    if (p instanceof CompositeParagraph) {
      return this.paragraph2HTML((CompositeParagraph) p);
    }
    else {
      return this.paragraph2HTML((SimpleParagraph) p);
    }
  }


  public String paragraph2HTML(CompositeParagraph p) {
    StringBuffer result = new StringBuffer();

    for (int i = 0; i < p.getNumParagraphs(); i++) {
      result.append("<p>" + p.getParagraphAt(i) + "</p>");
    }

    return result.toString();
  }


  public String paragraph2HTML(SimpleParagraph p) {
    return "<p>" + p.toString() + "</p>";
  }


  public String getHeader() {
    StringBuffer result = new StringBuffer();

    result.append("<html><head><title></title></head><body>");
    return result.toString();
  }


  public String getTrailer() {
    return "</body></html>";
  }

  public void process(AllAccountsOfCustomerReport r) {

    this.resetReportText();

    StringBuffer result = new StringBuffer();

 
    result.append("<H1>" + r.getTitle() + "</H1>");
    result.append("<table style=\"width:400px;border:1px solid silver\"><tr>");
    result.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getHeaderData())
        + "</b></td>");
    result.append("<td valign=\"top\">" + paragraph2HTML(r.getImprint())
        + "</td>");
    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
        + "</td></tr></table>");

    Vector<Row> rows = r.getRows();
    result.append("<table style=\"width:400px\">");

    for (int i = 0; i < rows.size(); i++) {
      Row row = rows.elementAt(i);
      result.append("<tr>");
      for (int k = 0; k < row.getNumColumns(); k++) {
        if (i == 0) {
          result.append("<td style=\"background:silver;font-weight:bold\">" + row.getColumnAt(k)
              + "</td>");
        }
        else {
          if (i > 1) {
            result.append("<td style=\"border-top:1px solid silver\">"
                + row.getColumnAt(k) + "</td>");
          }
          else {
            result.append("<td valign=\"top\">" + row.getColumnAt(k) + "</td>");
          }
        }
      }
      result.append("</tr>");
    }

    result.append("</table>");


    this.reportText = result.toString();
  }


  public void process(AllAccountsOfAllCustomersReport r) {

    this.resetReportText();

    StringBuffer result = new StringBuffer();


    result.append("<H1>" + r.getTitle() + "</H1>");
    result.append("<table><tr>");

    if (r.getHeaderData() != null) {
      result.append("<td>" + paragraph2HTML(r.getHeaderData()) + "</td>");
    }

    result.append("<td>" + paragraph2HTML(r.getImprint()) + "</td>");
    result.append("</tr><tr><td></td><td>" + r.getCreated().toString()
        + "</td></tr></table>");


    for (int i = 0; i < r.getNumSubReports(); i++) {

      AllAccountsOfCustomerReport subReport = (AllAccountsOfCustomerReport) r
          .getSubReportAt(i);

      this.process(subReport);

      result.append(this.reportText + "\n");

      this.resetReportText();
    }

    this.reportText = result.toString();
  }

  public String getReportText() {
    return this.getHeader() + this.reportText + this.getTrailer();
  }
}