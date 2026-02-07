package com.vente.systemeVentes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vente.systemeVentes.model.User;

public interface UserRepository extends JpaRepository<User, Long> {}
