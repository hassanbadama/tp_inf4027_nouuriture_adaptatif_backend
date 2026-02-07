package com.vente.systemeVentes.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private double calories;

    @ElementCollection
    private List<String> ingredients;

    @ElementCollection
    private List<String> tags;
    @Lob
    private byte[] image;

    public Recipe() {}

    public Recipe(String nom, double calories,
                  List<String> ingredients, List<String> tags) {
        this.nom = nom;
        this.calories = calories;
        this.ingredients = ingredients;
        this.tags = tags;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    // getters et setters

    
}

// @Entity
// public class Recipe {
//     @Id
//     @GeneratedValue(strategy=GenerationType.IDENTITY)
//     private Long id;

//     private String nom;
//     private double calories;

//     @ElementCollection
//     private List<String> ingredients;

//     @ElementCollection
//     private List<String> tags;

//     public Long getId() {
//         return id;
//     }

//     public void setId(Long id) {
//         this.id = id;
//     }

//     public String getNom() {
//         return nom;
//     }

//     public void setNom(String nom) {
//         this.nom = nom;
//     }

//     public double getCalories() {
//         return calories;
//     }

//     public void setCalories(double calories) {
//         this.calories = calories;
//     }

//     public List<String> getIngredients() {
//         return ingredients;
//     }

//     public void setIngredients(List<String> ingredients) {
//         this.ingredients = ingredients;
//     }

//     public List<String> getTags() {
//         return tags;
//     }

//     public void setTags(List<String> tags) {
//         this.tags = tags;
//     }

//     // Getters et Setters

    
// }

