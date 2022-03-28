package com.xyzcorp.testproject.persistence;

import com.xyzcorp.testproject.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    Page<User> findBySalaryBetween(float min, float max, Pageable pageable);
}
