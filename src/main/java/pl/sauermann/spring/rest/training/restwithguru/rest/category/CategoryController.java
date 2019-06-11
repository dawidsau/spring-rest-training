package pl.sauermann.spring.rest.training.restwithguru.rest.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO showAllCategories() {
        return new CategoryListDTO(categoryService.findAllCategories());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO showCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }


}
