/**
 * 
 */
package gamesys.config;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@org.springframework.context.annotation.Configuration
@ActiveProfiles(value = "integration-test", inheritProfiles = false)
@Profile("integration-test")
public class SpringConfigIT {

}