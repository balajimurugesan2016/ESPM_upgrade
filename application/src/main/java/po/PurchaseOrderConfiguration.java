package po;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceConfiguration;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceIsolationMode;
import com.sap.cloud.sdk.s4hana.connectivity.DefaultErpHttpDestination;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;

import po.controllers.PurchaseOrderGetService;
import po.controllers.PurchaseOrderPOSTService;
import po.controllers.RESTOPERATIONS;

import java.time.Duration;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchaseOrderConfiguration {
	
	

	PurchaseOrderGetService purchaseordergetservice;

	PurchaseOrderPOSTService purchaseorderpotservice;

	
	
	@Autowired
	public PurchaseOrderConfiguration(PurchaseOrderGetService purchaseordergetservice , PurchaseOrderPOSTService purchaseorderpotservice) {
		
		this.purchaseordergetservice = purchaseordergetservice;
		this.purchaseorderpotservice = purchaseorderpotservice;
		
	}
	@Bean
    public ErpHttpDestination getDestination() {
        return DestinationAccessor.getDestination("purchaseorderapi").asHttp().decorate(DefaultErpHttpDestination::new);
    }
	
	
	@Bean
	public HashMap<String, RESTOPERATIONS> getHashMap() {
		
		HashMap<String, RESTOPERATIONS>  objectcollection  = new HashMap<>();
		objectcollection.put("POGET",purchaseordergetservice );
		objectcollection.put("POPOST",purchaseorderpotservice );
		
		return objectcollection;
	
	}
	


}
