package com.App.BlogApplication.Controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.CommentDto;
import com.App.BlogApplication.Services.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@ContextConfiguration(classes = {CommentController.class})
@ExtendWith(SpringExtension.class)
class CommentControllerTest {
    @Autowired
    private CommentController commentController;

    @MockBean
    private CommentService commentService;

    /**
     * Method under test: {@link CommentController#createComment(CommentDto, Integer, Integer)}
     */
    @Test
    void testCreateComment() throws Exception {
        CommentDto commentDto = new CommentDto();
        commentDto.setContent("Not all who wander are lost");
        commentDto.setId(1);
        when(commentService.createComment((CommentDto) any(), (Integer) any(), (Integer) any())).thenReturn(commentDto);

        CommentDto commentDto1 = new CommentDto();
        commentDto1.setContent("Not all who wander are lost");
        commentDto1.setId(1);
        String content = (new ObjectMapper()).writeValueAsString(commentDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/user/{userId}/post/{postId}/comment", 123, 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"content\":\"Not all who wander are lost\"}"));
    }

    /**
     * Method under test: {@link CommentController#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment() throws Exception {
        doNothing().when(commentService).deleteComment((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/comment/{commentId}", 123);
        MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Comment deleted successfully!!\",\"status\":true}"));
    }

    /**
     * Method under test: {@link CommentController#deleteComment(Integer)}
     */
    @Test
    void testDeleteComment2() throws Exception {
        doNothing().when(commentService).deleteComment((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/comment/{commentId}", 123);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(commentController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"message\":\"Comment deleted successfully!!\",\"status\":true}"));
    }
}

