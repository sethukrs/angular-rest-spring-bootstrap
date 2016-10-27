package gamesys.util;

import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.URL;

@RunWith(Arquillian.class)
@ArquillianSuiteDeployment
public abstract class AbstractControllerIT {
    @ArquillianResource
    protected URL baseURL;

    @Before
    public void setUp() throws IOException {
        ITTestUtil.setUpRestAssured(baseURL);
    }

    @Deployment(testable = false)
    public static WebArchive createTestWar() {
        return ITTestUtil.createTestArchive();
    }
}