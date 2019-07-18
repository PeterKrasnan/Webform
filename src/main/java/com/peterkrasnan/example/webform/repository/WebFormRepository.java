package com.peterkrasnan.example.webform.repository;

import com.peterkrasnan.example.webform.model.WebForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebFormRepository extends JpaRepository<WebForm, Long> {
}
