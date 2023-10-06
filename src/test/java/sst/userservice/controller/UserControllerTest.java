package sst.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.hamcrest.Matchers.*;

import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sst.userservice.model.User;

import sst.userservice.repository.UserRepository;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    UserRepository userRepository;




    LocalDate date = LocalDate.parse("1995-09-14");


    User user1 = new User(1,"test95","Niclas","test95@outlook.com",date );
    User user2 = new User(1,"test96","Niclas","test96@outlook.com",date );
    User user3 = new User(1,"test97","Niclas","test97@outlook.com",date );

    @Test
    public void getAllUsers_success() throws Exception {


        List<User> users = new ArrayList<>(Arrays.asList(user1,user2,user3));

        Mockito.when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect((ResultMatcher) jsonPath("$[2].userName",is("test96")));

    }



}
