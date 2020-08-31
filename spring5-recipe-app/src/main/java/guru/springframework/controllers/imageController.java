package guru.springframework.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.ImageService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class imageController {
	private final RecipeService recipeService;
	private final ImageService imageService;

	public imageController(RecipeService recipeService, ImageService imageService) {
		super();
		this.recipeService = recipeService;
		this.imageService = imageService;
	}

	@GetMapping("recipe/{id}/image")
	public String showUploadForm(@PathVariable String id, Model model) {
		model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
		return ("recipe/imageuploadform");
	}

	@PostMapping("recipe/{id}/image")
	public String handelImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file) {

		imageService.saveImageFile(Long.valueOf(id), file);

		return ("redirect:/recipe/" + id + "/show");
	}

	@GetMapping("recipe/{id}/recipeimage")
	public void renderImageFromDb(@PathVariable String id, HttpServletResponse response) throws IOException {
		RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(id));
		if (recipeCommand.getImage() != null) {
			byte[] byteArray = new byte[recipeCommand.getImage().length];

			int i = 0;
			for (Byte wrappedByte : recipeCommand.getImage()) {
				byteArray[i++] = wrappedByte;

			}
			response.setContentType("image/jpeg");
			InputStream is = new ByteArrayInputStream(byteArray);
			IOUtils.copy(is, response.getOutputStream());
		}

	}

}
