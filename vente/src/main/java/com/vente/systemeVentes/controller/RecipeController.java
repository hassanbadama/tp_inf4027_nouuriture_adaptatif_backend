package com.vente.systemeVentes.controller;



import java.io.IOException;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.vente.systemeVentes.model.Recipe;
import com.vente.systemeVentes.repository.RecipeRepository;

import ch.qos.logback.core.model.Model;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins="http://localhost:3000")
//@CrossOrigin(origins="http://localhost:5174")
public class RecipeController {

    private final RecipeRepository recipeRepository;

    public RecipeController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    // ➕ Ajouter une recette
    // @PostMapping
    // public Recipe addRecipe(@RequestBody Recipe recipe) {
    //     return recipeRepository.save(recipe);
    // }

    //  Lister toutes les recettes
    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }


    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> creer(
            @RequestParam("nom") String nom,
            @RequestParam("calories") Double calories,
            @RequestParam("ingredients") List<String> ingredients,
            @RequestParam("tags") List<String> tags,
        
            @RequestParam("file") MultipartFile file
        ) throws IOException {
            Recipe recipe = new Recipe();
            recipe.setNom(nom);
            recipe.setCalories(calories);
            recipe.setIngredients(ingredients);
            recipe.setTags(tags);
            recipe.setImage(file.getBytes());
             recipeRepository.save(recipe);
        return ResponseEntity.ok("Ajout réussi");
    }


    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getImage(Model model, @PathVariable Long id) {
        Recipe recipe = recipeRepository.findById(id).orElse(null);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(recipe.getImage());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
        
    }

    // public void delete(Long id) {
    //     recipeRepository.deleteById(id);
    // }


}
