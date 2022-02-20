package po;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrder;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.purchaseorder.PurchaseOrderItem;
import com.sap.cloud.security.xsuaa.http.MediaType;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
	
    @Autowired
    private MockMvc mvc;
    @Test
    public void test() {
        assertTrue(true);
    }
    
    @Test
    public void testGETPurchaseOrders() throws Exception
    {
        

            mvc.perform(MockMvcRequestBuilders.get("/materialsmanagement/purchaseorder?purchaseorderid=45001122")
            		
                    //.header(HttpHeaders.AUTHORIZATION, jwt)
                    )
                    .andExpect(status().isOk());
                 
        
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
    	mvc.perform( MockMvcRequestBuilders
    		      .post("/materialsmanagement/purchaseorder")
    		      .content(asJsonString(purchaseorder))).andExpect(status().isCreated());
    		     
    		   
  
                 
        
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
