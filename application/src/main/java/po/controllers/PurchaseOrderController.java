package po.controllers;

import com.sap.cloud.sdk.cloudplatform.connectivity.exception.DestinationAccessException;
import com.sap.cloud.sdk.cloudplatform.resilience.ResilienceConfiguration;
import com.sap.cloud.sdk.datamodel.odata.client.exception.ODataServiceErrorException;
import com.sap.cloud.sdk.datamodel.odata.helper.ModificationResponse;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.DefaultPurchaseOrderService;

import po.Application;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/materialsmanagement")
public class PurchaseOrderController {

	
	ErpHttpDestination erphttpdestination;
	HashMap<String, RESTOPERATIONS> objectcollection;

	private static final Logger logger = LoggerFactory.getLogger(PurchaseOrderController.class);
	
	@Autowired
	public PurchaseOrderController(ErpHttpDestination erphttpdestination,HashMap<String, RESTOPERATIONS> objectcollection) {
		
		this.erphttpdestination = erphttpdestination;
		this.objectcollection = objectcollection;
		
	}

	@RequestMapping(path = "/purchaseorder", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<PurchaseOrder> postpurchaseorder(@RequestBody PurchaseOrder purchaseorder) {

		try {

			logger.info("Before Modify");

			POSTRestOperations post = (POSTRestOperations) objectcollection.get("POPOST");

			PurchaseOrder purchaseorderoutput = post.response(purchaseorder, erphttpdestination);

			logger.info("After Modify,{}", purchaseorderoutput);

			return new ResponseEntity<PurchaseOrder>(purchaseorderoutput, HttpStatus.CREATED);
		} catch (DestinationAccessException destinationaccessexception) {
			logger.error(destinationaccessexception.getMessage(), destinationaccessexception);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (ODataServiceErrorException odataServiceErrorException) {
			logger.error(odataServiceErrorException.getMessage());

			String httpbody = odataServiceErrorException.getHttpBody().get();

			throw new ResponseStatusException(odataServiceErrorException.getHttpCode(), httpbody,
					odataServiceErrorException);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

	@RequestMapping(path = "/purchaseorder", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<PurchaseOrder> getpurchaseorder(@RequestParam String purchaseorderid) {

		try {

			logger.info("Before Fetch");
			
			GETRestOperations get = (GETRestOperations) objectcollection.get("POGET");
			
			PurchaseOrder receivedpurchaseorder = get.response(purchaseorderid, erphttpdestination);
			
			logger.info("After fetch {}", receivedpurchaseorder);

			return ResponseEntity.ok(receivedpurchaseorder);
		}

		catch (DestinationAccessException destinationaccessexception) {
			logger.error(destinationaccessexception.getMessage(), destinationaccessexception);

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		} catch (ODataServiceErrorException odataServiceErrorException) {
			logger.error(odataServiceErrorException.getMessage());

			String httpbody = odataServiceErrorException.getHttpBody().get();

			throw new ResponseStatusException(odataServiceErrorException.getHttpCode(), httpbody,
					odataServiceErrorException);

		} catch (Exception exception) {
			logger.error(exception.getMessage(), exception);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

		}
	}

}
