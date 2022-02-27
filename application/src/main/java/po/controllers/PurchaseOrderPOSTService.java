package po.controllers;

import org.springframework.stereotype.Service;

import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;

@Service
public class PurchaseOrderPOSTService implements POSTRestOperations {
	
	
	public PurchaseOrder response(PurchaseOrder purchaseorder,ErpHttpDestination erphttpdestination) {
		
		return new DefaultPurchaseOrderService().createPurchaseOrder(purchaseorder)
				.executeRequest(erphttpdestination).getResponseEntity().get();
		
	}

}
