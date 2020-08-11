package guru.springframework.recipe.service;

import java.util.Set;

import guru.springframework.recipe.domain.Recipe;

public interface RecipeService {
Set<Recipe> getRecipies();
}
