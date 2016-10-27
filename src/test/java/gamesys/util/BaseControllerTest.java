package gamesys.util;

import gamesys.config.CommonConfigTest;
import gamesys.configuration.MvcConfig;
import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;


import javax.inject.Inject;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@WebAppConfiguration
@ContextConfiguration(classes = { MvcConfig.class, CommonConfigTest.class })
public abstract class BaseControllerTest extends AbstractSpringTest {

    protected MockMvc mockMvc;

    @Inject
    protected WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(this.wac).build();
    }
}
