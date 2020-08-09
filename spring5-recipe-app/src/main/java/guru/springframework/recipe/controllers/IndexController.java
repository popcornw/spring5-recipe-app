package guru.springframework.recipe.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.recipe.domain.Category;
import guru.springframework.recipe.domain.UnitOfMeasure;
import guru.springframework.recipe.repositories.CategorieRepository;
import guru.springframework.recipe.repositories.UnitOfMeasureRepository;

@Controller
public class IndexController {
	private CategorieRepository categorieRepository;
	private UnitOfMeasureRepository unitOfMeasureRepository;
	
	

	public IndexController(CategorieRepository categorieRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
		super();
		this.categorieRepository = categorieRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}



	@RequestMapping({"","/","/index"})
	public String getIndexPage() {
		Optional<Category> categyOptional= categorieRepository.findByDescription("American");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("teaspoon");
		
		System.out.println("cat id is " + categyOptional.get().getId());
		System.out.println("Unit Of Measure id is " + unitOfMeasureOptional.get().getId());
		return "index";
		  	
	}

}
