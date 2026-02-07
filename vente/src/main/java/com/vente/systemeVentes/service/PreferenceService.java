package com.vente.systemeVentes.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.vente.systemeVentes.model.Preference;
import com.vente.systemeVentes.model.Recipe;
import com.vente.systemeVentes.model.User;
import com.vente.systemeVentes.repository.PreferenceRepository;

@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;

    public PreferenceService(PreferenceRepository preferenceRepository) {
        this.preferenceRepository = preferenceRepository;
    }

    // Incrémenter le score d'une recette choisie par l'utilisateur
    public void incrementPreference(User user, Recipe recipe) {
        List<Preference> prefs = preferenceRepository.findByUserId(user.getId());
        Preference pref = prefs.stream()
            .filter(p -> p.getRecipe().getId().equals(recipe.getId()))
            .findFirst()
            .orElseGet(() -> {
                Preference newPref = new Preference();
                newPref.setUser(user);
                newPref.setRecipe(recipe);
                newPref.setScore(0);
                return newPref;
            });

        pref.setScore(pref.getScore() + 1);
        preferenceRepository.save(pref);
    }
}
