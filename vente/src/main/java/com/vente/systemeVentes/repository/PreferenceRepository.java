package com.vente.systemeVentes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vente.systemeVentes.model.Preference;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {
    List<Preference> findByUserId(Long userId);
}
