package po.commands;


import java.time.Duration;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.sap.cloud.sdk.cloudplatform.connectivity.exception.DestinationAccessException;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceConfiguration;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceDecorator;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceIsolationMode;
import com.sap.cloud.sdk.datamodel.odata.client.exception.ODataServiceErrorException;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;


@Component
public class GetPurchaseOrdersCommand 
{
	
	 private static final Logger logger = LoggerFactory.getLogger(GetPurchaseOrdersCommand.class);
	public Object execute(String purchaseorderid,ErpHttpDestination erphttpdestination,DefaultPurchaseOrderService defaultPurchaseOrderService) throws ODataServiceErrorException{
		
		return ResilienceDecorator.executeCallable(()->run(purchaseorderid,erphttpdestination,defaultPurchaseOrderService),  ResilienceConfiguration.of(DefaultPurchaseOrderService.class).isolationMode(ResilienceIsolationMode.TENANT_AND_USER_OPTIONAL).
				timeLimiterConfiguration(ResilienceConfiguration.TimeLimiterConfiguration.of().timeoutDuration(Duration.ofMillis(1000000000))).
				bulkheadConfiguration(ResilienceConfiguration.BulkheadConfiguration.of().maxConcurrentCalls(20)),e -> {
            logger.warn("Raised exception", e);
        
         
            return null;
          
        });
		
	}
	
	

	private Object run(String purchaseorderid,ErpHttpDestination erphttpdestination,DefaultPurchaseOrderService defaultPurchaseOrderService)  throws ODataServiceErrorException {
		// TODO Auto-generated method stub
		logger.info("Before Fetch");
		
		try
		{
			
		
		PurchaseOrder po = 	 defaultPurchaseOrderService
		.getPurchaseOrderByKey(purchaseorderid).executeRequest(erphttpdestination);
		
		return po;
		}
		catch (DestinationAccessException destinationaccessexception) {
			logger.error(destinationaccessexception.getMessage(), destinationaccessexception);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		catch(ODataServiceErrorException oDataServiceErrorException) {
			
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, oDataServiceErrorException.getLocalizedMessage(),
					oDataServiceErrorException);
			
		}
		catch (Exception exception) {

			logger.error(exception.getLocalizedMessage());

			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(),
					exception);

		}
	
		
	}








	
	
	
}
