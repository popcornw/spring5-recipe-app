package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

public interface IngredientService {
IngredientCommand findRecipeIdAndIngredientId(Long recipeId, Long ingredientId);
IngredientCommand saveIngredientCommand(IngredientCommand command);
void deleteById(Long recipeId, Long ingredientId);
}
