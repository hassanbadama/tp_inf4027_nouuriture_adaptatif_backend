package com.vente.systemeVentes.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vente.systemeVentes.model.History;
import com.vente.systemeVentes.model.Recipe;
import com.vente.systemeVentes.model.User;
import com.vente.systemeVentes.repository.HistoryRepository;
import com.vente.systemeVentes.repository.RecipeRepository;

@Service
public class HistoryService {

    private final HistoryRepository historyRepository;
    private final RecipeRepository recipeRepository;

    public HistoryService(HistoryRepository historyRepository, RecipeRepository recipeRepository) {
        this.historyRepository = historyRepository;
        this.recipeRepository = recipeRepository;
    }

    // Ajouter un repas à l'historique et calculer calories
    public History addHistory(User user, List<Long> recipeIds) {
        List<Recipe> recipes = recipeRepository.findAllById(recipeIds);
        double totalCalories = recipes.stream().mapToDouble(Recipe::getCalories).sum();

        History history = new History();
        history.setUser(user);
        history.setDate(LocalDate.now());
        history.setRecipes(recipes);
        history.setTotalCalories(totalCalories);

        return historyRepository.save(history);
    }

    // Obtenir l'historique d'un utilisateur
    public List<History> getHistoryByUser(User user) {
        return historyRepository.findByUserId(user.getId());
    }
}
