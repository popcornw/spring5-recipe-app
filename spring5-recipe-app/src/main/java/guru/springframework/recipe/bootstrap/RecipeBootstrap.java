package guru.springframework.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.Difficulty;
import guru.springframework.recipe.domain.Ingredient;
import guru.springframework.recipe.domain.Notes;
import guru.springframework.recipe.domain.Recipe;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategorieRepository;
import guru.springframework.recipe.repositories.RecipeRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;
@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {
	private final RecipeRepository recipeRepository;
	private final CategorieRepository categorieRepository;
	private final UnitOfMeasureRepository unitOfMeasureRepository;

	public RecipeBootstrap(RecipeRepository recipeRepository, CategorieRepository categorieRepository,
			UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categorieRepository = categorieRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}
	

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	recipeRepository.saveAll(getRecipies());
		
	}

	private List<Recipe> getRecipies() {
		List<Recipe> recipes = new ArrayList<>();

		Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("each");

		if (!eachUomOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("tablespoon");

		if (!tableSpoonUomOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("teaspoon");

		if (!teaSpoonUomOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("dash");

		if (!dashUomOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("each");

		if (!pintUomOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("cup");

		if (!cupUomOptional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		// getOptionals
		UnitOfMeasure eachUom = eachUomOptional.get();
		UnitOfMeasure tableUom = tableSpoonUomOptional.get();
		UnitOfMeasure teaUom = teaSpoonUomOptional.get();
		UnitOfMeasure dashUom = dashUomOptional.get();
		UnitOfMeasure pintUom = eachUomOptional.get();
		UnitOfMeasure cupUom = eachUomOptional.get();

		// getCategories

		Optional<Category> americanCategoryOtional = categorieRepository.findByDescription("american");

		if (!americanCategoryOtional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}
		Optional<Category> mexicanCategoryOtional = categorieRepository.findByDescription("mexican");

		if (!mexicanCategoryOtional.isPresent()) {
			throw new RuntimeException("expected UOM not found");
		}

		Category americanCategy = americanCategoryOtional.get();
		Category mexicanCategory = americanCategoryOtional.get();

		// yammy guac
		Recipe guacRecipe = new Recipe();
		guacRecipe.setCookTime(10);
		guacRecipe.setDescription("perfect Guacamole");
		guacRecipe.setDifficulty(Difficulty.EASY);
		guacRecipe.setPrepTime(0);
		guacRecipe.setDirections("1 Cut the avocado, remove flesh: Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl."
				+ "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\r\n" + 
				"\r\n" + 
				"3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\r\n" + 
				"\r\n" + 
				"Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\r\n" + 
				"\r\n" + 
				"Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\r\n" + 
				"\r\n" + 
				"Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving."
				+ "4 Serve: Serve immediately, or if making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.");
				
		Notes guacNotes = new Notes();

		guacNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\r\n" + 
				"\r\n" + 
				"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\r\n" + 
				"\r\n" + 
				"Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\r\n" + 
				"\r\n" + 
				"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");
guacNotes.setRecipe(guacRecipe);
		guacRecipe.setNotes(guacNotes);
		
		guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2),guacRecipe,eachUom));
		guacRecipe.getIngredients().add(new Ingredient("kocher salt", new BigDecimal(5),guacRecipe,teaUom));

		guacRecipe.getIngredients().add(new Ingredient("frech lime or lemon juice ", new BigDecimal(2),guacRecipe,eachUom));
		guacRecipe.getIngredients().add(new Ingredient("cilantro", new BigDecimal(5),guacRecipe,tableUom));

		guacRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2),guacRecipe,eachUom));
		guacRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2),guacRecipe,eachUom));
		
		guacRecipe.getCategories().add(americanCategy);
		guacRecipe.getCategories().add(mexicanCategory);
		
		// add to returnlist 
		recipes.add(guacRecipe);

		//yummy tacos
		
		// yammy guac
				Recipe tacosRecipe = new Recipe();
				tacosRecipe.setCookTime(9);
				tacosRecipe.setDescription("spice shicken grilled taco");
				tacosRecipe.setDifficulty(Difficulty.MODERATE);
				tacosRecipe.setPrepTime(20);
				tacosRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat."
						+ "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\r\n" + 
						"\r\n" + 
						"Set aside to marinate while the grill heats and you prepare the rest of the toppings."
						+ "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes."
						+ "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side."
						+ "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges."
						+ "");

				Notes tacoNotes = new Notes();

				tacoNotes.setRecipeNotes("We have a family motto and it is this: Everything goes better in a tortilla.\r\n" + 
						"\r\n" + 
						"Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\r\n" + 
						"\r\n" + 
						"Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\r\n" + 
						"\r\n" + 
						"\r\n" + 
						"First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\r\n" + 
						"\r\n" + 
						"Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!");
				tacoNotes.setRecipe(tacosRecipe);
				tacosRecipe.setNotes(tacoNotes);
				
				tacosRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2),tacosRecipe,eachUom));
				tacosRecipe.getIngredients().add(new Ingredient("kocher salt", new BigDecimal(5),tacosRecipe,teaUom));

				tacosRecipe.getIngredients().add(new Ingredient("frech lime or lemon juice ", new BigDecimal(2),tacosRecipe,eachUom));
				tacosRecipe.getIngredients().add(new Ingredient("cilantro", new BigDecimal(5),tacosRecipe,tableUom));

				tacosRecipe.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2),tacosRecipe,eachUom));
				tacosRecipe.getIngredients().add(new Ingredient("minced red onion or thinly sliced green onion", new BigDecimal(2),tacosRecipe,eachUom));
				
				tacosRecipe.getCategories().add(americanCategy);
				tacosRecipe.getCategories().add(mexicanCategory);
				
				// add to returnlist 
				recipes.add(tacosRecipe);

				return recipes;
		
	}


}
