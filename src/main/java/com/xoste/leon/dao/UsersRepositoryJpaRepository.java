package com.xoste.leon.dao;

import com.xoste.leon.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository
 * @author Leon
 */
public interface UsersRepositoryJpaRepository extends JpaRepository<Users, Integer> {
}
