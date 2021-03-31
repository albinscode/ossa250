package com.linagora.ossa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.apache.cxf.message.Message;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.example.customerservice.Customer;
import com.linagora.ossa.mock.CustomerServiceMock;

public class CustomerServiceTest {

    private static final String MOCK_SERVICE_URL = "http://localhost:9090/CustomerServicePort";

    @Rule
    public final CustomerServiceMock customerServiceMock = new CustomerServiceMock(
            MOCK_SERVICE_URL);

    @Before
    public void initializeMock() {
        Customer customer = new Customer();
        customer.setName("Redford");
        customerServiceMock.setCustomer(customer);
    }

    @Test
    public void customerServiceUpdate() throws Exception {

        final URL wsdlURL = Thread.currentThread().getContextClassLoader().getResource("customer.wsdl");
        assertNotNull(wsdlURL);

        // Create CXF client
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        final Client cxfClient = dcf.createClient(wsdlURL.toString());
        cxfClient.getRequestContext().put("org.apache.cxf.stax.force-start-document", Boolean.TRUE);

        // Invoque Mock
        cxfClient.getRequestContext().put(Message.ENDPOINT_ADDRESS, MOCK_SERVICE_URL);
        final Object[] response = cxfClient.invoke("getCustomersByName", new Object[] { "name" });
        
//        assertEquals(PARAM1, response[0]);
//        assertEquals(PARAM2, response[1]);
        System.out.println("test");
    }

}
