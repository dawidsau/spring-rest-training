package pl.sauermann.spring.rest.training.restwithguru.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sauermann.spring.rest.training.restwithguru.rest.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
