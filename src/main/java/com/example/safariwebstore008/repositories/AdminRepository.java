package com.example.safariwebstore008.repositories;

import com.example.safariwebstore008.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository  extends JpaRepository<Admin,Long> {
    Admin findAdminByEmail(String email);
}