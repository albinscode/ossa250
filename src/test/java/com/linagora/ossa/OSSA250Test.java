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

import com.linagora.ossa.mock.OSSA250ServiceMock;

public class OSSA250Test {

    private static final String MOCK_SERVICE_URL = "http://localhost:12345/OSSA-250-Service";

    private static final String PARAM0 = "incoming request";

    private static final String PARAM1 = "log on";

    private static final int PARAM2 = 987654321;

    private static final String PARAM3 = "test@john.doe";

    @Rule
    public final OSSA250ServiceMock ossa250ServiceMock = new OSSA250ServiceMock(
            MOCK_SERVICE_URL);

    @Before
    public void initializeMock() {
        ossa250ServiceMock.setParam1(PARAM1);
        ossa250ServiceMock.setParam2(PARAM2);
        ossa250ServiceMock.setParam3(PARAM3);
    }

    @Test
    public void ossa250_with_2_params() throws Exception {

        final URL wsdlURL = Thread.currentThread().getContextClassLoader().getResource("ossa-250.wsdl");
        assertNotNull(wsdlURL);

        // Create CXF client
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        final Client cxfClient = dcf.createClient(wsdlURL.toString());
        cxfClient.getRequestContext().put("org.apache.cxf.stax.force-start-document", Boolean.TRUE);

        // Invoque Mock
        cxfClient.getRequestContext().put(Message.ENDPOINT_ADDRESS, MOCK_SERVICE_URL);
        final Object[] response = cxfClient.invoke("ossa250with2params", new Object[] { PARAM0, null, null });
        
        assertEquals(PARAM1, response[0]);
        assertEquals(PARAM2, response[1]);
    }

    @Test
    public void ossa250_with_3_params() throws Exception {

        final URL wsdlURL = Thread.currentThread().getContextClassLoader().getResource("ossa-250.wsdl");
        assertNotNull(wsdlURL);

        // Create CXF client
        final JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        final Client cxfClient = dcf.createClient(wsdlURL.toString());
        cxfClient.getRequestContext().put("org.apache.cxf.stax.force-start-document", Boolean.TRUE);
        
        // Invoque Mock
        cxfClient.getRequestContext().put(Message.ENDPOINT_ADDRESS, MOCK_SERVICE_URL);
        final Object[] response = cxfClient.invoke("ossa250with3params",
                new Object[] { PARAM0, null, null, null });

        assertEquals(PARAM1, response[0]);
        assertEquals(PARAM2, response[1]);
        assertEquals(PARAM3, response[2]);
    }

}
