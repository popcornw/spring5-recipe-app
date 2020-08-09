package guru.springframework.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import guru.springframework.recipe.domain.Category;

public interface CategorieRepository extends CrudRepository<Category,Long> {
Optional<Category> findByDescription(String description);
}
