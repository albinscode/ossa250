package com.linagora.ossa.mock;

import javax.xml.ws.Endpoint;
import javax.xml.ws.Holder;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linagora.ossa._250.OSSA;

public class OSSA250ServiceMock extends ExternalResource implements OSSA {
    private static Logger LOGGER = LoggerFactory.getLogger(OSSA250ServiceMock.class);

    private final String mockServiceURL;
    private Endpoint mockServiceEdp;

    private String param1;

    private int param2;

    private String param3;

    public OSSA250ServiceMock(final String mockServiceUrl) {
        this.mockServiceURL = mockServiceUrl;
    }

    @Override
    protected void before() throws Throwable {
        super.before();
        this.mockServiceEdp = Endpoint.publish(this.mockServiceURL, this);
    }

    @Override
    protected void after() {
        this.mockServiceEdp.stop();
        super.after();
    }

    @Override
    public void ossa250With3Params(final String param, final Holder<String> repParam1, final Holder<Integer> repParam2,
            final Holder<String> repParam3) {

        repParam1.value = param1;
        repParam2.value = param2;
        repParam3.value = param3;

        LOGGER.error(
                "\n\n\n ossa250 with repParam1 [{}] | repParam2 [{}] , repParam3 [{}] \n\n\n----", repParam1.value,
                repParam2.value, repParam3.value);
    }

    @Override
    public void ossa250With2Params(final String param, final Holder<String> repParam1,
            final Holder<Integer> repParam2) {

        repParam1.value = param1;
        repParam2.value = param2;

        LOGGER.error("\n\n\n ossa250 with repParam1 [{}] | repParam2 [{}] \n\n\n----", repParam1.value,
                repParam2.value);
    }

    public void setParam1(final String param1) {
        this.param1 = param1;
    }

    public void setParam2(final int param2) {
        this.param2 = param2;
    }

    public void setParam3(final String param3) {
        this.param3 = param3;
    }
}

