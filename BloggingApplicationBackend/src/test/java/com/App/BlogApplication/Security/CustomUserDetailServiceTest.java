package com.App.BlogApplication.Security;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.Entities.User;
import com.App.BlogApplication.Exception.ResourceNotFoundException;
import com.App.BlogApplication.Repositories.UserRepo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CustomUserDetailService.class})
@ExtendWith(SpringExtension.class)
class CustomUserDetailServiceTest {
    @Autowired
    private CustomUserDetailService customUserDetailService;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link CustomUserDetailService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);
        Optional<User> ofResult = Optional.of(user);
        when(userRepo.findByEmail((String) any())).thenReturn(ofResult);
        assertSame(user, customUserDetailService.loadUserByUsername("janedoe"));
        verify(userRepo).findByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomUserDetailService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        when(userRepo.findByEmail((String) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customUserDetailService.loadUserByUsername("janedoe"));
        verify(userRepo).findByEmail((String) any());
    }

    /**
     * Method under test: {@link CustomUserDetailService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        when(userRepo.findByEmail((String) any())).thenThrow(new UsernameNotFoundException("User"));
        assertThrows(UsernameNotFoundException.class, () -> customUserDetailService.loadUserByUsername("janedoe"));
        verify(userRepo).findByEmail((String) any());
    }
}

