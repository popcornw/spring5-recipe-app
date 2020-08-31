package guru.springframework.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import javassist.bytecode.ByteArray;
import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;
	
	
	

	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}




	@Override
	public void saveImageFile(Long recipeId, MultipartFile file) {
		try {
		Recipe recipe = recipeRepository.findById(recipeId).get();
	
			Byte[] byteobjects = new Byte[file.getBytes().length];
			int i=0;
			for(Byte b:file.getBytes()) {
				
				byteobjects[i++]=b;
				
				
			}
			recipe.setImage(byteobjects);
			recipeRepository.save(recipe);
			
		}
		
		
		catch (IOException e) {
		log.error("error occured",e);
			e.printStackTrace();
		}
		
	}

}
