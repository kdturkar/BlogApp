package com.App.BlogApplication.Security;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.Entities.User;
import io.jsonwebtoken.Claims;

import java.util.ArrayList;
import java.util.function.Function;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtTokenHelper.class})
@ExtendWith(SpringExtension.class)
class JwtTokenHelperTest {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    /**
     * Method under test: {@link JwtTokenHelper#getUserNameFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetUserNameFromToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: JWT strings must contain exactly 2 period characters. Found: 0
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:235)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:481)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:541)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getAllClaimsFromToken(JwtTokenHelper.java:40)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getClaimFromToken(JwtTokenHelper.java:34)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getUserNameFromToken(JwtTokenHelper.java:25)
        //   See https://diff.blue/R013 to resolve this issue.

        jwtTokenHelper.getUserNameFromToken("ABC123");
    }

    /**
     * Method under test: {@link JwtTokenHelper#getExpirationDateFromToken(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetExpirationDateFromToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: JWT strings must contain exactly 2 period characters. Found: 0
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:235)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:481)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:541)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getAllClaimsFromToken(JwtTokenHelper.java:40)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getClaimFromToken(JwtTokenHelper.java:34)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getExpirationDateFromToken(JwtTokenHelper.java:30)
        //   See https://diff.blue/R013 to resolve this issue.

        jwtTokenHelper.getExpirationDateFromToken("ABC123");
    }

    /**
     * Method under test: {@link JwtTokenHelper#getClaimFromToken(String, Function)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGetClaimFromToken() {
        // TODO: Complete this test.
        //   Reason: R005 Unable to load class.
        //   Class: reactor.netty.http.server.HttpServer
        //   Please check that the class is available on your test runtime classpath.
        //   See https://diff.blue/R005 to resolve this issue.

        // Arrange
        // TODO: Populate arranged inputs
        String token = "";
        Function<Claims, Object> claimsResolver = null;

        // Act
        Object actualClaimFromToken = this.jwtTokenHelper.getClaimFromToken(token, claimsResolver);

        // Assert
        // TODO: Add assertions on result
    }

    /**
     * Method under test: {@link JwtTokenHelper#generateToken(UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests which were time-sensitive.
        //   The assertions no longer passed when run at an alternate date, time and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   Please see https://diff.blue/R031

        jwtTokenHelper.generateToken(new User());
    }

    /**
     * Method under test: {@link JwtTokenHelper#generateToken(UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken2() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests which were time-sensitive.
        //   The assertions no longer passed when run at an alternate date, time and
        //   timezone. Try refactoring the method to take a java.time.Clock instance so
        //   that the time can be parameterized during testing.
        //   Please see https://diff.blue/R031

        jwtTokenHelper.generateToken(
                new org.springframework.security.core.userdetails.User("janedoe", "iloveyou", new ArrayList<>()));
    }

    /**
     * Method under test: {@link JwtTokenHelper#generateToken(UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testGenerateToken3() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.NullPointerException: Cannot invoke "org.springframework.security.core.userdetails.UserDetails.getUsername()" because "userDetails" is null
        //       at com.App.BlogApplication.Security.JwtTokenHelper.generateToken(JwtTokenHelper.java:58)
        //   See https://diff.blue/R013 to resolve this issue.

        jwtTokenHelper.generateToken(null);
    }

    /**
     * Method under test: {@link JwtTokenHelper#generateToken(UserDetails)}
     */
    @Test
    void testGenerateToken4() {
        User user = mock(User.class);
        when(user.getUsername()).thenReturn("janedoe");
        jwtTokenHelper.generateToken(user);
        verify(user).getUsername();
    }

    /**
     * Method under test: {@link JwtTokenHelper#validateToken(String, UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testValidateToken() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: JWT strings must contain exactly 2 period characters. Found: 0
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:235)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:481)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:541)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getAllClaimsFromToken(JwtTokenHelper.java:40)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getClaimFromToken(JwtTokenHelper.java:34)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getUserNameFromToken(JwtTokenHelper.java:25)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.validateToken(JwtTokenHelper.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        jwtTokenHelper.validateToken("ABC123", new User());
    }

    /**
     * Method under test: {@link JwtTokenHelper#validateToken(String, UserDetails)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testValidateToken2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   io.jsonwebtoken.MalformedJwtException: JWT strings must contain exactly 2 period characters. Found: 0
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:235)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parse(DefaultJwtParser.java:481)
        //       at io.jsonwebtoken.impl.DefaultJwtParser.parseClaimsJws(DefaultJwtParser.java:541)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getAllClaimsFromToken(JwtTokenHelper.java:40)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getClaimFromToken(JwtTokenHelper.java:34)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.getUserNameFromToken(JwtTokenHelper.java:25)
        //       at com.App.BlogApplication.Security.JwtTokenHelper.validateToken(JwtTokenHelper.java:78)
        //   See https://diff.blue/R013 to resolve this issue.

        jwtTokenHelper.validateToken("ABC123", mock(User.class));
    }
}

