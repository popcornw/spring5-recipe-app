package guru.springframework.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategorieRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
import guru.springframework.recipe.service.RecipeService;

@Controller
public class IndexController {
private final RecipeService RecipeService; 


	public IndexController(guru.springframework.recipe.service.RecipeService recipeService) {
	super();
	RecipeService = recipeService;
}


	@RequestMapping({"","/","/index"})
	public String getIndexPage(Model model) {
		model.addAttribute("recipes",RecipeService.getRecipies());
		return "index";
		  	
	}
 
}
