package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class JwtAuthRequestTest {
    /**
     * Method under test: {@link JwtAuthRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        assertFalse((new JwtAuthRequest()).canEqual("Other"));
    }

    /**
     * Method under test: {@link JwtAuthRequest#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword("iloveyou");
        jwtAuthRequest1.setUsername("janedoe");
        assertTrue(jwtAuthRequest.canEqual(jwtAuthRequest1));
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link JwtAuthRequest}
     *   <li>{@link JwtAuthRequest#setPassword(String)}
     *   <li>{@link JwtAuthRequest#setUsername(String)}
     *   <li>{@link JwtAuthRequest#toString()}
     *   <li>{@link JwtAuthRequest#getPassword()}
     *   <li>{@link JwtAuthRequest#getUsername()}
     * </ul>
     */
    @Test
    void testConstructor() {
        JwtAuthRequest actualJwtAuthRequest = new JwtAuthRequest();
        actualJwtAuthRequest.setPassword("iloveyou");
        actualJwtAuthRequest.setUsername("janedoe");
        String actualToStringResult = actualJwtAuthRequest.toString();
        assertEquals("iloveyou", actualJwtAuthRequest.getPassword());
        assertEquals("janedoe", actualJwtAuthRequest.getUsername());
        assertEquals("JwtAuthRequest(username=janedoe, password=iloveyou)", actualToStringResult);
    }

    /**
     * Method under test: {@link JwtAuthRequest#equals(Object)}
     */
    @Test
    void testEquals() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername("janedoe");
        assertNotEquals(jwtAuthRequest, null);
    }

    /**
     * Method under test: {@link JwtAuthRequest#equals(Object)}
     */
    @Test
    void testEquals2() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername("janedoe");
        assertNotEquals(jwtAuthRequest, "Different type to JwtAuthRequest");
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtAuthRequest#equals(Object)}
     *   <li>{@link JwtAuthRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEquals3() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername("janedoe");
        assertEquals(jwtAuthRequest, jwtAuthRequest);
        int expectedHashCodeResult = jwtAuthRequest.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthRequest.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtAuthRequest#equals(Object)}
     *   <li>{@link JwtAuthRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEquals4() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername("janedoe");

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword("iloveyou");
        jwtAuthRequest1.setUsername("janedoe");
        assertEquals(jwtAuthRequest, jwtAuthRequest1);
        int expectedHashCodeResult = jwtAuthRequest.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthRequest1.hashCode());
    }

    /**
     * Method under test: {@link JwtAuthRequest#equals(Object)}
     */
    @Test
    void testEquals5() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("janedoe");
        jwtAuthRequest.setUsername("janedoe");

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword("iloveyou");
        jwtAuthRequest1.setUsername("janedoe");
        assertNotEquals(jwtAuthRequest, jwtAuthRequest1);
    }

    /**
     * Method under test: {@link JwtAuthRequest#equals(Object)}
     */
    @Test
    void testEquals6() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword(null);
        jwtAuthRequest.setUsername("janedoe");

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword("iloveyou");
        jwtAuthRequest1.setUsername("janedoe");
        assertNotEquals(jwtAuthRequest, jwtAuthRequest1);
    }

    /**
     * Method under test: {@link JwtAuthRequest#equals(Object)}
     */
    @Test
    void testEquals7() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername("iloveyou");

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword("iloveyou");
        jwtAuthRequest1.setUsername("janedoe");
        assertNotEquals(jwtAuthRequest, jwtAuthRequest1);
    }

    /**
     * Method under test: {@link JwtAuthRequest#equals(Object)}
     */
    @Test
    void testEquals8() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername(null);

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword("iloveyou");
        jwtAuthRequest1.setUsername("janedoe");
        assertNotEquals(jwtAuthRequest, jwtAuthRequest1);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtAuthRequest#equals(Object)}
     *   <li>{@link JwtAuthRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEquals9() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword(null);
        jwtAuthRequest.setUsername("janedoe");

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword(null);
        jwtAuthRequest1.setUsername("janedoe");
        assertEquals(jwtAuthRequest, jwtAuthRequest1);
        int expectedHashCodeResult = jwtAuthRequest.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthRequest1.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link JwtAuthRequest#equals(Object)}
     *   <li>{@link JwtAuthRequest#hashCode()}
     * </ul>
     */
    @Test
    void testEquals10() {
        JwtAuthRequest jwtAuthRequest = new JwtAuthRequest();
        jwtAuthRequest.setPassword("iloveyou");
        jwtAuthRequest.setUsername(null);

        JwtAuthRequest jwtAuthRequest1 = new JwtAuthRequest();
        jwtAuthRequest1.setPassword("iloveyou");
        jwtAuthRequest1.setUsername(null);
        assertEquals(jwtAuthRequest, jwtAuthRequest1);
        int expectedHashCodeResult = jwtAuthRequest.hashCode();
        assertEquals(expectedHashCodeResult, jwtAuthRequest1.hashCode());
    }
}

