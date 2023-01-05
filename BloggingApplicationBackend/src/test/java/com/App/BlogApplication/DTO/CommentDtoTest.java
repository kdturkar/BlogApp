package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CommentDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link CommentDto}
     *   <li>{@link CommentDto#setContent(String)}
     *   <li>{@link CommentDto#setId(int)}
     *   <li>{@link CommentDto#getContent()}
     *   <li>{@link CommentDto#getId()}
     * </ul>
     */
    @Test
    void testConstructor() {
        CommentDto actualCommentDto = new CommentDto();
        actualCommentDto.setContent("Not all who wander are lost");
        actualCommentDto.setId(1);
        assertEquals("Not all who wander are lost", actualCommentDto.getContent());
        assertEquals(1, actualCommentDto.getId());
    }
}

