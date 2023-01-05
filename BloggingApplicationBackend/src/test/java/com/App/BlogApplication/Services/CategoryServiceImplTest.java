package com.App.BlogApplication.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.CategoryDto;
import com.App.BlogApplication.Entities.Category;
import com.App.BlogApplication.Entities.Post;
import com.App.BlogApplication.Exception.ResourceNotFoundException;
import com.App.BlogApplication.Repositories.CategoryRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepo categoryRepo;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @MockBean
    private ModelMapper modelMapper;

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryDto)}
     */
    @Test
    void testCreateCategory() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category);
        when(modelMapper.map((Object) any(), (Class<Object>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.createCategory(categoryDto));
        verify(modelMapper).map((Object) any(), (Class<Category>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#createCategory(CategoryDto)}
     */
    @Test
    void testCreateCategory2() {
        Category category = mock(Category.class);
        doNothing().when(category).setCategoryDescription((String) any());
        doNothing().when(category).setCategoryId((Integer) any());
        doNothing().when(category).setCategoryTitle((String) any());
        doNothing().when(category).setPosts((List<Post>) any());
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category);
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn(null);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        assertNull(categoryServiceImpl.createCategory(categoryDto));
        verify(categoryRepo).save((Category) any());
        verify(category).setCategoryDescription((String) any());
        verify(category).setCategoryId((Integer) any());
        verify(category).setCategoryTitle((String) any());
        verify(category).setPosts((List<Post>) any());
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<Category>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(CategoryDto, Integer)}
     */
    @Test
    void testUpdateCategory() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category1);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any())).thenReturn(categoryDto);

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryDescription("Category Description");
        categoryDto1.setCategoryId(123);
        categoryDto1.setCategoryTitle("Dr");
        assertSame(categoryDto, categoryServiceImpl.updateCategory(categoryDto1, 123));
        verify(categoryRepo).save((Category) any());
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(CategoryDto, Integer)}
     */
    @Test
    void testUpdateCategory2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category1);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.updateCategory(categoryDto, 123));
        verify(categoryRepo).save((Category) any());
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#updateCategory(CategoryDto, Integer)}
     */
    @Test
    void testUpdateCategory3() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(categoryRepo.save((Category) any())).thenReturn(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any())).thenReturn(categoryDto);

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryDescription("Category Description");
        categoryDto1.setCategoryId(123);
        categoryDto1.setCategoryTitle("Dr");
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.updateCategory(categoryDto1, 123));
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        ArrayList<Post> postList = new ArrayList<>();
        category.setPosts(postList);
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(categoryRepo).delete((Category) any());
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        categoryServiceImpl.deleteCategory(123);
        verify(categoryRepo).findById((Integer) any());
        verify(categoryRepo).delete((Category) any());
        assertEquals(postList, categoryServiceImpl.getAllCategory());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        doThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42)).when(categoryRepo)
                .delete((Category) any());
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.deleteCategory(123));
        verify(categoryRepo).findById((Integer) any());
        verify(categoryRepo).delete((Category) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory3() {
        doNothing().when(categoryRepo).delete((Category) any());
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.deleteCategory(123));
        verify(categoryRepo).findById((Integer) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategory(Integer)}
     */
    @Test
    void testGetCategory() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any())).thenReturn(categoryDto);
        assertSame(categoryDto, categoryServiceImpl.getCategory(123));
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategory(Integer)}
     */
    @Test
    void testGetCategory2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        Optional<Category> ofResult = Optional.of(category);
        when(categoryRepo.findById((Integer) any())).thenReturn(ofResult);
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.getCategory(123));
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getCategory(Integer)}
     */
    @Test
    void testGetCategory3() {
        when(categoryRepo.findById((Integer) any())).thenReturn(Optional.empty());

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(modelMapper.map((Object) any(), (Class<Object>) any())).thenReturn("Map");
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any())).thenReturn(categoryDto);
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.getCategory(123));
        verify(categoryRepo).findById((Integer) any());
        verify(modelMapper).map((Object) any(), (Class<Object>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getAllCategory()}
     */
    @Test
    void testGetAllCategory() {
        when(categoryRepo.findAll()).thenReturn(new ArrayList<>());
        assertTrue(categoryServiceImpl.getAllCategory().isEmpty());
        verify(categoryRepo).findAll();
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getAllCategory()}
     */
    @Test
    void testGetAllCategory2() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepo.findAll()).thenReturn(categoryList);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any())).thenReturn(categoryDto);
        assertEquals(1, categoryServiceImpl.getAllCategory().size());
        verify(categoryRepo).findAll();
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getAllCategory()}
     */
    @Test
    void testGetAllCategory3() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        Category category1 = new Category();
        category1.setCategoryDescription("Category Description");
        category1.setCategoryId(123);
        category1.setCategoryTitle("Dr");
        category1.setPosts(new ArrayList<>());

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category1);
        categoryList.add(category);
        when(categoryRepo.findAll()).thenReturn(categoryList);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any())).thenReturn(categoryDto);
        assertEquals(2, categoryServiceImpl.getAllCategory().size());
        verify(categoryRepo).findAll();
        verify(modelMapper, atLeast(1)).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#getAllCategory()}
     */
    @Test
    void testGetAllCategory4() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(categoryRepo.findAll()).thenReturn(categoryList);
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.getAllCategory());
        verify(categoryRepo).findAll();
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#catEntToDto(Category)}
     */
    @Test
    void testCatEntToDto() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any())).thenReturn(categoryDto);

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        assertSame(categoryDto, categoryServiceImpl.catEntToDto(category));
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#catEntToDto(Category)}
     */
    @Test
    void testCatEntToDto2() {
        when(modelMapper.map((Object) any(), (Class<CategoryDto>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.catEntToDto(category));
        verify(modelMapper).map((Object) any(), (Class<CategoryDto>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#catDtoToEnt(CategoryDto)}
     */
    @Test
    void testCatDtoToEnt() {
        Category category = new Category();
        category.setCategoryDescription("Category Description");
        category.setCategoryId(123);
        category.setCategoryTitle("Dr");
        category.setPosts(new ArrayList<>());
        when(modelMapper.map((Object) any(), (Class<Category>) any())).thenReturn(category);

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        assertSame(category, categoryServiceImpl.catDtoToEnt(categoryDto));
        verify(modelMapper).map((Object) any(), (Class<Category>) any());
    }

    /**
     * Method under test: {@link CategoryServiceImpl#catDtoToEnt(CategoryDto)}
     */
    @Test
    void testCatDtoToEnt2() {
        when(modelMapper.map((Object) any(), (Class<Category>) any()))
                .thenThrow(new ResourceNotFoundException("Resource Name", "Field Name", 42));

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        assertThrows(ResourceNotFoundException.class, () -> categoryServiceImpl.catDtoToEnt(categoryDto));
        verify(modelMapper).map((Object) any(), (Class<Category>) any());
    }
}

