package com.App.BlogApplication.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.RoleDto;
import com.App.BlogApplication.DTO.UserDto;
import com.App.BlogApplication.Entities.Comment;
import com.App.BlogApplication.Entities.Post;
import com.App.BlogApplication.Entities.Role;
import com.App.BlogApplication.Entities.User;
import com.App.BlogApplication.Exception.ResourceNotFoundException;
import com.App.BlogApplication.Repositories.RoleRepo;
import com.App.BlogApplication.Repositories.UserRepo;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private RoleRepo roleRepo;

    @MockBean
    private UserRepo userRepo;

    @Autowired
    private UserServiceImpl userServiceImpl;

    /**
     * Method under test: {@link UserServiceImpl#createUser(UserDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateUser() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ClassCastException: class com.App.BlogApplication.Entities.User$MockitoMock$mVc6QRIZ cannot be cast to class com.App.BlogApplication.DTO.UserDto (com.App.BlogApplication.Entities.User$MockitoMock$mVc6QRIZ and com.App.BlogApplication.DTO.UserDto are in unnamed module of loader com.diffblue.cover.f.g @6934ce12)
        //       at com.App.BlogApplication.Services.UserServiceImpl.userToDto(UserServiceImpl.java:87)
        //       at com.App.BlogApplication.Services.UserServiceImpl.createUser(UserServiceImpl.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        UserServiceImpl userServiceImpl = new UserServiceImpl();

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        userServiceImpl.createUser(userDto);
    }

    /**
     * Method under test: {@link UserServiceImpl#createUser(UserDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateUser2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.ClassCastException: class com.App.BlogApplication.Entities.User$MockitoMock$mVc6QRIZ cannot be cast to class com.App.BlogApplication.DTO.UserDto (com.App.BlogApplication.Entities.User$MockitoMock$mVc6QRIZ and com.App.BlogApplication.DTO.UserDto are in unnamed module of loader com.diffblue.cover.f.g @6934ce12)
        //       at com.App.BlogApplication.Services.UserServiceImpl.userToDto(UserServiceImpl.java:87)
        //       at com.App.BlogApplication.Services.UserServiceImpl.createUser(UserServiceImpl.java:38)
        //   See https://diff.blue/R013 to resolve this issue.

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        UserDto userDto = mock(UserDto.class);
        doNothing().when(userDto).setAbout((String) any());
        doNothing().when(userDto).setEmail((String) any());
        doNothing().when(userDto).setId(anyInt());
        doNothing().when(userDto).setName((String) any());
        doNothing().when(userDto).setPassword((String) any());
        doNothing().when(userDto).setRoles((Set<RoleDto>) any());
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        userServiceImpl.createUser(userDto);
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UserDto, Integer)}
     */
    @Test
    void testUpdateUser() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

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

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);
        when(userRepo.save((User) any())).thenReturn(user1);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);

        UserDto userDto1 = new UserDto();
        userDto1.setAbout("About");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setId(1);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new HashSet<>());
        assertSame(userDto, userServiceImpl.updateUser(userDto1, 123));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).save((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UserDto, Integer)}
     */
    @Test
    void testUpdateUser2() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

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
        when(userRepo.save((User) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);

        UserDto userDto1 = new UserDto();
        userDto1.setAbout("About");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setId(1);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new HashSet<>());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.updateUser(userDto1, 123));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(userRepo).save((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#updateUser(UserDto, Integer)}
     */
    @Test
    void testUpdateUser3() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);
        when(userRepo.save((User) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());

        UserDto userDto1 = new UserDto();
        userDto1.setAbout("About");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setId(1);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new HashSet<>());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.updateUser(userDto1, 123));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(Integer)}
     */
    @Test
    void testGetUserById() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

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
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertSame(userDto, userServiceImpl.getUserById(123));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(Integer)}
     */
    @Test
    void testGetUserById2() {
        when(modelMapper.map((Object) any(), (Class<UserDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

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
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUserById(123));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getUserById(Integer)}
     */
    @Test
    void testGetUserById3() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getUserById(123));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers() {
        when(userRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(userServiceImpl.getAllUsers().isEmpty());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers2() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepo.findAll()).thenReturn(userList);
        assertEquals(1, userServiceImpl.getAllUsers().size());
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers3() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user);
        when(userRepo.findAll()).thenReturn(userList);
        assertEquals(2, userServiceImpl.getAllUsers().size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#getAllUsers()}
     */
    @Test
    void testGetAllUsers4() {
        when(modelMapper.map((Object) any(), (Class<UserDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(userRepo.findAll()).thenReturn(userList);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.getAllUsers());
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
        verify(userRepo).findAll();
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser() {
        User user = new User();
        user.setAbout("About");
        ArrayList<Comment> commentList = new ArrayList<>();
        user.setComments(commentList);
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);
        Optional<User> ofResult = Optional.of(user);
        doNothing().when(userRepo).delete((User) any());
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        userServiceImpl.deleteUser(123);
        verify(userRepo).findById((Integer) any());
        verify(userRepo).delete((User) any());
        assertEquals(commentList, userServiceImpl.getAllUsers());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser2() {
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
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42)).when(userRepo).delete((User) any());
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.deleteUser(123));
        verify(userRepo).findById((Integer) any());
        verify(userRepo).delete((User) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#deleteUser(Integer)}
     */
    @Test
    void testDeleteUser3() {
        doNothing().when(userRepo).delete((User) any());
        when(userRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.deleteUser(123));
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#dtoToUser(UserDto)}
     */
    @Test
    void testDtoToUser() {
        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        assertSame(user, userServiceImpl.dtoToUser(userDto));
        verify(modelMapper).map((Object) any(), (Class<User>) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#dtoToUser(UserDto)}
     */
    @Test
    void testDtoToUser2() {
        when(modelMapper.map((Object) any(), (Class<User>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.dtoToUser(userDto));
        verify(modelMapper).map((Object) any(), (Class<User>) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#userToDto(User)}
     */
    @Test
    void testUserToDto() {
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        when(modelMapper.map((Object) any(), (Class<UserDto>) any())).thenReturn(userDto);

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);
        assertSame(userDto, userServiceImpl.userToDto(user));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#userToDto(User)}
     */
    @Test
    void testUserToDto2() {
        when(modelMapper.map((Object) any(), (Class<UserDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.userToDto(user));
        verify(modelMapper).map((Object) any(), (Class<UserDto>) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#registerNewUser(UserDto)}
     */
    @Test
    void testRegisterNewUser() {
        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);

        Role role = new Role();
        role.setId(1);
        role.setName("Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepo.findById((Integer) any())).thenReturn(ofResult);

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);
        when(userRepo.save((User) any())).thenReturn(user1);
        when(passwordEncoder.encode((CharSequence) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        assertThrows(ResourceNotFoundException.class, () -> userServiceImpl.registerNewUser(userDto));
        verify(modelMapper).map((Object) any(), (Class<User>) any());
        verify(passwordEncoder).encode((CharSequence) any());
    }

    /**
     * Method under test: {@link UserServiceImpl#registerNewUser(UserDto)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testRegisterNewUser2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at com.App.BlogApplication.Services.UserServiceImpl.registerNewUser(UserServiceImpl.java:101)
        //   See https://diff.blue/R013 to resolve this issue.

        User user = mock(User.class);
        when(user.getPassword()).thenReturn("iloveyou");
        when(user.getRoles()).thenReturn(new HashSet<>());
        doNothing().when(user).setAbout((String) any());
        doNothing().when(user).setComments((List<Comment>) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setPosts((List<Post>) any());
        doNothing().when(user).setRoles((Set<Role>) any());
        doNothing().when(user).setUserId(anyInt());
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<User>) any())).thenReturn(user);
        when(roleRepo.findById((Integer) any())).thenReturn(Optional.empty());
        Role role = mock(Role.class);
        doNothing().when(role).setId(anyInt());
        doNothing().when(role).setName((String) any());
        role.setId(1);
        role.setName("Name");

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);
        when(userRepo.save((User) any())).thenReturn(user1);
        when(passwordEncoder.encode((CharSequence) any())).thenReturn("secret");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        userServiceImpl.registerNewUser(userDto);
    }
}

