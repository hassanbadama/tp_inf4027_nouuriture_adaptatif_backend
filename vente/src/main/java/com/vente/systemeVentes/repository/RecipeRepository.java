package com.vente.systemeVentes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vente.systemeVentes.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findByTagsContaining(String tag);
}
