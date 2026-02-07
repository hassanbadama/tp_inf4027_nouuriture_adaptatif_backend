package com.vente.systemeVentes.service;

import org.springframework.stereotype.Service;

import com.vente.systemeVentes.controller.UserController;
import com.vente.systemeVentes.dto.Login_user;
import com.vente.systemeVentes.model.User;
import com.vente.systemeVentes.repository.UserRepository;

@Service
public class Connecte_user {
        private final UserRepository userRepository;
        

    public Connecte_user(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


    public User connecter(Login_user userconncter){
        for(User c :userRepository.findAll() ){
            if (userconncter.getNom().equals(c.getNom()) && userconncter.getPassword().equals(c.getPassword())) {
                System.out.println(userconncter.getNom()+" ***connecter*****  "+userconncter.getPassword());
                UserController.idUser = c.getId();
                return c;
            }

        }
        return null;
    }

}



