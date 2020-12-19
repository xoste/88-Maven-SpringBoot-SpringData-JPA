package com.xoste.leon.dao;

import com.xoste.leon.pojo.Users;
import org.springframework.data.repository.CrudRepository;

/**
 * CrudRepository
 * @author Leon
 */
public interface UsersRepositoryCrudRepository extends CrudRepository<Users, Integer> {
}
