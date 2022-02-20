package po;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceConfiguration;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceIsolationMode;
import com.sap.cloud.sdk.s4hana.connectivity.DefaultErpHttpDestination;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchaseOrderConfiguration {

	@Bean
    public ErpHttpDestination getDestination() {
        return DestinationAccessor.getDestination("purchaseorderapi").asHttp().decorate(DefaultErpHttpDestination::new);
    }
	
	@Bean
	public ResilienceConfiguration getResilenceDecorator() {
		
		return ResilienceConfiguration.of(DefaultPurchaseOrderService.class).isolationMode(ResilienceIsolationMode.TENANT_AND_USER_OPTIONAL).
				
				bulkheadConfiguration(ResilienceConfiguration.BulkheadConfiguration.of().maxConcurrentCalls(20));
		
		
	}
	


}
