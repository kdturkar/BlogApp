package com.App.BlogApplication.DTO;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CategoryDtoTest {
    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link CategoryDto}
     *   <li>{@link CategoryDto#setCategoryDescription(String)}
     *   <li>{@link CategoryDto#setCategoryId(Integer)}
     *   <li>{@link CategoryDto#setCategoryTitle(String)}
     *   <li>{@link CategoryDto#getCategoryDescription()}
     *   <li>{@link CategoryDto#getCategoryId()}
     *   <li>{@link CategoryDto#getCategoryTitle()}
     * </ul>
     */
    @Test
    void testConstructor() {
        CategoryDto actualCategoryDto = new CategoryDto();
        actualCategoryDto.setCategoryDescription("Category Description");
        actualCategoryDto.setCategoryId(123);
        actualCategoryDto.setCategoryTitle("Dr");
        assertEquals("Category Description", actualCategoryDto.getCategoryDescription());
        assertEquals(123, actualCategoryDto.getCategoryId().intValue());
        assertEquals("Dr", actualCategoryDto.getCategoryTitle());
    }
}

