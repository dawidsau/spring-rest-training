package pl.sauermann.spring.rest.training.restwithguru.rest.category;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    public static final String NAME = "Dawid";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void shouldConvertCategoryToCategoryDTO() {
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertEquals(NAME, categoryDTO.getName());
        assertEquals(Long.valueOf(ID), categoryDTO.getId());

    }
}