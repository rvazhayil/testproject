package com.xyzcorp.testproject.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    Page<User> findBySalaryBetween(float min, float max, Pageable pageable);
}
