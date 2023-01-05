package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ApiResponseTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ApiResponse#ApiResponse()}
     *   <li>{@link ApiResponse#setMessage(String)}
     *   <li>{@link ApiResponse#setStatus(boolean)}
     *   <li>{@link ApiResponse#getMessage()}
     *   <li>{@link ApiResponse#isStatus()}
     * </ul>
     */
    @Test
    void testConstructor() {
        ApiResponse actualApiResponse = new ApiResponse();
        actualApiResponse.setMessage("Not all who wander are lost");
        actualApiResponse.setStatus(true);
        assertEquals("Not all who wander are lost", actualApiResponse.getMessage());
        assertTrue(actualApiResponse.isStatus());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link ApiResponse#ApiResponse(String, boolean)}
     *   <li>{@link ApiResponse#setMessage(String)}
     *   <li>{@link ApiResponse#setStatus(boolean)}
     *   <li>{@link ApiResponse#getMessage()}
     *   <li>{@link ApiResponse#isStatus()}
     * </ul>
     */
    @Test
    void testConstructor2() {
        ApiResponse actualApiResponse = new ApiResponse("Not all who wander are lost", true);
        actualApiResponse.setMessage("Not all who wander are lost");
        actualApiResponse.setStatus(true);
        assertEquals("Not all who wander are lost", actualApiResponse.getMessage());
        assertTrue(actualApiResponse.isStatus());
    }
}

