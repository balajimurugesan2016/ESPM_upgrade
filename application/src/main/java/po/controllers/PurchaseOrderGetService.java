package po.controllers;

import org.springframework.stereotype.Service;


import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;

@Service
public class PurchaseOrderGetService implements GETRestOperations {
	
	

	
	public PurchaseOrder response(String purchaseorderid,ErpHttpDestination erphttpdestination) {
		
		return  new DefaultPurchaseOrderService().getPurchaseOrderByKey(purchaseorderid)
				.executeRequest(erphttpdestination);
		
	}



}
