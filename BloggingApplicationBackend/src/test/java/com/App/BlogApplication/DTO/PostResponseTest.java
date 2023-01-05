package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class PostResponseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link PostResponse}
     *   <li>{@link PostResponse#setContent(List)}
     *   <li>{@link PostResponse#setLastPage(boolean)}
     *   <li>{@link PostResponse#setPageNumber(int)}
     *   <li>{@link PostResponse#setPageSize(int)}
     *   <li>{@link PostResponse#setTotalElements(long)}
     *   <li>{@link PostResponse#setTotalPages(int)}
     *   <li>{@link PostResponse#getContent()}
     *   <li>{@link PostResponse#getPageNumber()}
     *   <li>{@link PostResponse#getPageSize()}
     *   <li>{@link PostResponse#getTotalElements()}
     *   <li>{@link PostResponse#getTotalPages()}
     *   <li>{@link PostResponse#isLastPage()}
     * </ul>
     */
    @Test
    void testConstructor() {
        PostResponse actualPostResponse = new PostResponse();
        ArrayList<PostDto> postDtoList = new ArrayList<>();
        actualPostResponse.setContent(postDtoList);
        actualPostResponse.setLastPage(true);
        actualPostResponse.setPageNumber(10);
        actualPostResponse.setPageSize(3);
        actualPostResponse.setTotalElements(1L);
        actualPostResponse.setTotalPages(1);
        assertSame(postDtoList, actualPostResponse.getContent());
        assertEquals(10, actualPostResponse.getPageNumber());
        assertEquals(3, actualPostResponse.getPageSize());
        assertEquals(1L, actualPostResponse.getTotalElements());
        assertEquals(1, actualPostResponse.getTotalPages());
        assertTrue(actualPostResponse.isLastPage());
    }
}

