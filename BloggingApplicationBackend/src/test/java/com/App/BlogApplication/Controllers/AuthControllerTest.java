package com.App.BlogApplication.Controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.JwtAuthRequest;
import com.App.BlogApplication.DTO.UserDto;
import com.App.BlogApplication.Entities.User;
import com.App.BlogApplication.Security.JwtTokenHelper;
import com.App.BlogApplication.Services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtTokenHelper jwtTokenHelper;

    @MockBean
    private UserDetailsService userDetailsService;

    @MockBean
    private UserService userService;

    /**
     * Method under test: {@link AuthController#createToken(JwtAuthRequest)}
     */
    @Test
    void testCreateToken() throws Exception {
        when(jwtTokenHelper.generateToken((UserDetails) any())).thenReturn("ABC123");
        when(userDetailsService.loadUserByUsername((String) any())).thenReturn(new User());
        when(authenticationManager.authenticate((Authentication) any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(jwtAuthRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"token\":\"ABC123\"}"));
    }

    /**
     * Method under test: {@link AuthController#registerNewUser(UserDto)}
     */
    @Test
    void testRegisterNewUser() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(userService.registerNewUser((UserDto) any())).thenReturn(userDto);

        UserDto userDto1 = new UserDto();
        userDto1.setAbout("About");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setId(1);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new HashSet<>());
        String content = (new ObjectMapper()).writeValueAsString(userDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About\","
                                        + "\"roles\":[]}"));
    }
}

