package com.vente.systemeVentes.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vente.systemeVentes.dto.Login_user;
import com.vente.systemeVentes.model.History;
import com.vente.systemeVentes.model.Recipe;
import com.vente.systemeVentes.model.User;
import com.vente.systemeVentes.repository.RecipeRepository;
import com.vente.systemeVentes.repository.UserRepository;
import com.vente.systemeVentes.service.Connecte_user;
import com.vente.systemeVentes.service.HistoryService;
import com.vente.systemeVentes.service.PreferenceService;
import com.vente.systemeVentes.service.RecipeService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:3000")
//@CrossOrigin(origins="http://localhost:5174")
public class UserController {

    private final UserRepository userRepository;
    private final RecipeService recipeService;
    private final HistoryService historyService;
    private final RecipeRepository recipeRepository;
    private final PreferenceService preferenceService;
    private  Connecte_user connecter;
    public static Long idUser;

    public UserController(UserRepository userRepository, RecipeService recipeService,
                          HistoryService historyService, RecipeRepository recipeRepository,
                          PreferenceService preferenceService, Connecte_user connecter) {
        this.userRepository = userRepository;
        this.recipeService = recipeService;
        this.historyService = historyService;
        this.recipeRepository = recipeRepository;
        this.preferenceService = preferenceService;
        this.connecter = connecter;
    }

    // Créer utilisateur
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        User user2 = userRepository.save(user);
        idUser = user2.getId();
        return user2;
    }

    // Recommandations avancées
    @GetMapping("/{id}/recommend")
    public List<Recipe> recommend(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return List.of();
        System.out.print(user.getRegime()+" ----  valeur recommend ----"+user.getNom());
        return recipeService.recommendRecipes(user);
    }

    // Ajouter un repas à l'historique
    @PostMapping("/{id}/history")
    public History addHistory(@PathVariable Long id, @RequestBody List<Long> recipeIds){
        idUser = id;
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return null;
        System.out.print(idUser+"  -- valeur recu -- "+user.getNom());
        // Ajouter repas
        History history = historyService.addHistory(user, recipeIds);
        // Mettre à jour préférences
        recipeIds.forEach(rid -> {
            Recipe recipe = recipeRepository.findById(rid).orElse(null);
            if(recipe != null) preferenceService.incrementPreference(user, recipe);
        });
        return history;
    }

    // Obtenir l'historique
    @GetMapping("/{id}/history")
    public List<History> getHistory(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);
        if(user == null) return List.of();
        System.out.print("---- valeur history ---"+user.getNom());
        return historyService.getHistoryByUser(user);
    }
    @GetMapping("/history")
    public List<History> affichetHistory(){
        User user = userRepository.findById(idUser).orElse(null);
        if(user == null) return List.of();
        System.out.print("---- valeur history complet ---"+user.getNom());
        return historyService.getHistoryByUser(user);
    }

    @GetMapping
    public List<User> all() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.print("--- SUPPRIMER --- "+id);
        recipeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/connecter")
    public User connexion(@RequestBody Login_user userconnect ){
         System.out.print("user ajouter avc succee");
       return connecter.connecter(userconnect);
    }

    
}
