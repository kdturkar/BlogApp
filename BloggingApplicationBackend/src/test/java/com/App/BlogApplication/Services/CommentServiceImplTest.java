package com.App.BlogApplication.Services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.CommentDto;
import com.App.BlogApplication.Entities.Category;
import com.App.BlogApplication.Entities.Comment;
import com.App.BlogApplication.Entities.Post;
import com.App.BlogApplication.Entities.User;
import com.App.BlogApplication.Exception.ResourceNotFoundException;
import com.App.BlogApplication.Repositories.CommentRepo;
import com.App.BlogApplication.Repositories.PostRepo;
import com.App.BlogApplication.Repositories.UserRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CommentServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CommentServiceImplTest {
    @MockBean
    private CommentRepo commentRepo;

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PostRepo postRepo;

    @MockBean
    private UserRepo userRepo;

    /**
     * Method under test: {@link CommentServiceImpl#createComment(CommentDto, Integer, Integer)}
     */
    @Test
    void testCreateComment() {
        when(commentRepo.save((Comment) any())).thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

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

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Comment comment = new Comment();
        comment.setContent("Not all who wander are lost");
        comment.setId(1);
        comment.setPost(post);
        comment.setUser(user1);
        when(modelMapper.map((Object) any(), (Class<Comment>) any())).thenReturn(comment);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        User user2 = new User();
        user2.setAbout("About");
        user2.setComments(new ArrayList<>());
        user2.setEmail("jane.doe@example.org");
        user2.setName("Name");
        user2.setPassword("iloveyou");
        user2.setPosts(new ArrayList<>());
        user2.setRoles(new HashSet<>());
        user2.setUserId(123);

        Post post1 = new Post();
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        post1.setAddedDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));
        post1.setCategory(category1);
        post1.setComments(new HashSet<>());
        post1.setContent("Not all who wander are lost");
        post1.setImageName("Image Name");
        post1.setPostId(123);
        post1.setTitle("Dr");
        post1.setUser(user2);
        Optional<Post> ofResult = Optional.of(post1);
        when(postRepo.findById((Integer) any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setAbout("About");
        user3.setComments(new ArrayList<>());
        user3.setEmail("jane.doe@example.org");
        user3.setName("Name");
        user3.setPassword("iloveyou");
        user3.setPosts(new ArrayList<>());
        user3.setRoles(new HashSet<>());
        user3.setUserId(123);
        Optional<User> ofResult1 = Optional.of(user3);
        when(userRepo.findById((Integer) any())).thenReturn(ofResult1);

        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Not all who wander are lost");
        commentDto.setId(1);
        assertThrows(ResourceNotFoundException.class, () -> commentServiceImpl.createComment(commentDto, 123, 123));
        verify(commentRepo).save((Comment) any());
        verify(modelMapper).map((Object) any(), (Class<Comment>) any());
        verify(postRepo).findById((Integer) any());
        verify(userRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CommentServiceImpl#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment() {
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

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Comment comment = new Comment();
        comment.setContent("Not all who wander are lost");
        comment.setId(1);
        comment.setPost(post);
        comment.setUser(user1);
        Optional<Comment> ofResult = Optional.of(comment);
        doNothing().when(commentRepo).delete((Comment) any());
        when(commentRepo.findById((Integer) any())).thenReturn(ofResult);
        commentServiceImpl.deleteComment(123);
        verify(commentRepo).findById((Integer) any());
        verify(commentRepo).delete((Comment) any());
    }

    /**
     * Method under test: {@link CommentServiceImpl#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment2() {
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

        User user1 = new User();
        user1.setAbout("About");
        user1.setComments(new ArrayList<>());
        user1.setEmail("jane.doe@example.org");
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setPosts(new ArrayList<>());
        user1.setRoles(new HashSet<>());
        user1.setUserId(123);

        Comment comment = new Comment();
        comment.setContent("Not all who wander are lost");
        comment.setId(1);
        comment.setPost(post);
        comment.setUser(user1);
        Optional<Comment> ofResult = Optional.of(comment);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42)).when(commentRepo)
                .delete((Comment) any());
        when(commentRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> commentServiceImpl.deleteComment(123));
        verify(commentRepo).findById((Integer) any());
        verify(commentRepo).delete((Comment) any());
    }

    /**
     * Method under test: {@link CommentServiceImpl#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment3() {
        doNothing().when(commentRepo).delete((Comment) any());
        when(commentRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> commentServiceImpl.deleteComment(123));
        verify(commentRepo).findById((Integer) any());
    }
}

