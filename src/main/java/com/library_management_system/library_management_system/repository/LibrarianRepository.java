package com.library_management_system.library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library_management_system.library_management_system.dto.Librarian;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer>{

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	Librarian findByLibrarianId(String email);
}
