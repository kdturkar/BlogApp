package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class PostDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link PostDto}
     *   <li>{@link PostDto#setAddedDate(Date)}
     *   <li>{@link PostDto#setCategory(CategoryDto)}
     *   <li>{@link PostDto#setComments(Set)}
     *   <li>{@link PostDto#setContent(String)}
     *   <li>{@link PostDto#setImageName(String)}
     *   <li>{@link PostDto#setPostId(Integer)}
     *   <li>{@link PostDto#setTitle(String)}
     *   <li>{@link PostDto#setUser(UserDto)}
     *   <li>{@link PostDto#getAddedDate()}
     *   <li>{@link PostDto#getCategory()}
     *   <li>{@link PostDto#getComments()}
     *   <li>{@link PostDto#getContent()}
     *   <li>{@link PostDto#getImageName()}
     *   <li>{@link PostDto#getPostId()}
     *   <li>{@link PostDto#getTitle()}
     *   <li>{@link PostDto#getUser()}
     * </ul>
     */
    @Test
    void testConstructor() {
        PostDto actualPostDto = new PostDto();
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        actualPostDto.setAddedDate(fromResult);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        actualPostDto.setCategory(categoryDto);
        HashSet<CommentDto> commentDtoSet = new HashSet<>();
        actualPostDto.setComments(commentDtoSet);
        actualPostDto.setContent("Not all who wander are lost");
        actualPostDto.setImageName("Image Name");
        actualPostDto.setPostId(123);
        actualPostDto.setTitle("Dr");
        UserDto userDto = new UserDto();
        userDto.setAbout("About");
        userDto.setEmail("jane.doe@example.org");
        userDto.setId(1);
        userDto.setName("Name");
        userDto.setPassword("iloveyou");
        userDto.setRoles(new HashSet<>());
        actualPostDto.setUser(userDto);
        assertSame(fromResult, actualPostDto.getAddedDate());
        assertSame(categoryDto, actualPostDto.getCategory());
        assertSame(commentDtoSet, actualPostDto.getComments());
        assertEquals("Not all who wander are lost", actualPostDto.getContent());
        assertEquals("Image Name", actualPostDto.getImageName());
        assertEquals(123, actualPostDto.getPostId().intValue());
        assertEquals("Dr", actualPostDto.getTitle());
        assertSame(userDto, actualPostDto.getUser());
    }
}

