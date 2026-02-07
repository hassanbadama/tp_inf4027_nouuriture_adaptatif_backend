package com.vente.systemeVentes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vente.systemeVentes.model.Preference;
import com.vente.systemeVentes.model.Recipe;
import com.vente.systemeVentes.model.User;
import com.vente.systemeVentes.repository.PreferenceRepository;
import com.vente.systemeVentes.repository.RecipeRepository;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final PreferenceRepository preferenceRepository;

    public RecipeService(RecipeRepository recipeRepository, PreferenceRepository preferenceRepository) {
        this.recipeRepository = recipeRepository;
        this.preferenceRepository = preferenceRepository;
    }

    // Recommandation avancée
    public List<Recipe> recommendRecipes(User user) {
        List<Recipe> filtered = recipeRepository.findAll()
            .stream()
            // Filtrer par régime
            .filter(r ->r.getTags().contains(user.getRegime()))
            // Filtrer par allergies
            .filter(r -> user.getAllergies() == null || user.getAllergies().stream().allMatch(a -> !r.getTags().contains(a)))
            // Filtrer calories selon objectif
            .filter(r -> {
                if(user.getObjectif().equals("perte de poids")) return r.getCalories() <= 500;
                if(user.getObjectif().equals("prise de masse")) return r.getCalories() >= 400;
                return true;
            })
            .collect(Collectors.toList());

        // Trier par score préférences
        List<Preference> prefs = preferenceRepository.findByUserId(user.getId());
        filtered.sort((r1,r2) -> {
            int s1 = prefs.stream().filter(p -> p.getRecipe().getId().equals(r1.getId())).mapToInt(Preference::getScore).sum();
            int s2 = prefs.stream().filter(p -> p.getRecipe().getId().equals(r2.getId())).mapToInt(Preference::getScore).sum();
            return Integer.compare(s2, s1); // décroissant
        });

        return filtered;
    }
}
