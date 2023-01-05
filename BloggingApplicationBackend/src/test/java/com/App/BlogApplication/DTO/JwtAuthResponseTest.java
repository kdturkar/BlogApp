package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JwtAuthResponseTest {
    /**
     * Method under test: {@link JwtAuthResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new JwtAuthResponse()).canEqual("Other"));
    }

    /**
     * Method under test: {@link JwtAuthResponse#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();

        JwtAuthResponse jwtAuthResponse1 = new JwtAuthResponse();
        jwtAuthResponse1.setToken("ABC123");
        assertTrue(jwtAuthResponse.canEqual(jwtAuthResponse1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link JwtAuthResponse}
     *   <li>{@link JwtAuthResponse#setToken(String)}
     *   <li>{@link JwtAuthResponse#toString()}
     *   <li>{@link JwtAuthResponse#getToken()}
     * </ul>
     */
    @Test
    void testConstructor() {
        JwtAuthResponse actualJwtAuthResponse = new JwtAuthResponse();
        actualJwtAuthResponse.setToken("ABC123");
        String actualToStringResult = actualJwtAuthResponse.toString();
        assertEquals("ABC123", actualJwtAuthResponse.getToken());
        assertEquals("JwtAuthResponse(token=ABC123)", actualToStringResult);
    }

    /**
     * Method under test: {@link JwtAuthResponse#equals(Object)}
     */
    @Test
    void testEquals() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken("ABC123");
        assertNotEquals(jwtAuthResponse, null);
    }

    /**
     * Method under test: {@link JwtAuthResponse#equals(Object)}
     */
    @Test
    void testEquals2() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken("ABC123");
        assertNotEquals(jwtAuthResponse, "Different type to JwtAuthResponse");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtAuthResponse#equals(Object)}
     *   <li>{@link JwtAuthResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken("ABC123");
        assertEquals(jwtAuthResponse, jwtAuthResponse);
        int expectedHashCodeResult = jwtAuthResponse.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthResponse.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtAuthResponse#equals(Object)}
     *   <li>{@link JwtAuthResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken("ABC123");

        JwtAuthResponse jwtAuthResponse1 = new JwtAuthResponse();
        jwtAuthResponse1.setToken("ABC123");
        assertEquals(jwtAuthResponse, jwtAuthResponse1);
        int expectedHashCodeResult = jwtAuthResponse.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthResponse1.hashCode());
    }

    /**
     * Method under test: {@link JwtAuthResponse#equals(Object)}
     */
    @Test
    void testEquals5() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken("Token");

        JwtAuthResponse jwtAuthResponse1 = new JwtAuthResponse();
        jwtAuthResponse1.setToken("ABC123");
        assertNotEquals(jwtAuthResponse, jwtAuthResponse1);
    }

    /**
     * Method under test: {@link JwtAuthResponse#equals(Object)}
     */
    @Test
    void testEquals6() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(null);

        JwtAuthResponse jwtAuthResponse1 = new JwtAuthResponse();
        jwtAuthResponse1.setToken("ABC123");
        assertNotEquals(jwtAuthResponse, jwtAuthResponse1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtAuthResponse#equals(Object)}
     *   <li>{@link JwtAuthResponse#hashCode()}
     * </ul>
     */
    @Test
    void testEquals7() {
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(null);

        JwtAuthResponse jwtAuthResponse1 = new JwtAuthResponse();
        jwtAuthResponse1.setToken(null);
        assertEquals(jwtAuthResponse, jwtAuthResponse1);
        int expectedHashCodeResult = jwtAuthResponse.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthResponse1.hashCode());
    }
}

