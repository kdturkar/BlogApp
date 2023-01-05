package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class RoleDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link RoleDto}
     *   <li>{@link RoleDto#setId(int)}
     *   <li>{@link RoleDto#setName(String)}
     *   <li>{@link RoleDto#getId()}
     *   <li>{@link RoleDto#getName()}
     * </ul>
     */
    @Test
    void testConstructor() {
        RoleDto actualRoleDto = new RoleDto();
        actualRoleDto.setId(1);
        actualRoleDto.setName("Name");
        assertEquals(1, actualRoleDto.getId());
        assertEquals("Name", actualRoleDto.getName());
    }
}

