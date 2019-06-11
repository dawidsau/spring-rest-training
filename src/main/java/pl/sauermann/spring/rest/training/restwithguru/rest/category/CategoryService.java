package pl.sauermann.spring.rest.training.restwithguru.rest.category;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> findAllCategories();

    CategoryDTO getCategoryByName(String name);
}
