package com.xoste.leon.dao;

import com.xoste.leon.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Leon
 */
public interface UsersRepositoryJpaSpecificationExecutor extends JpaRepository<Users, Integer>, JpaSpecificationExecutor<Users> {
}
