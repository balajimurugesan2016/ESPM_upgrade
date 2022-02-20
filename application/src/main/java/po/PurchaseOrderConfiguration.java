package po;

import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.s4hana.connectivity.DefaultErpHttpDestination;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PurchaseOrderConfiguration {

	@Bean
    public ErpHttpDestination getDestination() {
        return DestinationAccessor.getDestination("purchaseorderapi").asHttp().decorate(DefaultErpHttpDestination::new);
    }

}
