package com.xoste.leon.dao;

import com.xoste.leon.pojo.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<参数一，参数二>
 *     参数一：当前需要映射的实体类
 *     参数二：当前需要映射的实体中的ID的类型
 * @author Leon
 */
public interface UsersRepository extends JpaRepository<Users, Integer> {

}
