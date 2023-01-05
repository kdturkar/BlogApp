package com.App.BlogApplication.Controllers;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.App.BlogApplication.DTO.CategoryDto;
import com.App.BlogApplication.Services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CategoryController.class})
@ExtendWith(SpringExtension.class)
class CategoryControllerTest {
    @Autowired
    private CategoryController categoryController;

    @MockBean
    private CategoryService categoryService;

    /**
     * Method under test: {@link CategoryController#createCategory(CategoryDto)}
     */
    @Test
    void testCreateCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link CategoryController#createCategory(CategoryDto)}
     */
    @Test
    void testCreateCategory2() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(categoryService.createCategory((CategoryDto) any())).thenReturn(categoryDto);

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryDescription("Category Description");
        categoryDto1.setCategoryId(123);
        categoryDto1.setCategoryTitle("DrDr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"categoryId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description\"}"));
    }

    /**
     * Method under test: {@link CategoryController#updateCategory(CategoryDto, Integer)}
     */
    @Test
    void testUpdateCategory2() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(categoryService.updateCategory((CategoryDto) any(), (Integer) any())).thenReturn(categoryDto);

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto1.setCategoryDescription("Category Description");
        categoryDto1.setCategoryId(123);
        categoryDto1.setCategoryTitle("DrDr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/categories/{catId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"categoryId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description\"}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).deleteCategory((Integer) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/categories/{catId}", 123);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Category is deleted\",\"status\":true}"));
    }

    /**
     * Method under test: {@link CategoryController#deleteCategory(Integer)}
     */
    @Test
    void testDeleteCategory2() throws Exception {
        doNothing().when(categoryService).deleteCategory((Integer) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/categories/{catId}", 123);
        deleteResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(deleteResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Category is deleted\",\"status\":true}"));
    }

    /**
     * Method under test: {@link CategoryController#getCategory(Integer)}
     */
    @Test
    void testGetCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        when(categoryService.getCategory((Integer) any())).thenReturn(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/categories/{catId}", 123);
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"categoryId\":123,\"categoryTitle\":\"Dr\",\"categoryDescription\":\"Category Description\"}"));
    }

    /**
     * Method under test: {@link CategoryController#getAllCategory()}
     */
    @Test
    void testGetAllCategory() throws Exception {
        when(categoryService.getAllCategory()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/categories/");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#getAllCategory()}
     */
    @Test
    void testGetAllCategory2() throws Exception {
        when(categoryService.getAllCategory()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/categories/");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CategoryController#updateCategory(CategoryDto, Integer)}
     */
    @Test
    void testUpdateCategory() throws Exception {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryDescription("Category Description");
        categoryDto.setCategoryId(123);
        categoryDto.setCategoryTitle("Dr");
        String content = (new ObjectMapper()).writeValueAsString(categoryDto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/categories/{catId}", 123)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(categoryController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }
}

