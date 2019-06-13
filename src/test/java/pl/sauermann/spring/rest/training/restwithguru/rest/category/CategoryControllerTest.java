package pl.sauermann.spring.rest.training.restwithguru.rest.category;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CategoryControllerTest {

    public static final String NAME = "First";
    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void testListCategories() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName(NAME);

        CategoryDTO category2 = new CategoryDTO();
        category2.setId(2L);
        category2.setName("Second");

        List<CategoryDTO> categories = Lists.newArrayList(category, category2);
        when(categoryService.findAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/api/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories",hasSize(2)));
    }

    @Test
    public void getCategoryByName() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(1L);
        category.setName(NAME);

        when(categoryService.getCategoryByName(NAME)).thenReturn(category);

        mockMvc.perform(get("/api/categories/First")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text",equalTo(NAME)));
    }
}