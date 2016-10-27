package gamesys.controller;

import gamesys.factory.UserFactory;
import gamesys.util.BaseControllerTest;
import gamesys.util.ObjectMapperUtil;
import org.junit.Test;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserRegistrationControllerTest extends BaseControllerTest {

    private static final String PATH = "/" + UserRegistrationController.PATH + UserRegistrationController.REGISTER_PATH;

    private static final String FETCH_ALL_USERS_RESPONSE = ObjectMapperUtil.pojoToJson(UserFactory.getUsers());
    private static final String REGISTER_USER = ObjectMapperUtil.pojoToJson(UserFactory.someUser());

    @Test
    public void testListAllUsers() throws Exception {
        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(FETCH_ALL_USERS_RESPONSE)));
    }

    @Test
    public void testRegisterUser() throws Exception {
        mockMvc.perform(post(PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(REGISTER_USER))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeregisterUser() throws Exception {
        mockMvc.perform(delete(PATH + "/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testDeregisterAllUsers() throws Exception {
        mockMvc.perform(delete(PATH))
                .andExpect(status().isNoContent());
    }

}
