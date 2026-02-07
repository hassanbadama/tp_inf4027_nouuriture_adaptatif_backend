package com.vente.systemeVentes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vente.systemeVentes.model.History;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(Long userId);
}
