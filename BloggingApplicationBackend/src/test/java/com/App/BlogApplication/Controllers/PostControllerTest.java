package com.App.BlogApplication.Controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.CategoryDto;
import com.App.BlogApplication.DTO.PostDto;
import com.App.BlogApplication.DTO.PostResponse;
import com.App.BlogApplication.DTO.UserDto;
import com.App.BlogApplication.Services.FileService;
import com.App.BlogApplication.Services.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {PostController.class})
@ExtendWith(SpringExtension.class)
class PostControllerTest {
    @MockBean
    private FileService fileService;

    @Autowired
    private PostController postController;

    @MockBean
    private PostService postService;

    /**
     * Method under test: {@link PostController#createPost(PostDto, Integer, Integer)}
     */
    @Test
    void testCreatePost() throws Exception {
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
        when(postService.createPost((PostDto) any(), (Integer) any(), (Integer) any())).thenReturn(postDto);

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
        String content = (new ObjectMapper()).writeValueAsString(postDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/user/{userId}/category/{categoryId}/posts", 123, 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"postId\":123,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageName\":\"Image Name\",\"user\":{"
                                        + "\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About\",\"roles\":[]"
                                        + "},\"addedDate\":0,\"category\":{\"categoryId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category"
                                        + " Description\"},\"comments\":[]}"));
    }

    /**
     * Method under test: {@link PostController#getPostsByCategory(Integer)}
     */
    @Test
    void testGetPostsByCategory() throws Exception {
        when(postService.getPostsByCategory((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/category/{categoryId}/posts",
                123);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#getPostsByCategory(Integer)}
     */
    @Test
    void testGetPostsByCategory2() throws Exception {
        when(postService.getPostsByCategory((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/category/{categoryId}/posts", 123);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link PostController#getAllPost(Integer, Integer, String, String)}
     */
    @Test
    void testGetAllPost() throws Exception {
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(new ArrayList<>());
        postResponse.setLastPage(true);
        postResponse.setPageNumber(10);
        postResponse.setPageSize(3);
        postResponse.setTotalElements(1L);
        postResponse.setTotalPages(1);
        when(postService.getAllPost((Integer) any(), (Integer) any(), (String) any(), (String) any()))
                .thenReturn(postResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get_all_ posts");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"content\":[],\"pageNumber\":10,\"pageSize\":3,\"totalPages\":1,\"totalElements\":1,\"lastPage\":true}"));
    }

    /**
     * Method under test: {@link PostController#updatePost(PostDto, Integer)}
     */
    @Test
    void testUpdatePost() throws Exception {
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
        when(postService.updatePost((PostDto) any(), (Integer) any())).thenReturn(postDto);

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
        String content = (new ObjectMapper()).writeValueAsString(postDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/update/post/{postId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"postId\":123,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageName\":\"Image Name\",\"user\":{"
                                        + "\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About\",\"roles\":[]"
                                        + "},\"addedDate\":0,\"category\":{\"categoryId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category"
                                        + " Description\"},\"comments\":[]}"));
    }

    /**
     * Method under test: {@link PostController#deletePost(Integer)}
     */
    @Test
    void testDeletePost() throws Exception {
        doNothing().when(postService).deletePost((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/delete/post/{postId}", 123);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Post is deleted with postId: 123\",\"status\":true}"));
    }

    /**
     * Method under test: {@link PostController#deletePost(Integer)}
     */
    @Test
    void testDeletePost2() throws Exception {
        doNothing().when(postService).deletePost((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/delete/post/{postId}", 123);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Post is deleted with postId: 123\",\"status\":true}"));
    }

    /**
     * Method under test: {@link PostController#downloadImage(String, HttpServletResponse)}
     */
    @Test
    void testDownloadImage() throws Exception {
        when(fileService.getResource((String) any(), (String) any()))
                .thenReturn(new ByteArrayInputStream("AAAAAAAA".getBytes("UTF-8")));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/post/image/{imageName}",
                "Image Name");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("image/jpeg"))
                .andExpect(MockMvcResultMatchers.content().string("AAAAAAAA"));
    }

    /**
     * Method under test: {@link PostController#getPost(Integer)}
     */
    @Test
    void testGetPost() throws Exception {
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
        when(postService.getPostById((Integer) any())).thenReturn(postDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get_post/{postId}", 123);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"postId\":123,\"title\":\"Dr\",\"content\":\"Not all who wander are lost\",\"imageName\":\"Image Name\",\"user\":{"
                                        + "\"id\":1,\"name\":\"Name\",\"email\":\"jane.doe@example.org\",\"password\":\"iloveyou\",\"about\":\"About\",\"roles\":[]"
                                        + "},\"addedDate\":0,\"category\":{\"categoryId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category"
                                        + " Description\"},\"comments\":[]}"));
    }

    /**
     * Method under test: {@link PostController#getPost(Integer)}
     */
    @Test
    void testGetPost2() throws Exception {
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
        when(postService.getPostById((Integer) any())).thenReturn(postDto);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/get_post/{postId}", 123);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link PostController#getPostsByUser(Integer)}
     */
    @Test
    void testGetPostsByUser() throws Exception {
        when(postService.getPostByUser((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/{userId}/posts", 123);
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#getPostsByUser(Integer)}
     */
    @Test
    void testGetPostsByUser2() throws Exception {
        when(postService.getPostByUser((Integer) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/user/{userId}/posts", 123);
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link PostController#searchPostByTitle(String)}
     */
    @Test
    void testSearchPostByTitle() throws Exception {
        when(postService.searchPosts((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/posts/search/{keywords}",
                "Keywords");
        MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link PostController#searchPostByTitle(String)}
     */
    @Test
    void testSearchPostByTitle2() throws Exception {
        when(postService.searchPosts((String) any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/posts/search/{keywords}", "Keywords");
        getResult.accept("https://example.org/example");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController).build().perform(getResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(406));
    }

    /**
     * Method under test: {@link PostController#uploadPostImage(Integer, MultipartFile)}
     */
    @Test
    void testUploadPostImage() throws Exception {
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/post/image/upload/{postId}",
                "Uri Variables", "Uri Variables");
        MockHttpServletRequestBuilder requestBuilder = postResult.param("image", String.valueOf((Object) null));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(postController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

