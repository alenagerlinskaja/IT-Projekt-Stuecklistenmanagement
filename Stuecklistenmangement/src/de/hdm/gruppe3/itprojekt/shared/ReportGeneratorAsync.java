package de.hdm.gruppe3.itprojekt.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.gruppe3.itprojekt.shared.bo.BillOfMaterial;
import de.hdm.gruppe3.itprojekt.shared.report.BillOfMaterialReport;
import de.hdm.gruppe3.itprojekt.shared.report.BillsOfMaterialReport;

public interface ReportGeneratorAsync {

	void createBillOfMaterialReport(BillOfMaterial b,
			AsyncCallback<BillOfMaterialReport> callback);

	void createBillsOfMaterialReport(
			AsyncCallback<BillsOfMaterialReport> callback);

	void init(AsyncCallback<Void> callback);

}
