package po.controllers;

import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;

public interface GETRestOperations extends RESTOPERATIONS {
	
	public PurchaseOrder response(String purchaseorderid,ErpHttpDestination erphttpdestination) ;

}
