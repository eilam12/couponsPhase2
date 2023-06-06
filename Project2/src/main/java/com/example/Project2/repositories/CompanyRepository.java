package com.example.Project2.repositories;

import com.example.Project2.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByEmailAndPassword(String email, String password);
}
