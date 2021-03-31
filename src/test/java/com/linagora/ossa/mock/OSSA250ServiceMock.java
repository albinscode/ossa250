package com.linagora.ossa.mock;

import javax.xml.ws.Endpoint;
import javax.xml.ws.Holder;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.linagora.ossa._250.OSSA;
import com.linagora.ossa._250.Ossa250With3ParamsResponseType;

public class OSSA250ServiceMock extends ExternalResource implements OSSA {
    private static Logger LOGGER = LoggerFactory.getLogger(OSSA250ServiceMock.class);

    private final String mockServiceURL;
    private Endpoint mockServiceEdp;

    private String param1;

    private int param2;

    private Ossa250With3ParamsResponseType ossa250With3ParamsResponseType;

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
    public Ossa250With3ParamsResponseType ossa250With3Params(final String param) {

        return this.ossa250With3ParamsResponseType;
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

    public void setOssa250With3ParamsResponseType(Ossa250With3ParamsResponseType ossa250With3ParamsResponseType) {
        this.ossa250With3ParamsResponseType = ossa250With3ParamsResponseType;
    }

}

