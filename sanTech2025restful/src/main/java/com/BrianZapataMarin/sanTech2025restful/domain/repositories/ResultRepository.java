package com.BrianZapataMarin.sanTech2025restful.domain.repositories;

import com.BrianZapataMarin.sanTech2025restful.application.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
}