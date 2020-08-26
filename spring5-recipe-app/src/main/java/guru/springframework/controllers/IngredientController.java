package guru.springframework.controllers;

import java.lang.ProcessBuilder.Redirect;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import guru.springframework.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class IngredientController {
	private final IngredientService ingredientService;
	private final RecipeService recipeService;
	private final UnitOfMeasureService unitOfMeasureService;

	public IngredientController(IngredientService ingredientService, RecipeService recipeService,
			UnitOfMeasureService unitOfMeasureService) {
		super();
		this.ingredientService = ingredientService;
		this.recipeService = recipeService;
		this.unitOfMeasureService = unitOfMeasureService;
	}

	@RequestMapping("/recipe/{recipeId}/ingredients")
	public String listIngredients(@PathVariable String recipeId, Model model) {
		log.debug("getting ingredient list for recipe id:" + recipeId);
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
		return "recipe/ingredient/list";

	}

	@GetMapping
	@RequestMapping("recipe/{recipeid}/ingredient/{id}/show")
	public String showRecipeIngredient(@PathVariable String recipeid, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findRecipeIdAndIngredientId(Long.valueOf(recipeid), Long.valueOf(id)));
		return "recipe/ingredient/show";

	}

	@GetMapping
	@RequestMapping("recipe/{recipeid}/ingredient/{id}/update")
	public String updateRecipeIngredient(@PathVariable String recipeid, @PathVariable String id, Model model) {
		model.addAttribute("ingredient",
				ingredientService.findRecipeIdAndIngredientId(Long.valueOf(recipeid), Long.valueOf(id)));
		model.addAttribute("uomList", unitOfMeasureService.listAllUoms());
		return ("recipe/ingredient/ingredientform");

	}
	
    @PostMapping("recipe/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command){
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("saved receipe id:" + savedCommand.getRecipeId());
        log.debug("saved ingredient id:" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";		
	}

}
