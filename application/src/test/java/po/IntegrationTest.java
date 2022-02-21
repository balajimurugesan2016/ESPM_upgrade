package po;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrderItem;


import junit.framework.Assert;

import static org.junit.Assert.*;


import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
	
	   @Autowired
	    private TestRestTemplate restTemplate;
	     
	    @LocalServerPort
	    int randomServerPort;
    @Test
    public void test() {
        assertTrue(true);
    }
    
    @Test
    public void testGETPurchaseOrders() throws Exception
    {
    	final String baseUrl = "http://localhost:"+randomServerPort+"/materialsmanagement/purchaseorder?purchaseorderid=4500041124";
    	URI uri = new URI(baseUrl);
    	
    	ResponseEntity<PurchaseOrder> result = this.restTemplate.getForEntity(uri, PurchaseOrder.class);

        Assert.assertEquals(200,result.getStatusCodeValue());
                 
        
    }
    
    @Test
    public void testPOSTPurchaseOrders() throws Exception
    {
    	List<PurchaseOrderItem> purchaseorderitems = new ArrayList<>();
    	BigDecimal _a = new BigDecimal("1");
    	PurchaseOrderItem purchaseorderitem = new PurchaseOrderItem();
    	purchaseorderitem.setMaterial("MZ-TG-S100-03");
    	purchaseorderitem.setOrderQuantity(_a);
    	purchaseorderitem.setOrderPriceUnit("EA");
    	purchaseorderitem.setPlant("1710");
    	purchaseorderitems.add(purchaseorderitem);
    	PurchaseOrder purchaseorder = new PurchaseOrder();
    	purchaseorder.setPurchaseOrderType("NB");
    	purchaseorder.setSupplier("USSU-VSF85");
    	purchaseorder.setDocumentCurrency("USD");
    	purchaseorder.setPurchasingOrganization("1710");
    	purchaseorder.setPurchasingGroup("002");
    	purchaseorder.setCompanyCode("1710");
    	purchaseorder.setPurchaseOrderItem(purchaseorderitems);
    	
    	final String baseUrl = "http://localhost:"+randomServerPort+"/materialsmanagement/purchaseorder";
    	URI uri = new URI(baseUrl);
    	 HttpEntity<PurchaseOrder> request = new HttpEntity<>(purchaseorder);
    	
    	ResponseEntity<PurchaseOrder> result = this.restTemplate.postForEntity(uri, request, PurchaseOrder.class);
    	Assert.assertEquals(201, result.getStatusCodeValue());
    	
    /*	mvc.perform( MockMvcRequestBuilders
    			
    		      .post("/materialsmanagement/purchaseorder")
    		      .content(asJsonString(purchaseorder))).andExpect(status().isCreated()); */
    		     
    }
    
    
    @Test
    public void testPOSTPurchaseOrders400() throws Exception
    {
    	List<PurchaseOrderItem> purchaseorderitems = new ArrayList<>();
    	BigDecimal _a = new BigDecimal("1");
    	PurchaseOrderItem purchaseorderitem = new PurchaseOrderItem();
    	purchaseorderitem.setMaterial("skjdsdajdadads");
    	purchaseorderitem.setOrderQuantity(_a);
    	purchaseorderitem.setOrderPriceUnit("EA");
    	purchaseorderitem.setPlant("1710");
    	purchaseorderitems.add(purchaseorderitem);
    	PurchaseOrder purchaseorder = new PurchaseOrder();
    	purchaseorder.setPurchaseOrderType("NB");
    	purchaseorder.setSupplier("dsfsfsdfsfsfd");
    	purchaseorder.setDocumentCurrency("USD");
    	purchaseorder.setPurchasingOrganization("1710");
    	purchaseorder.setPurchasingGroup("002");
    	purchaseorder.setCompanyCode("1710");
    	purchaseorder.setPurchaseOrderItem(purchaseorderitems);
    	
    	final String baseUrl = "http://localhost:"+randomServerPort+"/materialsmanagement/purchaseorder";
    	URI uri = new URI(baseUrl);
    	 HttpEntity<PurchaseOrder> request = new HttpEntity<>(purchaseorder);
    	
    	ResponseEntity<PurchaseOrder> result = this.restTemplate.postForEntity(uri, request, PurchaseOrder.class);
    	Assert.assertEquals(400, result.getStatusCodeValue());
    	
    /*	mvc.perform( MockMvcRequestBuilders
    			
    		      .post("/materialsmanagement/purchaseorder")
    		      .content(asJsonString(purchaseorder))).andExpect(status().isCreated()); */
    		     
    }
    
    
    @Test
    public void testGETPurchaseOrders404() throws Exception
    {
    	final String baseUrl = "http://localhost:"+randomServerPort+"/materialsmanagement/purchaseorder?purchaseorderid=45001124";
    	URI uri = new URI(baseUrl);
    	
    	ResponseEntity<PurchaseOrder> result = this.restTemplate.getForEntity(uri, PurchaseOrder.class);

        Assert.assertEquals(404,result.getStatusCodeValue());
                 
        
    }
    
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
