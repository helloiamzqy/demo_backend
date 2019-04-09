package com.example.demo.api;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.google.common.collect.Lists.newArrayList;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void list() throws Exception {
        // given
        when(userService.list()).thenReturn(newArrayList(givenUserDTO(), givenUserDTO()));

        // when
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void getUser_ok() throws Exception {
        // given
        when(userService.findUserById(eq("1"))).thenReturn(givenUserDTO());

        // when
        mockMvc.perform(get("/user/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.username").value("User"));
    }

    @Test
    public void getUser_not_found() throws Exception {
        // given
        when(userService.findUserById(eq("2"))).thenThrow(new NotFoundException("User not exist."));

        // when
        mockMvc.perform(get("/user/{id}", "2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("User not exist."));
    }

    @Test
    public void updateUser() throws Exception {
        // given
        when(userService.update(any(UserDTO.class))).thenReturn("1");

        // when
        mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"username\":\"User\",\"password\":\"123\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    public void updateUser_not_found() throws Exception {
        // given
        when(userService.update(any(UserDTO.class))).thenThrow(new NotFoundException("User not exist."));

        // when
        MvcResult mvcResult = mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\",\"username\":\"User\",\"password\":\"123\"}")).andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();

        Assert.assertThat(response.getStatus(), is(404));
        Assert.assertThat(response.getContentAsString(), is("{\"message\":\"User not exist.\"}"));
    }

    @Test
    public void updateUser_empty_body() throws Exception {
        // given

        // when
        mockMvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.messages").isArray())
                .andExpect(jsonPath("$.messages.length()").value(3));
    }

    private UserDTO givenUserDTO() {
        return UserDTO.builder().id("1").username("User").password("123").build();
    }


}