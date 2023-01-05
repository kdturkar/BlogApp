package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UserDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link UserDto}
     *   <li>{@link UserDto#setAbout(String)}
     *   <li>{@link UserDto#setEmail(String)}
     *   <li>{@link UserDto#setId(int)}
     *   <li>{@link UserDto#setName(String)}
     *   <li>{@link UserDto#setPassword(String)}
     *   <li>{@link UserDto#setRoles(Set)}
     *   <li>{@link UserDto#getAbout()}
     *   <li>{@link UserDto#getEmail()}
     *   <li>{@link UserDto#getId()}
     *   <li>{@link UserDto#getName()}
     *   <li>{@link UserDto#getPassword()}
     *   <li>{@link UserDto#getRoles()}
     * </ul>
     */
    @Test
    void testConstructor() {
        UserDto actualUserDto = new UserDto();
        actualUserDto.setAbout("About");
        actualUserDto.setEmail("jane.doe@example.org");
        actualUserDto.setId(1);
        actualUserDto.setName("Name");
        actualUserDto.setPassword("iloveyou");
        HashSet<RoleDto> roleDtoSet = new HashSet<>();
        actualUserDto.setRoles(roleDtoSet);
        assertEquals("About", actualUserDto.getAbout());
        assertEquals("jane.doe@example.org", actualUserDto.getEmail());
        assertEquals(1, actualUserDto.getId());
        assertEquals("Name", actualUserDto.getName());
        assertEquals("iloveyou", actualUserDto.getPassword());
        assertSame(roleDtoSet, actualUserDto.getRoles());
    }
}

