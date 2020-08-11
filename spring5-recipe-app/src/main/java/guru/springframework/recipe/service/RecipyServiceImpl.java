package guru.springframework.recipe.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.repositories.RecipeRepository;
@Service
public class RecipyServiceImpl implements RecipeService {
private final RecipeRepository RecipeRepository;

	
	
	
	
	public RecipyServiceImpl(guru.springframework.recipe.repositories.RecipeRepository recipeRepository) {
	super();
	RecipeRepository = recipeRepository;
}





	@Override
	public Set<Recipe> getRecipies() {
		Set<Recipe>recipeSet=new HashSet<>();
		RecipeRepository.findAll().iterator().forEachRemaining(recipeSet::add);
		return recipeSet ;
	}

}
