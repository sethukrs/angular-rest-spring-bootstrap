package gamesys.config;

import gamesys.factory.UserFactory;
import gamesys.model.User;
import gamesys.service.ExclusionService;
import gamesys.service.ExclusionServiceImpl;
import gamesys.service.UserService;
import gamesys.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
@ActiveProfiles(value = "test", inheritProfiles = false)
@Profile("test")
@ComponentScan(basePackages = { "gamesys.controller" })
public class CommonConfigTest {

    @Bean
    public UserService userService() {
        UserService userService = mock(UserServiceImpl.class);
        when(userService.findAllUsers()).thenReturn(UserFactory.getUsers());
        when(userService.findById(eq(1))).thenReturn(UserFactory.someUser());

        User user1 = UserFactory.someUser();
        doNothing().when(userService).register(user1);
        doNothing().when(userService).deregisterUserById(eq(1));
        doNothing().when(userService).deregisterAllUsers();

        return userService;
    }

    @Bean
    public ExclusionService exclusionService() {
        ExclusionService exclusionService = mock(ExclusionServiceImpl.class);
        when(exclusionService.validate(anyString(), anyString())).thenReturn(true);
        return exclusionService;
    }
}

