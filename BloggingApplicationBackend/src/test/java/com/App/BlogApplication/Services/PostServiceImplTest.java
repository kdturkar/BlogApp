package com.App.BlogApplication.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.CategoryDto;
import com.App.BlogApplication.DTO.PostDto;
import com.App.BlogApplication.DTO.PostResponse;
import com.App.BlogApplication.DTO.UserDto;
import com.App.BlogApplication.Entities.Category;
import com.App.BlogApplication.Entities.Post;
import com.App.BlogApplication.Entities.User;
import com.App.BlogApplication.Exception.ResourceNotFoundException;
import com.App.BlogApplication.Repositories.CategoryRepo;
import com.App.BlogApplication.Repositories.PostRepo;
import com.App.BlogApplication.Repositories.UserRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PostServiceImpl.class})
@ExtendWith(SpringExtension.class)
class PostServiceImplTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PostRepo postRepo;

    @Autowired
    private PostServiceImpl postServiceImpl;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDto, Integer, Integer)}
     */
    @Test
    void testCreatePost() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category1);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        when(modelMapper.map((Object) any(), (Class<Post>) any())).thenReturn(post);
        when(postRepo.save((Post) any())).thenThrow(new ResourceNotFoundException("default.png", "default.png", 42));

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);
        Optional<User> ofResult1 = Optional.of(user1);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult1);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.createPost(postDto, 123, 123));
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<Post>) any());
        verify(postRepo).save((Post) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#createPost(PostDto, Integer, Integer)}
     */
    @Test
    void testCreatePost2() {
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());
        Category category = mock(Category.class);
        doNothing().when(category).setCategoryDescription((String) any());
        doNothing().when(category).setCategoryId((Integer) any());
        doNothing().when(category).setCategoryTitle((String) any());
        doNothing().when(category).setPosts((List<Post>) any());
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category1);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<Post>) any())).thenReturn(post);

        Category category2 = new Category();
        category2.setCategoryDescription("Category Description");
        category2.setCategoryId(123);
        category2.setCategoryTitle("Dr");
        category2.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category2);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user1);
        when(postRepo.save((Post) any())).thenReturn(post1);

        User user2 = new User();
        user2.setAbout("About");
        user2.setComments(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());
        user2.setRoles(new HashSet<>());
        user2.setUserId(123);
        Optional<User> ofResult = Optional.of(user2);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.createPost(postDto, 123, 123));
        verify(categoryRepo).findById((Integer) any());
        verify(category).setCategoryDescription((String) any());
        verify(category).setCategoryId((Integer) any());
        verify(category).setCategoryTitle((String) any());
        verify(category).setPosts((List<Post>) any());
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#updatePost(PostDto, Integer)}
     */
    @Test
    void testUpdatePost() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user1);
        when(postRepo.save((Post) any())).thenReturn(post1);
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryDescription("Category Description");
        categoryDto1.setCategoryId(123);
        categoryDto1.setCategoryTitle("Dr");

        UserDto userDto1 = new UserDto();
        userDto1.setAbout("About");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setId(1);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new HashSet<>());

        PostDto postDto1 = new PostDto();
        LocalDateTime atStartOfDayResult3 = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto1.setAddedDate(Date.from(atStartOfDayResult3.atZone(ZoneId.of("UTC")).toInstant()));
        postDto1.setCategory(categoryDto1);
        postDto1.setComments(new HashSet<>());
        postDto1.setContent("Not all who wander are lost");
        postDto1.setImageName("Image Name");
        postDto1.setPostId(123);
        postDto1.setTitle("Dr");
        postDto1.setUser(userDto1);
        assertSame(postDto, postServiceImpl.updatePost(postDto1, 123));
        verify(modelMapper).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).save((Post) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#updatePost(PostDto, Integer)}
     */
    @Test
    void testUpdatePost2() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);
        when(postRepo.save((Post) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryDescription("Category Description");
        categoryDto1.setCategoryId(123);
        categoryDto1.setCategoryTitle("Dr");

        UserDto userDto1 = new UserDto();
        userDto1.setAbout("About");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setId(1);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new HashSet<>());

        PostDto postDto1 = new PostDto();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto1.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        postDto1.setCategory(categoryDto1);
        postDto1.setComments(new HashSet<>());
        postDto1.setContent("Not all who wander are lost");
        postDto1.setImageName("Image Name");
        postDto1.setPostId(123);
        postDto1.setTitle("Dr");
        postDto1.setUser(userDto1);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.updatePost(postDto1, 123));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(postRepo).save((Post) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#updatePost(PostDto, Integer)}
     */
    @Test
    void testUpdatePost3() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);
        when(postRepo.save((Post) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        when(postRepo.findById((Integer) any())).thenReturn(Optional.empty());

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryDescription("Category Description");
        categoryDto1.setCategoryId(123);
        categoryDto1.setCategoryTitle("Dr");

        UserDto userDto1 = new UserDto();
        userDto1.setAbout("About");
        userDto1.setEmail("jane.doe@example.org");
        userDto1.setId(1);
        userDto1.setName("Name");
        userDto1.setPassword("iloveyou");
        userDto1.setRoles(new HashSet<>());

        PostDto postDto1 = new PostDto();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        postDto1.setCategory(categoryDto1);
        postDto1.setComments(new HashSet<>());
        postDto1.setContent("Not all who wander are lost");
        postDto1.setImageName("Image Name");
        postDto1.setPostId(123);
        postDto1.setTitle("Dr");
        postDto1.setUser(userDto1);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.updatePost(postDto1, 123));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Integer)}
     */
    @Test
    void testDeletePost() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);
        doNothing().when(postRepo).delete((Post) any());
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);
        postServiceImpl.deletePost(123);
        verify(postRepo).findById((Integer) any());
        verify(postRepo).delete((Post) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Integer)}
     */
    @Test
    void testDeletePost2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42)).when(postRepo).delete((Post) any());
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.deletePost(123));
        verify(postRepo).findById((Integer) any());
        verify(postRepo).delete((Post) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#deletePost(Integer)}
     */
    @Test
    void testDeletePost3() {
        doNothing().when(postRepo).delete((Post) any());
        when(postRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.deletePost(123));
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPost(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllPost() {
        ArrayList<Post> postList = new ArrayList<>();
        when(postRepo.findAll((Pageable) any())).thenReturn(new PageImpl<>(postList));
        PostResponse actualAllPost = postServiceImpl.getAllPost(10, 3, "Sort By", "Sort Dir");
        assertEquals(postList, actualAllPost.getContent());
        assertTrue(actualAllPost.isLastPage());
        assertEquals(1, actualAllPost.getTotalPages());
        assertEquals(0L, actualAllPost.getTotalElements());
        assertEquals(0, actualAllPost.getPageSize());
        assertEquals(0, actualAllPost.getPageNumber());
        verify(postRepo).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPost(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllPost2() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("asc");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("asc");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("asc");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("asc");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        PageImpl<Post> pageImpl = new PageImpl<>(postList);
        when(postRepo.findAll((Pageable) any())).thenReturn(pageImpl);
        PostResponse actualAllPost = postServiceImpl.getAllPost(10, 3, "Sort By", "Sort Dir");
        assertEquals(1, actualAllPost.getContent().size());
        assertTrue(actualAllPost.isLastPage());
        assertEquals(1, actualAllPost.getTotalPages());
        assertEquals(1L, actualAllPost.getTotalElements());
        assertEquals(1, actualAllPost.getPageSize());
        assertEquals(0, actualAllPost.getPageNumber());
        verify(modelMapper).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getAllPost(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllPost3() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("asc");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("asc");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("asc");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("asc");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        Category category1 = new Category();
        category1.setCategoryDescription("asc");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("asc");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("asc");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("asc");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user1);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post);
        PageImpl<Post> pageImpl = new PageImpl<>(postList);
        when(postRepo.findAll((Pageable) any())).thenReturn(pageImpl);
        PostResponse actualAllPost = postServiceImpl.getAllPost(10, 3, "Sort By", "Sort Dir");
        assertEquals(2, actualAllPost.getContent().size());
        assertTrue(actualAllPost.isLastPage());
        assertEquals(1, actualAllPost.getTotalPages());
        assertEquals(2L, actualAllPost.getTotalElements());
        assertEquals(2, actualAllPost.getPageSize());
        assertEquals(0, actualAllPost.getPageNumber());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findAll((Pageable) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostById(Integer)}
     */
    @Test
    void testGetPostById() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);
        assertSame(postDto, postServiceImpl.getPostById(123));
        verify(modelMapper).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostById(Integer)}
     */
    @Test
    void testGetPostById2() {
        when(modelMapper.map((Object) any(), (Class<PostDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);
        Optional<Post> ofResult = Optional.of(post);
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostById(123));
        verify(modelMapper).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostById(Integer)}
     */
    @Test
    void testGetPostById3() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);
        when(postRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostById(123));
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
        verify(postRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByCategory(Integer)}
     */
    @Test
    void testGetPostsByCategory() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        when(postRepo.findByCategory((Category) any())).thenReturn(new ArrayList<>());
        assertTrue(postServiceImpl.getPostsByCategory(123).isEmpty());
        verify(categoryRepo).findById((Integer) any());
        verify(postRepo).findByCategory((Category) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByCategory(Integer)}
     */
    @Test
    void testGetPostsByCategory2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        when(postRepo.findByCategory((Category) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostsByCategory(123));
        verify(categoryRepo).findById((Integer) any());
        verify(postRepo).findByCategory((Category) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByCategory(Integer)}
     */
    @Test
    void testGetPostsByCategory3() {
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());
        when(postRepo.findByCategory((Category) any())).thenReturn(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostsByCategory(123));
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByCategory(Integer)}
     */
    @Test
    void testGetPostsByCategory4() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category1);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        when(postRepo.findByCategory((Category) any())).thenReturn(postList);
        assertEquals(1, postServiceImpl.getPostsByCategory(123).size());
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findByCategory((Category) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostsByCategory(Integer)}
     */
    @Test
    void testGetPostsByCategory5() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category1);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        Category category2 = new Category();
        category2.setCategoryDescription("Category Description");
        category2.setCategoryId(123);
        category2.setCategoryTitle("Dr");
        category2.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category2);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user1);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post);
        when(postRepo.findByCategory((Category) any())).thenReturn(postList);
        assertEquals(2, postServiceImpl.getPostsByCategory(123).size());
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findByCategory((Category) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUser(Integer)}
     */
    @Test
    void testGetPostByUser() {
        when(postRepo.findByUser((User) any())).thenReturn(new ArrayList<>());

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
        assertTrue(postServiceImpl.getPostByUser(123).isEmpty());
        verify(postRepo).findByUser((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUser(Integer)}
     */
    @Test
    void testGetPostByUser2() {
        when(postRepo.findByUser((User) any()))
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
        assertThrows(ResourceNotFoundException.class, () -> postServiceImpl.getPostByUser(123));
        verify(postRepo).findByUser((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUser(Integer)}
     */
    @Test
    void testGetPostByUser3() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        when(postRepo.findByUser((User) any())).thenReturn(postList);

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);
        Optional<User> ofResult = Optional.of(user1);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertEquals(1, postServiceImpl.getPostByUser(123).size());
        verify(modelMapper).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findByUser((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#getPostByUser(Integer)}
     */
    @Test
    void testGetPostByUser4() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user1);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post);
        when(postRepo.findByUser((User) any())).thenReturn(postList);

        User user2 = new User();
        user2.setAbout("About");
        user2.setComments(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());
        user2.setRoles(new HashSet<>());
        user2.setUserId(123);
        Optional<User> ofResult = Optional.of(user2);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult);
        assertEquals(2, postServiceImpl.getPostByUser(123).size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findByUser((User) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPosts(String)}
     */
    @Test
    void testSearchPosts() {
        when(postRepo.findByTitleContaining((String) any())).thenReturn(new ArrayList<>());
        assertTrue(postServiceImpl.searchPosts("Keyword").isEmpty());
        verify(postRepo).findByTitleContaining((String) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPosts(String)}
     */
    @Test
    void testSearchPosts2() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post);
        when(postRepo.findByTitleContaining((String) any())).thenReturn(postList);
        assertEquals(1, postServiceImpl.searchPosts("Keyword").size());
        verify(modelMapper).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findByTitleContaining((String) any());
    }

    /**
     * Method under test: {@link PostServiceImpl#searchPosts(String)}
     */
    @Test
    void testSearchPosts3() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");

        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());

        PostDto postDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        postDto.setAddedDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        postDto.setCategory(categoryDto);
        postDto.setComments(new HashSet<>());
        postDto.setContent("Not all who wander are lost");
        postDto.setImageName("Image Name");
        postDto.setPostId(123);
        postDto.setTitle("Dr");
        postDto.setUser(userDto);
        when(modelMapper.map((Object) any(), (Class<PostDto>) any())).thenReturn(postDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        User user = new User();
        user.setAbout("About");
        user.setComments(new ArrayList<>());
        user.setEmail("jane.doe@example.org");
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setPosts(new ArrayList<>());
        user.setRoles(new HashSet<>());
        user.setUserId(123);

        Post post = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post.setCategory(category);
        post.setComments(new HashSet<>());
        post.setContent("Not all who wander are lost");
        post.setImageName("Image Name");
        post.setPostId(123);
        post.setTitle("Dr");
        post.setUser(user);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult2 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult2.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user1);

        ArrayList<Post> postList = new ArrayList<>();
        postList.add(post1);
        postList.add(post);
        when(postRepo.findByTitleContaining((String) any())).thenReturn(postList);
        assertEquals(2, postServiceImpl.searchPosts("Keyword").size());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<PostDto>) any());
        verify(postRepo).findByTitleContaining((String) any());
    }
}

