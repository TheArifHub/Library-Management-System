package com.library_management_system.library_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library_management_system.library_management_system.dto.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	boolean existsByEmail(String email);

	boolean existsByPhone(String phone);

	Member findByEmail(String email);

	Member findByPassword(String password);
}
